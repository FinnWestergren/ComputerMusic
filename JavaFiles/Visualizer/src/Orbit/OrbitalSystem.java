package Orbit;

import java.util.ArrayList;
import java.util.LinkedList;

import Managers.OrbitalSystemManager;

public class OrbitalSystem {
	private ArrayList<Orbit> orbitList = new ArrayList<Orbit>();
	private int polarRes,cartRes;
	private LinkedList<Vector2D> polarControlPoints = new LinkedList<Vector2D>();
	private LinkedList<Vector2D> cartesianControlPoints = new LinkedList<Vector2D>();
	private double time = 0, offset;

	public OrbitalSystem() {
		update();
	}

	public void addOrbit(Orbit o) {
		orbitList.add(o);
		update();
	}

	private void update() {
		// TODO more Advanced calculation
		polarRes = 100;
		cartRes = 500;
	}

	public LinkedList<Vector2D> getPolarControlPoints() {
		return polarControlPoints;
	}

	public LinkedList<Vector2D> getCartesianControlPoints() {
		return cartesianControlPoints;
	}
	
	public void update(int rate) {
		time += 1.0 / rate;
	}

	public void getNextPolarControlPoint() {
		
		Vector2D data = new Vector2D();
		for (Orbit o : orbitList) {
			data.add(o.getPolarLocation(time));
		}
		polarControlPoints.add(data);
		if (polarControlPoints.size() > polarRes || (data.equals(polarControlPoints.get(0)) && time > 100)) {
			polarControlPoints.removeFirst();
		}
	}

	public void getNextCartesianControlPoint() {
		offset = 2000 * time % cartRes;
		Vector2D data = new Vector2D();
		for (Orbit o : orbitList) {
			data.add(o.getCartesianLocation(time));
		}
		data.add(new Vector2D(offset, 0));
		cartesianControlPoints.add(data);
		if (cartesianControlPoints.size() > cartRes) {
			cartesianControlPoints.removeFirst();
		} 
		
	}

	public Orbit getOrbit(int i) {
		// TODO Auto-generated method stub
		return orbitList.get(i);
	}

}
