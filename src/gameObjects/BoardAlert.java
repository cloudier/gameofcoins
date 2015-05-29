package gameObjects;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import gameEngine.Vec2;

public class BoardAlert extends Button {

	/**
	 * Create board button
	 * @param width The width of the board alert
	 * @param height The height of the board alert
	 * @param position The position of type Vec2 of the board alert
	 * @param string The string to be displayed in the board alert
	 */
	public BoardAlert(float width, float height, Vec2 position, String string) {
		super(width, height, position, string);
	}
	
	@Override
	public boolean mouseSelected() {
		return false;
	}

	@Override
	protected void onRender(Graphics2D g2d) {
		Vec2 worldPos = getWorldPosition();

		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());

		//int pixelWidth = (int) (width * JPANEL.getWidth());
		//int pixelHeight = (int) (height * JPANEL.getHeight());

		Color blackTransluscent = new Color(0f, 0f, 0f, .7f);
		g2d.setColor(blackTransluscent);
		g2d.fillRect(0, 0, JPANEL.getWidth(), JPANEL.getHeight());
		
		//g2d.setColor(Color.RED);
		//g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
		g2d.setColor(Color.WHITE);
		g2d.setFont(this.font.deriveFont((float) JPANEL.getWidth()/20));
		FontMetrics fm = g2d.getFontMetrics();
        int x = ((fm.stringWidth(string)) / 2);
        int y = fm.getHeight()/4;
		g2d.drawString(string, pixelX - x, pixelY + y);
	}
}
