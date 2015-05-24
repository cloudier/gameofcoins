package gameObjects;

import gameEngine.UIObject;
import gameEngine.Vec2;

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

public class MainMenuNewGame extends UIObject {

//	private BufferedImage normalImage;
//	private BufferedImage hoverImage;

	private float width;
	private float height;
	private Font font;

	public MainMenuNewGame() {
		width = .2f;
		height = .1f;
		position = new Vec2(0f, 0.5f);

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
//			normalImage = ImageIO.read(new File("assets/new game.png"));
//			hoverImage = ImageIO.read(new File("assets/new game on hover.png"));
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
	}

	@Override
	public void onMouseUp() {
		System.out.println("Clicked");
		GAME_MANAGER.activateMode();
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

//		g2d.drawImage(currentSprite, pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight, null);
		
		if (mouseSelected()) {
			g2d.setColor(Color.RED);
			g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
			g2d.setColor(Color.BLACK);
			g2d.setFont(this.font.deriveFont(worldPos.x * JPANEL.getWidth()/13));
			FontMetrics fm = g2d.getFontMetrics();
	        int x = ((fm.stringWidth("new game")) / 2);
	        int y = fm.getHeight()/4;
			g2d.drawString("new game", pixelX - x, pixelY + y);			
		} else {
			g2d.setColor(Color.BLUE);
			g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
			g2d.setColor(Color.WHITE);
			g2d.setFont(this.font.deriveFont(worldPos.x * JPANEL.getWidth()/13));
			FontMetrics fm = g2d.getFontMetrics();
	        int x = ((fm.stringWidth("new game")) / 2);
	        int y = fm.getHeight()/4;
			g2d.drawString("new game", pixelX - x, pixelY + y);
		}
	}

//	BufferedImage getCurrentSprite() {
//		if (mouseSelected())
//			return hoverImage;
//		else
//			return normalImage;
//	}

}
