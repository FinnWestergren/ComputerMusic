package main;

import java.util.ArrayList;

import graphical_assests.*;
import processing.core.*;
import wave_stuff.ComplexWave;
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
		drawList = new ArrayList<Drawable>();
		frameRate(FRAMERATE);
		ComplexWave carrierTestWave = new ComplexWave(), modTestWave = new ComplexWave();
		
		
		SineWave modulator = new SineWave(0, 200);
		SineWave carrier = new SineWave(800, 800, modulator);
		
		controllerSet.add(new Slider("modulation amplitude", "+/- Hz", 0, 100, false, false, 500, 20, new Vector2D(100, 50), this));
		controllerSet.add(new Slider("modulation frequency", "Hz", 10, 100, false, false, 500, 20, new Vector2D(100, 100), this));
		controllerSet.add(new Slider("carrier amplitude", "", 1, 30, false, false, 500, 20, new Vector2D(100, 150), this));
		controllerSet.add(new Slider("carrier frequency", "Hz", 10, 100, false, false, 500, 20, new Vector2D(100, 200), this));
		
		
		modulator.setRadiusController(controllerSet.get(0));
		modulator.setFrequencyController(controllerSet.get(1));
		carrier.setRadiusController(controllerSet.get(2));
		carrier.setFrequencyController(controllerSet.get(3));
		
		carrierTestWave.addOrbit(carrier);
		modTestWave.addOrbit(modulator);
		polarDisplayTest = new Visualizer(carrierTestWave, new Vector2D(width / 2, height*1.3 / 2), VisualizerType.POLAR, 500, 200,0, 500,3, this);
		cartDisplayTest = new Visualizer(carrierTestWave, new Vector2D(width / 2, height*1.3 / 2), VisualizerType.CARTESIAN, 500, 200,0, 500,4, this);
		
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
		stroke(255,0,0);
		strokeWeight(1.1f);
		polarDisplayTest.draw();
		stroke(0,255,100);
		cartDisplayTest.draw();
		
	}
}
