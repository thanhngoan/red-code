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
		Rectangle bounds = getModel().getBounds();
		points.add(new Point(bounds.x + 1,                bounds.y + 1));
		points.add(new Point(bounds.x + bounds.width - 1, bounds.y + bounds.height - 1));
		return points;
	}
	
	protected Point getOppositeKnob(int knobIndex)
	{
		return getKnobs().get((knobIndex + 1) % 2);
	}

}
 