import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Asteroid extends SpaceObject{
	
	public Asteroid(){
		
		setOpaque(false);
		
		ownmovey = (random.nextInt(3) -1 ) * random.nextDouble();
		ownmovex = (random.nextInt(3) -1 ) * random.nextDouble();

		this.sizex = (random.nextInt(180) + 20);
		this.sizey = sizex + (random.nextInt(20) - 10);
		
		try {
			img = ImageIO.read(new File("C:/Users/Dnae/workspace/SpaceExplorers/asteroid.png")).getScaledInstance(sizex, sizey, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int locx, locy;
		
		if(random.nextBoolean()){
			locx = random.nextInt(2000) - 1000;
			if(random.nextBoolean()){
				locy = -300;
			}
			else{
				locy = 1300;
			}
		}
		else{
			locy = random.nextInt(2000) - 1000;
			if(random.nextBoolean()){
				locx = -300;
			}
			else{
				locx = 1300;
			}
		}
		
		setLocation(locx, locy);
		setSize(sizex, sizey);
	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}	
}
