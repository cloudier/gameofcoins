package GameEngine;

import GameObjects.*;

/**
 * Manages the state of the game.
 * 
 * @author ivormetcalf
 *
 */
public class GameManager {

	private String state;
	/*
	 * States: start - menu that you start in. new game and exit button. mode -
	 * menu that you select mode, board size and victory condition in. players -
	 * select number of players. player settings - select settings for a player
	 * - AI and difficulty, or human and coin.
	 */
	private UIObject mainMenu;
	private UIObject modeMenu;
	private int[][] boardModel;
	private int boardRow;
	private int boardColumn;
	private int numPlayers;
	private int currentPlayer;

	private MainGame mainGame;
	private Board board;

	private boolean gameOver;

	public GameManager(MainGame mg) {
		mainGame = mg;
	}

	public void NewGame() {
		board = new Board();
		mainGame.AddGameObject(board);

		this.state = "start";
		this.mainMenu = new MainMenu(); // active and visible
		this.modeMenu = new ModeMenu(); // active and visible

		mainGame.AddGameObject(mainMenu);

		// mg.AddGameObject(ModeMenu);
		// ModeMenu.visible = false;
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

	// put coin into the board
	public void putCoin(int column) {
		if (gameOver)
			return;
		System.out.println(gameOver);
		for (int row = 0; row < this.boardRow; row++) {
			if (boardModel[row][column] == 0) {
				boardModel[row][column] = currentPlayer;
				// System.out.println("Put coin in " + String.valueOf(row) + ","
				// + String.valueOf(column) + " by " + this.currentPlayer);
				if (this.isVictorious(this.currentPlayer)) {
					System.out.println(this.currentPlayer + " won!!!!");
					gameOver = true;
					// resetGame();
				}

				if (currentPlayer == numPlayers) {
					currentPlayer = 1;
				} else {
					currentPlayer++;
				}

				return;
			}
		}

		// System.out.println("Couldn't do it m8");
	}

	// check if a player wins
	public boolean isVictorious(int currentPlayer) {
		int sum = 0;

		// checks vertical
		for (int c = 0; c < this.boardColumn; c++) {
			for (int r = 0; r < this.boardRow; r++) {
				if (boardModel[r][c] == currentPlayer) {
					sum++;
					if (sum == 4)
						return true;
				} else {
					sum = 0;
				}
			}
		}

		// checks horizontal
		sum = 0;
		for (int r = 0; r < this.boardRow; r++) {
			for (int c = 0; c < this.boardColumn; c++) {
				if (boardModel[r][c] == currentPlayer) {
					sum++;
					if (sum == 4)
						return true;
				} else {
					sum = 0;
				}
			}
		}

		// checks diagonal left
		sum = 0;
		for (int c = -this.boardRow; c < this.boardColumn; c++) {
			for (int r = 0; r < this.boardRow; r++) {

				if (getCoin(r, c + r) == currentPlayer) {
					sum++;
					if (sum == 4)
						return true;
				} else {
					sum = 0;
				}
			}
		}

		// checks diagonal right
		sum = 0;
		for (int c = 0; c < this.boardColumn + this.boardRow; c++) {
			for (int r = 0; r < this.boardRow; r++) {

				if (getCoin(r, c - r) == currentPlayer) {
					sum++;
					if (sum == 4)
						return true;
				} else {
					sum = 0;
				}
			}
		}

		return false;
	}

	// gets the coin in a given row and column
	public int getCoin(int r, int c) {
		if (r < 0 || c < 0 || r >= this.boardRow || c >= this.boardColumn)
			return 0;

		return boardModel[r][c];
	}

	public int[][] getBoard() {
		return boardModel;
	}
}
