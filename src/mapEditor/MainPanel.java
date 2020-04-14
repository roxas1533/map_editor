package mapEditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements KeyListener, Runnable, MouseListener, MouseMotionListener {
	private static final int WIDTH = 30 * 27;
	private static final int HEIGHT = 30 * 21;
	private Thread gameLoop;
	static int[][] map;
	int[][] chipInfo = {
			{ 0, 1, 2, 3 },
			{ 4, 5, 6, 7 },
	};
	private GameObject nowSelect = new GameObject(0, 0, 30, 30);

	MainPanel() {

		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		gameLoop = new Thread(this);
		gameLoop.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(120, 0, 120, 1000);
		for (int i = 0; i < chipInfo.length; i++) {
			for (int j = 0; j < chipInfo[i].length; j++) {
				switch (chipInfo[i][j]) {
				case 0:
					break;
				case 1:
					g.setColor(Color.GRAY);
					g.fillRect(j * 30, i * 30, 30, 30);
					break;
				case 2:
					g.setColor(Color.ORANGE);
					g.fillRect(j * 30, i * 30, 30, 30);
					break;
				case 3:
					g.setColor(Color.RED);
					g.fillRect(j * 30, i * 30, 30, 30);
					break;
				default:
					g.setColor(Color.BLACK);
					g.fillRect(j * 30, i * 30, 30, 30);
					break;
				}
			}
		}
		g.setColor(Color.GREEN);
		Graphics2D g2 = (Graphics2D)g;
		BasicStroke bs = new BasicStroke(5);
		g2.setStroke(bs);
		g.drawRect((int)nowSelect.x,(int)nowSelect.y,nowSelect.width,nowSelect.height);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void run() {
		while (true) {
			repaint();
			nowSelect.update();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getX()<30*4&e.getY()<chipInfo.length*30) {
//			System.out.println();
			nowSelect.selectId=e.getX()/30+e.getY()/30*4;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
