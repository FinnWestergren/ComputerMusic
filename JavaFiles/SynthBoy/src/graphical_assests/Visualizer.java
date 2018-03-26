package graphical_assests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import processing.core.PApplet;
import wave_stuff.ComplexWave;
import wave_stuff.Vector2D;

public class Visualizer implements Drawable, ActionListener {

	protected ComplexWave complexWave;
	protected PApplet p;
	protected Vector2D location;
	protected Vector2D[] wavePoints;
	protected float width, height; 
	protected VisualizerType type;
	private int resolution;
	private long startTime;
	private boolean firstUpdate = true;

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
	public Visualizer(ComplexWave compWave, Vector2D location, VisualizerType type, float width, float height, int resolution,
			PApplet p) {
		this.p = p;
		this.complexWave = compWave;
		this.width = width;
		this.height = height;
		this.type = type;
		this.wavePoints = new Vector2D[resolution];
		this.resolution = resolution;
		startTime = System.currentTimeMillis();
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
		// cartesian bullshit
		
			for (Vector2D v : wavePoints) {
				if(!firstUpdate) p.curveVertex((float) v.getdX(), (float) v.getdY());
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
	@Override
	public void update() {
		double rate = complexWave.period() * 2 / (resolution);
		switch (type) {
		case POLAR:
			polarPoints(rate);
			break;
		case CARTESIAN:
			cartesianPoints(rate);
		}
	}

	/**
	 * 
	 * sets the location of the visual points 
	 * 
	 * @param count
	 * @param currentTime
	 * @param rate
	 * 
	 */
	
	public void cartesianPoints(double rate) {
		float separation = width / resolution;
		for (int projectedTime = 0; projectedTime < resolution; projectedTime++) {
			double y = complexWave.getPolarLocation(System.currentTimeMillis() - startTime + projectedTime * rate).scale(height/2).getdY();
			if (firstUpdate) {
				double x = projectedTime * separation - width / 2;
				wavePoints[projectedTime] = new Vector2D(x, y);
				
			} else {
				wavePoints[projectedTime].setdY(y);
			}
		}
		firstUpdate = false;
	}

	public void polarPoints( double rate) {
		for (int projectedTime = 0; projectedTime < resolution; projectedTime++) {
			Vector2D v = complexWave.getPolarLocation(System.currentTimeMillis() - startTime + projectedTime * rate).scale(width/2);
			wavePoints[projectedTime] =  v;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
