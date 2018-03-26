package graphical_assests;

import wave_stuff.Vector2D;

public interface Drawable {

	public void setHeight(float height);
	public void setWidth(float width);
	public void setLocation(Vector2D location);
	public void update();
	public void draw();
}
