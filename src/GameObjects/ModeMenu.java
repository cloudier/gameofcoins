package gameObjects;
import gameEngine.GameObject;
import gameEngine.RectButton;
import gameEngine.UIObject;
import gameEngine.Vec2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ModeMenu extends GameObject {

	private String mode;
	private int victoryCondition;
	private int boardWidth;
	private int boardHeight;
	
	private Font font;
	
	private RectButton modeMenuNormal;
	private RectButton modeMenuAngry;
	
	private RectButton btn7by6;
	private RectButton btn14by12;
	private RectButton btn21by18;
	
	private RectButton back;
	private RectButton confirm;

	public ModeMenu() {
		this.position = new Vec2();
		
		this.mode = "Normal";
		
		this.victoryCondition = 4;
		// select victory condition: 4 <= n <= 10
		UIObject modeMenuVictory = new ModeMenuVictory();
		addChild(modeMenuVictory);
		
		this.boardWidth = 7;
		this.boardHeight = 6;
		// select width of board: 4 <= n <= 20
		// select height of board: 4 <= n <= 20

		try {
			this.font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("assets/fonts/Raleway-Regular.ttf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		createButtons();
	}
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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
	protected void onUpdate() { }

	@Override
	protected void onRender(Graphics2D g2d) {
		
		//Draw Background
		Image mainTitle = IMAGE_STORE.GetScaledImage("mainTitle", JPANEL.getWidth(), JPANEL.getHeight());
		g2d.drawImage(mainTitle, 0, 0, null);
		
//		Vec2 worldPos = getWorldPosition();
//		int pixelX = (int) ((worldPos.x+0.5f) * JPANEL.getWidth());
//		int pixelY = (int) ((worldPos.y+0.3f) * JPANEL.getHeight());
//
//		g2d.setColor(Color.WHITE);
//
//		Font textFont = this.font.deriveFont(worldPos.x * JPANEL.getWidth()/5);
//		g2d.setFont(textFont);
//		FontMetrics fm = g2d.getFontMetrics();
//        int x = ((fm.stringWidth("Game Settings")) / 2);
//        int y = fm.getHeight()/4;
//        
//		g2d.drawString("Game Settings", pixelX - x, pixelY);
//		
//		textFont = this.font.deriveFont((float) JPANEL.getWidth()/20);
//		g2d.setFont(textFont);
//		fm = g2d.getFontMetrics();
//        x = ((fm.stringWidth("Choose Mode")) / 2);
//        y = fm.getHeight();
//		g2d.drawString("Choose Mode", pixelX - x, pixelY + y*1);
//        x = ((fm.stringWidth("Choose Victory Condition")) / 2);
//		g2d.drawString("Choose Victory Condition", pixelX - x, pixelY + y*4);
//        x = ((fm.stringWidth("Choose Board Size")) / 2);
//		g2d.drawString("Choose Board Size", pixelX - x, pixelY + y*8);
	}

	private void createButtons()
	{
		modeMenuNormal = new RectButton("normal", "normalSelected", 0.3f, 0.4f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				modeMenuNormal.setDefaultImage("normalSelected");
				modeMenuAngry.setDefaultImage("angry");
				setMode("Normal");
			}
		};
		
		modeMenuAngry = new RectButton("angry", "angrySelected", 0.5f, 0.4f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				modeMenuNormal.setDefaultImage("normal");
				modeMenuAngry.setDefaultImage("angrySelected");
				setMode("Angry");
			}
		};
		
		btn7by6 = new RectButton("btn7by6", "btn7by6Selected", 0.2f, 0.6f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				btn7by6.setDefaultImage("btn7by6Selected");
				btn14by12.setDefaultImage("btn14by12");
				btn21by18.setDefaultImage("btn21by18");
				setBoardDim(7, 6);
			}
		};
		
		btn14by12 = new RectButton("btn14by12", "btn14by12Selected", 0.4f, 0.6f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				btn7by6.setDefaultImage("btn7by6");
				btn14by12.setDefaultImage("btn14by12Selected");
				btn21by18.setDefaultImage("btn21by18");
				setBoardDim(14, 12);
			}
		};
		
		btn21by18 = new RectButton("btn21by18", "btn21by18Selected", 0.6f, 0.6f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				btn7by6.setDefaultImage("btn7by6");
				btn14by12.setDefaultImage("btn14by12");
				btn21by18.setDefaultImage("btn21by18Selected");
				setBoardDim(21, 18);
			}
		};
		
		back = new RectButton("back", "backSelected", 0.1f, 0.85f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				GAME_MANAGER.back();
			}
		};
		
		confirm = new RectButton("confirm", "confirmSelected", 0.4f, 0.85f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				activatePlayers();
			}
		};
		
		addChild(modeMenuNormal);
		addChild(modeMenuAngry);
		
		addChild(btn7by6);
		addChild(btn14by12);
		addChild(btn21by18);
		
		addChild(back);
		addChild(confirm);
	}
	
	private void setBoardDim(int boardWidth, int boardHeight)
	{
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		if (getVictoryCondition() > maxAllowedVictory()) {
			setVictoryCondition(maxAllowedVictory());
		}
	}
	
}
