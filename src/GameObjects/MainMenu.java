package gameObjects;
import gameEngine.UIObject;
import gameEngine.Vec2;

import java.awt.Color;
import java.awt.Graphics2D;


public class MainMenu extends UIObject {
	private float width;
	private float height;
	
	public MainMenu() {
		position = new Vec2(0.5f, 0.2f);
		this.width = 0.04f;
		this.height = 0.05f;
		UIObject mainMenuNewGame = new MainMenuNewGame();
		this.addChild(mainMenuNewGame);
		//UIObject MainMenuExit = new MainMenuExit(); // exit button
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
		g2d.drawString("Connect 4", pixelX - width*JPANEL.getWidth(), pixelY);
	}

}
