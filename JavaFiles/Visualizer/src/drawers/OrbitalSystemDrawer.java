package drawers;

import Orbit.OrbitalSystem;
import Orbit.Vector2D;
import processing.core.*;

public class OrbitalSystemDrawer implements Drawable{
	
	/*
	 * Each OSD is dedicated to one orbital system. The reason I separated them from their respective OrbitalSystem is simply
	 * for organization. I wanted to let the orbits to contain only data points and their animations to be separate.
	 */

	private OrbitalSystem oSystem;
	private PApplet p;
	private Vector2D location;
	
	public OrbitalSystemDrawer(OrbitalSystem oSystem, PApplet p) {
		this.oSystem = oSystem;
		this.p = p;
	}
	
	public OrbitalSystemDrawer(OrbitalSystem oSystem, PApplet p, Vector2D location) {
		this.oSystem = oSystem;
		this.p = p;
		setLocation(location);
	}
	
	

	// draws the curve given by the control points given by the orbitalSystems
	public void draw() {
		p.pushMatrix();
		p.translate((float)location.getdX(),(float)location.getdY());
		p.smooth();
		p.beginShape();

		if (!oSystem.getPolarControlPoints().isEmpty()) {
			java.util.Iterator<Vector2D> iterator = oSystem.getPolarControlPoints().iterator();
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

	@Override
	public void setLocation(Vector2D location) {
		this.location = location;
		
	}

}
