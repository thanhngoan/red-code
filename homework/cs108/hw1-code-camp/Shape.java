import java.util.*;

/*
 Shape data for ShapeClient:
 "0 0  0 1  1 1  1 0"
 "10 10  10 11  11 11  11 10"
 "0.5 0.5  0.5 -10  1.5 0"
 "0.5 0.5  0.75 0.75  0.75 0.2"
*/

public class Shape {
	List<Point> points;
	Point circleCenter;
	double circleRadius;
	/**
	 * Creates a Shape from an input string.  The input string is an even number of double values
	 * in their string form separated by whitespace, e.g. "0.5 0.5 0.5 -10 1.5 0"
	 * @param loadForm
	 */
	public Shape(String loadForm)
	{
		points = readPoints(loadForm);
		//points.add(points.get(0)); // there is an assumed line segment at the end of the shape
		circleCenter = averageOfPoints(points);
		circleRadius = circleCenter.distance(closestPointTo(circleCenter, points));
	}

	/**
	 * Given two shapes A and B, A "crosses" B if A has a line which crosses the B circle -- one
	 * endpoint of the line is within the circle and the other endpoint is not. We will say that a
	 * point which is on the edge of the circle counts as being in the circle.
	 * WARNING: does not detect the case where the line crosses the circle, but both points are outside of it 
	 * @param otherShape
	 * @return
	 */
	public boolean crosses(Shape otherShape)
	{
		Iterator<Point> iter =  points.iterator();
		if (!iter.hasNext())
			return false;
		Point startPoint = null, endPoint = null;			
		do
		{
			startPoint = endPoint != null ? endPoint : iter.next();
			//if we are at the last point in the list, the first point is the terminus
			endPoint = iter.hasNext() ?
						iter.next() : points.get(0);
			//one needs to be inside but not both
            if (otherShape.pointInsideCircle(startPoint) ^ otherShape.pointInsideCircle(endPoint))
            	return true;
		} while (iter.hasNext());
		
		return false;
	}
	

	/**
	 * Given two shapes A and B, A "crosses" B if A has a line which crosses the B circle -- one
	 * endpoint of the line is within the circle and the other endpoint is not. We will say that a
	 * point which is on the edge of the circle counts as being in the circle.
	 * WARNING: does not detect the case where the line crosses the circle, but both points are outside of it 
	 * @param otherShape
	 * @return
	 */
	public int encircles(Shape otherShape)
	{
		if (this.pointInsideCircle(otherShape.circleCenter))
			return 2;
		else if (this.circleTouchesOtherCircle(otherShape.circleCenter, otherShape.circleRadius))
			return 1;
		else
			return 0;
	}

	/**
	 * is the given point inside this circle?
	 * @param p
	 * @return
	 */
	protected boolean pointInsideCircle(Point p)
	{
		return p.distance(circleCenter) <= circleRadius;
	}
	
	/**
	 * is the given point inside this circle?
	 * @param p
	 * @return
	 */
	protected boolean circleTouchesOtherCircle(Point otherCenter, double otherRadius)
	{
		return otherCenter.distance(this.circleCenter) < this.circleRadius + otherRadius;
	}

	/**
	 * reads in several doubles as points from an input string.
	 * will never throw an exception, but will instead read fewer points than perhaps expected
	 * @param input
	 * @return
	 */
	protected static List<Point> readPoints(String input)
	{
		ArrayList<Point> points = new ArrayList<Point>();
		Scanner scanner = new Scanner(input);
		while (scanner.hasNextDouble())
		{
			double x = scanner.nextDouble();
			double y = 0;
			if (scanner.hasNextDouble())
			{
				y = scanner.nextDouble();
				points.add(new Point(x,y));
			}
			else
				; // error (odd number of points)
		}
		return points;
	}
	
	/**
	 * returns an arithmatic average of several points
	 * @param points
	 * @return a point
	 */
	protected static Point averageOfPoints(List<Point> points)
	{
		double sumX = 0, sumY = 0;
		int totalPoints = 0;
		for (Point point : points)
		{
			++totalPoints;
			sumX += point.getX();
			sumY += point.getY();
		}
		if (totalPoints == 0)
			return null;
		else
			return new Point(sumX/totalPoints, sumY/totalPoints);
	}
	
	/**
	 * returns the closest point to the given point.
	 * @param target
	 * @param points
	 * @return
	 */
	protected static Point closestPointTo(Point target, List<Point> points)
	{
		Point closest = null;
		for (Point point : points)
		{
			if (closest == null)
				closest = point;
			else if (target.distance(point) < target.distance(closest))
				closest = point;
		}
		return closest;
	}
}

