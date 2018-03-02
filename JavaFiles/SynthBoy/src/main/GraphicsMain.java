package main;

import java.util.ArrayList;

import calculations.ComplexWave;
import calculations.SineWave;
import calculations.Vector2D;
import graphical_assests.*;
import processing.core.*;

public class GraphicsMain extends PApplet {

	/*
	 * Class for handling graphical output. Calls on Orbital System Manager for data
	 * to pass to each of its OrbitalSystemDrawers.
	 */

	public ArrayList<Drawable> drawList;
	public static final int FRAMERATE = 60;
	Visualizer cD;
	Slider s;

	public void settings() {
		size(Main.displayWidth / 2, Main.displayHeight);
	}

	public void setup() {
		drawList = new ArrayList<Drawable>();
		frameRate(FRAMERATE);
		ComplexWave cW = new ComplexWave();
		SineWave m = new SineWave(10, 1);
		SineWave o = new SineWave(50, 25,m);
		cW.addOrbit(o);
		//cW.addOrbit(new SineWave(100, 50));
		cD = new Visualizer(cW, new Vector2D(width / 2, height / 2), VisualizerType.CARTESIAN, 500, 200, this);
		s = new Slider("slide", "Hz", 0.1f, 25, true, false, 500, 20, new Vector2D(100, 100), this);
		m.setRadiusController(s);
	}

	public void mousePressed() {
		s.onMousePressed();
	}

	public void mouseReleased() {
		s.onMouseReleased();
	}

	public void draw() {

		cD.updatePoints(500, 0);
		fill(255);
		stroke(0);
		
		rect(0, 0, width, height);
		s.draw();
		noFill();
		
		text("FPS: " + (int) frameRate, 10, 10);
		stroke(255,0,0);
		strokeWeight(1.1f);
		cD.draw();
		
	}
}
