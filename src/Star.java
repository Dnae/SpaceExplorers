import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Star extends JPanel{

	Image star = null;
	int size;
	
	public Star(int size){
		
		setOpaque(false);
		
		this.size = size;
		
		try {
			star = ImageIO.read(new File("C:/Users/Dnae/workspace/SpaceExplorers/star.png")).getScaledInstance(size, size, Image.SCALE_DEFAULT);;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setSize(size, size);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(star, 0, 0, null);

	}
}
