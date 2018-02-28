package drawers;

import Orbit.Vector2D;
import processing.core.PApplet;

public class Slider implements Controller, Drawable {
	private PApplet p;
	private float  width, height, currentValue, unitWidth;
	private float faderWidth , faderHeight;
	private Vector2D location;
	private double minVal,maxVal;
	
	/**
	*@param title Title of the controller
	*@param units Units for the controller output
	*@param minVal Minimum possible output value
	*@param maxVal Maximum possible output value
	*@param continuous determines whether the controller is continuous or discrete (may change to a courseness variable
	*@param logarithmic determines whether the controller is logarithmic
	*@param width width of slider
	*@param height height of slider
	*@param p reference to the PApplet window
	*/
	public Slider(String title, String units, double minVal, double maxVal, boolean continuous, boolean logarithmic, float width, float height, Vector2D location, PApplet p) {
		this.p = p;
		this.width = width;
		this.height = height;
		this.maxVal = minVal;
		this.maxVal = maxVal;
		this.faderWidth = width * 0.03f;
		this.faderHeight = height * 1.2f;
		this.unitWidth = width/((float) (maxVal - minVal));
		setLocation(location);
		currentValue = 0;
	}
	
	@Override
	public void setCurrentValue(float currentValue) {
		this.currentValue = currentValue;
	}
	
	@Override
	public void draw() {
		p.pushMatrix();
		p.translate((float)location.getdX(),(float) location.getdY());
		p.rect(0, 0, width, height);
		p.fill(100);
		p.rect((float) (currentValue * unitWidth - (0.5 * faderWidth)), -(faderHeight - height)/2, faderWidth,faderHeight);
		p.popMatrix();
		currentValue += 1;
	}

	@Override
	public float getCurrentValue() {
		return currentValue;
	}

	@Override
	public Controller switchType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocation(Vector2D location) {
		this.location = location;
		
	}
}
