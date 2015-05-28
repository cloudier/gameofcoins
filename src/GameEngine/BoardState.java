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
	
	/**
	 * Construct a BoardState Object.
	 * 
	 * Sets game over and draw status to false.
	 */
	public BoardState() {
		this.gameOver = false;
		this.draw = false;
	}

	/**
	 * 
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

		boardGame = new int[this.boardRow][this.boardColumn];

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
		boardGame = new int[this.boardRow][this.boardColumn];
		this.currentPlayer = 1;
		gameOver = false;
	}
	
	/**
	 * 
	 * @param boardRow
	 * @param boardColumn
	 * @param victoryCondition
	 * @param mode
	 */
	public void initialiseMode(int boardRow, int boardColumn, int victoryCondition, String mode) {
		this.boardRow = boardRow;
		this.boardColumn = boardColumn;
		this.boardGame = new int[boardRow][boardColumn];
		this.victoryCondition = victoryCondition;
		this.mode = mode;		
	}

	/**
	 * 
	 * @param numPlayers
	 * @param players
	 */
	public void initialisePlayers(int numPlayers, HashMap<Integer, Player> players) {
		this.numPlayers = numPlayers;
		this.currentPlayer = 1;
		
//		for (Player p : players.values()) {
//			if (p.getPlayerType() == PlayerType.AI) {
//				if (p.getDifficulty() == 0) {
//			    	artificialIntelligence = new SmartRandomAI(this);				//Smart Random AI					
//				} else if (p.getDifficulty() == 1) {
//					
//				} else if (p.getDifficulty() == 2) {
//					artificialIntelligence = new AlphaBetaMiniMaxAI(this, 7, 2);			//Alpha Beta AI (Depth 7)					
//				}
//			}
//		}
		
//    	artificialIntelligence = new RandomAI(this);					//Random AI
		artificialIntelligence = new MonteCarloAI(this, 1000, 2);		//Monte Carlo AI (Search 1000)
//		artificialIntelligence = new MiniMaxAI(this, 5, 2);			//Mini Max AI (Depth 5)
	}

	/**
	 * 
	 * @return
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * 
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * 
	 * @return
	 */
	public int getVictoryCondition() {
		return victoryCondition;
	}

	/**
	 * 
	 * @param victoryCondition
	 */
	public void setVictoryCondition(int victoryCondition) {
		this.victoryCondition = victoryCondition;
	}

	/**
	 * 
	 * @return
	 */
	public int getBoardRow() {
		return boardRow;
	}

	/**
	 * 
	 * @param boardRow
	 */
	public void setBoardRow(int boardRow) {
		this.boardRow = boardRow;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getBoardColumn() {
		return boardColumn;
	}

	/**
	 * 
	 * @param boardColumn
	 */
	public void setBoardColumn(int boardColumn) {
		this.boardColumn = boardColumn;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumPlayers() {
		return this.numPlayers;
	}

	/**
	 * 
	 * @param numPlayers
	 */
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	/**
	 * 
	 * @return
	 */
	public int getCurrentPlayer() {
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
	 * 
	 * @param column
	 * @return
	 */
	public boolean putCoin(int column) {		
		user_Move(column);

		if (gameOver)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @param column
	 */
	private void user_Move(int column){
		for(int row = 0; row < this.boardRow; row++){
			if(boardGame[row][column] == 0){
				boardGame[row][column] = this.currentPlayer;
				//System.out.println("User Move : " + column);
				//output();
				//System.out.println("\n");
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
			for (int r = 0; r < this.boardRow; r++) {
				if (boardGame[r][c] == playerID) {
					sum++;
					
					if(sum == victoryCondition) {
						return 1;
					}
				}
				else {
					sum = 0;
				}
			}			
		}
		
		//checks horizontal win
		for (int r = 0; r < this.boardRow; r++) {
			sum = 0;
			for (int c = 0; c < this.boardColumn; c++) {
				if (boardGame[r][c] == playerID) {
					sum++;
					if(sum == victoryCondition) {
						return 1;
					}
				}
				else {
					sum = 0;
				}
			}			
		}
		
		//checks diagonal left win
		for (int c = -this.boardRow; c < this.boardColumn; c++) {
			sum = 0;
			for (int r = 0; r < this.boardRow; r++) {
				
				if(getCoin(r, c+r) == playerID)
				{
					sum++;
					if(sum == victoryCondition) {
						return 1;
					}
				}
				else
				{
					sum = 0;
				}
			}			
		}
		
		//checks diagonal right win
		for (int c = 0; c < this.boardColumn + this.boardRow; c++) {
			sum = 0;
			for (int r = 0; r < this.boardRow; r++) {
				
				if(getCoin(r, c-r) == playerID)
				{
					sum++;
					if(sum == victoryCondition) {
						return 1;
					}
				}
				else
				{
					sum = 0;
				}
			}			
		}

		if (!isBoardFull()){
			return 0;
		}

		return -1;
	}
}
