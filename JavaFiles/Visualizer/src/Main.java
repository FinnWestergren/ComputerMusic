public class Main {
	
	/**
	 * ---------------------------------------------------------------
	 * Finn Westergren 
	 * Independent Study: Computer Music
	 * Spring 2018 
	 * Skidmore College
	 * ----------------------------------------------------------------
	 *
	 * I think this might be my last java project if I can help it. I'm tired of having to 
	 * create a class for simple data tables. I wish that structs or aggregates were in play here. 
	 * Nonetheless,
	 * I aim to make this shit clean.
	 */
	
	static int displayWidth = 300;
	static int displayHeight = 300;
	
	public static void main(String[] args) {
		if(args.length != 0) {
		displayWidth = Integer.parseInt(args[0]);
		displayHeight = Integer.parseInt(args[1]);
		}
		GraphicsControl.main(GraphicsControl.class.getName());
	}
	
}
