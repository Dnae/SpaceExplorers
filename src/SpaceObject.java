import java.awt.Image;
import java.util.Random;

import javax.swing.JPanel;

public class SpaceObject extends JPanel{

	Random random = new Random();
	
	Image img;
	int sizex, sizey;
	
	double moveY;
	double moveX;
	
	double ownmovex;
	double ownmovey;

	public void updateSO(double x, double y){

		moveX = moveX + x + ownmovex;
		moveY = moveY + y + ownmovey;

		int xDif = 0;
		int yDif = 0;

		if(Math.abs(moveX) >= 1){
			xDif = (int)moveX;
			moveX = moveX%1;
		}
		if(Math.abs(moveY) >= 1){
			yDif = (int)moveY;
			moveY = moveY%1;
		}

		
		if(xDif != 0 || yDif != 0){
			setLocation(getX() + xDif, getY() + yDif);	
		}
	}
	
}
