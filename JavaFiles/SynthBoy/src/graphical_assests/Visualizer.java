package graphical_assests;

import java.util.ArrayList;

import calculations.ComplexWave;
import calculations.Vector2D;
import processing.core.PApplet;

public class Visualizer implements Drawable {

	protected ComplexWave complexWave;
	protected PApplet p;
	protected Vector2D location;
	protected ArrayList<Vector2D> wavePoints = new ArrayList<Vector2D>();
	protected float width, height;
	protected VisualizerType type;

	/**
	 * 
	 * @param compWave
	 *            the waveform this drawer will draw
	 * @param location
	 *            the centerpoint of the waveform
	 * @param width
	 *            the width of the display
	 * @param height
	 *            the height of the display
	 * @param p
	 *            reference to the PApplet
	 */
	public Visualizer(ComplexWave compWave, Vector2D location, VisualizerType type, float width, float height,
			PApplet p) {
		this.p = p;
		this.complexWave = compWave;
		this.width = width;
		this.height = height;
		this.type = type;
		setLocation(location);
	}

	public void setWave(ComplexWave CW) {
		this.complexWave = CW;
	}

	@Override
	public void setHeight(float height) {
		this.height = height;

	}

	@Override
	public void setWidth(float width) {
		this.width = width;
	}

	@Override
	public void setLocation(Vector2D location) {
		this.location = location;
	}

	@Override
	public void draw() {
		p.pushMatrix();
		p.translate((float) location.getdX(), (float) location.getdY());
		p.smooth();
		p.beginShape();
		System.out.println(wavePoints.size());
		// cartesian bullshit
		if (!wavePoints.isEmpty()) {
			for (Vector2D v : wavePoints) {
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

	/**
	 * 
	 * @param count
	 *            amount of points to retrieve
	 * @param currentTime
	 *            start time of retrieval
	 * @param rate
	 *            time between collection in mS
	 */
	public void updatePoints(int count, double currentTime) {
		double rate = complexWave.period() / (count);

		switch (type) {
		case POLAR:
			polarPoints(count, currentTime, rate);
			break;
		case CARTESIAN:
			cartesianPoints(count, currentTime, rate);
		}
	}

	public void cartesianPoints(int count, double currentTime, double rate) {
		float separation = width / count;
		for (int projectedTime = 0; projectedTime < count; projectedTime++) {
			double y = complexWave.getPolarLocation(currentTime + projectedTime * rate).getdY();
			if (wavePoints.size() > projectedTime) {
				wavePoints.get(projectedTime).setdY(y);
			} else {
				double x = projectedTime * separation - width / 2;
				wavePoints.add(new Vector2D(x, y));
			}
		}
	}

	public void polarPoints(int count, double currentTime, double rate) {

		for (int projectedTime = 0; projectedTime < count; projectedTime++) {
			Vector2D v = complexWave.getPolarLocation(currentTime + projectedTime * rate);
			if (wavePoints.size() > projectedTime) {
				wavePoints.set(projectedTime, v);
			} else {
				wavePoints.add(v);
			}
		}
	}

}
