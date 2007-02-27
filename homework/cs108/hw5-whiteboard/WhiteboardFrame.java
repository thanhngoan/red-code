import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;


public class WhiteboardFrame extends JFrame {
	public static String[] DEFAULT_FONT_NAMES = new String[]{ "Dialog" };
	public static String DEFAULT_TEXT = "Lisp is for lovers";
	public static final String[] SHAPE_TABLE_COLUMN_NAMES = new String[] {"Shape", "x", "y", "Width", "Height" };
	public static final int DEFAULT_PORT = 4444;
	JButton rectButton;
	JButton ovalButton;
	JButton lineButton;
	JButton textButton;

	JButton toFrontButton;
	JButton toBackButton;
	JButton removeButton;
	JButton setColorButton;

	JButton startServerButton;
	JButton startClientButton;
	JLabel  networkingStatus;
	
	JButton fileSaveButton;
	JButton fileLoadButton;
	JButton imageSaveButton;

	JTextField textSetField;
	JComboBox textFontField; 
	
	WhiteboardCanvas canvas;
	WhiteboardClient networkClient;
	WhiteboardServer networkServer;
	
	public class ShapeListTableModel extends AbstractTableModel
	{
		public static final int X_COL = 1;
		public static final int Y_COL = 2;
		public static final int WIDTH_COL = 3;
		public static final int HEIGHT_COL = 4;
		
		
		public ShapeListTableModel() {
			super();
			final ShapeListTableModel stmodel = this;
			
			//listen for xz 
			canvas.addShapeListListener(new WhiteboardCanvas.ShapeListListener() {
				DShapeModel.ModelListener listener;
				public void shapeAdded(DShape shape) {
					//notify the table listeners of a change to the model
					fireTableChanged(new TableModelEvent(stmodel));
					
					//listen for changes on the model
					shape.getModel().addChangeListener(listener = new DShapeModel.ModelListener() {
						public void modelChanged(DShapeModel model) {
							int index = canvas.getIndexOfShapeModel(model);
							fireTableRowsUpdated(index, index+1);
						}
					});
					
				}
				
				public void shapeRemoved(DShape shape) {
					shape.getModel().removeChangeListener(listener);
					listener = null;
					//notify the table listeners of a change to the model
					fireTableChanged(new TableModelEvent(stmodel));
				}
			});
		}

		public int getColumnCount() {
			return HEIGHT_COL + 1;
		}

		public int getRowCount() {
			return canvas.getShapes().size();
		}

		public Object getValueAt(int row, int column)
		{
			DShape shape = canvas.getShapes().get(row);
			switch (column)
			{
			case X_COL:      return shape.getModel().getBounds().x;
			case Y_COL:      return shape.getModel().getBounds().y;
			case WIDTH_COL:  return shape.getModel().getBounds().width;
			case HEIGHT_COL: return shape.getModel().getBounds().height;
			}
			return null;
		}
	};
	
	public WhiteboardFrame()
	{
		super("Whiteboard");
		setLayout(new BorderLayout());
		//BoxLayout buttonBox = new BoxLayout(this, BoxLayout.X_AXIS);
		Box box = new Box(BoxLayout.X_AXIS);
		Box leftSide = new Box(BoxLayout.Y_AXIS);
		Box rightSide = new Box(BoxLayout.Y_AXIS);

		// set up shape add buttons
		Box shapeAdder = new Box(BoxLayout.X_AXIS);
		shapeAdder.add(rectButton = new JButton("Rectangle"));
		shapeAdder.add(ovalButton = new JButton("Oval"));
		shapeAdder.add(lineButton = new JButton("Line"));
		shapeAdder.add(textButton = new JButton("Text"));
		shapeAdder.setBorder(BorderFactory.createTitledBorder("Add Shape"));

		leftSide.add(shapeAdder);

		//set up shape position buttons
		Box shapePositionContainer = new Box(BoxLayout.X_AXIS);
		shapePositionContainer.add(toFrontButton = new JButton("Move to Front"));
		shapePositionContainer.add(toBackButton = new JButton("Move to Back"));
		shapePositionContainer.add(removeButton = new JButton("Remove Shape"));
		shapePositionContainer.setBorder(BorderFactory.createTitledBorder("Shape Position"));

		leftSide.add(shapePositionContainer);
		
		//set up text property fields
		initializeFontComboBox();
		Box textControls = new Box(BoxLayout.X_AXIS);
		textControls.add(textSetField = new JTextField("Chao Bunga!"));
		textControls.add(textFontField);
		textControls.setBorder(BorderFactory.createTitledBorder("Text Properties"));
		textSetField.setMaximumSize(new Dimension(256, 20));
		textFontField.setMaximumSize(new Dimension(256, 20));

		leftSide.add(textControls);
		
		// set up color selection
		Box colorSelectionArea = new Box(BoxLayout.X_AXIS);
		setColorButton = new JButton("Set Color");
		colorSelectionArea.add(setColorButton);
		colorSelectionArea.setBorder(BorderFactory.createTitledBorder("Set Color"));
		
		leftSide.add(colorSelectionArea);
		
		// networking
		Box networkingArea = new Box(BoxLayout.X_AXIS);
		startServerButton = new JButton("Start Server");
		startClientButton = new JButton("Start Client");
		networkingStatus = new JLabel("Not connected.");
		networkingArea.add(startServerButton);
		networkingArea.add(startClientButton);
		networkingArea.add(networkingStatus);
		networkingArea.setBorder(BorderFactory.createTitledBorder("Networking"));
		leftSide.add(networkingArea);
		

		// file saving
		Box savingArea = new Box(BoxLayout.X_AXIS);
		fileSaveButton = new JButton("Save to File");
		fileLoadButton = new JButton("Load from File");
		imageSaveButton = new JButton("Save Image");
		savingArea.add(fileSaveButton);
		savingArea.add(fileLoadButton);
		savingArea.add(imageSaveButton);
		savingArea.setBorder(BorderFactory.createTitledBorder("Save Whiteboard"));
		leftSide.add(savingArea);
		
		//canvas
		canvas = new WhiteboardCanvas();
		rightSide.add(canvas);
		
		//create table
		JTable table = new JTable(0, SHAPE_TABLE_COLUMN_NAMES.length);
		for (int i=0; i < SHAPE_TABLE_COLUMN_NAMES.length; i++)
			table.getColumnModel().getColumn(i).setHeaderValue(SHAPE_TABLE_COLUMN_NAMES[i]);
		
		//table.set
		table.setModel(createShapesTableModel());
		JScrollPane scrollpane = new JScrollPane(table);
		leftSide.add(scrollpane);
		
		box.add(leftSide);
		box.add(rightSide);
		
		//general frame configuration
		JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSide, rightSide);
		this.add(mainSplit);
		//add(box);
		this.setSize(600, 400);
		
