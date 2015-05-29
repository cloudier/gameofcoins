package gameObjects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;

import gameEngine.UIObject;
import gameEngine.Vec2;

public class Coin extends UIObject {

	private Color color;
	private float circleRadius;
	private boolean winning;

	/**
	 * Create a coin and initialise its radius and position
	 */
	public Coin() {
		circleRadius = .06f;
		position = new Vec2();
		setWinning(false);
	}
	
	/**
	 * Method to get the radius of the coin
	 * @return Returns the radius of the circle
	 */
	public float getCircleRadius() {
		return circleRadius;
	}

	/**
	 * Method to set the radius of the coin
	 * @param circleRadius The radius of the circle
	 */
	public void setCircleRadius(float circleRadius) {
		this.circleRadius = circleRadius;
	}
	
	/**
	 * Get coin's color
	 * @return Returns the color of the coin
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Set coin's color
	 * @param color The color of the coin
	 */
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
		
		if(color == Color.WHITE){
			//Do nothing
		}
		else if(color == Color.RED){
			Image coinImage = IMAGE_STORE.GetScaledImage("coin_Red", pixelWidth, pixelHeight);
			g2d.drawImage(coinImage, pixelX, pixelY, null);
		}
		else if(color == Color.YELLOW){
			Image coinImage = IMAGE_STORE.GetScaledImage("coin_Yellow", pixelWidth, pixelHeight);
			g2d.drawImage(coinImage, pixelX, pixelY, null);
		}
		else {
			g2d.setColor(color);
			g2d.fillOval(pixelX, pixelY, pixelWidth, pixelHeight);
		}
		
		if (winning) {
			Stroke s = new BasicStroke(10f);
			g2d.setStroke(s);
			g2d.setColor(Color.WHITE);
			g2d.drawOval(pixelX, pixelY, pixelWidth, pixelHeight);
		}
	}

	public void setWinning(boolean winning) {
		this.winning = winning;
	}
}
