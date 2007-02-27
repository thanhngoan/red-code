import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public abstract class DShape {
	protected DShapeModel model;
	abstract void draw(Graphics g);
	public static int KNOB_SIZE = 9; // pixels
	
	/**
	 * @return the model
	 */
	public DShapeModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(DShapeModel model) {
		this.model = model;
	}
	
	public List<Point> getKnobs() {
		// TODO caching
		return computeKnobs();
	}
	
	/**
	 * 
	 * @return list of knobs
	 */
	protected List<Point> computeKnobs()
	{
		LinkedList<Point> points = new LinkedList<Point>();
		Rectangle bounds = getModel().getBounds();
		points.add(new Point(bounds.x + 1,                bounds.y + 1));
		points.add(new Point(bounds.x + 1,                bounds.y + bounds.height - 1));
		points.add(new Point(bounds.x + bounds.width - 1, bounds.y + bounds.height - 1));
		points.add(new Point(bounds.x + bounds.width - 1, bounds.y + 1));
		return points;
	}
	
	/**
	 * Resizes the shape using knobs.  Requires the supervisor to keep track of (1) the anchor knob
	 * (2) the initial position of the selected knob, and (3) the change in the position of the selected knob
	 * since dragging started.
	 * @param anchorKnob 
	 * @param initialPositionOfSelectKnob
	 * @param deltaSelected
	 */
	public void resizeByKnobs(Point anchorKnob, Point initialPositionOfSelectKnob, Point deltaSelected)
	{
		//simpleBounds does not correct for negative width/height
		Rectangle simpleBounds = new Rectangle(); //(Rectangle) getModel().getBounds().clone();
		simpleBounds.width = (anchorKnob.x - initialPositionOfSelectKnob.x) + deltaSelected.x;
		simpleBounds.height = (anchorKnob.y - initialPositionOfSelectKnob.y) + deltaSelected.y;
		
		Point currentKnobPos = new Point
			(initialPositionOfSelectKnob.x + deltaSelected.x,
			 initialPositionOfSelectKnob.y + deltaSelected.y);
		
		getModel().setBounds(createRectangleContainingPoints(anchorKnob, currentKnobPos));
	}
	
	/**
	 * Returns a rectangle that contains both the given points.
	 * @param one
	 * @param two
	 * @return
	 */
	public static Rectangle createRectangleContainingPoints(Point one, Point two)
	{
		Rectangle rect = new Rectangle();
		if (one.x < two.x)
			rect.x = one.x;
		else
			rect.x = two.x;

		if (one.y < two.y)
			rect.y = one.y;
		else
			rect.y = two.y;

		rect.width = Math.abs(two.x - one.x);
		rect.height = Math.abs(two.y - one.y);
		return rect;
	}
	
	
	/**
	 * Attempts to select an appropriate knob within this shape given the point of a click.
	 * Returns the anchor knob and modifies outSelectedKnob to be the selected knob.
	 * @param click point of click
	 * @param outSelectedKnob output point for knob selected by mouse
	 * @return
	 */
	public Point attemptKnobSelection(Point click, Point outSelectedKnob)
	{
		int index = 0;
		List<Point> knobs = getKnobs();
		// loop through the knob points and return true and set the selected/anchor knob
		// when one is found under the mouse
		for (Point knobPoint : knobs)
		{
			Rectangle knobRect = computeKnobRect(knobPoint);
			if (knobRect.contains(click))
			{
				//return appropriately
				outSelectedKnob.x = knobPoint.x;
				outSelectedKnob.y = knobPoint.y;
				Point anchor = getOppositeKnob(index); // get opposite corner
				return anchor;
			}
			index++;
		}
		return null;
	}
	
	/**
	 * Returns the knob opposite the knob at the given index
	 * @param knobIndex
	 * @return
	 */
	protected Point getOppositeKnob(int knobIndex)
	{
		return getKnobs().get((knobIndex + 2) % 4);
	}
	
	public void drawKnobs(Graphics g)
	{
		g.setColor(Color.BLACK);
		Collection<Point> points = computeKnobs();
		for (Point knobPoint : points)
			drawKnobAtPoint(g, knobPoint);
	}
	
	protected static Rectangle computeKnobRect(Point knobPoint)
	{
		return new Rectangle
		(knobPoint.x - (KNOB_SIZE / 2), 
		 knobPoint.y - (KNOB_SIZE / 2),
		 KNOB_SIZE,
		 KNOB_SIZE);
	}
	
	protected void drawKnobAtPoint(Graphics g, Point point)
	{
		Rectangle rect = computeKnobRect(point);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
	}
	
	public void adjustForDrag(Point anchor, Point initialDragPoint, Point changeInDragPoint ) {
		
	}
}
