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

	private GameState state;
	private MainMenu mainMenu;
	private ModeMenu modeMenu;
	private PlayersMenu playersMenu;
	private Board board;

	private BoardState boardModel;
	
	public GameManager(MainGame mg) {
		mainGame = mg;
		this.state = GameState.START;
		this.mainMenu = new MainMenu(); // active and visible
		this.modeMenu = new ModeMenu();
		this.playersMenu = new PlayersMenu();
		this.boardModel = new BoardState();
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
		mainMenu.setActiveVisible(false);
		modeMenu.setActiveVisible(true);
		state = GameState.nextState(state);
	}
	
	public void activatePlayers(String mode, int boardRows, int boardColumns, int victoryCondition) {		
		boardModel.initialiseMode(boardRows, boardColumns, victoryCondition, mode);
		board.initialiseColumnsRows();
		
		mainGame.AddGameObject(board);
		board.setActiveVisible(false);			
		
		modeMenu.setActiveVisible(false);
		playersMenu.setActiveVisible(true);
		state = GameState.nextState(state);
	}

	public void activateBoard(int numPlayers, HashMap<Integer, Player> players) {
		boardModel.initialiseMode(boardModel.getBoardRow(), boardModel.getBoardColumn(),
				boardModel.getVictoryCondition(), boardModel.getMode());
		board.initialiseColumnsRows();
		boardModel.initialisePlayers(numPlayers, players);
		
		mainGame.AddGameObject(board);
		board.setActiveVisible(false);
		
		playersMenu.setActiveVisible(false);
		board.setActiveVisible(true);
		state = GameState.nextState(state);			
	}

	public void activateStart() { // go back to start menu
		board.setActiveVisible(false);
		mainMenu.setActiveVisible(true);
		state = GameState.nextState(state);			
	}
	
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
			board.setActiveVisible(false);
			playersMenu.setActiveVisible(true);
			board = new Board(boardModel);
		}
		state = GameState.prevState(state);
	}
	
	public void activateReset() {
		boardModel.reset();
		board.reset();
	}
	
	public void victorious(int id) {
		board.victorious(id);
	}
}
