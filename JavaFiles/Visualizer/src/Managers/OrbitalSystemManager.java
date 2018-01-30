package Managers;

import java.util.ArrayList;
import Orbit.OrbitalSystem;

public class OrbitalSystemManager {
	
	public static ArrayList<OrbitalSystem> systemList = new ArrayList<OrbitalSystem>();
	public static int systemResolution;
	
	public static void addSystem() {
		systemList.add(new OrbitalSystem());
	}

	public static void addSystem(OrbitalSystem o) {
		systemList.add(o);
	}

}
