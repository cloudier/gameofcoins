package gameObjects;
import gameEngine.UIObject;
import gameEngine.Vec2;

import java.awt.Color;
import java.awt.Graphics2D;


public class ModeMenu extends UIObject{
	private float width;
	private float height;

	private String mode;

	public ModeMenu() {
		position = new Vec2(0.5f, 0.2f);
		this.width = 0.04f;
		this.height = 0.05f;

		// select mode: normal/blitz
		//UIObject modeMenuSelect = new ModeMenuSelect();
		
		this.mode = "Normal";
		UIObject modeMenuNormal = new ModeMenuNormal();
		UIObject modeMenuBlitz = new ModeMenuBlitz();
		
		addChild(modeMenuNormal);
		addChild(modeMenuBlitz);
		// select victory condition: 4 <= n <= 10
//		UIObject modeMenuVictory = new ModeMenuVictory();
		
//		UIObject modeMenuBoard = new ModeMenuBoard();
		// select width of board: 4 <= n <= 20
		
		// select height of board: 4 <= n <= 20
		
//		UIObject modeMenuNext = new ModeMenuNext();
		// confirm settings and go to next window button
	}
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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
		g2d.drawString("Game Settings", pixelX - width*JPANEL.getWidth(), pixelY);
		g2d.drawString("Choose Mode", pixelX - width*JPANEL.getWidth(), pixelY);
	}

}