		for (Component comp : leftSide.getComponents())
		{
		    ((JComponent)comp).setAlignmentX(Box.LEFT_ALIGNMENT);
		}
		initializeControlListeners();
	}
	
	public void setNetworkStatusText(String str) {
		networkingStatus.setText(str);
	}

	public boolean inServerMode() {
		return networkServer != null;
	}
	
	public boolean isInteractive() {
		return !inClientMode();
	}
	
	public boolean inClientMode() {
		return networkClient != null;
	}
	
	protected ShapeListTableModel createShapesTableModel() {
		ShapeListTableModel model = new ShapeListTableModel();
		return model;
	}
	
	protected void initializeFontComboBox()
	{
		textFontField = new JComboBox();
		String[] fontFamilies = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for (String family : fontFamilies)
			textFontField.addItem(family);
		
		for (String fontName : DEFAULT_FONT_NAMES)
			textFontField.setSelectedItem(fontName);
	}
	
	/**
	 * Begin listening to events on the buttons.
	 */
	protected void initializeControlListeners()
	{
		final WhiteboardFrame frame = this;
		rectButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				initializeAndAddModelToCanvas(new DRectModel());
			}
		}); 
		ovalButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DOvalModel model = new DOvalModel();
				initializeAndAddModelToCanvas(model);
			}
		}); 
		lineButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DLineModel model = new DLineModel();
				model.setOrigin(new Point(100, 100));
				model.setEndpoint(new Point(150, 150));
				canvas.addShape(model);
			}
		}); 
		textButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final DTextModel model = new DTextModel();
				model.setText(DEFAULT_TEXT);
				model.setFontFamily(DEFAULT_FONT_NAMES[0]);
				initializeAndAddModelToCanvas(model);
				model.addChangeListener(new DShapeModel.ModelListener() {

					public void modelChanged(DShapeModel changedModel) {
						updateInspectorInformation();
					}
					
				});
			}
		});
		
		textSetField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DShape shape = canvas.getSelectedShape(); 
				if (shape != null)
				{
					if (shape.getModel() instanceof DTextModel)
					{
						((DTextModel)shape.getModel()).setText(textSetField.getText());
					}
				}
			}
		});
		
		textFontField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DShape shape = canvas.getSelectedShape(); 
				if (shape != null)
				{
					if (shape.getModel() instanceof DTextModel)
					{
						((DTextModel)shape.getModel()).setFontFamily(textFontField.getSelectedItem().toString());
					}
				}
			}
		});
		
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				if (canvas.getSelectedShape() != null)
					canvas.removeShape(canvas.getSelectedShape());
			}
		});
		
		toFrontButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				if (canvas.getSelectedShape() != null)
					canvas.moveShapeToFront(canvas.getSelectedShape());
			}
		});
		
		toBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				if (canvas.getSelectedShape() != null)
					canvas.moveShapeToBack(canvas.getSelectedShape());
			}
		});
		
		setColorButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				// initial color is the color of the selected shape or gray if there is no selected shape
				Color initialColor = canvas.getSelectedShape() == null ? Color.GRAY : canvas.getSelectedShape().getModel().getColor();
				final JColorChooser chooser = new JColorChooser(initialColor);
				
				// listen for the OK in a color selection dialog,
				// setting the color of the given model
				JColorChooser.createDialog(
					frame, "Choose Draw Color", true, chooser, 
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (canvas.getSelectedShape() != null)
								canvas.getSelectedShape().getModel().setColor(chooser.getColor());
						}
					}, 
					null).setVisible(true);
			}
			
		});
		
		canvas.addSelectionChangeListener(new WhiteboardCanvas.SelectionChangeListener() {
			public void selectionChanged(DShape newShape, DShape oldShape) {
				updateInspectorInformation();
			}
		});
		
		startServerButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int port = DEFAULT_PORT;
				boolean performConnect = true;
				// loop until the user enters a valid port
				while (true) {
					String portString = JOptionPane.showInputDialog("Server Port:", port);
					if (portString == null)
					{
						performConnect = false;
						break;
					}
					try {
						int portAttempt = Integer.parseInt(portString);
						port = portAttempt;
						break;
					} catch (NumberFormatException e) {}
				}
				try {
					if (performConnect)
					{
						networkServer = new WhiteboardServer(canvas, port);
						updateControlsForServerMode();
						setNetworkStatusText("Serving...");
					}
				} catch (IOException e) {
					setNetworkStatusText("Error starting server.");
				}
			}
		});
		
		startClientButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int port = DEFAULT_PORT;
				String host = "localhost";
				boolean performConnect = true;
				while (true)
				{
					String urlString = JOptionPane.showInputDialog("Enter a URL to connect to (host:port syntax).", "localhost:" + port);
					if (urlString == null)
					{
						performConnect = false;
						break;
					}
					URL url;
					try {
						url = new URL("http://" + urlString);
						if (url.getPort() > 0) port = url.getPort();
						host = url.getHost();
						break;
					} catch (MalformedURLException e) {} //loop
				}
			    
				try {
					if (performConnect)
					{
						networkClient = new WhiteboardClient(canvas, host, port);
						updateControlsForClientMode();
						setNetworkStatusText("Connected.");
					}
				} catch (UnknownHostException e) {
					setNetworkStatusText("Unknown host.");
				} catch (IOException e) {
					setNetworkStatusText("Error connecting.");
				}
			}
		});
		
		fileSaveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
		        JFileChooser chooser = createFileChooser();
		        int status = chooser.showOpenDialog(frame);
		        if (status == JFileChooser.APPROVE_OPTION) {
		        	File file = chooser.getSelectedFile();
					canvas.saveSemanticFile(file);
		        }
			}
			
		});
		
		fileLoadButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
		        JFileChooser chooser = createFileChooser();
		        int status = chooser.showOpenDialog(frame);
		        if (status == JFileChooser.APPROVE_OPTION) {
		        	File file = chooser.getSelectedFile();
					canvas.loadSemanticFile(file);
		        }
			}
			
		});
		
		imageSaveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
		        JFileChooser chooser = createFileChooser();
		        int status = chooser.showOpenDialog(frame);
		        if (status == JFileChooser.APPROVE_OPTION) {
		        	File file = chooser.getSelectedFile();
					canvas.saveImageFile(file);
		        }
			}
			
		});	
	}
	
	//thanks nick
    protected JFileChooser createFileChooser() {
    	JFileChooser chooser = new JFileChooser();
    	try {
    	    // The "." stuff attempts to open in the "current"
    	    // directory.
    	    File dir = new File(new File(".").getCanonicalPath());
    	    chooser.setCurrentDirectory(dir);
    	}
    	catch (Exception ex) {
    	    ex.printStackTrace();
    	}
    	return chooser;
    }

	
	protected void updateControlsForClientMode() {
		startClientButton.setEnabled(false);
		startServerButton.setEnabled(false);
		fileLoadButton.setEnabled(false);
		canvas.setInteractive(false);
		lineButton.setEnabled(false);
		textButton.setEnabled(false);
		ovalButton.setEnabled(false);
		rectButton.setEnabled(false);
		removeButton.setEnabled(false);
		toFrontButton.setEnabled(false);
		toBackButton.setEnabled(false);
		fileLoadButton.setEnabled(false);
		textFontField.setEnabled(false);
		textSetField.setEnabled(false);
		setColorButton.setEnabled(false);
	}
	
	protected void updateControlsForServerMode() {
		startClientButton.setEnabled(false);
		startServerButton.setEnabled(false);
	}
	
	protected void updateInspectorInformation() {
		DShape shape = canvas.getSelectedShape();
		if (shape != null && shape instanceof DText)
		{
			DTextModel model = ((DTextModel)shape.getModel());
			textSetField.setText(model.getText());
			textFontField.setSelectedItem(model.getFontFamily());
			if (isInteractive())
			{
				textSetField.setEnabled(true);
				textFontField.setEnabled(true);
			}
		}
		else
		{
			textSetField.setEnabled(false);
			textFontField.setEnabled(false);
		}
	}
	
	protected void initializeAndAddModelToCanvas(DShapeModel model) {
		model.setBounds(canvas.generateRandomBounds());
		canvas.addShape(model);
	}

	/**
	 * generated serial version id for some strange registration reason
	 */
	private static final long serialVersionUID = 3242310365874641826L;

}
