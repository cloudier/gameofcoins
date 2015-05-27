package gameObjects;
import gameEngine.RectButton;
import gameEngine.UIObject;
import gameEngine.Vec2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// move positions into this class

public class ModeMenu extends UIObject{
	private float width;
	private float height;

	private String mode;
	private int victoryCondition;
	private int boardWidth;
	private int boardHeight;
	
	private Font font;
	
	private UIObject modeMenuNormal;
	private UIObject modeMenuBlitz;
	private RectButton modeMenuAngry;
	private UIObject back;
	
	

	public ModeMenu() {
		this.position = new Vec2(0.5f, 0.15f);
		this.width = 0.04f;
		this.height = 0.05f;

		// select mode: normal/blitz
		//UIObject modeMenuSelect = new ModeMenuSelect();
		
		this.mode = "Normal";
		modeMenuNormal = new ModeMenuNormal();
		modeMenuBlitz = new ModeMenuBlitz();
		
		modeMenuAngry = new RectButton("modeMenu_Angry") {
			@Override
			public void onMouseDown() {
				System.out.println("Bring on the anger!");
			}
		};
		
		addChild(modeMenuNormal);
		addChild(modeMenuBlitz);
		addChild(modeMenuAngry);
		
		this.victoryCondition = 4;
		// select victory condition: 4 <= n <= 10
		UIObject modeMenuVictory = new ModeMenuVictory();
		addChild(modeMenuVictory);
		
		this.boardWidth = 7;
		this.boardHeight = 6;
		// select width of board: 4 <= n <= 20
		// select height of board: 4 <= n <= 20
		UIObject modeMenuBoardSize = new ModeMenuBoardSize();
		addChild(modeMenuBoardSize);

		// confirm settings and go to next window button
		Button modeMenuNext = new ModeMenuNext(0.3f, 0.1f, new Vec2(0f, 0.775f), "Confirm");
		addChild(modeMenuNext);
		modeMenuNext.position = new Vec2(0f, 0.775f);
		
		back = new BackButton(0.1f, 0.1f, new Vec2(-0.35f, 0.775f));
		addChild(back);

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
	
	public int maxAllowedVictory() {
		if (this.boardWidth == 7 && this.boardHeight == 6) return 4;
		if (this.boardWidth == 14 && this.boardHeight == 12) return 5;
		if (this.boardWidth == 21 && this.boardHeight == 18) return 7;
		return 4;
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

		Font textFont = this.font.deriveFont(worldPos.x * JPANEL.getWidth()/5);
		g2d.setFont(textFont);
		FontMetrics fm = g2d.getFontMetrics();
        int x = ((fm.stringWidth("Game Settings")) / 2);
        int y = fm.getHeight()/4;
		g2d.drawString("Game Settings", pixelX - x, pixelY);
		
		textFont = this.font.deriveFont((float) JPANEL.getWidth()/20);
		g2d.setFont(textFont);
		fm = g2d.getFontMetrics();
        x = ((fm.stringWidth("Choose Mode")) / 2);
        y = fm.getHeight();
		g2d.drawString("Choose Mode", pixelX - x, pixelY + y*1);
        x = ((fm.stringWidth("Choose Victory Condition")) / 2);
		g2d.drawString("Choose Victory Condition", pixelX - x, pixelY + y*4);
        x = ((fm.stringWidth("Choose Board Size")) / 2);
		g2d.drawString("Choose Board Size", pixelX - x, pixelY + y*8);
	}

}
