import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public abstract class CelestialBody extends JPanel{

	Image bodyimage = null;
	int size;
	
	int index;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bodyimage, 0, 0, null);

	}	
	
	public int getBodySize(){
		return size;
	}
	
	public int getIndex(){
		return index;
	}

}
