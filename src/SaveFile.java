import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaveFile implements Serializable{

	private static final long serialVersionUID = 429423423358L;
	
	int savenum;
	String name;
	List<Planet> discoveredplanets = new ArrayList<Planet>();
//	List<Hero> crew = new ArrayList<Hero>(); 

	int chunk32x, chunk32y;

	int chunkx, chunky;

	double posX, posY;
	
	boolean isNew = true;

	public SaveFile(int i){

		this.savenum = i;
		name =  System.getProperty("user.name") + " " + i;
		
		save();
	}
		
	private void save(){
		try {
			// Write to disk with FileOutputStream
			FileOutputStream f_out = new FileOutputStream(savenum + ".savefile");


			// Write object with ObjectOutputStream
			ObjectOutputStream obj_out = new ObjectOutputStream (f_out);

			obj_out.writeObject(this);

			obj_out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isNew(){
		return isNew;
	}

	public void isNew(boolean isNew){
		this.isNew = isNew;
	}
	
	public int getChunk32X(){
		return chunk32x;
	}
	
	public int getChunk32Y(){
		return chunk32y;
	}
	
	public int getChunkX(){
		return chunkx;
	}
	
	public int getChunkY(){
		return chunky;
	}
	
	public double getPosX(){
		return posX;
	}
	
	public double getPosY(){
		return posY;
	}
	
}
