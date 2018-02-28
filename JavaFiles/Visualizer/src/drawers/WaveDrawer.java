package drawers;

import java.util.Collection;

import Orbit.OrbitalSystem;
import Orbit.Vector2D;
import processing.core.PApplet;

public class WaveDrawer implements Drawable {
	
	private OrbitalSystem oSystem;
	private PApplet p;
	private Vector2D location;
	
	public WaveDrawer(OrbitalSystem oSystem, PApplet p) {
		this.p = p;
		this.oSystem = oSystem;
	}
	
	public WaveDrawer(OrbitalSystem oSystem, PApplet p, Vector2D location) {
		this.p = p;
		this.oSystem = oSystem;
		setLocation(location);
	}

	@Override
	public void draw() {
		p.pushMatrix();
		p.translate((float)location.getdX(),(float) location.getdY());
		p.smooth();
		p.beginShape();

		if (!oSystem.getCartesianControlPoints().isEmpty()) {
			java.util.Iterator<Vector2D> iterator = oSystem.getCartesianControlPoints().iterator();
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
