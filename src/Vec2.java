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
		this.x = 0;
		this.y = 0;
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
	
	public Vec2 rotatedBy(float degrees) {
		double newX, newY;
		newX = Math.cos((double)degrees) - Math.sin((double)degrees);
		newY = Math.sin((double)degrees) + Math.cos((double)degrees);
		return new Vec2((float)newX, (float)newY);
	}
	
	final public float x, y;

}
