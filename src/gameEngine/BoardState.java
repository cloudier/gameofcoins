package gameEngine;

import gameObjects.*;
import ai.*;	

import java.util.*;

/**
 * 
 * @author
 *
 */
public class BoardState {

	private int[][] boardGame;
	private AI artificialIntelligence;
	private int currentPlayer;
	private int boardRow;
	private int boardColumn;
	
	private int victoryCondition;
	private int numPlayers;
	private boolean gameOver;
	private boolean draw;
	private String mode;

	private int ai_Selection;
	private ArrayList<Integer> winningColumn;
	private ArrayList<Integer> winningRow;

	private HashMap<Integer, Player> players;
	
	/**
	 * Construct a BoardState Object.
	 * 
	 * Sets game over and draw status to false.
	 */
	public BoardState() {
		this.gameOver = false;
		this.draw = false;
		this.winningColumn = new ArrayList<Integer>();
		this.winningRow = new ArrayList<Integer>();
	}

	/**
	 * Constructs a board state given the old state.
	 * @param oldState
	 * @param player
	 */
	public BoardState(BoardState oldState, int player){
		this.boardRow = oldState.getBoardRow();
		this.boardColumn = oldState.getBoardColumn();
		this.victoryCondition = oldState.getVictoryCondition();
		this.mode = oldState.getMode();		
		this.currentPlayer = player;
		this.numPlayers = oldState.getNumPlayers();
		
		this.boardGame = new int[this.boardRow][this.boardColumn];
		this.winningColumn = new ArrayList<Integer>();
		this.winningRow = new ArrayList<Integer>();
		
		for (int row = 0; row < this.boardRow; row++){
			for (int col = 0; col < this.boardColumn; col++){
				this.boardGame[row][col] = oldState.getBoard()[row][col];
			}
		}		
	}

	/**
	 * This function resets the game.
	 */
	public void reset() {
		this.currentPlayer = 1;
		this.gameOver = false;
		
		this.boardGame = new int[this.boardRow][this.boardColumn];
		this.winningColumn.clear();
		this.winningRow.clear();
	}
	
	/**
	 * Initialises the mode and board dimensions of the board.
	 * @param boardRow
	 * @param boardColumn
	 * @param victoryCondition
	 * @param mode
	 */
	public void initialiseMode(int boardRow, int boardColumn, int victoryCondition, String mode) {
		this.boardRow = boardRow;
		this.boardColumn = boardColumn;
		this.victoryCondition = victoryCondition;
		this.mode = mode;	
		this.gameOver = false;
		this.draw = false;
		
		this.boardGame = new int[boardRow][boardColumn];
		this.winningColumn.clear();
		this.winningRow.clear();
	}

	/**
	 * Initialises the players of the game.
	 * @param numPlayers
	 * @param players
	 */
	public void initialisePlayers(int numPlayers, HashMap<Integer, Player> players) {
		this.numPlayers = numPlayers;
		this.currentPlayer = 1;
		this.players = players;

		for (Player p : players.values()) {
			if (p.getPlayerType() == PlayerType.AI) {
				if (p.getDifficulty() == 0) {
			    	artificialIntelligence = new SmartRandomAI();				//Smart Random AI					
				} else if (p.getDifficulty() == 1) {
					artificialIntelligence = new AlphaBetaMiniMaxAI(7);			//Alpha Beta AI (Depth 7)					
				} else if (p.getDifficulty() == 2) {
					artificialIntelligence = new MonteCarloAI(1000);		//Monte Carlo AI (Search 1000)
				}
			}
		}
	}

	/**
	 * Gets the mode of the board.
	 * @return the mode of the board as a string
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * Sets the mode of the board.
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * The victory condition (number of consecutive coins to win)
	 * of the board.
	 * @return the number of consecutive coins required to win
	 */
	public int getVictoryCondition() {
		return victoryCondition;
	}

	/**
	 * Sets the victory condition (the number of consecutive coins
	 * required to win).
	 * @param the number of consecutive coins required to win
	 */
	public void setVictoryCondition(int victoryCondition) {
		this.victoryCondition = victoryCondition;
	}

	/**
	 * Returns the number of rows in the board.
	 * @return the number of rows in the board
	 */
	public int getBoardRow() {
		return boardRow;
	}

	/**
	 * Sets the number of rows in the board.
	 * @param number of rows in the board
	 */
	public void setBoardRow(int boardRow) {
		this.boardRow = boardRow;
	}
	
	/**
	 * Returns the number of columns in the board.
	 * @return the number of columns in the board
	 */
	public int getBoardColumn() {
		return boardColumn;
	}

	/**
	 * Sets the number of columns in the board.
	 * @param the number of columns in the board
	 */
	public void setBoardColumn(int boardColumn) {
		this.boardColumn = boardColumn;
	}
	
	/**
	 * Returns the number of players in the game.
	 * @return number of players in the game
	 */
	public int getNumPlayers() {
		return this.numPlayers;
	}

	/**
	 * Sets the number of players in the game
	 * @param number of players in the game
	 */
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	/**
	 * Returns the current player
	 * @return player object representing the current player
	 */
	public Player getCurrentPlayer() {
		return this.players.get(currentPlayer);
	}
	
