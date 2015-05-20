package Demos;

import java.awt.Graphics2D;

import GameEngine.GameObject;
import GameEngine.Vec2;


/**
 * The Moon GameObject demonstrates the GameObject parenting
 * feature. Add this as a child to any other GameObject, and
 * the Moon GameObject will orbit around it.
 * 
 * @author ivormetcalf
 */
public class Moon extends GameObject {

	public Moon() {
		circleRadius = 0.02f;
		orbitalRadius = 0.1f;
		angularVelocity = ((float)Math.random()-0.5f) * 2f * 100f;
		position = new Vec2(orbitalRadius, 0);
	}

	@Override
	protected void OnUpdate() {
		//Rotate the position around
		position = position.rotatedByDeg(angularVelocity/TICK_RATE);
	}

	@Override
	protected void OnRender(Graphics2D g2d) {
		
		Vec2 worldPos = GetWorldPosition();
		
		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());
		
		int pixelRadiusX = (int) (circleRadius * JPANEL.getWidth());
		int pixelRadiusY = (int) (circleRadius * JPANEL.getHeight());
		
		g2d.drawOval(pixelX, pixelY, pixelRadiusX, pixelRadiusY);
	}

	protected float circleRadius;
	protected float orbitalRadius;
	protected float angularVelocity;
	
}
