package Managers;

import java.util.ArrayList;
import Orbit.OrbitalSystem;

public class OrbitalSystemManager {
	
	public static ArrayList<OrbitalSystem> systemList = new ArrayList<OrbitalSystem>();
	public static OrbitalSystem test;
	public static double systemResolution = 2;
	
	public static void addSystem() {
		systemList.add(new OrbitalSystem());
	}

	public static void addSystem(OrbitalSystem o) {
		systemList.add(o);
	}
	//method used to add the next n points to each respective orbit in one frame.
	public static void incrementData(int frameRate) {
		int increment = (int) (frameRate * systemResolution);
		for(int i =0; i < increment ; i ++)
		for(OrbitalSystem o:systemList ) {
			o.getNextControlPoint(frameRate * increment);
		}
	}
}
