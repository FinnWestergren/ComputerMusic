package utilities;

import Orbit.Orbit;
import drawers.Controller;

public class OrbitUtilities {
	
	public static void linkFreqControllerToOrbit(Controller c, Orbit o) {
		o.setFrequencyController(c);
	}
	public static void linkAmpControllerToOrbit(Controller c, Orbit o) {
		o.setRadiusController(c);
	}
}
