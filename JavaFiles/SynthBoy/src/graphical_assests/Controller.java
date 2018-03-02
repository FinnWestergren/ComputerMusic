package graphical_assests;

public interface Controller extends Clickable, Drawable {
	public void setCurrentValue(float value);
	public float getCurrentValue();
	public String getLabel();
	public Controller switchType();
	public void drag();
}
