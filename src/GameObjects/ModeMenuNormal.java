package gameObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import gameEngine.*;

public class ModeMenuNormal extends UIObject{
	
//	private BufferedImage normalImage;
//	private BufferedImage hoverImage;
//	private BufferedImage selectedImage;
	
	private float width;
	private float height;
	private Font font;
	
	public ModeMenuNormal() {
		super();
		width = .2f;
		height = .1f;
		position = new Vec2(-width/2, 0.15f);

		try {
			this.font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("assets/fonts/Raleway-Regular.ttf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		try {
//			normalImage = ImageIO.read(new File("assets/ModeMenu/NormalUnselected.png"));
//			hoverImage = ImageIO.read(new File("assets/ModeMenu/NormalSelected.png"));
//			selectedImage =  ImageIO.read(new File("assets/ModeMenu/NormalSelected.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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

//		BufferedImage currentSprite = getCurrentSprite();
		
		if (((ModeMenu) this.getParent()).getMode().equals("Normal") || mouseSelected()) {
			g2d.setColor(Color.RED);
			g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
			g2d.setColor(Color.BLACK);
			g2d.setFont(this.font.deriveFont(worldPos.x * JPANEL.getWidth()/10));
			FontMetrics fm = g2d.getFontMetrics();
	        int x = ((fm.stringWidth("Normal")) / 2);
	        int y = fm.getHeight()/4;
			g2d.drawString("Normal", pixelX - x, pixelY + y);			
		} else if (!((ModeMenu) this.getParent()).getMode().equals("Normal")) {
			g2d.setColor(Color.BLUE);
			g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
			g2d.setColor(Color.WHITE);
			g2d.setFont(this.font.deriveFont(worldPos.x * JPANEL.getWidth()/10));
			FontMetrics fm = g2d.getFontMetrics();
	        int x = ((fm.stringWidth("Normal")) / 2);
	        int y = fm.getHeight()/4;
			g2d.drawString("Normal", pixelX - x, pixelY + y);			
		}
	}

//	BufferedImage getCurrentSprite() {		
//		GameObject gameObjParent = this.getParent();
//		if (gameObjParent instanceof ModeMenu) {
//			ModeMenu parent = (ModeMenu) gameObjParent;
//			if (parent.getMode().equals("Normal")) {
//				return selectedImage;
//			} else if (mouseSelected()) {
//				return hoverImage;
//			} else {
//				return normalImage;
//			}
//		} else {
//			System.err.println("Parent is not instance of ModeMenu");
//			return null;
//		}
//	}
}
