import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class SpacePanel extends JLayeredPane implements ActionListener{

	JPanel systempanel = new JPanel();
	JLabel xcoord = new JLabel("X: 0");
	JLabel ycoord = new JLabel("Y: 0");
	JLabel chunklabel = new JLabel("Chunk: 0  0");
	JLabel solarsystem = new JLabel("Solarsystem: 0  0");
	JLabel onplanet = new JLabel("On planet: ?");
	
	
	Radar radar = new Radar(0, 0);
	ActionPanel actionpanel = new ActionPanel();
	JPanel shippanel = new JPanel();
	PlanetPanel planetpanel = new PlanetPanel();
	BackGroundPanel backgroundpanel = new BackGroundPanel();
	
	ShipIcon ship = new ShipIcon();
	
	Timer spacetimer = new Timer(20, this);
	boolean newlyclicked = false;
	boolean lockOn = false;
	int clickcount;
	
	SaveFile sf;
	
	int chunk32x, chunk32y;
	
	int chunkx, chunky;

	double posX, posY;
	
	double dirX, dirY;
	double moveX, moveY;
	
	double speed;
	
	boolean blastOn = false;
	boolean onPlanet = false;
	
	public SpacePanel(SaveFile sf){

		this.sf = sf;
		
		if(sf.isNew()){
			setSpawn();
			sf.isNew(false);
		}
		else{
			chunk32x = sf.getChunk32X(); 
			chunk32y = sf.getChunk32Y(); 
			
			chunkx = sf.getChunkX();  
			chunky = sf.getChunkY(); 

			posX = sf.getPosX();  
			posY = sf.getPosY(); 
		}
		
		
		
		setBackground(Color.RED);
		setLayout(null);
		
		drawSystemPanel();
		
		drawShip();
		
		drawActionPanel();
		
		drawBackground();
		
		drawPlanets();
		
		drawRadar();
		
		spacetimer.start();
	
	}
	
	private void setSpawn(){
		
		boolean lookingforspawn = true;
		
		int i = 0;
		int j = 0;
		
		while(lookingforspawn == true){
			if(SystemVariables.getRandomFromSeed(i, j, 4) == 0){
				
				Planet p = planetpanel.getSpawnPlanet(i, j);
				
				chunk32x = i; 
				chunk32y = j;
				
				int planetlox = p.getX()+(p.getBodySize()/2);
				int planetloy = p.getY()+(p.getBodySize()/2);
				
				chunkx = planetlox/1000; 
				chunky = planetloy/1000;

				posX = planetlox%1000;
				posY = planetloy%1000;
				
				lookingforspawn = false;
			}
			else{
				i++;
			}
		}
		
		int x = (int)posX;
		int y = (int)posY;
		
		planetpanel.setLocation(-x + (-chunkx * 1000) + (SystemVariables.getWidth()/2), -y + (-chunky * 1000) + (SystemVariables.getHeight()/2));

	}
	
	private void drawSystemPanel(){
		systempanel.setLayout(null);
		systempanel.setLocation(0,0);
		systempanel.setSize(500, 500);
		systempanel.setOpaque(false);
		
		xcoord.setLocation(5, 5);
		xcoord.setSize(400, 50);
		xcoord.setForeground(Color.GREEN);
		
		ycoord.setLocation(5, 20);
		ycoord.setSize(400, 50);
		ycoord.setForeground(Color.GREEN);
		
		chunklabel.setLocation(5, 35);
		chunklabel.setSize(400, 50);
		chunklabel.setForeground(Color.GREEN);
		
		solarsystem.setLocation(5, 50);
		solarsystem.setSize(400, 50);
		solarsystem.setForeground(Color.GREEN);
		
		onplanet.setLocation(5, 65);
		onplanet.setSize(400, 50);
		onplanet.setForeground(Color.GREEN);
		
		systempanel.add(xcoord);
		systempanel.add(ycoord);
		systempanel.add(chunklabel);
		systempanel.add(solarsystem);
		systempanel.add(onplanet);
		
		systempanel.validate();
		
		add(systempanel, new Integer(5));
	}
	
	private void drawRadar(){
		
		radar.addCelestialBodys(planetpanel.getCelestialBodys());
		
		radar.setLocation(20, SystemVariables.getHeight() - 75 - radar.getHeight());
		
		radar.setRadarLocation(planetpanel.getX(), planetpanel.getY());
		
		radar.validate();
		
		add(radar, new Integer(4));
	}
	
	private void updateRadar(){
				
		radar.setRadarLocation(planetpanel.getX(), planetpanel.getY());
	}
	
	private void drawShip(){
		
		shippanel.setOpaque(false);
		shippanel.setLayout(new GridBagLayout());
		shippanel.setSize(SystemVariables.getWidth(), SystemVariables.getHeight());
		
		shippanel.add(ship);

		shippanel.validate();
		
		add(shippanel, new Integer(3));
		
		mouseStuff();
		
	}
	
	private void drawActionPanel(){
		add(actionpanel, new Integer(2));
	}
	
	private void drawPlanets(){
		planetpanel.validate();
		
		add(planetpanel, new Integer(1));
	}
	
	
	private void drawBackground(){
				
		backgroundpanel.validate();
		
		add(backgroundpanel, new Integer(0));
		
	}
	
	private void blastDirection(MouseEvent e){

		int centerwidth = SystemVariables.getWidth()/2;
		
		int centerheight = SystemVariables.getHeight()/2;
		
		int deltaX = e.getX() - (centerwidth);
		int deltaY = e.getY() - (centerheight);
		
		double a = Math.sqrt(
						Math.pow(deltaX, 2) + 
						Math.pow(deltaY, 2));
		
		double rotation = Math.atan2(e.getY()-centerheight,e.getX()-centerwidth) + Math.PI/2;
		
//		rotation = Math.atan2(y-centerY,x-centerX) + Math.PI/2;
		
		ship.newDirection(rotation);
		
		if(a != 0){
			dirX = deltaX / a;
			dirY = deltaY / a;
		}
	}
	
	private void mouseStuff(){
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e)){
					blastOn = true;
					ship.blastOn();
				}

			}

			@Override
		    public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e)){
					
					if(newlyclicked == false){
						blastOn = false;
						ship.blastOff();
						lockOn = false;
					}
					else{
						lockOn = true;
					}
					newlyclicked = true;
					clickcount = 0;
				}
				if(SwingUtilities.isLeftMouseButton(e)){
					
					int centerwidth = SystemVariables.getWidth()/2;
					
					int centerheight = SystemVariables.getHeight()/2;
					
					int deltaX = e.getX() - (centerwidth);
					int deltaY = e.getY() - (centerheight);
					
					double a = Math.sqrt(
									Math.pow(deltaX, 2) + 
									Math.pow(deltaY, 2));
					
					actionpanel.addThing(new Bullet((deltaX/a)*20, (deltaY/a)*20));
				}
		    }
		    
		    @Override
		    public void mouseEntered(MouseEvent e) {    	
		    	setCursor(Toolkit.getDefaultToolkit().createCustomCursor((new ImageIcon("cursor.png")).getImage(),new Point(16,16),"custom cursor"));
		    }
		    
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	
		    	setCursor(Cursor.getDefaultCursor());
		    }
		});

		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent e) {
				if(lockOn == false){
					blastDirection(e);
				}
			}

			public void mouseDragged(MouseEvent e) {
				if(lockOn == false){
					blastDirection(e);
				}
			} 
		});
	}
	
	private void clickCheck(){
		
		if(clickcount < 10){
			clickcount++;
		}
		else{
			newlyclicked = false;	
		}
		
	}
	
	private void speedChange(){
		if(blastOn){
			if(speed < 20){ //TODO
				speed += 1;
			}
		}
		else{
			if(speed > 0){
				speed--;
			}
		}
	}
	
	private void updateCoordinates(){
		moveX = dirX * speed;
		moveY = dirY * speed;
		
		posX += moveX;
		posY += moveY;
		
		
		
		updateRadar();
		actionpanel.updateActionPanel(-moveX, -moveY);
		planetpanel.updatePlanets(-moveX, -moveY);
		backgroundpanel.updateStars(-moveX, -moveY);
		
		if(posX >= 1000){
			chunkx++;		
			posX = posX%1000;
			calculateChunk32();
				
		}
		else if(posX < 0){
			chunkx--;
			posX += 1000;
			calculateChunk32();

		}
		if(posY >= 1000){
			chunky++;
			posY = posY%1000;
			calculateChunk32();
			
		}
		else if(posY < 0){
			chunky--;
			posY += 1000;
			calculateChunk32();
		
		}
	}
	
	private void newChunk32(){

		new Thread(){
			public void run(){
				newSolarSystem();
				radar.addCelestialBodys(planetpanel.getCelestialBodys());
			}
		}.start();

	}

	private void calculateChunk32(){

		if(chunkx >= 32){
			chunk32x++;
			
			chunkx = chunkx%32;
			
			newChunk32();
		}
		else if(chunkx < 0){
			chunk32x--;
			
			chunkx += 32;
			
			newChunk32();
		}
		if(chunky >= 32){
			chunk32y++;
			
			chunky = chunky%32;
			
			newChunk32();
		}
		else if(chunky < 0){
			chunk32y--;
			
			chunky += 32;
			
			newChunk32();
		}
	}
	
	private void newSolarSystem(){
		
		int x = (int)posX;
		int y = (int)posY;
		
		planetpanel.setLocation(-x + (-chunkx * 1000) + (SystemVariables.getWidth()/2), -y + (-chunky * 1000) + (SystemVariables.getHeight()/2));
	
		planetpanel.newSolarSystem(chunk32x, chunk32y);
	}

	
	private void updateLabels(){
		xcoord.setText("X: " + posX);
		ycoord.setText("Y: " + posY);
		chunklabel.setText("Chunk: " + chunkx + "  " + chunky);	
		solarsystem.setText("Solarsystem: " + chunk32x + "  " + chunk32y);
		onplanet.setText("On planet: " + onPlanet);	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == spacetimer){
			
			//Check for double-click
			clickCheck();
			
			//Update movement speed
			speedChange();
			
			//Change coordinates
			updateCoordinates();
			
			//Update labels
			updateLabels();
			
		}
	}
}
