import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class PlanetPanel extends JPanel{
	
	List<CelestialBody> planetlist = new ArrayList<CelestialBody>(); 
	
	double moveX, moveY;
	
	int currentx, currenty;
	
	public PlanetPanel(){
		
		setLayout(null);

		setOpaque(false);
		setBackground(Color.BLUE);

		setSize(32*1000, 32*1000);
		
	}
	
	private void calculateSolarSystem(){
		
		for(CelestialBody cb : planetlist){
			remove(cb);
		}
		
		planetlist.clear();

		if(SystemVariables.getRandomFromSeed(currentx, currenty, 4) == 0){
			int numplanet = SystemVariables.getRandomFromSeed(currentx, currenty, 20) + 4;
			
			//SET SUN STUFF
			Sun sun = new Sun(100 * numplanet + 1000);
			sun.setLocation(14000, 14000);
			planetlist.add(sun);
			sun.validate();
			add(sun);
			
			int sunposx = sun.getX();
			int sunposy = sun.getY();

			//PLOT ALL PLANETS
			for(int i = 0; i < numplanet; i++){

				//POTENTIAL COORDINATES				
				int px = SystemVariables.getRandomFromSeed(currentx, currenty, i, 30000);
				int py = SystemVariables.getRandomFromSeed(currentx, currenty, i + 1, 30000);
				
				//CHECK IF IT'S INSIDE CIRCLE
				if((Math.pow(px - sunposx, 2)) + (Math.pow(py - sunposy, 2) ) < Math.pow(16000, 2)){
					
					boolean planetnearby = false;
					
					//CHECK IF THERE IS A NEARBY PLANET
					for(CelestialBody cb : planetlist){
						if(Math.abs(px - cb.getX()) < 900 && Math.abs(py - cb.getY()) < 900){
							planetnearby = true;
						}
					}
					
					if(planetnearby == false){
						Planet newplanet = new Planet(
								SystemVariables.getRandomFromSeed(currentx, currenty, i, 4) + 1, 
								SystemVariables.getRandomFromSeed(currentx, currenty, i, 500) + 400);
						planetlist.add(newplanet);
						
						newplanet.validate();
						add(newplanet);
						newplanet.setLocation(px, py);
						newplanet.setSunPos(sunposx, sunposy);
					}
				}
			}	
		}
	}

	public void newSolarSystem(int x, int y){

		currentx = x;
		currenty = y;

		calculateSolarSystem();

	}

	public Planet getSpawnPlanet(int x, int y){
		
		currentx = x;
		currenty = y;
		
		calculateSolarSystem();
		
		Planet p = null;
		
		for(CelestialBody sb : planetlist){
			if(sb instanceof Planet){
				p = (Planet) sb;
			}
		}
		
		return p;
	}
	
	public List<CelestialBody> getCelestialBodys(){
		
		return planetlist;
	}
	
	public void updatePlanets(double x, double y){

		moveX += x;
		moveY += y;

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
			
			for(CelestialBody cb : planetlist){
				if(cb instanceof Planet){
					if(
							cb.getX() + getX() > 0 && 
							cb.getX() + getX() < cb.getBodySize() &&
							cb.getY() + getY() > 0 && 
							cb.getY() + getY() < cb.getBodySize()){
						((Planet) cb).shipOver(true);
					}
					else{
						((Planet) cb).shipOver(false);
					}
				}
			}
		}
	}

}
