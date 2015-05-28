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
	/**
	 * Initialise x and y to 0
	 */
	public Vec2() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Constructor to initialise x and y
	 * @param x
	 * @param y
	 */
	public Vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Add 2 vectors
	 * @param other vector to add to current vector
	 * @return the sum of the 2 vectors
	 */
	public Vec2 plus(Vec2 other) {
		return new Vec2(this.x + other.x, this.y + other.y);
	}
	
	/**
	 * Subtract this vector from the other vector
	 * @param other
	 * @return the difference of the vectors
	 */
	public Vec2 minus(Vec2 other) {
		return new Vec2(this.x - other.x, this.y - other.y);
	}
	
	/**
	 * Scale vector
	 * @param scalar to scale the vector by
	 * @return scaled vector
	 */
	public Vec2 scale(float scalar) {
		return new Vec2(this.x * scalar, this.y * scalar);
	}
	
	/**
	 * Divide both components of vector by a given float
	 * @param divisor
	 * @return result of division
	 */
	public Vec2 divide(float divisor) {
		return new Vec2(this.x / divisor, this.y / divisor);
	}
	
	/**
	 * Rotate vector by given radians
	 * @param radians
	 * @return rotated vector
	 */
	public Vec2 rotatedByRad(float radians) {
		double newX, newY;
		newX = Math.cos((double)radians) * this.x - Math.sin((double)radians) * this.y;
		newY = Math.sin((double)radians) * this.x + Math.cos((double)radians) * this.y;
		return new Vec2((float)newX, (float)newY);
	}
	
	/**
	 * Rotate vector to the left
	 * @return left rotated vector
	 */
	public Vec2 rotateLeft() {
		return new Vec2(-this.y, this.x);
	}
	
	/**
	 * Rotate vector to the right
	 * @return right rotated vector
	 */
	public Vec2 rotateRight() {
		return new Vec2(this.y, -this.x);
	}
	
	/**
	 * Flip vector
	 * @return flipped vector
	 */
	public Vec2 flip() {
		return new Vec2(-this.x, -this.y);
	}
	
	/**
	 * Calculate the magnitude of vector
	 * @return the magnitude of the vector as a float
	 */
	public float magnitude() {
		return (float)Math.sqrt(x*x + y*y);
	}
	
	/**
	 * Normalise the vector, i.e. divide the vector
	 * by its magnitude
	 * @return normalised vector
	 */
	public Vec2 normalised() {
		float mag = this.magnitude();
		return new Vec2(this.x/mag, this.y/mag);
	}

	/**
	 * Rotate the vector by degrees
	 * @param degrees
	 * @return rotated vector
	 */
	public Vec2 rotatedByDeg(float degrees) {
		return rotatedByRad(degrees * (float)Math.PI / 180f);
	}
	
	/**
	 * Find dot product of 2 vectors 
	 * @param other
	 * @return float that is the dot product of the vectors
	 */
	public float dot(Vec2 other) {
		return this.x * other.x + this.y * other.y;
	}
	
	/**
	 * Find the projection of 2 vectors
	 * @param direction
	 * @return vector that is the projection of the 2 vectors
	 */
	public Vec2 project(Vec2 direction) {
		Vec2 dir = direction.normalised();
		return dir.scale(dir.dot(this));
	}
	
	/**
	 * Gives a string representation of this vector
	 * @return a string representation of this vector
	 */
	public String toString() { 
		return new String("(" + x + "," + y + ")");
	}
	
	final public float x, y;

}
