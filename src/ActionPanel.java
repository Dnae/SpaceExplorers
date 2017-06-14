import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ActionPanel extends JPanel{
	
	List<SpaceObject> thinglist = new ArrayList<SpaceObject>();
	List<SpaceObject> removelist = new ArrayList<SpaceObject>();
	
	double currentx, currenty;
	
	public ActionPanel(){
		setLayout(null);
		setSize(SystemVariables.getWidth(), SystemVariables.getHeight());
		setOpaque(false);
		setLocation(0, 0);
	}
	
	public void addThing(SpaceObject so){
		
		thinglist.add(so);
		add(so);
		
	}
	
	public void updateActionPanel(double x, double y){
		
		currentx = x;
		currenty = y;	
		
		new Thread(){
			public void run(){
				long startTime = System.nanoTime();
				for(SpaceObject so : thinglist){
					so.updateSO(currentx, currenty);

					if(so.getX() > 2000 || so.getX() < -1000 || so.getY() > 2000 || so.getY() < -1000){
						removelist.add(so);
					}

					if(so instanceof Bullet){
						for(SpaceObject so2 : thinglist){
							if(so2 instanceof Asteroid){				
							
								if(
										(so.getX() - so2.getX()) < so2.getWidth() && 
										(so.getX() - so2.getX()) > 0 && 
										(so.getY() - so2.getY()) < so2.getHeight() &&
										(so.getY() - so2.getY()) > 0){
									removelist.add(so);
									removelist.add(so2);
								}
							}
						}
					}


				}
				for(SpaceObject as : removelist){
					thinglist.remove(as);
					remove(as);
				}
				
				removelist.clear();
				
				int numast = 0;
				for(SpaceObject so : thinglist){
					if(so instanceof Asteroid){
						numast++;
					}
				}
				if(numast < 20){
					addThing(new Asteroid());
				}
				
				long endTime = System.nanoTime();
				System.out.println("Millis: " + ((endTime - startTime)/1000000));
			}
		}.start();
	}
}
