package main;
import wave_stuff.ComplexWave;
import wave_stuff.SineWave;

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
		GraphicsMain.main(GraphicsMain.class.getName());	
	}
}