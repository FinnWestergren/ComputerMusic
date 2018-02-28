package Managers;
import Orbit.Orbit;
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
	 * I intend to make this shit clean.
	 */
	
	static int displayWidth = 1400;
	
	static int displayHeight = 700;
	
	public static void main(String[] args) {
		if(args.length != 0) {
		displayWidth = Integer.parseInt(args[0]);
		displayHeight = Integer.parseInt(args[1]);
		}
		Orbit modulator = new Orbit(100,10);
		OrbitalSystem o = new OrbitalSystem();
		o.addOrbit(new Orbit(100, 25,modulator));
		//o.addOrbit(new Orbit(100, 5));
		//o.addOrbit(new Orbit(100,75.2));
		//o.addOrbit(new Orbit(100, 75));
		//o.addOrbit(new Orbit(100,150));
		//o.addOrbit(new Orbit(100, 50, modulator));
		//o.addOrbit(new Orbit(100, 70.4, modulator));
		//o.addOrbit(new Orbit(100, 160));
		
//		o.addOrbit(6.25, 160);
		OrbitalSystemManager.addSystem(o);
		GraphicsManager.main(GraphicsManager.class.getName());
	}
}