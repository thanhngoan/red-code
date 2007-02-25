import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class WhiteboardFrame extends JFrame {

	public class WhiteboardCanvas extends JPanel {
		
		public WhiteboardCanvas()
		{
			super();
		}
		/**
		 * 
		 */
		private static final long serialVersionUID = -6616373622612163736L;
	}
	
	public WhiteboardFrame()
	{
		super("Whiteboard");
		setPreferredSize(new Dimension(600, 400));
		setLayout(new BorderLayout());
		//BoxLayout buttonBox = new BoxLayout(this, BoxLayout.X_AXIS);
		Box box = new Box(BoxLayout.X_AXIS);
		box.add(new JButton("dollar"));
		add(box);
		
		//this.setLayout(new BorderLayo
	}

	/**
	 * generated serial version id for some strange registration reason
	 */
	private static final long serialVersionUID = 3242310365874641826L;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WhiteboardFrame frame = new WhiteboardFrame();
		frame.setVisible(true);
	}

}
