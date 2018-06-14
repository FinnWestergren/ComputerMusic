package wave_stuff;

import ddf.minim.ugens.Oscil;
import ddf.minim.ugens.Waves;
import graphical_assests.Controller;
import listeners.ControllerListener;

public class Wave implements ControllerListener {

	protected float amplitude, frequency, baseFrequency;
	protected Wave modulator = null;
	protected Controller freqController, ampController;
	private Oscil osc;
	private boolean patched, latched;
	private Wave modulating;
	private float modRatio;

	public Wave(Vector2D polarVect) {
		this(polarVect.dX, polarVect.dY);
	}

	public Wave(float amplitude, float frequency) {
		this.amplitude = amplitude;
		this.baseFrequency = frequency;
		this.frequency = frequency;
		osc = new Oscil(frequency, amplitude, Waves.SINE);

	}
	
	public void latch() {
		latched = true;
	}
	
	public void unlatch() {
		latched = false;
	}

	public Oscil getOsc() {
		return osc;
	}

	public float getFrequency() {
		return frequency;
	}

	public float getBaseFrequency() {
		return baseFrequency;
	}

	public float getAmplitude() {
		return amplitude;
	}

	public void setModulating(Wave modulating) {
		this.modulating = modulating;
	}

	public void setModulator(Wave modulator) {
		this.modulator = modulator;
		modulator.setModulating(this);
		modulator.getOsc().offset.setLastValue(frequency);
		modulator.getOsc().patch(osc.frequency);
	}

	public float getCurrentAmplitude(float time) {
		// TODO Auto-generated method stub
		return 0;
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
		if (sig.equals("freq")) {
			setFrequency(freqController.getCurrentValue());
		}
		if (sig.equals("amp")) {
			setAmplitude(ampController.getCurrentValue());
		}

		if (modulating != null) {
			modulating.onModulatorChange();
		}
		
		if(modulator != null) {
			modulator.onModulatingChange();
		}
	}

	private void onModulatingChange() {
//		if(latched) {
//			unlatch();
//			setFrequency(modulating.getFrequency() * modRatio);
//			latch();
//		}
	}

	private void setFrequency(float freq) {
		if(modulating != null && !latched) {
			modRatio = getFrequency() / modulating.getFrequency();
		}
		this.frequency = freq;
		this.osc.setFrequency(frequency);
		if (modulator != null) {
			modulator.getOsc().offset.setLastValue(frequency);
			// if(frequency == 0.0f) setAmplitude(0);
		}
	}

	private void setAmplitude(float amp) {
		this.amplitude = amp;
		this.osc.setAmplitude(amplitude);
	}

	private void onModulatorChange() {
		if (modulator == null)
			return;
		if (modulator.getFrequency() == 0) {
			modulator.getOsc().unpatch(osc);
			setFrequency(frequency);
			patched = false;
		} else if (!patched) {
			modulator.getOsc().offset.setLastValue(frequency);
			modulator.getOsc().patch(osc.frequency);
			patched = true;
		}
	}

	public float getCurrentFrequency(float time) {
		// TODO Auto-generated method stub
		return 0;
	}

}
