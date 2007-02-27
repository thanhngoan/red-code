import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
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
	
	public Point attemptKnobSelection(Point click)
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
				Point anchor = getOppositeKnob(index); // get opposite corner
				return anchor;
			}
			index++;
		}
		return null;
	}
	
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
