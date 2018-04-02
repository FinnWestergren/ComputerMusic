package sound;

import ddf.minim.ugens.Oscil;
import wave_stuff.Wave;
import wave_stuff.WaveSet;

public class Oscillator {
	private WaveSet waveSet;
	private Oscil osc;
	public Oscillator(WaveSet waveSet) {
		this.waveSet = waveSet;
		osc = new Oscil(0, 0, null);
		for(Wave w : waveSet.getWaveList()) {
			
		}
	}

}
