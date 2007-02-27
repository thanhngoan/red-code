import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;


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
	
	/**
	 * 
	 * @return the origin of the line
	 */
	public Point startPoint() {
		return new Point(pStart.x, pStart.y);
	}

	/**
	 * 
	 * @return the teminal point of the line
	 */
	public Point endPoint() {
		return new Point(pEnd.x, pEnd.y);
	}

	public void setEndpoint(Point end) {
		pEnd = (Point) end.clone();
		setBounds(DShape.createRectangleContainingPoints(pStart, pEnd));
	}
	
	public void setOrigin(Point start) {
		pStart = (Point) start.clone();
		setBounds(DShape.createRectangleContainingPoints(pStart, pEnd));
	}
	

	public void setBounds(Rectangle bounds)
	{
		super.setBounds(bounds);
	}

	//serialization hack for deep copy
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		this.pStart = (Point) this.pStart.clone();
		this.pEnd = (Point) this.pEnd.clone();
		this.bounds = (Rectangle) this.bounds.clone();
		this.color = new Color(this.color.getRGB());
		out.defaultWriteObject();
	}
	

	public void mimic(DShapeModel other) {
		this.pStart = ((DLineModel) other).pStart;
		this.pEnd = ((DLineModel) other).pEnd;
		super.mimic(other);
	}
}
