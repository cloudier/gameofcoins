package gameObjects;
import gameEngine.RectButton;
import gameEngine.UIObject;
import gameEngine.Vec2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;


public class MainMenu extends UIObject {
	
	RectButton mainMenuButton;
	
	public MainMenu() {
		position = new Vec2(0.5f, 0.3f);
		createButtons();
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
		Font headerFont = UIObject.font.deriveFont((float) JPANEL.getWidth()/5);
		g2d.setFont(headerFont);
		FontMetrics fm = g2d.getFontMetrics();
        int x = ((fm.stringWidth("Connect 4")) / 2);
		g2d.drawString("Connect 4", pixelX - x, pixelY);
	}
	
	private void createButtons()
	{
		mainMenuButton = new RectButton("newGame", "newGameHover", 0.1f, 0.1f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				GAME_MANAGER.activateMode();
			}
		};
		
		this.addChild(mainMenuButton);
	}

}
