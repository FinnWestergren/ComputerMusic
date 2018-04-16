package wave_stuff;

public class SineWave extends Wave {

	
	public SineWave(Vector2D polarVect) {
		super(polarVect);
		
	}

	public SineWave(float radius, float frequency) {
		super(radius,frequency);
		
	}



	// used to find the tip of the arm (or the satellite, if you think about
	// planetary motion)
	// extending from the center to the edge of the radius given a current time in
	// the period
	// actually returns Cartesian vector, but used for the polar display.
	@Override
	public float getCurrentAmplitude(float time) {
		float tau = (float) (Math.PI * 2);
		float alphaT = tau * frequency * time;
		float modIndex = 0;
		float betaT = 0;
		if(modulator != null) {

			betaT = tau * modulator.getCurrentFrequency(time) * time;
			if(modulator.getCurrentFrequency(time) != 0)
				modIndex = (modulator.getCurrentAmplitude(time)/modulator.getCurrentFrequency(time));
		}
		return (float)(getAmplitude() * Math.sin(alphaT + modIndex * Math.sin(betaT)));
	}
	
	@Override
	public float getCurrentFrequency(float time) {
		if(modulator == null) return frequency;
		return frequency + modulator.getCurrentAmplitude(time);
	}
}