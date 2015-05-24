package gameObjects;
import gameEngine.UIObject;
import gameEngine.Vec2;

import java.awt.Color;
import java.awt.Graphics2D;


public class ModeMenu extends UIObject{
	private float width;
	private float height;

	private String mode;
	private int victoryCondition;
	private int boardWidth;
	private int boardHeight;

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
		UIObject modeMenuVictory = new ModeMenuVictory();
		
		// select width of board: 4 <= n <= 20
		// select height of board: 4 <= n <= 20
		UIObject modeMenuBoard = new ModeMenuBoard();

		// confirm settings and go to next window button		
		UIObject modeMenuNext = new ModeMenuNext();
		modeMenuNext.position = new Vec2(0f, 0.5f);
		addChild(modeMenuNext);

	}
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setVictoryCondition(int victoryCondition) {
		this.victoryCondition = victoryCondition;
	}

	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}

	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public int getVictoryCondition() {
		return victoryCondition;
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public void activatePlayers() {
		GAME_MANAGER.activatePlayers(mode, boardHeight, boardWidth, victoryCondition);
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
		g2d.drawString("Choose Mode", pixelX - width*JPANEL.getWidth(), pixelY + height*JPANEL.getHeight());
	}

}
