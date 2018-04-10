package sound;


import ddf.minim.AudioOutput;
import ddf.minim.Minim;
import processing.core.PApplet;
import wave_stuff.Wave;
import wave_stuff.WaveSet;

public class OscillatorManager {

	private Minim minim;
	private AudioOutput output;

	
	public OscillatorManager(PApplet p) {
		minim = new Minim(p);
		output = minim.getLineOut();
	}
	public void patchWaveSet(WaveSet waveSet) {
		for (Wave w : waveSet.getWaveList()) {
			w.getOsc().patch(output);
		}
	}
}
