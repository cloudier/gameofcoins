package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import gameEngine.UIObject;
import gameEngine.Vec2;

public class AnimatedCoin extends UIObject {
	private Vec2 endPosition;
	private Color color;
	private float circleRadius;
	private boolean coinNotAdded;
	private static float ANIMATION_STEP = 0.04f;
	private Coin coin;
	
	public AnimatedCoin(Vec2 endPosition, int column, Coin coin) {
		super();
		this.position = new Vec2();
		this.endPosition = endPosition;
		this.circleRadius = .06f;
		this.color = Color.BLACK;
		this.coinNotAdded = true;
		this.coin = coin;
	}
	
	public float getCircleRadius() {
		return circleRadius;
	}

	public void setCircleRadius(float circleRadius) {
		this.circleRadius = circleRadius;
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
		if (this.position.y < endPosition.y - ANIMATION_STEP) {
			this.position = position.plus(new Vec2(0f, ANIMATION_STEP));
			
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
			
			((NormalGame) this.getParent()).setAnimated(true);
		}
		if ((this.position.y >= endPosition.y - ANIMATION_STEP * 2) && coinNotAdded){
			coin.setColor(this.color);
			coinNotAdded = false;
		}
		if (!coinNotAdded) {
			((NormalGame) this.getParent()).setAnimated(false);
		}
	}

}
