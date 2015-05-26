package gameEngine;

import gameObjects.*;

import java.util.*;

public class BoardState {

	private int[][] boardModel;
	private int rows;
	private int columns;
	private int victoryCondition;
	private int numPlayers;
	private int currentPlayer;
	private boolean gameOver;
	private boolean draw;
	private String mode;

	public BoardState() {
		this.gameOver = false;
		this.draw = false;
	}

	public boolean gameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public boolean isDraw() {
		return draw;
	}

	public void reset() {
		boardModel = new int[rows][columns];
		gameOver = false;
	}
	
	public void initialiseMode(int rows, int columns, int victoryCondition, String mode) {
		this.rows = rows;
		this.columns = columns;
		this.boardModel = new int[rows][columns];
		this.victoryCondition = victoryCondition;
		this.mode = mode;		
	}

	public void initialisePlayers(int numPlayers, HashMap<Integer, Player> players) {
		this.numPlayers = numPlayers;
		this.currentPlayer = 1;
	}
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getVictoryCondition() {
		return victoryCondition;
	}

	public void setVictoryCondition(int victoryCondition) {
		this.victoryCondition = victoryCondition;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getCurrentPlayer() {
		return this.currentPlayer;
	}

	// returns row that coin would be placed in
	// else returns -1
	public int getTopRow(int column) {
		if (gameOver)
			return -1;
		for (int row = 0; row < this.rows; row++) {
			if (boardModel[row][column] == 0) {
				return row;
			}
		}
		
		return -1;
	}
	
	public boolean isFilled() {
		for (int i = 0; i < columns; i++) {
			if (getTopRow(i) != -1) {
				return false;
			}
		}
		draw = true;
		return true;
	}
	
	// put coin into the board
	public void putCoin(int column) {
		if (gameOver)
			return;
		for (int row = 0; row < this.rows; row++) {
			if (boardModel[row][column] == 0) {
				boardModel[row][column] = currentPlayer;
				System.out.println("row " + row + " column " + column);
				if (this.isVictorious(this.currentPlayer) || isFilled()) {
					gameOver = true;
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

	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	// check if a player wins
	public boolean isVictorious(int currentPlayer) {
		int sum = 0;

		// checks vertical
		for (int c = 0; c < this.columns; c++) {
			sum = 0;
			
			for (int r = 0; r < this.rows; r++) {
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
		for (int r = 0; r < this.rows; r++) {
			sum = 0;

			for (int c = 0; c < this.columns; c++) {
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
		for (int c = -this.rows; c < this.columns; c++) {
			sum = 0;
			
			for (int r = 0; r < this.rows; r++) {

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
		for (int c = 0; c < this.columns + this.rows; c++) {
			sum = 0;
			
			for (int r = 0; r < this.rows; r++) {

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
		if (r < 0 || c < 0 || r >= this.rows || c >= this.columns)
			return 0;

		return boardModel[r][c];
	}
}