package mapEditor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MapEditor extends JFrame {
	static MapEditor frame;

	MapEditor() {
		setTitle("マップエディタ");
		MainPanel panel = new MainPanel();
		Container contentPane = getContentPane();
		contentPane.add(panel);
		setResizable(false);
		JMenuBar menubar = new JMenuBar();
		JMenu menu1 = new JMenu("ファイル");
		JMenu menu2 = new JMenu("編集");
		menubar.add(menu1);
		menubar.add(menu2);
		JMenuItem menuitem1_1 = new JMenuItem("    新規");
		JMenuItem menuitem1_2 = new JMenuItem("    開く");
		JMenuItem menuitem1_3 = new JMenuItem("    名前を付けて保存");

		menuitem1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				T subwindow = new T();

				subwindow.setVisible(true);
				setEnabled(false);
			}
		});

		menu1.add(menuitem1_1);
		menu1.add(menuitem1_2);
		menu1.add(menuitem1_3);
		setJMenuBar(menubar);
		pack();
	}

	public static void main(String[] args) {
		frame = new MapEditor();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Rectangle screen = frame.getGraphicsConfiguration().getBounds();
		frame.setLocation(screen.x + screen.width / 2 - frame.getSize().width / 2,
				screen.y + screen.height / 2 - frame.getSize().height / 2);
	}

}

class T extends JFrame implements WindowListener {

	T() {
		setTitle("新規作成");
		setSize(200, 100);
		setLocationRelativeTo(null);
		setResizable(false);
		addWindowListener(this);
		P panel = new P();
		Container contentPane = getContentPane();
		contentPane.add(panel);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		MapEditor.frame.setEnabled(true);
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

}

class P extends JPanel implements ActionListener {
	JTextField tate = new JTextField("20", 5);
	JTextField yoko = new JTextField("20", 5);

	P() {
		JLabel label = new JLabel("縦");
		label.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		label.setFont(new Font("明朝体", Font.PLAIN, 16));
		JLabel label2 = new JLabel("×");
		label2.setFont(new Font("明朝体", Font.PLAIN, 20));
		JLabel label3 = new JLabel("横");
		label3.setFont(new Font("明朝体", Font.PLAIN, 16));

		JButton bu = new JButton("OK");
		bu.addActionListener(this);

		this.add(label, BorderLayout.CENTER);
		this.add(tate);
		this.add(label2);
		this.add(label3);
		this.add(yoko);
		this.add(bu);

	}

	public void actionPerformed(ActionEvent e) {
		try {
			MainPanel.map = new int[Integer.parseInt(tate.getText())][Integer.parseInt(yoko.getText())];
			Arrays.stream(MainPanel.map).forEach(a -> Arrays.fill(a, 0));
			Window w = SwingUtilities.getWindowAncestor(this);
			MapEditor.frame.setEnabled(true);
			MainPanel.sc = new Scroll(120, 30 * 20,
					MainPanel.map[0].length > 23 ? (int)Math.floor(23.0 / MainPanel.map[0].length * 30 * 23)
							: (30 * 23),30);
			w.dispose();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, new JLabel("0以上の半角数字を入力してください"));
		} catch (NegativeArraySizeException ex) {
			JOptionPane.showMessageDialog(this, new JLabel("0以上の半角数字を入力してください"));
		}

	}
}
