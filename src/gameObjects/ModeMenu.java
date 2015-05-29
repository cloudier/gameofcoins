package gameObjects;
import gameEngine.GameObject;
import gameEngine.RectButton;
import gameEngine.UIObject;
import gameEngine.Vec2;

import java.awt.Graphics2D;
import java.awt.Image;

public class ModeMenu extends GameObject {

	private String mode;
	private int victoryCondition;
	private int boardWidth;
	private int boardHeight;
	
	private RectButton modeMenuNormal;
	private RectButton modeMenuAngry;
	
	private RectButton btn7by6;
	private RectButton btn14by12;
	private RectButton btn21by18;
	
	private RectButton back;
	private RectButton confirm;
	
	private RectButton four, five, six, seven;

	/**
	 * Create mode menu for the game panel
	 */
	public ModeMenu() {
		this.position = new Vec2();
		
		this.mode = "Normal";
		
		//this.victoryCondition = 4;
		// select victory condition: 4 <= n <= 10
//		UIObject modeMenuVictory = new ModeMenuVictory();
//		addChild(modeMenuVictory);
		
		this.boardWidth = 7;
		this.boardHeight = 6;
		// select width of board: 4 <= n <= 20
		// select height of board: 4 <= n <= 20

		createButtons();
		
		//Default Selections
		modeMenuNormal.onMouseDown();
		btn7by6.onMouseDown();
		four.onMouseDown();
	}
	
	/**
	 * Gets the currently selected mode
	 * @return string representing the currently selected mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * Sets the currently selected game mode
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * Sets the currently selected victory condition
	 * @param int representing the victory condition
	 */
	public void setVictoryCondition(int victoryCondition) {
		this.victoryCondition = victoryCondition;
	}

	/**
	 * Sets the currently selected width of the board
	 * @param int representing the board width
	 */
	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}

	/**
	 * Sets the currently selected height of the board
	 * @param int representing the board height
	 */
	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}

	/**
	 * Returns the currently selected victory condition
	 * @return int representing the number of consecutive coins
	 * required to win
	 */
	public int getVictoryCondition() {
		return victoryCondition;
	}
	
	/**
	 * Returns the maximum allowable victory condition given the current
	 * board width and height.
	 * @return int indicating the maximum allowable victory condition
	 */
	public int maxAllowedVictory() {
		if (this.boardWidth == 7 && this.boardHeight == 6) return 4;
		if (this.boardWidth == 14 && this.boardHeight == 12) return 5;
		if (this.boardWidth == 21 && this.boardHeight == 18) return 7;
		return 4;
	}
	
	/**
	 * Returns the currently selected board width
	 * @return width of the board as an int
	 */
	public int getBoardWidth() {
		return boardWidth;
	}

	/**
	 * Returns the currently selected board height
	 * @return height of the board as an int
	 */
	public int getBoardHeight() {
		return boardHeight;
	}

	/**
	 * Tells the game manager to move to the next window and to set
	 * the properties of the board.
	 */
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
	}

	/**
	 * Creates the buttons for the mode menu
	 */
	private void createButtons(){
		modeMenuNormal = new RectButton("normal", "normalSelected", 0.2f, 0.35f, 0.3f, 0.15f) {
			@Override
			public void onMouseDown() {
				modeMenuNormal.setDefaultImage("normalSelected");
				modeMenuAngry.setDefaultImage("angry");
				
				btn7by6.setActiveVisible(true);
				btn14by12.setActiveVisible(true);
				btn21by18.setActiveVisible(true);
				
				setMode("Normal");
			}
		};
		
		modeMenuAngry = new RectButton("angry", "angrySelected", 0.5f, 0.35f, 0.3f, 0.15f) {
			@Override
			public void onMouseDown() {
				modeMenuNormal.setDefaultImage("normal");
				modeMenuAngry.setDefaultImage("angrySelected");
				
				btn7by6.setActiveVisible(false);
				btn14by12.setActiveVisible(false);
				btn21by18.setActiveVisible(false);
				
				setMode("Angry");
			}
		};
		
		btn7by6 = new RectButton("btn7by6", "btn7by6Selected", 0.2f, 0.55f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				btn7by6.setDefaultImage("btn7by6Selected");
				btn14by12.setDefaultImage("btn14by12");
				btn21by18.setDefaultImage("btn21by18");
				setBoardDim(7, 6);
			}
		};
		
		btn14by12 = new RectButton("btn14by12", "btn14by12Selected", 0.4f, 0.55f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				btn7by6.setDefaultImage("btn7by6");
				btn14by12.setDefaultImage("btn14by12Selected");
				btn21by18.setDefaultImage("btn21by18");
				setBoardDim(14, 12);
			}
		};
		
		btn21by18 = new RectButton("btn21by18", "btn21by18Selected", 0.6f, 0.55f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				btn7by6.setDefaultImage("btn7by6");
				btn14by12.setDefaultImage("btn14by12");
				btn21by18.setDefaultImage("btn21by18Selected");
				setBoardDim(21, 18);
			}
		};
		
		four = new RectButton("four", "fourSelected", 0.3f, 0.7f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				four.setDefaultImage("fourSelected");
				five.setDefaultImage("five");
				six.setDefaultImage("six");
				seven.setDefaultImage("seven");
				setVictoryCondition(4);
			}
		};
		five = new RectButton("five", "fiveSelected", 0.4f, 0.7f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				four.setDefaultImage("four");
				five.setDefaultImage("fiveSelected");
				six.setDefaultImage("six");
				seven.setDefaultImage("seven");
				setVictoryCondition(5);
			}
		};
		six = new RectButton("six", "sixSelected", 0.5f, 0.7f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				four.setDefaultImage("four");
				five.setDefaultImage("five");
				six.setDefaultImage("sixSelected");
				seven.setDefaultImage("seven");
				setVictoryCondition(6);
			}
		};
		seven = new RectButton("seven", "sevenSelected", 0.6f, 0.7f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				four.setDefaultImage("four");
				five.setDefaultImage("five");
				six.setDefaultImage("six");
				seven.setDefaultImage("sevenSelected");
				setVictoryCondition(7);
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
		
		addChild(four);
		addChild(five);
		addChild(six);
		addChild(seven);
		
		addChild(back);
		addChild(confirm);
	}
	
	/**
	 * Sets the board dimensions
	 * @param width of the board
	 * @param height of the board
	 */
	private void setBoardDim(int boardWidth, int boardHeight){
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		if (getVictoryCondition() > maxAllowedVictory()) {
			setVictoryCondition(maxAllowedVictory());
		}
	}
}
