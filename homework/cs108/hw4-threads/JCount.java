// JCount.java

/*
 Basic GUI/Threading exercise.
*/

import javax.swing.*;

import java.awt.event.*;

public class JCount extends JPanel {
	JTextField textfield;
	JLabel label;
	JButton startButton;
	JButton stopButton;
	WorkerCounter counter;
	int count;
	
	public class WorkerCounter extends Thread {
		public void run() {
			int i = 0;
			while (count <= Integer.parseInt(textfield.getText()))
			{
				++count;
				++i;
				if ((i % 10000) == 0  && 
					!isInterrupted())
				{
					label.setText(Integer.toString(count));
					yield();
				}
				else if (isInterrupted())
					break;
			}
		}
	}
	
	public JCount() {
		// Set the JCount to use Box layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(textfield = new JTextField("100000000"));
		add(label = new JLabel("0"));
		add(startButton = new JButton("Start"));
		add(stopButton = new JButton("Stop"));
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (counter != null)
					counter.interrupt();
				
				counter = new WorkerCounter();
				
				counter.start();
			}
			
		});
		
		
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (counter != null)
					counter.interrupt();
			}
		});
	}
	
	static public void main(String[] args)  {
		// Creates a frame with 4 JCounts in it.
		// (provided)
		JFrame frame = new JFrame("The Count");
		frame.setContentPane(Box.createVerticalBox());
		
		frame.add(new JCount());
		frame.add(new JCount());
		frame.add(new JCount());
		frame.add(new JCount());

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
