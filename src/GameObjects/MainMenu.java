package gameObjects;
import gameEngine.UIObject;
import gameEngine.Vec2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.*;


public class MainMenu extends UIObject {
	private Font font;
	private int pixelShiftX;
	private int pixelShiftY;
	
	public MainMenu() {
		position = new Vec2(0.5f, 0.15f);
		pixelShiftX = 0;
		pixelShiftY = 0;
		UIObject mainMenuNewGame = new MainMenuNewGame();
		this.addChild(mainMenuNewGame);
		//UIObject MainMenuExit = new MainMenuExit(); // exit button
		try {
			this.font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("assets/fonts/Raleway-Regular.ttf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean mouseSelected() {
		return false;
	}

	@Override
	public void onMouseDown() {
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
		
		Vec2 mousePos = getScaledMousePosition();
		if (mousePos == null) {
			if (pixelShiftX < 0) {
				pixelShiftX += 1;
			} else if (pixelShiftX > 0) {
				pixelShiftX -= 1;
			}
			
			if (pixelShiftY < 0) {
				pixelShiftY += 1;
			} else if (pixelShiftY > 0) {
				pixelShiftY -= 1;
			}
			
			g2d.setColor(Color.BLACK);
			// replace this with an image
			Font headerFont = this.font.deriveFont((float) JPANEL.getWidth()/5);
			g2d.setFont(headerFont);
			FontMetrics fm = g2d.getFontMetrics();
	        int x = ((fm.stringWidth("Connect 4")) / 2);
			g2d.drawString("Connect 4", pixelX - x + pixelShiftX, pixelY + pixelShiftY);		
		} else {
			float shiftX = -mousePos.x/64;
			float shiftY = -mousePos.y/64;
			float currentPixelShiftX = (int) (shiftX * JPANEL.getWidth());
			float currentPixelShiftY = (int) (shiftY * JPANEL.getHeight());
			
			if (pixelShiftX < currentPixelShiftX) {
				pixelShiftX += 1;
			} else if (pixelShiftX > currentPixelShiftX) {
				pixelShiftX -= 1;
			}

			if (pixelShiftY < currentPixelShiftY) {
				pixelShiftY += 1;
			} else if (pixelShiftY > currentPixelShiftY) {
				pixelShiftY -= 1;
			}
			
			g2d.setColor(Color.BLACK);
			// replace this with an image
			Font headerFont = this.font.deriveFont((float) JPANEL.getWidth()/5);
			g2d.setFont(headerFont);
			FontMetrics fm = g2d.getFontMetrics();
	        int x = ((fm.stringWidth("Connect 4")) / 2);
			g2d.drawString("Connect 4", pixelX - x + pixelShiftX, pixelY + pixelShiftY);
		}
	}

}
