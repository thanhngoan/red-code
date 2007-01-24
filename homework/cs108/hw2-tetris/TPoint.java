// TPoint.java
/*
 This is just a trivial "struct" type class --
 it simply holds an int x/y point for use by Tetris,
 and supports equals() and toString().
 We'll allow public access to x/y, so this
 is not an object really.
*/
public class TPoint {
	public int x;
	public int y;

	// Creates a TPoint based in int x,y
	public TPoint(int x, int y) {
    		// questionable style but convenient --
    		// params with same name as ivars
		
		this.x = x;
		this.y = y;
	}
	
	public Object clone() { return new TPoint(x,y);}
	
	// Creates a TPoint, copied from an existing TPoint
	public TPoint(TPoint point) {
		this.x = point.x;
		this.y = point.y;
	}

	// Standard equals() override
	public boolean equals(Object other) {
		// standard two checks for equals()
		if (this == other) return true;
		if (!(other instanceof TPoint)) return false;
		
		// check if other point same as us
		TPoint pt = (TPoint)other;
		return(x==pt.x && y==pt.y);
 	}
	
	// Standard toString() override, produce
	// human-readable String from object
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	/**
	 * Immutable method that returns a point with swapped x and y.
	 * 
	 */
	public TPoint swapped() { return new TPoint(y,x); }
	
	/**
	 * 2 Dimensional rotation matrix is
	 * <math format="LaTeX">
	 * 	M(\theta) = \begin{bmatrix} 
	 *	\cos{\theta} & -\sin{\theta} \\
	 *	\sin{\theta} & \cos{\theta} 
	 *	\end{bmatrix}
	 * </math>
	 */
	public TPoint rotated(double angleInRadians)
	{
		Math.sin(3);
		double cos = Math.cos(angleInRadians), sin = Math.sin(angleInRadians);
		double rotatedX = cos * x - sin*y,
			   rotatedY = sin * x + cos*y;
		return new TPoint((int)Math.round(rotatedX),
						  (int)Math.round(rotatedY));
	}
	
	public TPoint translated(TPoint addPoint)
	{
		return new TPoint(addPoint.x + x, addPoint.y + y);
	}
}
