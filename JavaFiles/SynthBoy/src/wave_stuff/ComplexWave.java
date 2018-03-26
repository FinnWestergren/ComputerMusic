package wave_stuff;

import java.util.ArrayList;


public class ComplexWave {
	private ArrayList<SineWave> orbitList = new ArrayList<SineWave>();
	private double minFreq = Double.MAX_VALUE;
	private double maxAmp  = Double.MIN_NORMAL;
	public ComplexWave() {
	}

	public void addOrbit(SineWave o) {
		orbitList.add(o);
		if(o.getFrequency() < minFreq) minFreq = o.getFrequency();
		if(o.getRadius()> maxAmp) maxAmp = o.getFrequency();
	}
	
	public Vector2D getPolarLocation(double time) {
		Vector2D data = new Vector2D();
		for (SineWave o : orbitList) {
			data.add(o.getPolarLocation(time).scale(1/(2.0*maxAmp)));
		}
		return data;
	}
	
	public SineWave getOrbit(int i) {
		// TODO Auto-generated method stub
		return orbitList.get(i);
	}

	public double period() {
		return 1/minFreq;
	}

}
