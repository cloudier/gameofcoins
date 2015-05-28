package gameObjects;

import gameEngine.*;

import java.awt.Color;
import java.awt.Graphics2D;

public class StaticWall extends GameObject {

	public StaticWall() {
		CoinProjectile.WallColliders.add(this);
		
		position = new Vec2(0.1f, 0.9f);
		position2 = new Vec2(0.8f, 0.8f);
	}
	
	public StaticWall(Vec2 p1, Vec2 p2) {
		CoinProjectile.WallColliders.add(this);
		
		position = p1;
		position2 = p2;
	}

	@Override
	protected void onUpdate() {
		
	}
	
	public void Collide(CoinProjectile cp) {
		Vec2 coinPos = cp.getWorldPosition();
		Vec2 worldPos1 = getWorldPosition(position);
		Vec2 worldPos2 = getWorldPosition(position2);
		
		if(LineIntersection(cp)){
			CollideLineSegment(cp);
		}
		else {
			Vec2 displacement1 = coinPos.minus(worldPos1);
			Vec2 displacement2 = coinPos.minus(worldPos2);
			
			if(displacement1.magnitude() < cp.circleRadius)
				CollideWithPoint(cp, worldPos1);
			else if(displacement2.magnitude() < cp.circleRadius)
				CollideWithPoint(cp, worldPos2);
		}
	}
	
	private void CollideLineSegment(CoinProjectile cp){
		Vec2 coinPos = cp.getWorldPosition();
		Vec2 worldPos1 = getWorldPosition(position);
		Vec2 worldPos2 = getWorldPosition(position2);
		
		Vec2 lineDirection = worldPos2.minus(worldPos1).normalised();
		Vec2 displacement = coinPos.minus(worldPos1);
		
		Vec2 normal = lineDirection.rotateLeft();
		
		Vec2 perpendicularPoint = displacement.project(lineDirection).plus(worldPos1);
		Vec2 bounceDir = displacement.project(normal).normalised();
		
		//Place circle so that wall is tangential
		Vec2 newCoinPos = perpendicularPoint.plus(bounceDir.scale(cp.circleRadius)); 
		cp.setWorldPosition(newCoinPos);
		
		Vec2 deflected = cp.velocity.project(lineDirection);
		Vec2 reflected = cp.velocity.project(bounceDir).scale(-1f);
		
		deflected = deflected.scale(0.8f);
		reflected = reflected.scale(0.8f);
		
		cp.velocity = deflected.plus(reflected);
		
	}
	
	private void CollideWithPoint(CoinProjectile cp, Vec2 point){
		Vec2 coinPos = cp.getWorldPosition();
		
		Vec2 displacement = coinPos.minus(point); 
		
		if(displacement.magnitude() < cp.circleRadius) {
			
			Vec2 bounceDir = displacement.normalised();
			Vec2 tangentDir = bounceDir.rotateLeft();
			
			Vec2 newCoinPos = point.plus(bounceDir.scale(cp.circleRadius));
			cp.setWorldPosition(newCoinPos);
			
			Vec2 deflected = cp.velocity.project(tangentDir);
			Vec2 reflected = cp.velocity.project(bounceDir).scale(-1f);
			
			deflected = deflected.scale(cp.friction);
			reflected = reflected.scale(cp.restitution);
			
			cp.velocity = deflected.plus(reflected);
		}
		else {
			System.err.println("Why did I get called?");
		}
	}
	
	private boolean LineIntersection(CoinProjectile cp){
		Vec2 coinPos = cp.getWorldPosition();
		Vec2 worldPos1 = getWorldPosition(position);
		Vec2 worldPos2 = getWorldPosition(position2);
		
		Vec2 lineVector = worldPos2.minus(worldPos1);
		Vec2 lineDirection = lineVector.normalised();
		Vec2 displacement = coinPos.minus(worldPos1);
		
		float dotProduct = displacement.dot(lineDirection);
		
		Vec2 projection = displacement.project(lineDirection);
		Vec2 perpendicularPoint = projection.plus(worldPos1);
		
		if(dotProduct > 0f && dotProduct < lineVector.magnitude() &&
				perpendicularPoint.minus(coinPos).magnitude() < cp.circleRadius)
			return true;
		else
			return false;
	}

	@Override
	protected void onRender(Graphics2D g2d) {
		Vec2 worldPos1 = getWorldPosition(position);
		Vec2 worldPos2 = getWorldPosition(position2);
		
		int pixelX1 = (int) (worldPos1.x * JPANEL.getWidth());
		int pixelY1 = (int) (worldPos1.y * JPANEL.getHeight());
		int pixelX2 = (int) (worldPos2.x * JPANEL.getWidth());
		int pixelY2 = (int) (worldPos2.y * JPANEL.getHeight());
		
		g2d.setColor(Color.black);
		g2d.drawLine(pixelX1, pixelY1, pixelX2, pixelY2);
	}
	
	private Vec2 position2; //Position of other end point
}
