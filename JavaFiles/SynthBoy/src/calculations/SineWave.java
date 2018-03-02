package calculations;
import graphical_assests.Controller;
public class SineWave {

	private double radius, frequency;
	private SineWave modulator = null;
	private Controller freqController, radController;

	public SineWave(Vector2D polarVect) {
		this.radius = polarVect.dX;
		this.frequency = polarVect.dY;
	}

	public SineWave(double radius, double frequency) {
		super();
		this.radius = radius;
		this.frequency = frequency;
	}

	public SineWave(double radius, double frequency, SineWave modulator) {
		super();
		this.radius = radius;
		this.frequency = frequency;
		this.modulator = modulator;
	}

	// used to find the tip of the arm (or the satellite, if you think about
	// planetary motion)
	// extending from the center to the edge of the radius given a current time in
	// the period
	// actually returns Cartesian vector, but used for the polar display.
	public Vector2D getPolarLocation(double time) {

		double alphaT = Math.PI * 2 * time * getFrequency();
		double modulation = 0;
		if (modulator != null) {
			double betaT = Math.PI * 2 * time * getFrequency();
			modulation = modulator.getRadius() / modulator.getFrequency() * Math.sin(betaT);
		}

		double dX = Math.cos(alphaT) * getRadius();
		double dY = Math.sin(alphaT + modulation) * getRadius();

		return new Vector2D(dX, dY);
	}

	public double getFrequency() {
		if(freqController == null) 
			return frequency;
			return freqController.getCurrentValue();
	}
	
	public double getRadius() {
		if(radController == null) 
			return radius;
			return radController.getCurrentValue();
	}

	public Vector2D getCartesianLocation(double time) {

		double alphaT = Math.PI * 2 * time * getFrequency();
		double modulation = 0;
		if (modulator != null) {
			double betaT = Math.PI * 2 * time * getFrequency();
			modulation = modulator.getRadius() / modulator.getFrequency() * Math.sin(betaT);
		}
		double dX = time;
		double dY = Math.sin(alphaT + modulation) * getRadius();
		System.out.println((new Vector2D(dX, dY)).toString());
		return new Vector2D(dX, dY);
	}

	public void setFrequencyController(Controller c) {
		this.freqController = c;
	}
	
	public void setRadiusController(Controller c) {
		this.radController = c;
	}

	public SineWave getModulator() {
		return modulator;
	}

}