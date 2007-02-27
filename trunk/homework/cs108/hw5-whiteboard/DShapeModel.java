import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;


public class DShapeModel implements Serializable {
	public static int UNINITIALIZED_ID = -1;
	/*
		protected int x;
		protected int y;
		protected int width;
		protected int height;
	 */
	protected Color color;
	protected Rectangle bounds;
	protected int id;
	protected transient Collection<ModelListener> modelChangeListeners;
	

	public static interface ModelListener {
		public void modelChanged(DShapeModel model);
	}
	
	DShapeModel()
	{
		color = Color.GRAY;
		bounds = new Rectangle(0, 0, 0, 0);
		modelChangeListeners = new LinkedList<ModelListener>();
		id = UNINITIALIZED_ID;
	}
	
	 private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		 in.defaultReadObject();
		 if (modelChangeListeners == null)
			 modelChangeListeners = new LinkedList<ModelListener>();
	 }
	 
	 private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		this.bounds = (Rectangle) this.bounds.clone();
		this.color = new Color(this.color.getRGB());
		out.defaultWriteObject();
	 }
	
	public void mimic(DShapeModel other) {
		this.bounds = (Rectangle) other.bounds.clone();
		this.id = other.id;
		this.color = (Color) other.color;
		notifyListenersOfChange();
	}
	
	public void addChangeListener(ModelListener l)
	{
		modelChangeListeners.add(l);
	}
	
	public boolean removeChangeListener(ModelListener l)
	{
		return modelChangeListeners.remove(l);
	}
	
	protected void notifyListenersOfChange()
	{
		for (ModelListener listener : modelChangeListeners)
			listener.modelChanged(this);
	}

	public Rectangle getBounds() { return bounds; }
	public void setBounds(Rectangle bounds)
	{
		boolean changed =  !this.bounds.equals(bounds);
		this.bounds = bounds;
		if (changed)
			notifyListenersOfChange();
	}
	
	public Point getOrigin() {
		return bounds.getLocation();
	}
	
	public void setOrigin(Point o) {
		bounds.x = o.x;
		bounds.y = o.y;
		notifyListenersOfChange();
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		boolean changed =  !this.color.equals(color);
		this.color = color;
		if (changed)
			notifyListenersOfChange();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8185486088337582941L;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
