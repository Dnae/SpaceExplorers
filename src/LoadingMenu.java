import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadingMenu extends JPanel{

	JPanel menubuttons = new JPanel();
	JLabel title = new JLabel("SpaceExplorers");
	JPanel newgame = new JPanel();
	JPanel loadgame = new JPanel();
	JPanel back = new JPanel();
	
	List<JPanel> displayedpanels = new ArrayList<JPanel>();
	

	
	GameWindow gw;
	
	public LoadingMenu(GameWindow gw){
		
		this.gw = gw;
		
		this.setLayout(new GridBagLayout());
	//	setAlignmentY(CENTER_ALIGNMENT);
		setOpaque(true);
		setBackground(Color.BLACK);
		
		drawMenu();
	
	}
	
	private void drawLoadMenu(){

		for(JPanel jp : displayedpanels){
			menubuttons.remove(jp);

			repaint();

		}
		displayedpanels.clear();
		
		int i = 1;
		String str = i + ".savefile";
		File f = new File(str);

		while(f.exists()){
			
			LoadButton lb = new LoadButton(loadFile(str));
			displayedpanels.add(lb.getPanel());
			menubuttons.add(lb.getPanel());
			
			JPanel space = new JPanel();
			space.setOpaque(false);			
			
			displayedpanels.add(space);
			menubuttons.add(space);
			
			i++;
			str = i + ".savefile";
			
			f = new File(str);			
		}
		
		displayedpanels.add(back);
		menubuttons.add(back);
	}
	
	private void drawNewLoad(){
		for(JPanel jp : displayedpanels){
			menubuttons.remove(jp);
		}
		displayedpanels.clear();

		displayedpanels.add(newgame);
		menubuttons.add(newgame);
		
		JPanel spacegap = new JPanel();
		spacegap.setOpaque(false);
		displayedpanels.add(spacegap);
		menubuttons.add(spacegap);
		
		displayedpanels.add(loadgame);
		menubuttons.add(loadgame);

		
	}
	
	private void drawMenu(){
		
		menubuttons.setLayout(new BoxLayout(menubuttons, BoxLayout.Y_AXIS));
		menubuttons.setAlignmentX(CENTER_ALIGNMENT);
		menubuttons.setOpaque(false);
		
		title.setForeground(Color.WHITE);
		title.setAlignmentX(CENTER_ALIGNMENT);

		newgame.setBackground(Color.WHITE);
		newgame.setAlignmentX(CENTER_ALIGNMENT);
		newgame.addMouseListener(new MouseAdapter() {

			@Override
		    public void mouseClicked(MouseEvent e) {
				newgame.setBackground(Color.WHITE);
				
				int i = 1;
				boolean trollet = true;
				while(trollet == true){
					File file = new File(i + ".savefile");
					if(!file.exists()){
						trollet = false;
						gw.drawSpace(new SaveFile(i));
					}
					else if(i > 5){
						trollet = false;
						newgame.setBackground(Color.RED);
					}
					else{
						i++;
					}
				}							
		    }
		    
		    @Override
		    public void mouseEntered(MouseEvent e) {    	
		    	newgame.setBackground(Color.GREEN);
		    }
		    
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	newgame.setBackground(Color.WHITE);
		    }
		});
		newgame.add(new JLabel("New game"));
		
		loadgame.setBackground(Color.WHITE);
		loadgame.setAlignmentX(CENTER_ALIGNMENT);
		loadgame.addMouseListener(new MouseAdapter() {

			@Override
		    public void mouseClicked(MouseEvent e) {
				loadgame.setBackground(Color.WHITE);
				drawLoadMenu();
				revalidate();
				repaint();
		    }
		    
		    @Override
		    public void mouseEntered(MouseEvent e) {    	
		    	loadgame.setBackground(Color.GREEN);
		    }
		    
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	loadgame.setBackground(Color.WHITE);
		    }
		});
		loadgame.add(new JLabel("Load game"));
		
		back.setBackground(Color.WHITE);
		back.setAlignmentX(CENTER_ALIGNMENT);
		back.addMouseListener(new MouseAdapter() {

			@Override
		    public void mouseClicked(MouseEvent e) {
				back.setBackground(Color.WHITE);
				drawNewLoad();
				revalidate();
				repaint();
		    }
		    
		    @Override
		    public void mouseEntered(MouseEvent e) {    	
		    	back.setBackground(Color.GREEN);
		    }
		    
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	back.setBackground(Color.WHITE);
		    }
		});
		back.add(new JLabel("Back"));
		
		menubuttons.add(title);
		
		JPanel spacegap1 = new JPanel();
		spacegap1.setOpaque(false);
		menubuttons.add(spacegap1);
	
		displayedpanels.add(newgame);
		menubuttons.add(newgame);
		
		JPanel spacegap2 = new JPanel();
		spacegap2.setOpaque(false);
		displayedpanels.add(spacegap2);
		menubuttons.add(spacegap2);
		
		displayedpanels.add(loadgame);
		menubuttons.add(loadgame);
		
		add(menubuttons);
		
	}	
	
	private SaveFile loadFile(String str){
		
		SaveFile sf = null;

		FileInputStream f_in;

		try {
			f_in = new FileInputStream(str);


			ObjectInputStream obj_in = 	new ObjectInputStream(f_in);

			Object obj = null;
			try {
				obj = obj_in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			if (obj instanceof SaveFile)
			{
				sf = (SaveFile) obj;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sf;
	}

	class LoadButton{
		
		JPanel buttonpanel = new JPanel();
		
		SaveFile savf;
		
		public LoadButton(SaveFile sf){
			
			this.savf = sf;
			
			buttonpanel.setBackground(Color.WHITE);
			buttonpanel.setAlignmentX(CENTER_ALIGNMENT);
			buttonpanel.addMouseListener(new MouseAdapter() {

				@Override
			    public void mouseClicked(MouseEvent e) {
					buttonpanel.setBackground(Color.WHITE);
					
					gw.drawSpace(savf);
			    }
			    
			    @Override
			    public void mouseEntered(MouseEvent e) {    	
			    	buttonpanel.setBackground(Color.GREEN);
			    }
			    
			    @Override
			    public void mouseExited(MouseEvent e) {
			    	buttonpanel.setBackground(Color.WHITE);
			    }
			});
			buttonpanel.add(new JLabel(sf.getName()));
		}
		
		public JPanel getPanel(){
			return buttonpanel;
		}
	}
}
