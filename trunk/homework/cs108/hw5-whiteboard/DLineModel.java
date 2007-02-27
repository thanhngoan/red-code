import java.awt.Point;
import java.awt.Rectangle;


public class DLineModel extends DShapeModel {


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
		Rectangle bounds = getBounds();
		return new Point(bounds.x, bounds.y);
	}

	/**
	 * 
	 * @return the teminal point of the line
	 */
	protected Point endPoint() {
		Rectangle bounds = getBounds();
		return new Point(bounds.x + bounds.width, bounds.y + bounds.height);
	}
}
