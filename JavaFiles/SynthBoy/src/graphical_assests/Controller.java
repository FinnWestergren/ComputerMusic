package graphical_assests;

public interface Controller {
	public void setCurrentValue(float value);
	public float getCurrentValue();
	public String getLabel();
	public Controller switchType();
}
