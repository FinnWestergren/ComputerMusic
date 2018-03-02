package main;


import java.util.ArrayList;

import calculations.ComplexWave;
import calculations.SineWave;
import calculations.Vector2D;
import graphical_assests.*;
import processing.core.*;


public class GraphicsMain extends PApplet {
	
	/*
	 * Class for handling graphical output. Calls on Orbital System Manager for 
	 * data to pass to each of its OrbitalSystemDrawers.
	 */
	
	public ArrayList<Drawable> drawList;
	public static final int FRAMERATE = 60;
	Visualizer cD;

	public void settings() {
		size(Main.displayWidth / 2, Main.displayHeight);
	}

	public void setup() {
		drawList = new ArrayList<Drawable>();
		frameRate(FRAMERATE);
		ComplexWave cW = new ComplexWave();
		cW.addOrbit(new SineWave(100,120));
		cW.addOrbit(new SineWave(100, 80));
		cD = new Visualizer(cW,new Vector2D(width/2,height/2), VisualizerType.POLAR,200,40, this);
		
	}

	public void draw() {
		cD.updatePoints(300, 0);
		fill(255);
		rect(0, 0, width, height);
		noFill();
		stroke(1);
		cD.draw();
		for(Drawable d : drawList) {
			d.draw();
		}
		fill(0);
		text("FPS: " + (int)frameRate,10,10);
	}
}
