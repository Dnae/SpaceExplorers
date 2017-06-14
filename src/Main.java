import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] arg){
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		new SystemVariables(width, height);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GameWindow();
			}
		});
	}
}
