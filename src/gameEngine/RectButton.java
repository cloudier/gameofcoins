package gameEngine;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class RectButton extends UIObject {
	/**
	 * Initialise the size and the texture for the rectangular button
	 * with the size respect to the panel ratio.
	 * @param DefaultImage
	 */
	public RectButton(String DefaultImage) {
		defaultImage = DefaultImage;
		this.width = 0.15f;
		this.height = 0.10f;
	}
	
	/**
	 * Initialise the real size of the button based on the panel size
	 * @param DefaultImage
	 * @param Width
	 * @param Height
	 */
	public RectButton(String DefaultImage, float Width, float Height) {
		defaultImage = DefaultImage;
		this.width = Width;
		this.height = Height;
	}
	
	/**
	 * Initialise the button's position.
	 * @param DefaultImage
	 * @param X
	 * @param Y
	 * @param Width
	 * @param Height
	 */
	public RectButton(String DefaultImage, float X, float Y, float Width, float Height) {
		this(DefaultImage, Width, Height);
		this.position = new Vec2(X, Y);
	}
	
	/**
	 * Sets an alternate image when mouse hovers over the button.
	 * @param DefaultImage
	 * @param HoveringOver
	 * @param Width
	 * @param Height
	 */
	public RectButton(String DefaultImage, String HoveringOver, float Width, float Height) {
		this(DefaultImage, Width, Height);
		hoveringOver = HoveringOver;
	}

	/**
	 * Initialise button given the size of the button and its position
	 * @param DefaultImage
	 * @param HoveringOver
	 * @param X
	 * @param Y
	 * @param Width
	 * @param Height
	 */
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
	
	/*
	 * Used in subclasses to change the image if the mode
	 * that the button represents is set.
	 */
	public boolean modeSelected() {
		return false;
	}
	
	/**
	 * Sets the default image
	 * @param DefaultImage
	 */
	public void setDefaultImage(String DefaultImage) {
		this.defaultImage = DefaultImage;
	}
	
	/**
	 * Sets hovering image
	 * @param HoveringOverImage
	 */
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
