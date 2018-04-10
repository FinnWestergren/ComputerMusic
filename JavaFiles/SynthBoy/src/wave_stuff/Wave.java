package wave_stuff;

import ddf.minim.ugens.Oscil;
import ddf.minim.ugens.Waves;
import graphical_assests.Controller;
import listeners.ControllerListener;

public class Wave implements ControllerListener{

	protected float amplitude, frequency;
	protected Wave modulator = null;
	protected Controller freqController, ampController;
	private Oscil osc;

	public Wave(Vector2D polarVect) {
		this(polarVect.dX, polarVect.dY);
	}

	public Wave(float amplitude, float frequency) {
		this.amplitude = amplitude;
		this.frequency = frequency;
		osc = new Oscil(frequency, amplitude, Waves.SINE);
		
	}

	public Oscil getOsc() {
		return osc;
	}

	public float getFrequency() {
		if (freqController == null)
			return frequency;
		return freqController.getCurrentValue();
	}

	public float getBaseFrequency() {
		return frequency;
	}

	public float getAmplitude() {
		if (ampController == null)
			return amplitude;
		return ampController.getCurrentValue();
	}

	public void setModulator(Wave modulator) {
		this.modulator = modulator;
		modulator.getOsc().offset.setLastValue(frequency);
		modulator.getOsc().patch(osc.frequency);
	}

	public Vector2D getPolarLocation(float time) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFrequencyController(Controller c) {
		this.freqController = c;
		c.setSignature("freq");
		c.setContollerListener(this);
	}

	public void setAmplitudeController(Controller c) {
		this.ampController = c;
		c.setSignature("amp");
		c.setContollerListener(this);
	}

	public Wave getModulator() {
		return modulator;
	}

	@Override
	public void onChange(String sig) {
		if(sig.equals("freq"))
		{
			setFrequency(freqController.getCurrentValue());
		}
		if(sig.equals("amp"))
		{
			setAmplitude(ampController.getCurrentValue());
		}
	}

	private void setFrequency(float freq) {
		this.frequency = freq;
		this.osc.setFrequency(frequency);
		if(modulator != null)
		modulator.getOsc().offset.setLastValue(frequency);
	}
	
	private void setAmplitude(float amp) {
		this.amplitude = amp;
		this.osc.setAmplitude(amplitude);
	}

}
