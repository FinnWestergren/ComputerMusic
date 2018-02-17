package Managers;


import java.util.ArrayList;

import Orbit.OrbitalSystem;
import drawers.*;
import processing.core.*;

public class GraphicsManager extends PApplet {
	
	/*
	 * Class for handling graphical output. Calls on Orbital System Manager for 
	 * data to pass to each of its OrbitalSystemDrawers.
	 */
	
	public ArrayList<OrbitalSystemDrawer> oSysDrawerList;
	private long time;
	public static final int FRAMERATE = 60;
	

	public void settings() {
		size(Main.displayWidth, Main.displayHeight);
		
	}

	public void setup() {
		oSysDrawerList = new ArrayList<OrbitalSystemDrawer>();
		for(OrbitalSystem oSystem : OrbitalSystemManager.systemList) {
			oSysDrawerList.add(new OrbitalSystemDrawer(oSystem, this));
		}
		
	}

	public void draw() {
		OrbitalSystemManager.incrementData(FRAMERATE);
		fill(255);
		rect(0, 0, width, height);
		noFill();
		stroke(1);
		for (OrbitalSystemDrawer OSD : oSysDrawerList) {
			OSD.drawSpline(width/2,height/2);
		}
		
	}
}
