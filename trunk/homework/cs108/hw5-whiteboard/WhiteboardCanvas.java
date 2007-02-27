import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class WhiteboardCanvas extends JPanel {
	
	protected LinkedList<DShape> shapes;
	protected DShape selectedShape;
	protected DragData drag;
	protected int shapeIdCounter;
	

	// listeners
	protected List<SelectionChangeListener> selectionChangeListeners;
	protected List<ShapeListListener> shapeListListeners;
	protected List<ZOrderChangeListener> zOrderChangedListeners;
	
	public static interface SelectionChangeListener {
		public void selectionChanged(DShape newShape, DShape oldShape);
	}
	
	public static interface ZOrderChangeListener {
		public void shapedMovedToFront(DShape shape);
		public void shapedMovedToBack(DShape shape);
	}
	
	public static interface ShapeListListener {
		public void shapeAdded(DShape shape);
		public void shapeRemoved(DShape shape);
	}
	
	public void addSelectionChangeListener(SelectionChangeListener listener) {
		selectionChangeListeners.add(listener);
	}
	public boolean removeSelectionChangeListener(SelectionChangeListener listener) {
		return selectionChangeListeners.remove(listener);
	}
	
	public void addShapeListListener(ShapeListListener listener) {
		shapeListListeners.add(listener);
	}
	public boolean removeShapeListListener(ShapeListListener listener) {
		return shapeListListeners.remove(listener);
	}
	
	public void addZOrderListener(ZOrderChangeListener listener) {
		zOrderChangedListeners.add(listener);
	}
	public boolean removeZOrderListener(ZOrderChangeListener listener) {
		return zOrderChangedListeners.remove(listener);
	}
	
	public class DragData {
		// generic drag data:
		/**
		 * Initial position when starting to drag.
		 */
		Point start;

		// drag data for resizing
		Point resizeAnchor;
		
		// drag data for translating
		Point initialShapeOrigin;
		
		public boolean isDragging()      { return start != null; }
		public boolean isResizingShape() { return isDragging() && resizeAnchor != null; }
		public boolean isMovingShape()   { return isDragging() && resizeAnchor == null; }
	}
	
	public WhiteboardCanvas()
	{
		super();
		shapeIdCounter = 1;
		selectionChangeListeners = new LinkedList<SelectionChangeListener>();
		shapeListListeners = new LinkedList<ShapeListListener>();
		zOrderChangedListeners = new LinkedList<ZOrderChangeListener>();
		setPreferredSize(new Dimension(600, 400));
		setBackground(Color.WHITE);
		shapes = new LinkedList<DShape>();
		initializeMouseListeners();
	}
	
	protected void stopDragging() { drag = null; }

	protected void initializeMouseListeners() {
		this.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				setSelectedShape(findShapeAtPoint(e.getPoint()));
			}

			// when the mouse is pressed,
			// 1) start to resize if the selected shape has a knob in the current click position
			// else
			// 2) start to move if the click is within the currently selected shape
			public void mousePressed(MouseEvent e) {
				/*
				if (getSelectedShape() == null)
					setSelectedShape(findShapeAtPoint(e.getPoint()));
				*/
				if (getSelectedShape() != null)
				{
					drag = new DragData();
					drag.start = (Point) e.getPoint().clone();
					//  start to resize if the selected shape has a knob in the current click position
					drag.resizeAnchor = getSelectedShape().attemptKnobSelection(drag.start);
					if (drag.resizeAnchor != null)
					{
						//we are resizing
					}
					//else check if the click was within the current shape
					else if (getSelectedShape().getModel().getBounds().contains(e.getPoint())) {
						drag.initialShapeOrigin = getSelectedShape().getModel().getBounds().getLocation();
					}
					else
					{
						stopDragging();
						setSelectedShape(null);
					}
				}
			}
			
			public void mouseReleased(MouseEvent e) {
				stopDragging();
			}
			
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent e) {
				if (drag != null && drag.isDragging())
				{
					Point changeSinceDragStart = new Point
					   (e.getPoint().x - drag.start.x, e.getPoint().y -  drag.start.y);
					if (drag.isResizingShape()) {
						
					} 
					else if (drag.isMovingShape())
					{
						Point newLocation = new Point
							(drag.initialShapeOrigin.x + changeSinceDragStart.x,
							 drag.initialShapeOrigin.y + changeSinceDragStart.y);
						getSelectedShape().getModel().setOrigin(newLocation);
					}
				}
			}

			public void mouseMoved(MouseEvent e) { }
		});
	}
	
	protected DShape findShapeAtPoint(Point point)
	{
		DShape match = null;
		for (DShape shape : shapes)
		{
			if (shape.getModel().getBounds().contains(point))
				match = shape;
		}
		return match;
	}
	
	public void addShape(DShapeModel shapeModel) {
		DShape shape = makeShapeFromModel(shapeModel);
		
		if (shapeModel.getId() == DShapeModel.UNINITIALIZED_ID)
			shapeModel.setId(shapeIdCounter++);
		
		shapes.add(shape);
		//listen for changes to the model
		shapeModel.addChangeListener(new DShapeModel.ModelListener() {
			public void modelChanged(DShapeModel model) {
				//whenever a model changes, repaint the canvas
				repaint();
			}
		});
		setSelectedShape(shape);
		//repaint ourselves with the new shape
		repaint();
		
		for (ShapeListListener listener : shapeListListeners)
			listener.shapeAdded(shape);
	}
	
	public void removeShape(DShape shape)
	{
		if (shapes.remove(shape))
		{
			repaint();
			for (ShapeListListener listener : shapeListListeners)
				listener.shapeRemoved(shape);
		}
	}
	
	public void moveShapeToBack(DShape shape)
	{
		if (shapes.remove(shape))
		{
			shapes.addFirst(shape);
			repaint();
			for (ZOrderChangeListener listener : zOrderChangedListeners)
				listener.shapedMovedToBack(shape);
		}
	}
	
	public void moveShapeToFront(DShape shape)
	{
		if (shapes.remove(shape))
		{
			shapes.addLast(shape);
			repaint();
			for (ZOrderChangeListener listener : zOrderChangedListeners)
				listener.shapedMovedToFront(shape);
		}
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		for (DShape shape : shapes)
		{
			shape.draw(g);
			if (shape == selectedShape)
			{
				shape.drawKnobs(g);
			}
		}
	}
	
	public Rectangle generateRandomBounds()
	{
		Rectangle rect = new Rectangle();
		// completely random origin
		rect.x = (int) Math.floor(Math.random() * getWidth());
		rect.y = (int) Math.floor(Math.random() * getHeight());
		
		//restrict the width and height based on the origin
		int possibleWidth = getWidth() -  rect.x;
		int possibleHeight = getWidth() -  rect.x;
		rect.width = (int) Math.floor(Math.random() *  possibleWidth);
		rect.height = (int) Math.floor(Math.random() *  possibleHeight);
		return rect;
		
	}
	
	public static DShape makeShapeFromModel(DShapeModel model)
	{
		DShape shape = null;
		if (model instanceof DTextModel)
			shape = new DText();
		else if (model instanceof DOvalModel)
			shape = new DOval();
		else if (model instanceof DRectModel)
			shape = new DRect();
		else if (model instanceof DLineModel)
			shape = new DLine();
		shape.setModel(model);
		return shape;
	}
	
	public int getIndexOfShapeModel(DShapeModel model) {

		int index = 0;
		for (DShape shape : getShapes())
		{
			if (shape.getModel() == model)
				break;
			index++;
		}
		return index;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6616373622612163736L;

	
	
	/**
	 * @return the selectedShape
	 */
	public DShape getSelectedShape() {
		return selectedShape;
	}

	/**
	 * @param selectedShape the selectedShape to set
	 */
	public void setSelectedShape(DShape selectedShape) {
		DShape oldShape = this.selectedShape;
		if (selectedShape != this.selectedShape)
		{
			this.selectedShape = selectedShape;
			repaint();
			
			for (SelectionChangeListener listener : selectionChangeListeners)
				listener.selectionChanged(this.selectedShape, oldShape);
		}
	}
	/**
	 * @return the shapes
	 */
	public List<DShape> getShapes() {
		return shapes;
	}
	public DShape findShapeById(int id) {
		for (DShape shape : shapes)
			if (shape.getModel().getId() == id)
				return shape;
		return null;
	}
}
