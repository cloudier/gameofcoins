package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;

import gameEngine.*;

public class ModeMenuAngry extends UIObject{
	
	private float width;
	private float height;
	
	public ModeMenuAngry() {
		super();
		width = .2f;
		height = .1f;
		position = new Vec2(width/2 + width, 0.15f);
	}
	
	@Override
	public boolean mouseSelected() {
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
		GameObject gameObjParent = this.getParent();
		if (gameObjParent instanceof ModeMenu) {
			ModeMenu parent = (ModeMenu) gameObjParent;
			parent.setMode("Angry");
		} else {
			System.err.println("Parent is not instance of ModeMenu");
		}
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
		
		int pixelX = (int) ((worldPos.x) * JPANEL.getWidth());
		int pixelY = (int) ((worldPos.y) * JPANEL.getHeight());
		
		int pixelWidth = (int) (width * JPANEL.getWidth());
		int pixelHeight = (int) (height * JPANEL.getHeight());
		
		Image coinImage = IMAGE_STORE.GetScaledImage("modeMenu_Angry", pixelWidth, pixelHeight);
		
		g2d.drawImage(coinImage, pixelX, pixelY, null);
	}

}
