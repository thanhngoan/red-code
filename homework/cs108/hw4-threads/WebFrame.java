import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class WebFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3989149265720816453L;
	
	protected Semaphore canBeginDownload;
	protected Collection<Thread> workers;
	protected JTable table;
	protected DefaultTableModel tableModel;
	protected JButton singleFetchButton;
	protected JButton concurrentFetchButton;
	protected JButton stopButton;
	protected JTextField maxThreadsField;
	protected Thread launcher;
	public WebFrame()
	{

		setContentPane(Box.createVerticalBox());
		table = new JTable(
				tableModel = 
					new DefaultTableModel(
						new String[] { "url", "status"},
						0));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(600, 300));
		add(scrollpane);
		
		tableModel.setColumnIdentifiers( new String[]{ "url", "status"});
		add(singleFetchButton = new JButton("Single Thread Fetch"));
		add(concurrentFetchButton = new JButton("Concurrent Fetch"));
		add(maxThreadsField = new JTextField("4"));
		add(stopButton = new JButton("Stop"));

		stopButton.setEnabled(true);

		singleFetchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				enterRunningMode(1);
			}
		});
		
		concurrentFetchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				enterRunningMode(Integer.parseInt(maxThreadsField.getText()));
			}
		});

		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (launcher != null)
				{
					launcher.interrupt();
					enterIdleMode();
				}
			}
		});
	}
	
	protected void enterRunningMode(int maxDownloaders)
	{
		canBeginDownload = new Semaphore(maxDownloaders);
		concurrentFetchButton.setEnabled(false);
		singleFetchButton.setEnabled(false);
		stopButton.setEnabled(true);
		workers = new LinkedList<Thread>();
		final WebFrame frame = this;
		launcher = new Thread() {
			
			public void handleInterrupt()
			{
				for (Thread thread : workers)
					thread.interrupt();
			}
			
			public void run() {
				for (int row=0; row < tableModel.getRowCount(); row++)
				{
					try {
						canBeginDownload.acquire();
					} catch (InterruptedException e) {
						handleInterrupt();
						break;
					}
					String url = tableModel.getValueAt(row, 0).toString();
					WebWorker worker = new WebWorker(frame, url, row);
					workers.add(worker);
					worker.start();
					canBeginDownload.release();
				}

				for (Thread thread : workers)
					try {
						thread.join();
					} catch (InterruptedException e) {
						handleInterrupt();
					}
				enterIdleMode();
			}
		};
		launcher.start();
		/*
		for (Thread thread : workers)
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 */
	}
	
	public void setStatus(int rowNum, String status)
	{
		tableModel.setValueAt(status, rowNum, 1);
	}

	protected void enterIdleMode()
	{
		concurrentFetchButton.setEnabled(true);
		singleFetchButton.setEnabled(true);
		stopButton.setEnabled(false);
	}
	
	public void loadLinksFile(String filename) throws IOException
	{
		InputStreamReader fin = new InputStreamReader(new FileInputStream(filename));
		BufferedReader buff = new BufferedReader(fin);
		String link;
		synchronized (tableModel)
		{
			int rowIndex = tableModel.getRowCount() - 1;
			while ((link =  buff.readLine()) != null)
			{
				Vector<String> rowData = new Vector<String>();
				rowData.add(link);
				tableModel.addRow(rowData);
				rowIndex++;
			}
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		WebFrame frame = new WebFrame();

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.loadLinksFile("links.txt");
	}
	
}
