package calculations;

import java.util.ArrayList;


public class ComplexWave {
	private ArrayList<SineWave> orbitList = new ArrayList<SineWave>();

	public ComplexWave() {
	}

	public void addOrbit(SineWave o) {
		orbitList.add(o);
	}
	
	public Vector2D getPolarLocation(double time) {
		Vector2D data = new Vector2D();
		for (SineWave o : orbitList) {
			data.add(o.getPolarLocation(time));
		}
		return data;
	}
	
	public SineWave getOrbit(int i) {
		// TODO Auto-generated method stub
		return orbitList.get(i);
	}

	public double period() {
		double minFreq = Double.MAX_VALUE;
		for(SineWave o : orbitList) {
			if(o.getFrequency() < minFreq) minFreq = o.getFrequency();
		} 
		
		return 1/minFreq;
	}

}
