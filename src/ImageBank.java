import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class ImageBank {

	public static Image getImage(String path){
		Image img = null;
		
		try {
			img = ImageIO.read(new File(System.getProperty("user.dir") + "/" + path + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}
}
