package gameObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import gameEngine.GameObject;
import gameEngine.UIObject;
import gameEngine.Vec2;

public class ModeMenuBoardHeightNumber extends UIObject{

	private int number;
	private float width;
	private float height;
	private String stringOfNumber;
	
	private Font font;
	
	public ModeMenuBoardHeightNumber (int number) {
		this.number = number;
		this.stringOfNumber = Integer.toString(number);
		width = .1f;
		height = .1f;
		
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
		GameObject gameObjParent = this.getParent().getParent();
		if (gameObjParent instanceof ModeMenu) {
			ModeMenu parent = (ModeMenu) gameObjParent;
			parent.setBoardHeight(number);
		} else {
			System.err.println("Parent of parent is not instance of ModeMenu");
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
		
		if (((ModeMenu) this.getParent().getParent()).getBoardHeight() == number || mouseSelected()) {
			g2d.setColor(Color.RED);
			g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
			g2d.setColor(Color.BLACK);
			g2d.setFont(this.font.deriveFont((float) JPANEL.getWidth()/30));
			FontMetrics fm = g2d.getFontMetrics();
	        int x = ((fm.stringWidth(stringOfNumber)) / 2);
	        int y = fm.getHeight()/4;
			g2d.drawString(stringOfNumber, pixelX - x, pixelY + y);			
		} else if (((ModeMenu) this.getParent().getParent()).getBoardHeight() != number) {
			g2d.setColor(Color.BLUE);
			g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
			g2d.setColor(Color.WHITE);
			g2d.setFont(this.font.deriveFont((float) JPANEL.getWidth()/30));
			FontMetrics fm = g2d.getFontMetrics();
	        int x = ((fm.stringWidth(stringOfNumber)) / 2);
	        int y = fm.getHeight()/4;
			g2d.drawString(stringOfNumber, pixelX - x, pixelY + y);			
		}
	}

}
