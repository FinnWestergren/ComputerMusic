package Orbit;

import java.util.ArrayList;
import java.util.LinkedList;

import Managers.OrbitalSystemManager;

public class OrbitalSystem {
	private ArrayList<Orbit> orbitList = new ArrayList<Orbit>();
	private int resolution;
	private LinkedList<Vector2D> controlPoints = new LinkedList<Vector2D>();
	private double time = 0;
	
	public OrbitalSystem() {
		update();
	}

	public void addOrbit(double radius, double frequency) {
		orbitList.add(new Orbit(radius, frequency));
		update();
	}
	
	private void update() {
		//TODO more Advanced calculation
		resolution = 2000;
	}

	public LinkedList<Vector2D> getControlPoints() {
		return controlPoints;
	}
	
	public void getNextControlPoint(int rate) {
		time += 1.0/rate;
		Vector2D data = new Vector2D();
		for(Orbit o : orbitList) {
			data.add(o.getSatelliteLocation(time));	
		}
		controlPoints.add(data);
		if(controlPoints.size() > resolution) {
			controlPoints.removeFirst();
		}
	}
}
