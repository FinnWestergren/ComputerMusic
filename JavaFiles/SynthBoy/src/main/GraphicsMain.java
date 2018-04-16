package main;

import java.util.ArrayList;

import graphical_assests.*;
import processing.core.*;
import sound.OscillatorManager;
import wave_stuff.WaveSet;
import wave_stuff.SineWave;
import wave_stuff.Vector2D;

public class GraphicsMain extends PApplet {

	/*
	 * Class for handling graphical output. Calls on Orbital System Manager for data
	 * to pass to each of its OrbitalSystemDrawers.
	 */

	public ArrayList<Drawable> drawList;
	public static final int FRAMERATE = 60;
	
	Visualizer polarDisplayTest,cartDisplayTest;
	private ArrayList<Controller> controllerSet = new ArrayList<Controller>();

	public void settings() {
		size(Main.displayWidth / 2, Main.displayHeight);
	}

	public void setup() {
		OscillatorManager o = new OscillatorManager(this);
		drawList = new ArrayList<Drawable>();
		frameRate(FRAMERATE);
		WaveSet carrierTestWave = new WaveSet(), modTestWave = new WaveSet(), mod2TestWave = new WaveSet();
		SineWave modulator = new SineWave(0, 0);
		SineWave mod2 = new SineWave(0,0);
		SineWave carrier = new SineWave(0, 0);
		carrier.setModulator(modulator);
		modulator.setModulator(mod2);
		
		controllerSet.add(new Slider("modulation2 amplitude", "+/- Hz", 0, 10000, false, false, 500, 20, new Vector2D(100, 50), this));
		controllerSet.add(new Slider("modulation2 frequency", "Hz", 0, 10000, false, false, 500, 20, new Vector2D(100, 100), this));
		controllerSet.add(new Slider("modulation amplitude", "+/- Hz", 0, 10000, false, false, 500, 20, new Vector2D(100, 150), this));
		controllerSet.add(new Slider("modulation frequency", "Hz", 0, 10000, false, false, 500, 20, new Vector2D(100, 200), this));
		controllerSet.add(new Slider("carrier amplitude", "", 0, 1.0f, true, false, 500, 20, new Vector2D(100, 250), this));
		controllerSet.add(new Slider("carrier frequency", "Hz", 0, 10000, false, false, 500, 20, new Vector2D(100, 300), this));
		
		mod2.setAmplitudeController(controllerSet.get(0));
		mod2.setFrequencyController(controllerSet.get(1));
		modulator.setAmplitudeController(controllerSet.get(2));
		modulator.setFrequencyController(controllerSet.get(3));
		carrier.setAmplitudeController(controllerSet.get(4));
		carrier.setFrequencyController(controllerSet.get(5));
		
		carrierTestWave.addOrbit(carrier);
		modTestWave.addOrbit(modulator);
		mod2TestWave.addOrbit(mod2);
		
		polarDisplayTest = new Visualizer(carrierTestWave, new Vector2D(width / 2, height*1.6f / 2), VisualizerType.POLAR, 500, 200,0, 500,6, this);
		cartDisplayTest = new Visualizer(carrierTestWave, new Vector2D(width / 2, height *1.6f/2), VisualizerType.CARTESIAN, 500, 200,0, 500,2, this);
		o.patchWaveSet(carrierTestWave);
	}

	public void mousePressed() {
		for(Controller s: controllerSet ) {
			s.onMousePressed();
		}
	}

	public void mouseReleased() {
		for(Controller s: controllerSet ) {
			s.onMouseReleased();
		}
	}

	public void draw() {
		polarDisplayTest.update();
		cartDisplayTest.update();
		fill(255);
		stroke(0);
		rect(0, 0, width, height);
		for(Controller s: controllerSet ) {
			s.draw();
		}
		noFill();
		text("FPS: " + (int) frameRate, 10, 10);
		stroke(255,100,160);
		strokeWeight(1.1f);
		polarDisplayTest.draw();
		stroke(0,255,100);
		cartDisplayTest.draw();
		
	}
}
