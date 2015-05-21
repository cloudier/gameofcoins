package gameEngine;

public class BoardModel {
	
	private static final int DEFAULT_ROWS = 6;
	private static final int DEFAULT_COLUMNS = 7;
	private static final int DEFAULT_NUM_PLAYERS = 2;
	private static final int DEFAULT_STARTING_PLAYER = 1;
	private static final int DEFAULT_VICTORY_CONDITION = 4;

	private int[][] boardModel;
	private int boardRow;
	private int boardColumn;
	private int victoryCondition;
	private int numPlayers;
	private int currentPlayer;
	private boolean gameOver;

	public BoardModel(int boardRow, int boardColumn, int victoryCondition,
			int numPlayers, int currentPlayer) {
		this.boardModel = new int[boardRow][boardColumn];
		this.boardRow = boardRow;
		this.boardColumn = boardColumn;
		this.victoryCondition = victoryCondition;
		this.numPlayers = numPlayers;
		this.currentPlayer = currentPlayer;
		this.gameOver = false;
	}
	
	public BoardModel(int boardRow, int boardColumn, int victoryCondition) {
		this(boardRow, boardColumn, victoryCondition, DEFAULT_NUM_PLAYERS, DEFAULT_STARTING_PLAYER);
	}
	
	public BoardModel() {
		this(DEFAULT_ROWS, DEFAULT_COLUMNS, DEFAULT_VICTORY_CONDITION, DEFAULT_NUM_PLAYERS, DEFAULT_STARTING_PLAYER);
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
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
					System.out.println(this.currentPlayer + " won!");
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
	}

	// check if a player wins
	public boolean isVictorious(int currentPlayer) {
		int sum = 0;

		// checks vertical
		for (int c = 0; c < this.boardColumn; c++) {
			for (int r = 0; r < this.boardRow; r++) {
				if (boardModel[r][c] == currentPlayer) {
					sum++;
					if (sum == victoryCondition)
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
					if (sum == victoryCondition)
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
					if (sum == victoryCondition)
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
					if (sum == victoryCondition)
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

}
