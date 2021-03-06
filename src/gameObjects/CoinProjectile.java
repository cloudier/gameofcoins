package gameObjects;

import gameEngine.GameObject;
import gameEngine.Vec2;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.LinkedList;

public class CoinProjectile extends GameObject {

	public static LinkedList<StaticPeg> PegColliders = new LinkedList<StaticPeg>();
	public static LinkedList<StaticWall> WallColliders = new LinkedList<StaticWall>();
	
	/**
	 * Constructor to create a coin which will be thrown by player
	 */
	public CoinProjectile() {
		velocity = new Vec2();
		position = new Vec2();
		circleRadius = 0.05f;
	}
	
	public void setRadius(float circleRadius) {
		this.circleRadius = circleRadius;
	}

	@Override
	protected void onUpdate() {
		velocity = velocity.plus(new Vec2(0, gravity));
		position = position.plus(velocity.divide(TICK_RATE));
		
		for(StaticPeg sp : PegColliders){
			sp.Collide(this);
		}
		
		for(StaticWall sw : WallColliders){
			sw.Collide(this);
		}
	}

	@Override
	protected void onRender(Graphics2D g2d) {
		Vec2 worldPos = getWorldPosition();
		
		int pixelX = (int) ((worldPos.x-circleRadius) * JPANEL.getWidth());
		int pixelY = (int) ((worldPos.y-circleRadius) * JPANEL.getHeight());
		
		int pixelWidth = (int) (circleRadius * JPANEL.getWidth() * 2f);
		int pixelHeight = (int) (circleRadius * JPANEL.getHeight() * 2f);
		
		Image coinImage;
		
		if(player1)	
			coinImage = IMAGE_STORE.GetScaledImage("coin_Red", pixelWidth, pixelHeight);
		else
			coinImage = IMAGE_STORE.GetScaledImage("coin_Yellow", pixelWidth, pixelHeight);
		
		g2d.drawImage(coinImage, pixelX, pixelY, null);
	}
	
	public boolean player1; 
	public float gravity = 0.010f;
	public float restitution = 0.9f;
	public float friction = 0.9f;
	public Vec2 velocity;
	public float circleRadius;
}
