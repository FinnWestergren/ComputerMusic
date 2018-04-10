package graphical_assests;

import listeners.ControllerListener;

public interface Controller extends Clickable, Drawable {
	public void setCurrentValue(float value);
	public float getCurrentValue();
	public String getLabel();
	public Controller switchType();
	public void onMousePressed();
	public void onMouseReleased();
	public void drag();
	public void setSignature(String sig);
	public void setContollerListener(ControllerListener cL);
}
