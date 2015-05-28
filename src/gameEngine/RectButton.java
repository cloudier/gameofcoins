package gameEngine;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class RectButton extends UIObject {
	
	public RectButton(String DefaultImage) {
		defaultImage = DefaultImage;
		this.width = 0.15f;
		this.height = 0.10f;
	}
	
	public RectButton(String DefaultImage, float Width, float Height) {
		defaultImage = DefaultImage;
		this.width = Width;
		this.height = Height;
	}
	
	public RectButton(String DefaultImage, float X, float Y, float Width, float Height) {
		this(DefaultImage, Width, Height);
		this.position = new Vec2(X, Y);
	}
	
	
	public RectButton(String DefaultImage, String HoveringOver, float Width, float Height) {
		this(DefaultImage, Width, Height);
		hoveringOver = HoveringOver;
	}

	public RectButton(String DefaultImage, String HoveringOver, float X, float Y, float Width, float Height) {
		defaultImage = DefaultImage;
		hoveringOver = HoveringOver;
		this.width = Width;
		this.height = Height;
		this.position = new Vec2(X, Y);
	}	

	@Override
	public boolean mouseSelected() {
		Vec2 mousePos = getScaledMousePosition();
		if (mousePos == null)
			return false;

		Vec2 worldPosition = getWorldPosition();

		if ((mousePos.x >= worldPosition.x &&
				mousePos.y >= worldPosition.y) &&
				(mousePos.x <= worldPosition.x + width) &&
				(mousePos.y <= worldPosition.y + height)) {
			return true;
		}
		return false;
	}

	@Override
	public abstract void onMouseDown();

	@Override
	public void onMouseUp() { }

	@Override
	protected void onUpdate() { }

	@Override
	protected final void onRender(Graphics2D g2d) {
		Vec2 worldPos = getWorldPosition();

		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());

		int pixelWidth = (int) (width * JPANEL.getWidth());
		int pixelHeight = (int) (height * JPANEL.getHeight());
		
		Image img1 = IMAGE_STORE.GetScaledImage(defaultImage, pixelWidth, pixelHeight);
		Image img2 = null;
		
		if(hoveringOver != null)
		{
			img2 = IMAGE_STORE.GetScaledImage(hoveringOver, pixelWidth, pixelHeight);
		}
		
		if((mouseSelected() || modeSelected()) && img2 != null)
		{
			g2d.drawImage(img2, pixelX, pixelY, null);
		}
		else 
		{
			g2d.drawImage(img1, pixelX, pixelY, null);
		}
	}
	
	public boolean modeSelected() {
		return false;
	}
	
	public void setDefaultImage(String DefaultImage) {
		this.defaultImage = DefaultImage;
	}
	
	public void setHoveringOver(String HoveringOverImage) {
		this.hoveringOver = HoveringOverImage;
	}
	
	public float getWidth() { return width; }
	public float getHeight() { return height; }
	
	public void setWidth(float width) {	this.width = width; }
	public void setHeight(float height) { this.height = height; }

	private float width, height;
	private String defaultImage, hoveringOver;

}
