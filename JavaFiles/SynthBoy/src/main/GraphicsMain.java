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
	
	Visualizer carrierDisplay,modDisplay;
	Slider testSlider;

	public void settings() {
		size(Main.displayWidth / 2, Main.displayHeight);
	}

	public void setup() {
		drawList = new ArrayList<Drawable>();
		frameRate(FRAMERATE);
		ComplexWave carrierTestWave = new ComplexWave(), modTestWave = new ComplexWave();
		
		
		SineWave modulator = new SineWave(500, 100);
		SineWave carrier = new SineWave(500, 500, modulator);
		
		testSlider = new Slider("slide", "Hz", 0.1f, 10000, false, false, 500, 20, new Vector2D(100, 100), this);
		modulator.setRadiusController(testSlider);
		
		carrierTestWave.addOrbit(carrier);
		modTestWave.addOrbit(modulator);
		carrierDisplay = new Visualizer(carrierTestWave, new Vector2D(width / 2, height / 2), VisualizerType.CARTESIAN, 500, 200, 500, this);
		modDisplay = new Visualizer(modTestWave, new Vector2D(width / 2, height / 2), VisualizerType.CARTESIAN, 500, 200, 500, this);
		
	}

	public void mousePressed() {
		testSlider.onMousePressed();
	}

	public void mouseReleased() {
		testSlider.onMouseReleased();
	}

	public void draw() {
		modDisplay.update();
		carrierDisplay.update();
		fill(255);
		stroke(0);
		rect(0, 0, width, height);
		testSlider.draw();
		noFill();
		text("FPS: " + (int) frameRate, 10, 10);
		stroke(255,0,0);
		strokeWeight(1.1f);
		carrierDisplay.draw();
		stroke(0,255,100);
		modDisplay.draw();
		
	}
}
