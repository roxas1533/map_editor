package mapEditor;

import java.awt.Container;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class MapEditor extends JFrame{
	MapEditor(){
		   setTitle("マップエディタ");
		   MainPanel panel = new MainPanel();
	        Container contentPane = getContentPane();
	        contentPane.add(panel);
	        setResizable(false);
	        pack();
	}
	public static void main(String[] args) {
		MapEditor frame = new MapEditor();
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setVisible(true);
	      Rectangle screen = frame.getGraphicsConfiguration().getBounds();
	      frame.setLocation(screen.x + screen.width  / 2 - frame.getSize().width  / 2,
	                        screen.y + screen.height / 2 - frame.getSize().height / 2);
	}

}
