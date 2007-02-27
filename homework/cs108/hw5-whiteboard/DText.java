import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;


public class DText extends DShape {

	@Override
	void draw(Graphics g) {
		g.setColor(getModel().getColor());
		g.setFont(getFont(g));
		Point offset = getDrawOffset(g.getFont(), g);
		
		Point position = new Point(offset.x + getModel().getBounds().x, offset.y + getModel().getBounds().y);
		
		//from nick parlante
		Shape temp = g.getClip(); // get current g clip
		g.setClip(getModel().getBounds()); // clip to the bounds rect
		g.drawString(getText(), position.x, position.y);
		g.setClip(temp); // restore the old g clip
	}
	
	protected Point getDrawOffset(Font font, Graphics g)
	{
		return computeDrawOffset(font, g);
	}
	
	protected Font getFont(Graphics g) {
		return computeFont(g);
	}
	
	protected Point computeDrawOffset(Font font, Graphics g)
	{
		Point offsetFromBounds = new Point();
		LineMetrics lmetrics = g.getFontMetrics(font).getLineMetrics(getText(), g);
		offsetFromBounds.y = (int) -lmetrics.getDescent();
		offsetFromBounds.y += getModel().getBounds().height;
		return offsetFromBounds;
	}
	
	protected String getText() { return ((DTextModel)getModel()).getText(); }
	protected DTextModel getTextModel() { return (DTextModel)getModel(); }
	
	protected Font computeFont(Graphics g) {
		Rectangle bounds = getModel().getBounds();
		Font font = Font.decode(getTextModel().getFontFamily());
		font = font.deriveFont(1.0f);
		for (float size=1; true; size = (size * 1.1f) + 1)
		{
			Font hypotheticalFont = font.deriveFont(size);
			FontMetrics metrics = g.getFontMetrics(font);
			Rectangle hypotheticalBounds = metrics.getStringBounds(getText(), g).getBounds();
			if (hypotheticalBounds.height > getModel().getBounds().height ||
				hypotheticalBounds.width > getModel().getBounds().width)
				break;
			font = hypotheticalFont;
		}
		return font;
	}

}