	/**
	 * Returns the other player
	 * @return player object representing the other player
	 */
	public Player getOtherPlayer() {
		if (currentPlayer == numPlayers)
			return this.players.get(1);
		else
			return this.players.get(currentPlayer);
	}
	
	/**
	 * Returns the id representing the current player
	 * @return current player's id as an int
	 */
	public int getCurrentPlayerID() {
		return this.currentPlayer;
	}

	/**
	 * Sets the current player.
	 * 
	 * @param currentPlayer The given current player.
	 */
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Get the board of the current BoardState.
	 * 
	 * @return The Board of the current BoardStaet.
	 */
	public int[][] getBoard(){
		return this.boardGame;
	}
	
	/**
	 * Checks if the game is ended up as a draw.
	 * 
	 * @return Whether the game ended up as a draw.
	 */
	public boolean isDraw() {
		return draw;
	}

	/**
	 * Sets the game draw status.
	 * 
	 * @param draw The given game draw status.
	 */
	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	/**
	 * Checks if the game is over.
	 * 
	 * @return Whether game is over.
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * Sets the game over status.
	 * 
	 * @param gameOver The given game over status.
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * This function acts as a helper for the AI where it puts 
	 * a coin given the Column and the Player ID.
	 * 
	 * @param column The column in which the coins should be put into.
	 * @param playerID The ID of the player that wants to put the coin.
	 */
	public void putCoinForAI(int column, int playerID){
		for(int row = 0; row < this.boardRow; row++){
			if(boardGame[row][column] == 0){
				boardGame[row][column] = playerID;
				break;
			}
		}
	}

	/**
	 * Puts a coin in the given column
	 * @param column
	 * @return whether or not the operation was successful
	 */
	public boolean putCoin(int column) {		
		user_Move(column);

		if (gameOver)
			return true;
		else
			return false;
	}

	/**
	 * Used by the AI to simulate a user move
	 * @param column to put coin in
	 */
	private void user_Move(int column){
		for(int row = 0; row < this.boardRow; row++){
			if(boardGame[row][column] == 0){
				boardGame[row][column] = this.currentPlayer;
				break;
			}
			else if (row == (this.boardRow - 1)){
				//full column
				break;
			}
		}
		
		if (this.isWinner(this.currentPlayer) || isBoardFull()) {
			gameOver = true;
			return;
		}
		
		if (this.currentPlayer == numPlayers) {
			this.currentPlayer = 1;
		} 
		else {		
			this.currentPlayer++;
		}
	}
	
	/**
	 * This function calls the putCoin function in AI to
	 * determine the column the AI should put the coin into.
	 * 
	 * @return Return the column that the AI has chose.
	 */
	public int ai_Move(){ // returns column
		int column = artificialIntelligence.putCoin(this, this.currentPlayer);	
		//output();
		return column;
	}

	/**
	 * This function gets column that the AI chose in the last move.
	 * 
	 * @return The column that is chosen by the AI in the last move.
	 */
	public int getLastAIMove(){
		return this.ai_Selection;
	}

	/**
	 * This function generates the child of the current BoardState.
	 * It generates a BoardState which contains the movement of
	 * the other player.
	 * 
	 * @return The children of the current BoardState
	 */
	public LinkedList<BoardState> getChildren(){
		LinkedList<BoardState> children = new LinkedList<BoardState>();

		for (int col = 0; col < this.boardColumn; col++){
			if (isLegalMove(col)){

				int newPlayer = 0;

				if(this.currentPlayer == 1){
					newPlayer = 2;
				}
				else{
					newPlayer = 1;
				}

				BoardState child = new BoardState(this, this.currentPlayer);
				child.putCoinForAI(col, newPlayer);
				child.setCurrentPlayer(newPlayer);
				children.add(child);
			}
		}

		return children;
	}

	/**
	 * This function gets the ID of the Player given a row and column of the coin.
	 * 
	 * @param row The row the coin is in
	 * @param col The column the coin is in.
	 * @return The ID of the Player which the coin is belong to
	 */
	public int getCoin(int row, int col){
		if(row < 0 || col < 0 || row >= this.boardRow || col >= this.boardColumn) 
			return 0;

		return this.boardGame[row][col];
	}

	/**
	 * This function checks if a particular player wins a game.
	 * 
	 * @param playerID The ID of the Player.
	 * @return Whether the given player wins a game.
	 */
	public boolean isWinner(int playerID){
		if (checkWinner(playerID) == 1)
			return true;
		else
			return false;
	}

	/**
	 * This function prints out the current BoardState.
	 */
	public void output(){
		for (int r = (this.boardRow - 1); r >= 0; r--){
			String makeString = "";
			for (int c = 0; c < this.boardColumn; c++){
				makeString = makeString + boardGame[r][c] + " ";
			}
			System.out.println(makeString);
		} 
		
		System.out.println("= = = = = = =");
		System.out.println("0 1 2 3 4 5 6");
	}
	
