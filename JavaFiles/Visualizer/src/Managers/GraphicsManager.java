package Managers;


import java.util.ArrayList;

import Orbit.OrbitalSystem;
import Orbit.Vector2D;
import drawers.*;
import processing.core.*;
import utilities.OrbitUtilities;

public class GraphicsManager extends PApplet {
	
	/*
	 * Class for handling graphical output. Calls on Orbital System Manager for 
	 * data to pass to each of its OrbitalSystemDrawers.
	 */
	
	public ArrayList<Drawable> drawList;
	public static final int FRAMERATE = 30;
	

	public void settings() {
		size(Main.displayWidth, Main.displayHeight);
	}

	public void setup() {
		drawList = new ArrayList<Drawable>();
		for(OrbitalSystem oSystem : OrbitalSystemManager.systemList) {
			drawList.add(new OrbitalSystemDrawer(oSystem, this, new Vector2D(width/4, height/2)));
			drawList.add(new WaveDrawer(oSystem,this, new Vector2D(width/2, height/2)));
			drawList.add(new Slider("Frequency", "Hz", 0, 20000, true, false, width/4, 10, new Vector2D(width/4, height/5), this));
		}
		OrbitUtilities.linkAmpControllerToOrbit((Controller)drawList.get(2),OrbitalSystemManager.systemList.get(0).getOrbit(0).getModulator());
		frameRate(FRAMERATE);
	}

	public void draw() {
		OrbitalSystemManager.incrementData(FRAMERATE);
		fill(255);
		rect(0, 0, width, height);
		noFill();
		stroke(1);
		for(Drawable d : drawList) {
			d.draw();
		}
		text("FPS: " + frameRate,10,10);
	}
}
