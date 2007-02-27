import java.awt.Graphics;
import java.awt.Rectangle;


public class DRect extends DShape {

	@Override
	void draw(Graphics g) {
		g.setColor(getModel().getColor());
		Rectangle pos = ((DRectModel)getModel()).getBounds();
		g.drawRect(pos.x, pos.y, pos.width, pos.height);
	}

}
