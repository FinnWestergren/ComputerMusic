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
	public Vector2D getPolarLocation(float time) {

		float alphaT = (float) (Math.PI * 2 * time * getFrequency());
		float mod = 0;
		if (modulator != null && modulator.getFrequency() != 0) {
			float betaT = (float) (Math.PI * 2 * time * modulator.getFrequency());
			mod = (float) ((modulator.getAmplitude()/modulator.getFrequency()) * Math.sin(betaT));
		}

		float dX = (float) (Math.cos(alphaT ) * getAmplitude());
		float dY = (float) (Math.sin(alphaT + mod) * getAmplitude());

		return new Vector2D(dX, dY);
	}





}