package gameObjects;

import gameEngine.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlayersMenu extends UIObject {
	private int numPlayers;
	private PlayersMenuNext playersMenuNext;
	private Font font;
	
	public PlayersMenu() {
		this.position = new Vec2(0.5f, 0.15f);
		this.numPlayers = 2;
		// player 1 object
			// select name
			// select human/ai
			// select difficulty
			// select coin color
		// player 2 object
		// player 3 object
		// player 4 object
		
		this.playersMenuNext = new PlayersMenuNext();
		this.addChild(playersMenuNext);
		
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

	public void activateBoard() {
		GAME_MANAGER.activateBoard(numPlayers);
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

		g2d.setColor(Color.BLACK);
		// replace this with an image
		Font headerFont = this.font.deriveFont(worldPos.x * JPANEL.getWidth()/5);
		g2d.setFont(headerFont);
		FontMetrics fm = g2d.getFontMetrics();
        int x = ((fm.stringWidth("Player Settings")) / 2);
		g2d.drawString("Player Settings", pixelX - x, pixelY);
		
		
	}

}
