import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ShipIcon extends JPanel{

	Image ship = null;
	Image blast = null;
	
	int centerX, centerY;
	
	double rotation = 0;
	
	boolean blastOn = false;

	public ShipIcon(){
		
		loadImages();
		
		centerX = SystemVariables.getWidth()/2;
		centerY = SystemVariables.getHeight()/2;
		
		setOpaque(true);
		setBackground(Color.RED);
		setPreferredSize(new Dimension(200,200));
		setOpaque(false);
	}
	
	public void newDirection(double rotation){
		this.rotation = rotation;
		
		repaint();
	}
	
	public void blastOn(){
		blastOn = true;
		repaint();
	}
	
	public void blastOff(){
		blastOn = false;
		repaint();
	}
	
	private void loadImages(){
			
		ship = ImageBank.getImage("ship");
		
		blast = ImageBank.getImage("blast");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

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
		at.translate(-25, -25);

		// draw the image
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(ship, at, null);
		

		// continue drawing other stuff (non-transformed)
		
		if(blastOn){
		
			at.translate(0, 50/1.2);

			g2d.drawImage(blast, at, null);
		}
	}
}
