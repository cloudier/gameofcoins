package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import gameEngine.UIObject;
import gameEngine.Vec2;

public class Coin extends UIObject {

	private Color color;
	private float circleRadius;

	public Coin() {
		circleRadius = .06f;
		position = new Vec2();
	}
	
	public float getCircleRadius() {
		return circleRadius;
	}

	public void setCircleRadius(float circleRadius) {
		this.circleRadius = circleRadius;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public boolean mouseSelected() {
		return false;
	}

	@Override
	public void onMouseDown() {

	}

	@Override
	public void onMouseUp() {
	}

	@Override
	protected void onUpdate() {
	}

	@Override
	protected void onRender(Graphics2D g2d) {
		Vec2 worldPos = getWorldPosition();
		
		int pixelX = (int) ((worldPos.x-circleRadius) * JPANEL.getWidth());
		int pixelY = (int) ((worldPos.y-circleRadius) * JPANEL.getHeight());
		
		int pixelWidth = (int) (circleRadius * JPANEL.getWidth() * 2f);
		int pixelHeight = (int) (circleRadius * JPANEL.getHeight() * 2f);
		
		if(color == Color.RED)
		{
			Image coinImage = IMAGE_STORE.GetScaledImage("coin_Red", pixelWidth, pixelHeight);
			g2d.drawImage(coinImage, pixelX, pixelY, null);
		}
		else if(color == Color.YELLOW)
		{
			Image coinImage = IMAGE_STORE.GetScaledImage("coin_Yellow", pixelWidth, pixelHeight);
			g2d.drawImage(coinImage, pixelX, pixelY, null);
		}
		else 
		{
			g2d.setColor(color);
			g2d.fillOval(pixelX, pixelY, pixelWidth, pixelHeight);
		}
		
	}

}
