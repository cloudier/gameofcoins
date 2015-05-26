package gameEngine;

import java.util.*;

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
	
	protected static final int DEFAULT_ROWS = 6;
	protected static final int DEFAULT_COLUMNS = 7;
	protected static final int DEFAULT_NUM_PLAYERS = 2;
	protected static final int DEFAULT_STARTING_PLAYER = 1;
	protected static final int DEFAULT_VICTORY_CONDITION = 4;
	protected static final String DEFAULT_MODE = "Normal";
	
	private MainGame mainGame;

	private String state;
	private MainMenu mainMenu;
	private ModeMenu modeMenu;
	private PlayersMenu playersMenu;
	private Board board;

	private BoardModel boardModel;
	
	public GameManager(MainGame mg) {
		mainGame = mg;
		this.state = "start";
		this.mainMenu = new MainMenu(); // active and visible
		this.modeMenu = new ModeMenu();
		this.playersMenu = new PlayersMenu();
		this.boardModel = new BoardModel();
		this.board = new Board(boardModel);
		
		mainGame.AddGameObject(mainMenu);
		mainMenu.setActiveVisible(true);
		mainGame.AddGameObject(modeMenu);
		modeMenu.setActiveVisible(false);
		mainGame.AddGameObject(playersMenu);
		playersMenu.setActiveVisible(false);
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
		boardModel.initialiseMode(boardRows, boardColumns, victoryCondition, mode);
		board.initialiseColumnsRows();
		mainGame.AddGameObject(board);
		board.setActiveVisible(false);
		
		if (state.equals("mode")) {
			modeMenu.setActiveVisible(false);
			playersMenu.setActiveVisible(true);
			this.state = "players";
		}
	}

	public void activateBoard(int numPlayers, HashMap<Integer, Player> players) {
		boardModel.initialisePlayers(numPlayers, players);
		
		if (state.equals("players")) {
			playersMenu.setActiveVisible(false);
			board.setActiveVisible(true);
			this.state = "board";			
		}
	}

	public void activateStart() { // go back to start menu
		if (state.equals("board")) {
			board.setActiveVisible(false);
			mainMenu.setActiveVisible(true);
			state = "start";			
		}
	}
}
