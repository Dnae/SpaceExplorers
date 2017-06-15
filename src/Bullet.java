import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends SpaceObject{

	public Bullet(double x, double y){

		

		setOpaque(false);

		ownmovex = x;
		ownmovey = y;
		
		this.sizex = 6;
		this.sizey = 6;

		img = ImageBank.getImage("bullet");

		setLocation(SystemVariables.getWidth()/2, SystemVariables.getHeight()/2);
		setSize(sizex, sizey);

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, sizex, sizey, null);
	
	}
}
