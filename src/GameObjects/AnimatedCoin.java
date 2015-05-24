package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;

import gameEngine.UIObject;
import gameEngine.Vec2;

public class AnimatedCoin extends UIObject {
	private Vec2 endPosition;
	private int column;
	private Color color;
	private float circleRadius;
	private boolean coinNotAdded;
	private static float ANIMATION_STEP = 0.04f;
	private Coin coin;
	
	public AnimatedCoin(Vec2 endPosition, int column, Coin coin) {
		super();
		this.position = new Vec2();
		this.endPosition = endPosition;
		this.column = column;
		this.circleRadius = .06f;
		this.color = Color.BLACK;
		this.coinNotAdded = true;
		this.coin = coin;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public boolean mouseSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onMouseDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseUp() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onRender(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if (this.position.y < endPosition.y - ANIMATION_STEP) {
			this.position = position.plus(new Vec2(0f, ANIMATION_STEP));
			
			Vec2 worldPos = getWorldPosition();

			int pixelX = (int) (worldPos.x * JPANEL.getWidth());
			int pixelY = (int) (worldPos.y * JPANEL.getHeight());

			int pixelWidth = (int) (circleRadius * JPANEL.getWidth() * 2f);
			int pixelHeight = (int) (circleRadius * JPANEL.getHeight() * 2f);

			g2d.setColor(this.color);
			g2d.fillOval(pixelX - pixelWidth / 2, pixelY - pixelHeight / 2,
					pixelWidth, pixelHeight);
		} else if (coinNotAdded){
			coin.setColor(this.color);
			coinNotAdded = false;
		}
	}

}
