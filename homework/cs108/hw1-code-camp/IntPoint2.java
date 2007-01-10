// CS108 HW1 -- provided simple immutable Point 2-d point class
// that encapsulates a double x/y pair.
// Could also use the java.awt.Point2D class, but its
// interface is more messy.

public class IntPoint2 {
	private int x;
	private int y;
	
	/**
	 * Constructs a new point.
	 */
	public IntPoint2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Copy constructor -- copies the given point.
	 * @param other
	 */
	public IntPoint2(IntPoint2 other) {
		this.x = other.x;
		this.y = other.y;
	}

	/**
	 * Gets the x value.
	 * @return x
	 */
	public int getX() {
		return x;
	}


	/**
	 * Gets the y value.
	 * @return y
	 */
	public int getY() {
		return y;
	}

	
	/**
	 * Returns a new IntPoint2 which is dx/dy shifted
	 * from this IntPoint2.
	 * @param dx
	 * @param dy
	 * @return new IntPoint2 dx/dy shifted from this IntPoint2
	 */
	public IntPoint2 shiftedPoint(int dx, int dy) {
		return new IntPoint2(x+dx, y+dy);
	}
	
	/**
	 * Returns the distance between this IntPoint2 an another.
	 * @param other
	 * @return distance to other IntPoint2
	 */
	public double distance(IntPoint2 other) {
		double x2 = Math.abs(x - other.x);
		double y2 = Math.abs(y - other.y);
		return Math.sqrt(x2*x2 + y2*y2);
	}
	
	/**
	 * Returns a "x y" string representation of the IntPoint2.
	 * @return string representation
	 */
	public String toString() {
		return x + " " + y;
	}

	public IntPoint2 add(IntPoint2 other)
	{
		return new IntPoint2(other.x + this.x, other.y+this.y);
	}
	
	/**
	 * Compares two points. Note: uses == on x and y
	 * double values, which is a questionable practice.
	 * Consider using distance() for a more
	 * flexible way to compare two  points.
	 */
	public boolean equals(Object object) {
		if (! (object instanceof IntPoint2)) return false;
		IntPoint2 other = (IntPoint2)object;
		// Note: here we == compare doubles, which is not a good practice
		return (other.x==x && other.y==y);
	}

	public static IntPoint2 UP = new IntPoint2(0, 1);
	public static IntPoint2 DOWN = new IntPoint2(0, 1);
	public static IntPoint2 LEFT = new IntPoint2(0, 1);
	public static IntPoint2 RIGHT = new IntPoint2(0, 1);
}
