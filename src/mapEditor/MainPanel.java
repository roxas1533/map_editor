package mapEditor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements KeyListener, Runnable{
	private static final int WIDTH = 700;
	private static final int HEIGHT = 30 * 21;
	private Thread gameLoop;
	MainPanel(){
		setFocusable(true);
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		gameLoop = new Thread(this);
		gameLoop.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}
	@Override
	public void run() {
		while (true) {
			repaint();

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
