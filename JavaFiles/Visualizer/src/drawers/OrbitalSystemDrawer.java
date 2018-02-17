package drawers;

import Orbit.OrbitalSystem;
import Orbit.Vector2D;
import processing.core.*;

public class OrbitalSystemDrawer {
	
	/*
	 * Each OSD is dedicated to one orbital system. The reason I separated them from their respective OrbitalSystem is simply
	 * for organization. I wanted to let the orbits to contain only data points and their animations to be separate.
	 */

	private OrbitalSystem oSystem;
	private PApplet p;

	public OrbitalSystemDrawer(OrbitalSystem oSystem, PApplet p) {
		this.oSystem = oSystem;
		this.p = p;
	}

	// draws the curve given by the control points given by the orbitalSystems
	public void drawSpline(float centerX, float centerY) {
		p.pushMatrix();
		p.translate(centerX, centerY);
		p.smooth();
		p.beginShape();

		if (!oSystem.getControlPoints().isEmpty()) {
			java.util.Iterator<Vector2D> iterator = oSystem.getControlPoints().iterator();
			while (iterator.hasNext()) {
				Vector2D v = iterator.next();
				p.curveVertex((float) v.getdX(), (float) v.getdY());
			}
		} else {
			p.fill(0);
			p.textAlign(p.CENTER);
			p.text("Empty Set", 0, 0);
		}
		p.endShape();
		p.popMatrix();
	}

}
