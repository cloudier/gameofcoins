package gameObjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameEngine.*;

public class ModeMenuNormal extends UIObject{
	
	private BufferedImage normalImage;
	private BufferedImage hoverImage;
	private BufferedImage selectedImage;
	
	private float width;
	private float height;
	
	public ModeMenuNormal() {
		super();
		width = .2f;
		height = .1f;
		position = new Vec2(-width/2, 0.1f);

		try {
			normalImage = ImageIO.read(new File("assets/ModeMenu/NormalUnselected.png"));
			hoverImage = ImageIO.read(new File("assets/ModeMenu/NormalSelected.png"));
			selectedImage =  ImageIO.read(new File("assets/ModeMenu/NormalSelected.png"));
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
		GameObject gameObjParent = this.getParent();
		if (gameObjParent instanceof ModeMenu) {
			ModeMenu parent = (ModeMenu) gameObjParent;
			parent.setMode("Normal");
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

		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());

		int pixelWidth = (int) (width * JPANEL.getWidth());
		int pixelHeight = (int) (height * JPANEL.getHeight());

		BufferedImage currentSprite = getCurrentSprite();

		g2d.drawImage(currentSprite, pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight, null);
	}

	BufferedImage getCurrentSprite() {		
		GameObject gameObjParent = this.getParent();
		if (gameObjParent instanceof ModeMenu) {
			ModeMenu parent = (ModeMenu) gameObjParent;
			if (parent.getMode().equals("Normal")) {
				return selectedImage;
			} else if (mouseSelected()) {
				return hoverImage;
			} else {
				return normalImage;
			}
		} else {
			System.err.println("Parent is not instance of ModeMenu");
			return null;
		}
	}
}
