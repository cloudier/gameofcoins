package gameObjects;

import gameEngine.GameObject;
import gameEngine.Vec2;

import java.awt.Color;
import java.awt.Graphics2D;

public class StaticPeg extends GameObject {
	/**
	 * 
	 */
	public StaticPeg() {
		CoinProjectile.PegColliders.add(this);
		circleRadius = 0.005f;
	}
	
	public StaticPeg(Vec2 Position) {
		position = Position;
		CoinProjectile.PegColliders.add(this);
		circleRadius = 0.005f;
	}

	@Override
	protected void onUpdate() { } //Does nothing
	
	/**
	 * Update the speed of the coin when collided
	 * @param cp
	 */
	public void Collide(CoinProjectile cp){
		Vec2 coinPos = cp.getWorldPosition();
		Vec2 worldPos = getWorldPosition();
		
		Vec2 displacement = coinPos.minus(worldPos); 
		
		if(displacement.magnitude() < cp.circleRadius + circleRadius) {
			
			Vec2 bounceDir = displacement.normalised();
			Vec2 tangentDir = bounceDir.rotateLeft();
			
			Vec2 newCoinPos = worldPos.plus(bounceDir.scale(circleRadius+cp.circleRadius));
			cp.setWorldPosition(newCoinPos);
			
			Vec2 deflected = cp.velocity.project(tangentDir);
			Vec2 reflected = cp.velocity.project(bounceDir).scale(-1f);
			
			//Pegs don't have friction as friction prevents coins from rolling off
			reflected = reflected.scale(cp.restitution * restitution);
			
			cp.velocity = deflected.plus(reflected);
		}
	}
	
	/**
	 * True if the coin is at intersect
	 * @param cp
	 * @return
	 */
	public boolean IntersectsWith(CoinProjectile cp){
		Vec2 coinPos = cp.getWorldPosition();
		Vec2 worldPos = getWorldPosition();
		
		Vec2 displacement = coinPos.minus(worldPos);
		
		if(displacement.magnitude() < cp.circleRadius + circleRadius) {
			return true;
		} 
		else {
			return false;
		}
	}
	

	@Override
	protected void onRender(Graphics2D g2d) {
		Vec2 worldPos = getWorldPosition();
		
		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());
		
		int pixelWidth = (int) (circleRadius * JPANEL.getWidth() * 2f);
		int pixelHeight = (int) (circleRadius * JPANEL.getHeight() * 2f);
		
		g2d.setColor(Color.black);
		
		g2d.fillOval(pixelX-pixelWidth/2, pixelY-pixelHeight/2, pixelWidth, pixelHeight);
	}
	
	public float circleRadius;
	public float restitution = 0.95f;
}
