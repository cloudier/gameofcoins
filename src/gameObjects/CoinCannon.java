package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;

import gameEngine.GameObject;
import gameEngine.Vec2;

public class CoinCannon extends GameObject {

	public CoinCannon() {
		baseRadius = 0.02f;
		circle2Radius = 0.01f;
		circle3Radius = 0.005f;
		coinHolderRadius = 0.05f;
		
		basePosition = new Vec2();
		circle2Pos = new Vec2();
		circle3Pos = new Vec2();
		coinHolderPos = new Vec2();
		
	}

	@Override
	protected void onUpdate() {
		Vec2 mousePos = getScaledMousePosition();
		if(mousePos != null)
		{
			Vec2 shotVector = mousePos.minus(getWorldPosition());
			Vec2 shotDir = shotVector.normalised();
			
			basePosition = getWorldPosition();
			circle2Pos = basePosition.plus(shotDir.scale(baseRadius+circle2Radius).scale(1.1f));
			circle3Pos = circle2Pos.plus(shotDir.scale(circle2Radius+circle3Radius).scale(1.1f));
			
			coinHolderPos = circle3Pos.plus(shotDir.scale(circle3Radius+coinHolderRadius).scale(1.1f));
		}
		
	}

	@Override
	protected void onRender(Graphics2D g2d) {
		drawCircle(g2d, basePosition, baseRadius);
		drawCircle(g2d, circle2Pos, circle2Radius);
		drawCircle(g2d, circle3Pos, circle3Radius);
		
		//drawCircle(g2d, coinHolderPos, coinHolderRadius);

	}
	
	private void drawCircle(Graphics2D g2d, Vec2 WorldPosition, float Radius)
	{
		int pixelX = (int) (WorldPosition.x * JPANEL.getWidth());
		int pixelY = (int) (WorldPosition.y * JPANEL.getHeight());
		
		int pixelWidth = (int) (Radius * JPANEL.getWidth() * 2f);
		int pixelHeight = (int) (Radius * JPANEL.getHeight() * 2f);
		
		g2d.setColor(Color.black);
		
		g2d.fillOval(pixelX-pixelWidth/2, pixelY-pixelHeight/2, pixelWidth, pixelHeight);
	}
	
	public float getCoinHolderRadius() {
		return coinHolderRadius;
	}
	
	public void setCoinHolderRadius(float radius) {
		coinHolderRadius = radius;
	}
	
	public Vec2 getCoinHolderWorldPosition() {
		return coinHolderPos;
	}
	
	Vec2 basePosition;
	float baseRadius;
	
	Vec2 circle2Pos;
	float circle2Radius;
	
	Vec2 circle3Pos;
	float circle3Radius;
	
	Vec2 coinHolderPos;
	float coinHolderRadius;

}
