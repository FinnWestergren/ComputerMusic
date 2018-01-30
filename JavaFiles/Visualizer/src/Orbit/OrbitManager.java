package Orbit;

import java.util.ArrayList;

public class OrbitManager {
	private static ArrayList<Orbit> orbitList = new ArrayList<Orbit>();
	private static double time = 0.0;
	private static double increment = 0.0;
	
	public static void setPace(double pace) {
		increment = pace;
	}
	
	public static void addOrbit(double radius, double frequency) {
		orbitList.add(new Orbit(radius, frequency));
	}
	
	public static Vector2D getNext( ) {
		Vector2D data = new Vector2D();
		time += increment;
		for(Orbit o : orbitList) {
			data.add(o.getSatelliteLocation(time));	
		}

		return data;
	}
}
