package graphical_assests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import processing.core.PApplet;
import wave_stuff.WaveSet;
import wave_stuff.Vector2D;

public class Visualizer implements Drawable, SliderListener {

	protected WaveSet complexWave;
	protected PApplet p;
	protected Vector2D location;
	protected Vector2D[] wavePoints;
	protected float width, height, phaseShift; 
	protected VisualizerType type;
	private int resolution,periods;

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
	public Visualizer(WaveSet compWave, Vector2D location, VisualizerType type, 
						float width, float height, float phaseShift,
						int resolution, int periods, PApplet p) {
		this.p = p;
		this.complexWave = compWave;
		this.width = width;
		this.height = height;
		this.phaseShift = phaseShift;
		this.periods = periods;
		this.type = type;
		this.wavePoints = new Vector2D[resolution];
		this.resolution = resolution;
		setLocation(location);
	}

	public void setWave(WaveSet CW) {
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
				if(v != null)p.curveVertex((float) v.getdX(), (float) v.getdY());
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
		float rate = complexWave.period() * periods / resolution;
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
	
	public void cartesianPoints(float rate) {
		float separation = width / resolution;
		float maxAmp = 0;
		for (int projectedTime = 0; projectedTime < resolution ; projectedTime++) {
			float y = complexWave.getPolarLocation( (resolution*phaseShift) + projectedTime * rate).getdY();
			if(Math.abs(y) > maxAmp) maxAmp = (float) Math.abs(y);
			float x = projectedTime  * separation - width / 2;
			wavePoints[projectedTime] = new Vector2D(x, y);
			
		}
		if(maxAmp > height/2) {
			for(Vector2D t : wavePoints)
				t.setdY(t.getdY() * (height/(2*maxAmp))); 
		}
	}

	public void polarPoints( float rate) {
		float maxAmp = 0;
		for (int projectedTime = 0; projectedTime < resolution; projectedTime++) {
			Vector2D v = complexWave.getPolarLocation( projectedTime * rate);
			if(Math.abs(v.getdY()) > maxAmp) maxAmp = Math.abs(v.getdY());
			wavePoints[projectedTime] =  v;
		}
		if(maxAmp > height/2) {
			for(Vector2D t : wavePoints)
				t.scale(height/(2*maxAmp));
		}
	}

	@Override
	public void OnSliderEvent() {
		update();
	}



}
