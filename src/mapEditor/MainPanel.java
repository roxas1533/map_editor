package mapEditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements KeyListener, Runnable, MouseListener, MouseMotionListener {
	private static final int WIDTH = 30 * 27;
	private static final int HEIGHT = 30 * 21;
	private Thread gameLoop;
	static int[][] map;
	static Scroll sc;
	int width = 30;
	int height = 30;
	public boolean changed;
	static int offsetX;
	int[][] chipInfo = {
			{ 0, 1, 2, 3 },
			{ 4, 5, 6, 7 },
	};
	private GameObject nowSelect = new GameObject(0, 0, 30, 30);
	private GameObject nowChip = new GameObject(120, 0, width, height);

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

		//------------------------------------------------------マップ本体----------------------------------------------
		if (map != null) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					switch (map[i][j]) {
					case 0:
						g.setColor(Color.black);
						g.drawRect(120 + j * width - offsetX, i * height, width, height);
						break;
					case 1:
						g.setColor(Color.GRAY);
						break;
					case 2:
						g.setColor(Color.ORANGE);
						break;
					case 3:
						g.setColor(Color.RED);
						break;
					default:
						g.setColor(Color.BLACK);
						break;
					}
					if (map[i][j] != 0)
						g.fillRect(120 + j * width - offsetX, i * height, width, height);
					//					g.drawString(j + "", 120 + j * width - offsetX, i * height);
				}
			}
			g.setColor(Color.GREEN);
			Graphics2D g2 = (Graphics2D) g;
			BasicStroke bs = new BasicStroke(5);
			g2.setStroke(bs);
			//			g.drawRect((int) nowChip.x, (int) nowChip.y, nowChip.width, nowChip.height);
			sc.draw(g);
		}
		//------------------------------------------------------ここまで----------------------------------------------
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 120, HEIGHT);
		g.setColor(Color.black);
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
		Graphics2D g2 = (Graphics2D) g;
		BasicStroke bs = new BasicStroke(5);
		g2.setStroke(bs);
		g.drawRect((int) nowSelect.x, (int) nowSelect.y, nowSelect.width, nowSelect.height);

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();

		int mod = e.getModifiersEx();
		if ((mod & InputEvent.CTRL_DOWN_MASK) != 0) {
			if (keycode == KeyEvent.VK_S) {
				if (MapEditor.nowSelect != null) {
					try {
						FileWriter fe = new FileWriter(MapEditor.nowSelect);
						fe.write(MainPanel.map.length + "\r\n");
						fe.write(MainPanel.map[0].length + "\r\n");
						for (int i = 0; i < MainPanel.map.length; i++) {
							for (int j = 0; j < MainPanel.map[0].length; j++) {
								fe.write(MainPanel.map[i][j] + "");
								if (j != MainPanel.map[0].length - 1)
									fe.write(",");
							}
							fe.write("\r\n");
						}
						fe.close();
						if (changed)
							MapEditor.frame.setTitle(MapEditor.title.substring(3));
					} catch (IOException e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void run() {
		while (true) {
			repaint();
			nowSelect.update();

			if (map != null & sc != null)
				offsetX = (int) ((sc.x - 120) / (WIDTH - 120) * map[0].length * width);
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
		if (e.getX() < 30 * 4 & e.getY() < chipInfo.length * 30) {
			//			System.out.println();
			nowSelect.selectId = e.getX() / 30 + e.getY() / 30 * 4;
		}
		if (map != null) {
			drawMap(e);
			if (e.getX() >= sc.x && e.getX() <= sc.x + sc.width && e.getY() <= sc.y + sc.height && e.getY() >= sc.y) {
				sc.MousePoint = (int) (e.getX() - sc.x);
			}
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
		if (map != null) {
			drawMap(e);
			if (e.getX() >= sc.x && e.getX() <= sc.x + sc.width && e.getY() <= sc.y + sc.height && e.getY() >= sc.y) {
				sc.x = e.getX() - sc.MousePoint;
				sc.x = Math.max(120, sc.x);
				sc.x = Math.min(WIDTH, sc.x + sc.width) - sc.width;
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (map != null)
			if (x < map[0].length * width + 120 && x > 120 && map.length * height > y && y > 0) {
				nowChip.x = e.getX() / width * width;
				nowChip.y = e.getY() / width * height;
			}
	}

	public void drawMap(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int btn = e.getModifiers();
		if (x < map[0].length * width + 120 && x > 120 && map.length * height > y && y > 0) {
			if (!changed) {
				changed = true;
				MapEditor.title = " * " + MapEditor.title;
				MapEditor.frame.setTitle(MapEditor.title);
			}

			nowChip.x = e.getX() / width * width;
			nowChip.y = e.getY() / width * height;
			if (btn == 16)
				map[e.getY() / height][(e.getX() + offsetX - 120) / width] = nowSelect.selectId;
			else
				map[e.getY() / height][(e.getX() + offsetX - 120) / width] = 0;
		}
	}
}
