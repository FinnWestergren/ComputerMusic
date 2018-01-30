package Orbit;

import java.util.ArrayList;

import Managers.OrbitalSystemManager;

public class OrbitalSystem {
	private ArrayList<Orbit> orbitList = new ArrayList<Orbit>();
	private double fundamentalFreq = Double.MAX_VALUE;
	
	public OrbitalSystem() {
	}
	
	public void addOrbit(double radius, double frequency) {
		orbitList.add(new Orbit(radius, frequency));
		//TODO make more sophisticated calculation for fundamental
		if(frequency < fundamentalFreq) fundamentalFreq = frequency;
	}
	
	public Vector2D[] controlPoints() {
		Vector2D[] output = new Vector2D[OrbitalSystemManager.systemResolution];
		double increment = 1/(OrbitalSystemManager.systemResolution * fundamentalFreq);
		for(int r = 0; r < OrbitalSystemManager.systemResolution; r++) {
			output[r] = getNext(r * increment);
		}
		return output;
	}
	
	private Vector2D getNext(double time) {
		Vector2D data = new Vector2D();
		for(Orbit o : orbitList) {
			data.add(o.getSatelliteLocation(time));	
		}
		return data;
	}
}
