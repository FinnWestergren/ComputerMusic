package graphical_assests;

import calculations.Vector2D;
import processing.core.PApplet;

public class Slider implements Controller {
	private PApplet p;
	private float  width, height, currentValue, unitWidth;
	private float faderWidth , faderHeight, faderLeft;
	private float beginDraggingX, initDraggingVal; //used for reference when dragging
	private Vector2D location;
	private float minVal,maxVal;
	private boolean dragging;
	private String title,units;
	
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
	public Slider(String title, String units, float minVal, float maxVal, boolean continuous, boolean logarithmic, float width, float height, Vector2D location, PApplet p) {
		this.p = p;
		this.title = title;
		this.units = units;
		this.width = width;
		this.height = height;
		this.minVal = minVal;
		this.maxVal = maxVal;
		this.faderWidth = width * 0.03f;
		this.faderHeight = height * 1.2f;
		this.unitWidth = width/((float) (maxVal - minVal));
		
	
		setLocation(location);
		setCurrentValue(0);
		
	}
	
	@Override
	public void setCurrentValue(float currentValue) {
		this.currentValue = currentValue;
		updateFaderLocation();
	}
	
	@Override
	public void draw() {
		if(currentValue > maxVal) setCurrentValue(maxVal);
		if(currentValue < minVal) setCurrentValue(minVal);
		p.pushMatrix();
		p.translate((float)location.getdX(),(float) location.getdY());
		p.rect(0, 0, width, height);
		p.fill(100);
		p.rect(faderLeft, -(faderHeight - height)/2, faderWidth,faderHeight);
		p.text(currentValue + " " + units, 0, 40);
		p.popMatrix();
		if(dragging) drag();
	}

	@Override
	public float getCurrentValue() {
		if(currentValue > maxVal) return (maxVal);
		if(currentValue < minVal) return (minVal);
		return currentValue;
	}

	@Override
	public Controller switchType() {
		 return null;
	}
	
	private void setFaderLocation(float value) {
		faderLeft = (float) (value - (0.5 * faderWidth));
	}
	
	private void updateFaderLocation() {
		faderLeft = (float) ((currentValue - minVal) * unitWidth - (0.5 * faderWidth));
	}
	
	private void updateCurrentValue() {
		currentValue = minVal + (float) (faderLeft + (0.5 * faderWidth))/unitWidth;
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

	@Override
	public void setHeight(float height) {
		this.height = height;
		
	}

	@Override
	public void setWidth(float width) {
		this.width = width;
	}

	@Override
	public void onMousePressed() {
		if(withinSlider(p.mouseX,p.mouseY)) {
			//System.out.println(p.mouseX - (location.getdX() + faderLeft));
			dragging = true;
			beginDraggingX = p.mouseX;
			initDraggingVal = faderLeft+ faderWidth/2;
		}
	}
	
	private float faderTop() {
		return (float) ( -(faderHeight - height)/2);
	}

	private boolean withinSlider(int mouseX, int mouseY) {
		boolean xSat = ((float) mouseX >= location.getdX() + faderLeft) && ((float) mouseX <= location.getdX() + faderLeft + faderWidth) ;
		boolean ySat = ((float) mouseY >= location.getdY() + faderTop()) && ((float) mouseY <= location.getdY() + faderTop() + faderHeight) ;
		return xSat && ySat;
	}

	@Override
	public void drag() {
		float deltaX = p.mouseX - beginDraggingX;
		setFaderLocation(deltaX +initDraggingVal );
		updateCurrentValue();
	}

	@Override
	public void onMouseReleased() {
		dragging = false;
	}
}
