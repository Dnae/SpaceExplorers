import java.util.Random;

public final class SystemVariables {

	static int height;
	static int width;
	
	public SystemVariables(int x, int y){
		
		width = x;
		height = y;
	}
	
	public static int getHeight(){
		return height;
	}
	
	public static int getWidth(){
		return width;
	}
	
	public static int getRandom(int range){
		
		Random random = new Random();
		
		return random.nextInt(range);
	}
	
	public static int getRandomFromSeed(int x, int y, int range){
		
		int seed = ((x + y)*(x + y + 1) + 2 * y) * 10000;
		
		Random random = new Random(seed);

		return random.nextInt(range);
	}
	
	public static int getRandomFromSeed(int x, int y, int z, int range){
		
		int seed = ((x + y)*(x + y + 1) + 2 * y + z) * 10000;
		
		Random random = new Random(seed);
	
		return random.nextInt(range);
	}

}
