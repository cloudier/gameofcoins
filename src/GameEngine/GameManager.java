package gameEngine;

import gameObjects.*;

/**
 * Manages the state of the game.
 * 
 * @author ivormetcalf
 *
 */
public class GameManager {

	/*
	 * States: start - menu that you start in. new game and exit button.
	 * mode - menu that you select mode, board size and victory condition in.
	 * players - select number of players. select settings for a player - player name, type (AI/human).
	 * if AI choose difficulty.
	 * if human choose coin colour/type.
	 * board - game is running.
	 */
	private MainGame mainGame;

	private String state;
	private UIObject mainMenu;
	private UIObject modeMenu;
	private UIObject playersMenu;
	private UIObject board;

	private BoardModel boardModel;
	
	public GameManager(MainGame mg) {
		mainGame = mg;
		this.state = "start";
		this.mainMenu = new MainMenu(); // active and visible
		this.modeMenu = new ModeMenu();
		this.playersMenu = new PlayersMenu();
		this.board = new Board();
		
		mainGame.AddGameObject(mainMenu);
		mainGame.AddGameObject(modeMenu);
		modeMenu.visible = false;
		modeMenu.active = false;
		mainGame.AddGameObject(playersMenu);
		playersMenu.visible = false;
		playersMenu.active = false;
		mainGame.AddGameObject(board);
		board.visible = false;
		board.active = false;
	}

	/**
	 * new game button has been pressed - go to player settings
	 * @param mode
	 * @param boardRows
	 * @param boardColumns
	 */
	public void activateMode() {
		if (state.equals("start")) {
			mainMenu.setActiveVisible(false);
			modeMenu.setActiveVisible(true);
			state = "mode";
		}
	}
	
	public void activatePlayers(String mode, int boardRows, int boardColumns, int victoryCondition) {		
		boardModel = new BoardModel(boardRows, boardColumns, victoryCondition);

		if (state.equals("mode")) {
			modeMenu.setActiveVisible(false);
			playersMenu.setActiveVisible(true);
			this.state = "players";
		}
	}
	
	public void activateBoard(int numPlayers) {
		boardModel.setNumPlayers(numPlayers);

		// make players
		
		// set player types
		// set AI difficulties
		// set human chars
		
		if (state.equals("players")) {
			playersMenu.setActiveVisible(false);
			board.setActiveVisible(true);
			this.state = "board";			
		}
	}

	public void activateStart() { // game has finished or is being reset - go back to start menu? maybe go back to mode
		if (state.equals("board")) {
			board.setActiveVisible(false);
			mainMenu.setActiveVisible(true);
			state = "start";			
		}
	}
}
