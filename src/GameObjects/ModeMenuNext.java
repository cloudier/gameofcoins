package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gameEngine.UIObject;
import gameEngine.Vec2;

public class ModeMenuNext extends UIObject {

	private float width;
	private float height;
	// confirm settings and go to next window button
	
	public ModeMenuNext() {
		this.width = 0.3f;
		this.height = 0.1f;
	}
	
	@Override
	public boolean mouseSelected() {
		// TODO Auto-generated method stub

		Vec2 mousePos = getScaledMousePosition();
		if (mousePos == null)
			return false;

		Vec2 worldPosition = getWorldPosition();

		if ((mousePos.x >= worldPosition.x - width/2 &&
				mousePos.y >= worldPosition.y - height/2) &&
				(mousePos.x <= worldPosition.x + width/2) &&
				(mousePos.y <= worldPosition.y + height/2)) {
			return true;
		}
		return false;
	}

	@Override
	public void onMouseDown() {
		// TODO Auto-generated method stub
		
		((ModeMenu) this.getParent()).activatePlayers();
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
		Vec2 worldPos = getWorldPosition();

		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());

		int pixelWidth = (int) (width * JPANEL.getWidth());
		int pixelHeight = (int) (height * JPANEL.getHeight());

		g2d.setColor(Color.BLACK);
		g2d.drawString("confirm", pixelX - (pixelWidth/2), pixelY - (pixelHeight/2));
		g2d.setColor(Color.RED);
		g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
		g2d.setColor(Color.BLUE);

	}

}
