package GameObjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameEngine.UIObject;
import GameEngine.Vec2;

public class MainMenuNewGame extends UIObject {

	public MainMenuNewGame() {
		width = .2f;
		height = .1f;
		position = new Vec2();
		
		try {
			normalImage = ImageIO.read(new File("assets/new game.png"));
			hoverImage = ImageIO.read(new File("assets/new game on hover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean MouseSelected() {
		Vec2 mousePos = GetScaledMousePosition();
		if(mousePos == null) return false;

		Vec2 worldPosition = GetWorldPosition();
		
		if ((mousePos.x >= worldPosition.x && mousePos.y >= worldPosition.y)
				&& (mousePos.x <= worldPosition.x + width)
				&& (mousePos.y <= worldPosition.y + height)) {
			return true;
		}
		return false;
	}

	@Override
	public void OnMouseDown() {
	}

	@Override
	public void OnMouseUp() {
		System.out.println("Clicked");
		// make ModeMenu visible and active

		GAME_MANAGER.activateNextState();
		// make MainMenu invisible and inactive

		GetParent().visible = false;
		GetParent().active = false;
		this.visible = false;

	}

	@Override
	protected void OnUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void OnRender(Graphics2D g2d) {
		
		Vec2 worldPos = GetWorldPosition();
		
		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());

		int pixelWidth = (int) (width * JPANEL.getWidth());
		int pixelHeight = (int) (height * JPANEL.getHeight());
		
		BufferedImage currentSprite = GetCurrentSprite();

		g2d.drawImage(currentSprite, pixelX, pixelY, pixelWidth, pixelHeight, null);
	}
	
	BufferedImage GetCurrentSprite()
	{
		if(MouseSelected())
			return hoverImage;
		else 
			return normalImage;
	}
	
	private BufferedImage normalImage;
	private BufferedImage hoverImage;
	
	private float width;
	private float height;

}
