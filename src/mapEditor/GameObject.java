package mapEditor;

import java.awt.Graphics;

public class GameObject {
	double x, y;
	int width, height;
	double veloX, veloY;
	int selectId;

	GameObject(double x, double y, int w, int h) {
		this.x=x;
		this.y=y;
		this.width=w;
		this.height=h;
	}

	public void update() {
		x=((selectId%4)*30);
		y=selectId/4*30;
	}

	public void draw(Graphics g, int offsetX) {
	}
}
