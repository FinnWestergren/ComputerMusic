import processing.core.*;
public class Visualizer extends PApplet{
	
	public static int width, height, maxRadius;
	
	public static void main(String[] args) {
		width = Integer.parseInt(args[0]);
		height = Integer.parseInt(args[1]);
		PApplet.main("Visualizer");
	}
	
	public void settings() {
		size(width,height);
	}
	
	public void setup() {
		
	}
}
