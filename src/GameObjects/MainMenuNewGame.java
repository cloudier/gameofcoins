package gameObjects;

import gameEngine.UIObject;
import gameEngine.Vec2;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainMenuNewGame extends UIObject {

	private BufferedImage normalImage;
	private BufferedImage hoverImage;

	private float width;
	private float height;

	public MainMenuNewGame() {
		width = .2f;
		height = .1f;
		position = new Vec2(0f, 0.5f);

		try {
			normalImage = ImageIO.read(new File("assets/new game.png"));
			hoverImage = ImageIO.read(new File("assets/new game on hover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

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
	}

	@Override
	public void onMouseUp() {
		System.out.println("Clicked");
		GAME_MANAGER.activateMode();
	}

	@Override
	protected void onUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onRender(Graphics2D g2d) {
		Vec2 worldPos = getWorldPosition();

		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());

		int pixelWidth = (int) (width * JPANEL.getWidth());
		int pixelHeight = (int) (height * JPANEL.getHeight());

		BufferedImage currentSprite = getCurrentSprite();

		g2d.drawImage(currentSprite, pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight, null);
	}

	BufferedImage getCurrentSprite() {
		if (mouseSelected())
			return hoverImage;
		else
			return normalImage;
	}

}
