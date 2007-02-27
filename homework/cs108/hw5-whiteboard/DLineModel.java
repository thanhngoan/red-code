import java.awt.Point;
import java.awt.Rectangle;


public class DLineModel extends DShapeModel {
	
	Point pStart;
	Point pEnd;
	
	public DLineModel() {
		super();
		pStart = new Point();
		pEnd = new Point();
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 8371521603504972880L;

	public void mimic(DShapeModel other) {
		super.mimic(other);
	}
	
	/**
	 * 
	 * @return the origin of the line
	 */
	protected Point startPoint() {
		return new Point(pStart.x, pStart.y);
	}

	/**
	 * 
	 * @return the teminal point of the line
	 */
	protected Point endPoint() {
		Rectangle bounds = getBounds();
		return new Point(pEnd.x, pEnd.y);
	}
}
