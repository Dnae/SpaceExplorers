import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BackGroundPanel extends JPanel{

	List<JPanel> starpanels = new ArrayList<JPanel>();
	
	int height, width;
	
	int coordx, coordy;
	double xrest, yrest;
	
	public BackGroundPanel(){
		
		width = SystemVariables.getWidth();
		height = SystemVariables.getHeight();
		
		setLayout(null);
		
		setOpaque(true);
		setBackground(Color.BLACK);
		
		setSize(width, height);
		
		setLocation(0, 0);
		
		drawStars(this);
		
		initiateList();
	}

	private void initiateList(){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				
				JPanel jp = new JPanel();
				jp.setLayout(null);
				jp.setOpaque(false);
				jp.setBackground(Color.RED);
				
				jp.setSize(1000, 1000);
				jp.setLocation((i-1)*1000, (j-1)*1000);
				
				drawStars(jp);
				
				starpanels.add(jp);
				add(jp);
				
			}
		}
	}
	
	private void drawStars(JPanel jp){
		
		for(int i = 0; i < 11; i++){
			Star st = new Star(SystemVariables.getRandom(32) + 1);
			
			st.setLocation(SystemVariables.getRandom(jp.getWidth() - 32), SystemVariables.getRandom(jp.getWidth() - 32));
			
			jp.add(st);
		}
	}
	
	/**
	 * 
	 * @param x is the x coordinate of rocket speed
	 * @param y is the y coordinate of rocket speed
	 */
	
	public void updateStars(double x, double y){
		
		int xDif = 0;
		int yDif = 0;
		
		int starspeed = 50;
		
		xrest += x;
		yrest += y;
		
		xDif = (int)xrest/starspeed;
		yDif = (int)yrest/starspeed;
		
		xrest = xrest%starspeed;
		yrest = yrest%starspeed;

		if(xDif != 0 || yDif != 0){	
		
			
			for(JPanel jp : starpanels){
				
				if(jp.getX() < -1000){
					jp.setLocation(1999, jp.getY());
				}
				
				if(jp.getX() >= 2000){
					jp.setLocation(-1000, jp.getY());
				}

				if(jp.getY() < -1000){
					jp.setLocation(jp.getX(), 1999);
				}
				if(jp.getY() >= 2000){
					jp.setLocation(jp.getX(), -1000);
				}
				
				jp.setLocation(jp.getX() + xDif, jp.getY() + yDif);
			}
		}
	}
	
}
