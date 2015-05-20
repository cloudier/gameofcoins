package GameEngine;

import GameObjects.*;

/**
 * Manages the state of the game.
 * @author ivormetcalf
 *
 */
public class GameManager {

	private String state;
	/*
	 * States:
	 * 	start - menu that you start in. new game and exit button.
	 *  mode - menu that you select mode, board size and victory condition in.
	 *  players - select number of players.
	 *  player settings - select settings for a player - AI and difficulty, or human and coin.
	 */
	private UIObject mainMenu;
	private UIObject modeMenu;
	private int[][] boardModel;
	
	private MainGame mainGame;
	private Board board;
	
	public GameManager(MainGame mg) {
		mainGame = mg;
	}
	
	public void NewGame()
	{
		board = new Board();
		mainGame.AddGameObject(board);
		
		this.state = "start";
		this.mainMenu = new MainMenu(); // active and visible
		this.modeMenu = new ModeMenu(); // active and visible
		
		mainGame.AddGameObject(mainMenu);
				
//		mg.AddGameObject(ModeMenu);
//		ModeMenu.visible = false;
	}
	
	public void activateNextState() {
		// test
		if (this.state.equals("start")) {
			
		}
		
		// if start, activate mode
		if (this.state.equals("start")) {
			modeMenu.active = true;
			modeMenu.visible = true;
			this.state = "mode";
		}
		// if mode, activate players
		if (this.state.equals("mode")) {
			
		}
	}
	
	// put victory condition and putcoin methods here
	
	public int[][] getBoard() {
		return boardModel;
	}
}
