package wave_stuff;
/** 
 *  ========================================================
 *  Vector2D.java: Source code for two-dimensional vectors
 * 
 *  Written by: Mark Austin                   November, 2005
 *  ========================================================
 */

import java.lang.Math;

public class Vector2D {

	protected float dX;

	public float getdX() {
		return dX;
	}

	public void setdX(float dX) {
		this.dX = dX;
	}

	public float getdY() {
		return dY;
	}

	public void setdY(float dY) {
		this.dY = dY;
	}

	protected float dY;

	// Constructor methods ....

	public Vector2D() {
		dX = dY = 0.0f;
	}

	public Vector2D(float dX, float dY) {
		this.dX = dX;
		this.dY = dY;
	}

	// Convert vector to a string ...

	public String toString() {
		return "Vector2D(" + dX + ", " + dY + ")";
	}

	// Compute magnitude of vector ....

	public double length() {
		return Math.sqrt(dX * dX + dY * dY);
	}

	// Sum of two vectors ....

	public void add(Vector2D v1) {
		this.dX += v1.dX;
		this.dY += v1.dY;
	}

	// Subtract vector v1 from v .....

	public Vector2D sub(Vector2D v1) {
		Vector2D v2 = new Vector2D(this.dX - v1.dX, this.dY - v1.dY);
		return v2;
	}

	// Scale vector by a constant ...

	public void scale(float scaleFactor) {
		this.dX *= scaleFactor; this.dY *= scaleFactor;
	}

	// Normalize a vectors length....

	public Vector2D normalize() {
		Vector2D v2 = new Vector2D();

		double length = Math.sqrt(this.dX * this.dX + this.dY * this.dY);
		if (length != 0) {
			v2.dX = (float) (this.dX / length);
			v2.dY = (float) (this.dY / length);
		}

		return v2;
	}

	// Dot product of two vectors .....

	public double dotProduct(Vector2D v1) {
		return this.dX * v1.dX + this.dY * v1.dY;
	}
}