package sound;
import ddf.minim.*;
import ddf.minim.ugens.*;
import main.SoundMain;
import wave_stuff.Wave;
import wave_stuff.WaveSet;

public class Sound_Utilities {

	public static void patchOscilator(Oscil o) {
		o.patch(SoundMain.out);
	}
	
}
