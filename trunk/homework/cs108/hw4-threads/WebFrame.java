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
	
	protected Semaphore canBeginDownload;    // semaphore for starting next download thread
	protected Collection<Thread> workers;    // collection of the download threads @deprecated
	protected JTextField maxThreadsField;    // max # of threads
	protected Thread launcher;               // launcher thread.  interrupt this to cancel everything

	protected int downloadsRunningCount;
	protected int downloadsCompletedCount; 
	
	// interface components
	protected JTable table;                  // table view element
	protected DefaultTableModel tableModel;  // table model
	protected JButton singleFetchButton;     // click to fetch 1 at a time 
	protected JButton concurrentFetchButton; // click to fetch many
	protected JButton stopButton;            // stop downloads
	protected JLabel runningLabel;
	protected JLabel completedLabel;
	protected JLabel elapsedLabel;
	protected JProgressBar progressBar;
	
	public void repaint() {
		super.repaint();
	}
	
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
		add(runningLabel = new JLabel("Running:0"));
		add(completedLabel = new JLabel("Completed:0"));
		add(elapsedLabel = new JLabel("Elapsed:"));
		add(progressBar = new JProgressBar());
		add(stopButton = new JButton("Stop"));

		stopButton.setEnabled(true);
		
		initListeners();
	}

	protected void initListeners() {
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
	
	/**
	 * Called when a worker begins a download job.
	 * @param worker
	 */
	public void registerDownloaderStarted(WebWorker worker)
	{
		setRunningCount(downloadsRunningCount+1);
	}
	
	/**
	 * Called when a worker stops downloading a segment
	 * @param worker
	 */
	public void registerDownloaderCeased(WebWorker worker, boolean successful)
	{
		setRunningCount(downloadsRunningCount-1);
		if (successful)
			setCompletedCount(downloadsCompletedCount+1);
	}

	/**
	 * Sets the running number of downloads
	 * @param c
	 */
	protected void setRunningCount(int c)
	{
		downloadsRunningCount = c;
		runningLabel.setText("Running:" + downloadsRunningCount);
	}

	/**
	 * Sets the number of completed downloads.
	 * @param c
	 */
	protected void setCompletedCount(int c)
	{
		downloadsCompletedCount = c;
		completedLabel.setText("Completed:" + downloadsCompletedCount);
		progressBar.setValue((int) (100 * downloadsCompletedCount / getDownloadCount()));
	}
	
	/**
	 * 
	 * @return The number of things we are attempting to download.
	 */
	protected int getDownloadCount() { return tableModel.getRowCount(); }
	
	/**
	 * There are two modes of our program: downloading ("running") and idle.
	 * 
	 * This function transitions to the running mode.  
	 * @param maxDownloaders max number of downloads to run simultaneously
	 */
	protected void enterRunningMode(int maxDownloaders)
	{
		setCompletedCount(0);
		setRunningCount(0);
		canBeginDownload = new Semaphore(maxDownloaders);
		concurrentFetchButton.setEnabled(false);
		singleFetchButton.setEnabled(false);
		stopButton.setEnabled(true);
		workers = new LinkedList<Thread>();
		final WebFrame frame = this;
		launcher = new Thread() {
			
			public void handleInterrupt()
			{
				// interrupt all the workers
				for (Thread thread : workers)
					thread.interrupt();
				// join all the workers
				for (Thread thread : workers)
					try {
						thread.join();
					} catch (InterruptedException e) {
						handleInterrupt();
					}
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
					registerDownloaderStarted(worker);
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
	
	/**
	 * Loads a file filled with links and adds them to the download queue.
	 * @param filename
	 * @throws IOException
	 */
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
		frame.loadLinksFile("links2.txt");
	}
	
}
