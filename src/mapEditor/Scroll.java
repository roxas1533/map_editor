package mapEditor;

import java.awt.Color;
import java.awt.Graphics;

public class Scroll extends GameObject {

	int MousePoint;
	Scroll(double x, double y, int w, int h) {
		super(x, y, w, h);
	}
	@Override
	public void update() {

	}
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, width, height);
	}

}
