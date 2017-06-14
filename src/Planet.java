import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Planet extends CelestialBody{

	double rotation;
	
	int sunposx, sunposy;
	
	Image shadow = null;
	boolean shipOver = false;
	
	public Planet(int index, int size){
		
		setOpaque(false);
		
		setRotation();
		
		this.index = index;
		this.size = size;
		
		try {
			bodyimage = ImageIO.read(new File("C:/Users/Dnae/workspace/SpaceExplorers/planet" + index + ".png")).getScaledInstance(size, size, Image.SCALE_DEFAULT);;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			shadow = ImageIO.read(new File("C:/Users/Dnae/workspace/SpaceExplorers/shadow.png")).getScaledInstance(size, size, Image.SCALE_DEFAULT);;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setSize(size, size);
	}
	
	private void setRotation(){
		rotation = Math.atan2(getY()-sunposy,getX()-sunposx);
	}
	
	public void shipOver(boolean bol){
		shipOver = bol;
	}
	
	public void setSunPos(int x, int y){
		sunposx = x;
		sunposy = y;
		
		setRotation();
	}
	
	public int getIndex(){
		return index;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bodyimage, 0, 0, null);

		// create the transform, note that the transformations happen
		// in reversed order (so check them backwards)
		AffineTransform at = new AffineTransform();

		// 4. translate it to the center of the component
		at.translate(getWidth() / 2, getHeight() / 2);

		// 3. do the actual rotation
		at.rotate(rotation);

		// 2. just a scale because this image is big
		at.scale(1, 1);

		// 1. translate the object so that you rotate it around the 
		//    center (easier :))
		at.translate(-getWidth() / 2, -getHeight() / 2);

		// draw the image
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(shadow, at, null);

		if(shipOver){
			g.setColor(Color.GREEN);
			g.drawOval(0, 0, size - 1 ,size - 1);
		}
	}	

}