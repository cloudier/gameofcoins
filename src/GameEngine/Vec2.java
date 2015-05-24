package gameEngine;

/**
 * 2D Floating Point Vector. <br />
 * This is an immutable object which means that: <br />
 *  -  <tt>Vec2 a = new Vec2(2, 3);</tt> //constructs a Vec2 <br />
 *  -  <tt>Vec2 b = a;</tt> //copies a.x, a.y into b <b>by value</b>. 
 *  
 * @author ivormetcalf
 */

public final class Vec2 {

	public Vec2() {
		this(0, 0);
	}
	
	public Vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vec2 plus(Vec2 other) {
		return new Vec2(this.x + other.x, this.y + other.y);
	}
	
	public Vec2 minus(Vec2 other) {
		return new Vec2(this.x - other.x, this.y - other.y);
	}
	
	public Vec2 scale(float scalar) {
		return new Vec2(this.x * scalar, this.y * scalar);
	}
	
	public Vec2 divide(float divisor) {
		return new Vec2(this.x / divisor, this.y / divisor);
	}
	
	public Vec2 rotatedByRad(float radians) {
		double newX, newY;
		newX = Math.cos((double)radians) * this.x - Math.sin((double)radians) * this.y;
		newY = Math.sin((double)radians) * this.x + Math.cos((double)radians) * this.y;
		return new Vec2((float)newX, (float)newY);
	}
	
	public float magnitude() {
		return (float)Math.sqrt(x*x + y*y);
	}
	
	public Vec2 normalised() {
		float mag = this.magnitude();
		return new Vec2(this.x/mag, this.y/mag);
	}
	
	public Vec2 rotatedByDeg(float degrees) {
		return rotatedByRad(degrees * (float)Math.PI / 180f);
	}
	
	public String toString() { 
		return new String("(" + x + "," + y + ")");
	}
	
	final public float x, y;

}
