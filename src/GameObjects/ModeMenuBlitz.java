package gameObjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameEngine.*;

public class ModeMenuBlitz extends UIObject{
	
	private ModeMenu parent;
	
	private BufferedImage normalImage;
	private BufferedImage hoverImage;
	private BufferedImage selectedImage;
	
	private float width;
	private float height;
	
	public ModeMenuBlitz() {
		width = .2f;
		height = .1f;
		position = new Vec2(width/2, 0.3f);

		try {
			normalImage = ImageIO.read(new File("assets/ModeMenu/BlitzUnselected.png"));
			hoverImage = ImageIO.read(new File("assets/ModeMenu/BlitzSelected.png"));
			selectedImage =  ImageIO.read(new File("assets/ModeMenu/NormalSelected.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GameObject gameObjParent = GetParent();
		if (gameObjParent instanceof ModeMenu) {
			parent = (ModeMenu) gameObjParent;
		} else {
			System.err.println("Parent is not instance of ModeMenu");
		}
	}

	@Override
	public boolean MouseSelected() {
		Vec2 mousePos = GetScaledMousePosition();
		if (mousePos == null)
			return false;

		Vec2 worldPosition = GetWorldPosition();

		if ((mousePos.x >= worldPosition.x - width/2 &&
				mousePos.y >= worldPosition.y - height/2) &&
				(mousePos.x <= worldPosition.x + width/2) &&
				(mousePos.y <= worldPosition.y + height/2)) {
			return true;
		}
		return false;
	}

	@Override
	public void OnMouseDown() {
		parent.setMode("Blitz");
	}

	@Override
	public void OnMouseUp() {
	}

	@Override
	protected void OnUpdate() {		
	}

	@Override
	protected void OnRender(Graphics2D g2d) {
		Vec2 worldPos = GetWorldPosition();

		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());

		int pixelWidth = (int) (width * JPANEL.getWidth());
		int pixelHeight = (int) (height * JPANEL.getHeight());

		BufferedImage currentSprite = GetCurrentSprite();

		g2d.drawImage(currentSprite, pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight, null);
		
	}

	BufferedImage GetCurrentSprite() {			
		if (parent.getMode().equals("Blitz")) {
			return selectedImage;
		} else if (MouseSelected()) {
			return hoverImage;
		} else {
			return normalImage;
		}
	}
}
