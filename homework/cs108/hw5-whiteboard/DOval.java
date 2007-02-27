import java.awt.Graphics;
import java.awt.Rectangle;


public class DOval extends DShape {

	@Override
	void draw(Graphics g) {
		g.setColor(getModel().getColor());
		Rectangle pos = ((DOvalModel)getModel()).getBounds();
		g.fillOval(pos.x, pos.y, pos.width, pos.height);
	}

}
