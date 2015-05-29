package gameEngine;

import java.util.*;

import gameObjects.*;

/**
 * Manages the state of the game.
 * 
 * @author ivormetcalf
 *
 */
public class WindowManager {

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

	private GameState state;
	private MainMenu mainMenu;
	private ModeMenu modeMenu;
	private PlayersMenu playersMenu;
	private Game board;

	private BoardState boardModel;
	
	/**
	 * Constructs a window manager.
	 * @param mg
	 */
	public WindowManager(MainGame mg) {
		mainGame = mg;
		this.state = GameState.START;
		this.mainMenu = new MainMenu(); // active and visible
		this.modeMenu = new ModeMenu();
		this.playersMenu = new PlayersMenu();
		this.boardModel = new BoardState();
		
		mainGame.AddGameObject(mainMenu);
		mainMenu.setActiveVisible(true);
		mainGame.AddGameObject(modeMenu);
		modeMenu.setActiveVisible(false);
		mainGame.AddGameObject(playersMenu);
		playersMenu.setActiveVisible(false);
	}

	/**
	 * New game button has been pressed.
	 * Hides the main menu and displays the mode menu.
	 * 
	 * @param mode
	 * @param boardRows
	 * @param boardColumns
	 */
	public void activateMode() {
		mainMenu.setActiveVisible(false);
		modeMenu.setActiveVisible(true);
		state = GameState.nextState(state);
	}
	
	/**
	 * Used to set the settings of the board state
	 * as the application moves to the players menu.
	 * Hides the previous menu and displays the
	 * players menu.
	 * 
	 * @param mode
	 * @param boardRows
	 * @param boardColumns
	 * @param victoryCondition
	 */
	public void activatePlayers(String mode, int boardRows, int boardColumns, int victoryCondition) {		
		boardModel.initialiseMode(boardRows, boardColumns, victoryCondition, mode);
		if (mode.equals("Normal")) {
			this.board = new NormalGame(boardModel);
		} else if (mode.equals("Angry")) {
			this.board = new AngryGame(boardModel);
		}
		
		mainGame.AddGameObject(board);
		board.setActiveVisible(false);			
		
		modeMenu.setActiveVisible(false);
		playersMenu.setActiveVisible(true);
		state = GameState.nextState(state);
	}

	/**
	 * Used to update the board state and the
	 * board as the application moves to the
	 * board.
	 * Hides the players menu and displays the
	 * board.
	 * 
	 * @param numPlayers
	 * @param players
	 */
	public void activateBoard(int numPlayers, HashMap<Integer, Player> players) {
		boardModel.initialiseMode(boardModel.getBoardRow(), boardModel.getBoardColumn(),
				boardModel.getVictoryCondition(), boardModel.getMode());
		if (!board.visible && !board.active) {
			if (boardModel.getMode().equals("Normal")) {
				this.board = new NormalGame(boardModel);
			} else if (boardModel.getMode().equals("Angry")) {
				this.board = new AngryGame(boardModel);
			}
			board.initialiseColumnsRows();
		}
		boardModel.initialisePlayers(numPlayers, players);
		
		mainGame.AddGameObject(board);
		playersMenu.setActiveVisible(false);
		board.setActiveVisible(true);
		state = GameState.nextState(state);
	}

	/**
	 * Hides the board when the Main Menu button
	 * is pressed in the board screen.
	 */
	public void activateStart() { // go back to start menu
		boardModel.reset();
		board.reset();

		board.setActiveVisible(false);
		mainMenu.setActiveVisible(true);
		state = GameState.nextState(state);			
	}
	
	/**
	 * Used to hide and display the appropriate
	 * UIObjects when the back button is pressed.
	 */
	public void back() {
		if (state == GameState.START) {
			mainMenu.setActiveVisible(false);
			board.setActiveVisible(true);
		} else if (state == GameState.MODE) {
			modeMenu.setActiveVisible(false);
			mainMenu.setActiveVisible(true);
		} else if (state == GameState.PLAYERS) {
			playersMenu.setActiveVisible(false);
			modeMenu.setActiveVisible(true);
		} else if (state == GameState.BOARD) {
			boardModel.reset();
			board.reset();

			board.setActiveVisible(false);
			playersMenu.setActiveVisible(true);
		}
		state = GameState.prevState(state);
	}
	
	/**
	 * Used to update the boardModel and board
	 * when the reset button is pressed when the
	 * board is displayed.
	 */
	public void activateReset() {
		boardModel.reset();
		board.reset();
	}
}
