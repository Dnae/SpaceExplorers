
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class GameWindow {

	int width, height;
	
	JFrame window = new JFrame();
	LoadingMenu lm = new LoadingMenu(this);
	
	SaveFile sf;
	
	public GameWindow(){
		
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setTitle("SpaceExplorers");
	//	window.setUndecorated(true);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		window.getContentPane().setBackground(Color.RED);
		
		drawLoadingMenu();
		
	//	window.pack();
		window.setVisible(true);
	}
	
	private void drawLoadingMenu(){
		
		window.add(lm);
		
	}
	
	public void drawSpace(SaveFile save){
	
		this.sf = save;
		
		window.remove(lm);
		
		lm = null;
		
		window.add(new SpacePanel(sf));

		SwingUtilities.updateComponentTreeUI(window);
	}
}
