package demos;

import gameEngine.GameObject;
import gameEngine.Vec2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The BraidGuy GameObject is intended to test image
 * animation in Java. 
 * 
 * @author ivormetcalf
 */

public class BraidGuy extends GameObject {

	public BraidGuy() {
		
		try {
			spriteSheet = ImageIO.read(new File("assets/braidsheet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		defaultWidth = spriteSheet.getWidth()/7;
		defaultHeight = spriteSheet.getHeight()/4;
		
		currentSpriteIndex = 0;
	}

	@Override
	protected void onUpdate() {
		
	}

	@Override
	protected void onRender(Graphics2D g2d) {
		
		Dimension preferredSize = JPANEL.getPreferredSize();
		
		int panelWidth = JPANEL.getWidth();
		int panelHeight = JPANEL.getHeight();
		
		Vec2 worldPos = getWorldPosition();
		BufferedImage currentSprite = getCurrentSprite();
		
		int pixelX, pixelY, pixelWidth, pixelHeight;
		
		pixelX = (int)(worldPos.x * panelWidth);
		pixelY = (int)(worldPos.y * panelHeight);
		
		pixelWidth = (int) (defaultWidth * panelWidth/preferredSize.getWidth());
		pixelHeight = (int) (defaultHeight * panelHeight/preferredSize.getHeight());
		
		g2d.drawImage(currentSprite, pixelX, pixelY, pixelWidth, pixelHeight, null);
		
		//currentSpriteIndex = (currentSpriteIndex+1)%27;
		
		if (skip) {
			skip = false;
		} else {
			currentSpriteIndex = (currentSpriteIndex+1)%27;
			skip = true;
		}
		
	}
	
	protected BufferedImage getCurrentSprite() {
		
		int width = (int) defaultWidth;
		int height = (int) defaultHeight;
		
		int row = currentSpriteIndex/7;
		int col = currentSpriteIndex%7;
		
		return spriteSheet.getSubimage(col*width, row * height, (int)defaultWidth, (int)defaultHeight);
	}
	
	private boolean skip = false;
	
	private int currentSpriteIndex;
	
	private final float defaultWidth;
	private final float defaultHeight;
	private BufferedImage spriteSheet;

}
