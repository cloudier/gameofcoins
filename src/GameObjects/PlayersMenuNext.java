package gameObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import gameEngine.UIObject;
import gameEngine.Vec2;

public class PlayersMenuNext extends UIObject {

	private float width;
	private float height;
	private Font font;
	// confirm settings and go to next window button
	
	public PlayersMenuNext() {
		this.position = new Vec2(0f, 0.6f);
		this.width = 0.3f;
		this.height = 0.1f;
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
		((PlayersMenu) this.getParent()).activateBoard();
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

		if (mouseSelected()) {
			g2d.setColor(Color.RED);
			g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
			g2d.setColor(Color.BLACK);
			g2d.setFont(this.font.deriveFont(worldPos.x * JPANEL.getWidth()/10));
			FontMetrics fm = g2d.getFontMetrics();
	        int x = ((fm.stringWidth("confirm")) / 2);
	        int y = fm.getHeight()/4;
			g2d.drawString("confirm", pixelX - x, pixelY + y);			
		} else {
			g2d.setColor(Color.BLUE);
			g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
			g2d.setColor(Color.WHITE);
			g2d.setFont(this.font.deriveFont(worldPos.x * JPANEL.getWidth()/10));
			FontMetrics fm = g2d.getFontMetrics();
	        int x = ((fm.stringWidth("confirm")) / 2);
	        int y = fm.getHeight()/4;
			g2d.drawString("confirm", pixelX - x, pixelY + y);
		}

	}

}
