package gameObjects;
import gameEngine.RectButton;
import gameEngine.UIObject;
import gameEngine.Vec2;

import java.awt.Graphics2D;
import java.awt.Image;


public class MainMenu extends UIObject {
	
	RectButton mainMenuButton;
	
	public MainMenu() {
		position = new Vec2(0.5f, 0.5f);
		createButtons();
	}
	
	@Override
	public boolean mouseSelected() {
		return false;
	}

	@Override
	public void onMouseDown() {	}

	@Override
	public void onMouseUp() { }

	@Override
	protected void onUpdate() { }

	@Override
	protected void onRender(Graphics2D g2d) {
		Image mainTitle = IMAGE_STORE.GetScaledImage("mainTitle", JPANEL.getWidth(), JPANEL.getHeight());
		g2d.drawImage(mainTitle, 0, 0, null);
		
	}
	
	private void createButtons()
	{
		mainMenuButton = new RectButton("newGame", "newGameSelected", -0.15f, 0.35f, 0.3f, 0.1f) {
			@Override
			public void onMouseDown() {
				GAME_MANAGER.activateMode();
			}
		};
		
		this.addChild(mainMenuButton);
	}

}
