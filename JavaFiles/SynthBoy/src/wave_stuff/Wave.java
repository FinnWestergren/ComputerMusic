package wave_stuff;

import graphical_assests.Controller;

public class Wave {

	protected float amplitude, frequency;
	protected Wave modulator = null;
	protected Controller freqController, ampController;

	public Wave(Vector2D polarVect) {
		this.amplitude = polarVect.dX;
		this.frequency = polarVect.dY;
	}

	public Wave(float amplitude, float frequency) {
		this.amplitude = amplitude;
		this.frequency = frequency;
	}

	public float getFrequency() {
		if (freqController == null)
			return frequency;
		return freqController.getCurrentValue();
	}

	public float getAmplitude() {
		if (ampController == null)
			return amplitude;
		return ampController.getCurrentValue();
	}

	public void setModulator(Wave modulator) {
		this.modulator = modulator;
	}

	public Vector2D getPolarLocation(float time) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFrequencyController(Controller c) {
		this.freqController = c;
	}

	public void setAmplitudeController(Controller c) {
		this.ampController = c;
	}

}
