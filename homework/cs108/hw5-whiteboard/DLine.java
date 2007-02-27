import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class DLine extends DShape {

	@Override
	void draw(Graphics g)
	{
		g.setColor(getModel().getColor());
		Point start = ((DLineModel)getModel()).startPoint(),
		      end   = ((DLineModel)getModel()).endPoint();
		g.drawLine(start.x, start.y, end.x, end.y);
	}

	public List<Point> computeKnobs()
	{
		LinkedList<Point> points = new LinkedList<Point>();
		DLineModel line = (DLineModel) getModel();
		points.add(new Point(line.getOrigin().x,                line.getOrigin().y));
		points.add(new Point(line.endPoint().x,                 line.endPoint().y));
		return points;
	}
	
	protected Point getOppositeKnob(int knobIndex)
	{
		return getKnobs().get((knobIndex + 1) % 2);
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
		Point theOrigin = (Point) anchorKnob.clone();
		Point theEndpoint = new Point
			(initialPositionOfSelectKnob.x + deltaSelected.x,
			 initialPositionOfSelectKnob.y + deltaSelected.y);
		DLineModel line = (DLineModel) getModel();
		line.setOrigin(theOrigin);
		line.setEndpoint(theEndpoint);
	}	

}
 