package wave_stuff;

import java.util.ArrayList;

public class WaveSet {
	private ArrayList<Wave> waveList = new ArrayList<Wave>();

	public ArrayList<Wave> getWaveList() {
		return waveList;
	}

	public void setWaveList(ArrayList<Wave> waveList) {
		this.waveList = waveList;
	}

	private float minFreq = Float.MAX_VALUE;

	public WaveSet() {
	}

	public void addOrbit(Wave o) {
		waveList.add(o);
	}

	public Vector2D getPolarLocation(float time) {
		Vector2D data = new Vector2D();
		minFreq = Float.MAX_VALUE;
		for (Wave o : waveList) {
			if (o.getFrequency() < minFreq)
				minFreq = o.getFrequency();
			data.add(new Vector2D((float) Math.cos(2 * Math.PI * time  * o.getFrequency()) * o.getAmplitude(), o.getCurrentAmplitude(time)));
		}
		return data;
	}

	public Wave getOrbit(int i) {
		// TODO Auto-generated method stub
		return waveList.get(i);
	}

	public float period() {
		return 1 / minFreq;
	}

}
