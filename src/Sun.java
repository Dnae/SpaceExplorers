import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Sun extends CelestialBody{
	
	public Sun(int size){
		
		setOpaque(false);

		this.size = size;

		bodyimage = ImageBank.getImage("sun");

		setSize(size, size);
	}
	
}