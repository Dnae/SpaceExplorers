import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Radar extends JPanel{

	Image radar = null;
	int size;
	
	int radarx, radary;
	
	List<CelestialBody> radarplanets = new ArrayList<CelestialBody>();
	
	public Radar(int x, int y){
		
		setOpaque(false);
		setRadarLocation(x, y);
		
		setLayout(null);
		
		size = SystemVariables.getHeight()/5;
		
		try {
			radar = ImageIO.read(new File("C:/Users/Dnae/workspace/SpaceExplorers/radar.png")).getScaledInstance(size, size, Image.SCALE_DEFAULT);;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setSize(size, size);
	}
	
	public void addCelestialBodys(CelestialBody cb){
		
		radarplanets.add(cb);
	}
	
	public void addCelestialBodys(List<CelestialBody> cblist){
		
		radarplanets.clear();
		
		for(CelestialBody cb : cblist){
			radarplanets.add(cb);
		}
	}
	
	public void setRadarLocation(int x, int y){
		radarx = x;
		radary = y;
		
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		Area rect = new Area(new Rectangle(0, 0, size, size));
		Area circle = new Area(new Ellipse2D.Double(0, 0, size, size));
		rect.subtract(circle);

		g2d.drawImage(radar, 0, 0, null);
		
		for(CelestialBody cb : radarplanets){
			
			int half = size / 2;
			
			int locx = (cb.getX() + radarx - SystemVariables.getWidth()/2) / 100  + half;
			int locy = (cb.getY() + radary - SystemVariables.getHeight()/2) / 100  + half;
			
			if(locx-half < size && locy - half < size){
				int cbsize = cb.getBodySize() /100;
				
				g2d.setColor(Color.WHITE);
				
				Area dot = new Area(new Ellipse2D.Double(locx, locy, cbsize, cbsize));
				dot.subtract(rect);
				
				g2d.fill(dot);
				
			}
			
			
		}

	}
}