	/**
	 * This function checks if a coin can be put into the given column.
	 * 
	 * @param column The column the coin wants to be put in.
	 * @return Whether the coin can be put into the column.
	 */
	public boolean isLegalMove(int column){
        if (column < 0 || column >= this.boardColumn){
        	return false;
        }
		if (boardGame[(this.boardRow - 1)][column] == 0) {
			return true;
		}

        return false;
	}

	/**
	 * This function checks whether a board is full.
	 * 
	 * @return Whether the board is full
	 */
	public boolean isBoardFull(){
		for (int c = 0; c < this.boardColumn; c++) {
			if (getTopRow(c) != -1) {
				return false;
			}
		}

		draw = true;
		return true;
	}

	/**
	 * This function gets the top row of a particular column.
	 * It checks if a particular row is empty at a given column and 
	 * returns that row. Should the column is filled, this function will
	 * return -1. That can be said as well for when the game is over.
	 * 
	 * @param column The column used to determine the top row
	 * @return The top row of that column that is not filled
	 */
	public int getTopRow(int column) {
		if (gameOver)
			return -1;
		for (int row = 0; row < this.boardRow; row++) {
			if (boardGame[row][column] == 0) {
				return row;
			}
		}
		
		return -1;
	}

	/**
	 * This function gets all the possible column that the coin can
	 * be put in.
	 * 
	 * @return A list possible columns that the coin can be put in.
	 */
	public List<Integer> getPossibleMoves(){
		List<Integer> possibleMoves = new LinkedList<Integer>();

        for (int col = 0; col < this.boardColumn; col++) {
            if (isLegalMove(col)) {
                possibleMoves.add(col);
            }
        }

        return possibleMoves;
	}

	/**
	 * This function undo a movement. It sets the top row of the
	 * particular column to empty.
	 * 
	 * @param column The column that wants to be undone.
	 */
	public void undoMovement(int column){
		for (int row = (this.boardRow - 1); row >= 0; row--){
			if(boardGame[row][column] != 0){
				boardGame[row][column] = 0;
				return;
			}
		}
	}

	/**
	 * This function checks whether a particular player wins a game.
	 * It checks horizontal, vertical, diagonal left and right win.
	 * 
	 * @param playerID The ID of a player.
	 * @return 1 if the winner wins, 0 if the board is not full and the player
	 * does not win, -1 if the player loses.
	 */
	public int checkWinner(int playerID){
		int sum = 0;

		//checks vertical win
		for (int c = 0; c < this.boardColumn; c++) {
			sum = 0;
			this.winningColumn.clear();
			this.winningRow.clear();
			
			for (int r = 0; r < this.boardRow; r++) {
				if (boardGame[r][c] == playerID) {
					sum++;
					this.winningColumn.add(c);
					this.winningRow.add(r);

					if(sum == victoryCondition) {
						return 1;
					}
				}
				else {
					sum = 0;
					this.winningColumn.clear();
					this.winningRow.clear();
				}
			}			
		}
		
		//checks horizontal win
		for (int r = 0; r < this.boardRow; r++) {
			sum = 0;
			this.winningColumn.clear();
			this.winningRow.clear();
			
			for (int c = 0; c < this.boardColumn; c++) {
				if (boardGame[r][c] == playerID) {
					sum++;
					this.winningColumn.add(c);
					this.winningRow.add(r);
					
					if(sum == victoryCondition) {
						return 1;
					}
				}
				else {
					sum = 0;
					this.winningColumn.clear();
					this.winningRow.clear();
				}
			}			
		}
		
		//checks diagonal left win
		for (int c = -this.boardRow; c < this.boardColumn; c++) {
			sum = 0;
			this.winningColumn.clear();
			this.winningRow.clear();
			
			for (int r = 0; r < this.boardRow; r++) {
				if(getCoin(r, c+r) == playerID){
					sum++;
					this.winningColumn.add(c + r);
					this.winningRow.add(r);
					
					if(sum == victoryCondition) {
						return 1;
					}
				}
				else{
					sum = 0;
					this.winningColumn.clear();
					this.winningRow.clear();
				}
			}			
		}
		
		//checks diagonal right win
		for (int c = 0; c < this.boardColumn + this.boardRow; c++) {
			sum = 0;
			this.winningColumn.clear();
			this.winningRow.clear();
			
			for (int r = 0; r < this.boardRow; r++) {
				if(getCoin(r, c-r) == playerID){
					sum++;
					this.winningColumn.add(c - r);
					this.winningRow.add(r);
					
					if(sum == victoryCondition) {
						return 1;
					}
				}
				else{
					sum = 0;
					this.winningColumn.clear();
					this.winningRow.clear();
				}
			}			
		}

		this.winningColumn.clear();
		this.winningRow.clear();
		
		if (!isBoardFull()){
			return 0;
		}

		return -1;
	}
	
	/**
	 * Get all the row that make the player win
	 * @return All the winning row
	 */
	public ArrayList<Integer> getWinningRow(){
		return this.winningRow;
	}
	
	/**
	 * Get all the column that make the player win
	 * @return All the winning column
	 */
	public ArrayList<Integer> getWinningColumn(){
		return this.winningColumn;
	}
}
