package Managers;
import Orbit.OrbitalSystem;

public class Main {
	
	/**
	 * ---------------------------------------------------------------
	 * Finn Westergren 
	 * Independent Study on Computer Music
	 * Spring 2018 
	 * Skidmore College
	 * ----------------------------------------------------------------
	 *
	 * I think this might be my last java project if I can help it. I'm tired of having to 
	 * create a class for simple data tables. I wish that structs or aggregates were in play here. 
	 * Nonetheless,
	 * I aim to make this shit clean.
	 */
	
	static int displayWidth = 700;
	static int displayHeight = 700;
	
	public static void main(String[] args) {
		if(args.length != 0) {
		displayWidth = Integer.parseInt(args[0]);
		displayHeight = Integer.parseInt(args[1]);
		}
		OrbitalSystem o = new OrbitalSystem();
		o.addOrbit(100, 40);
		o.addOrbit(100, 80);
		o.addOrbit(100, 160.3);
		
//		o.addOrbit(6.25, 160);
		OrbitalSystemManager.addSystem(o);
		GraphicsManager.main(GraphicsManager.class.getName());
	}
}
