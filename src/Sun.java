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
		
		try {
			bodyimage = ImageIO.read(new File("C:/Users/Dnae/workspace/SpaceExplorers/sun.png")).getScaledInstance(size, size, Image.SCALE_DEFAULT);;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setSize(size, size);
	}
	
}