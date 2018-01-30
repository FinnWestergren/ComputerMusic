package Orbit;

public class Orbit {
	
	private double radius, period;
	
	public Orbit(double radius, double frequency) {
		
		this.radius = radius;
		this.period = 1/frequency;
	}
	
	//used to find the tip of the arm (or the satellite, if you think about planetary motion)
	//extending from the center to the edge of the radius given a current time in the period
	public Vector2D getSatelliteLocation(double time) {
		
		
		double dX = Math.cos(time % period * 2 * Math.PI) * radius;
		double dY = Math.sin(time % period * 2 * Math.PI) * radius;

		
		return new Vector2D(dX,dY);
	}
	

	
	
	
}
