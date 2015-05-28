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
	 * 
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
	 * 
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
		
		//Select your AI Option :
    	//artificialIntelligence = new RandomAI(this);					//Random AI
    	artificialIntelligence = new SmartRandomAI(this);				//Smart Random AI
		//artificialIntelligence = new MonteCarloAI(this, 1000, 2);		//Monte Carlo AI (Search 1000)
		//artificialIntelligence = new MiniMaxAI(this, 5, 2);			//Mini Max AI (Depth 5)
		//artificialIntelligence = new AlphaBetaMiniMaxAI(this, 7, 2);	//Alpha Beta AI (Depth 7)
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
	 * 
	 * @param currentPlayer
	 */
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * 
	 * @return
	 */
	public int[][] getBoard(){
		return this.boardGame;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isDraw() {
		return draw;
	}

	/**
	 * 
	 * @param draw
	 */
	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * 
	 * @param gameOver
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * 
	 * @param column
	 * @param player
	 */
	public void putCoinForAI(int column, int player){
		for(int row = 0; row < this.boardRow; row++){
			if(boardGame[row][column] == 0){
				boardGame[row][column] = player;
				break;
			}
		}
	}

	// put coin into the board
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
				System.out.println("User Move : " + column);
				output();
				System.out.println("\n");
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
	 * 
	 * @return
	 */
	public int ai_Move(){ // returns column
		int columnX = artificialIntelligence.putCoin();	
		output();
		return columnX;
	}

	/**
	 * 
	 * @return
	 */
	public int getLastAIMove(){
		return this.ai_Selection;
	}

	/**
	 * 
	 * @return
	 */
	public LinkedList<BoardState> getChildren(){
		LinkedList<BoardState> children = new LinkedList<BoardState>();

		for (int col = 0; col < this.boardColumn; col++){
			if (isLegalMove(col)){

				int newPlayer = 0;

				if(this.currentPlayer == 1){
					newPlayer = 2;
				} 
				else if(this.currentPlayer == 2){
					newPlayer = 3;
				}
				else if(this.currentPlayer == 3){
					newPlayer = 4;
				}
				else{
					newPlayer = 1;
				}

				BoardState child = new BoardState(this, this.currentPlayer);
				child.putCoin(col);
				child.setCurrentPlayer(newPlayer);
				children.add(child);
			}
		}

		return children;
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public int getCoin(int row, int col){
		if(row < 0 || col < 0 || row >= this.boardRow || col >= this.boardColumn) 
			return 0;

		return this.boardGame[row][col];
	}

	/**
	 * 
	 * @param player
	 * @return
	 */
	public boolean isWinner(int player){
		if (checkWinner(player) == 1)
			return true;
		else
			return false;
	}

	/**
	 * 
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
	 * 
	 * @param column
	 * @return
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
	 * 
	 * @return
	 */
	public boolean isBoardFull(){ //node is terminal (or in other word full)
		for (int c = 0; c < this.boardColumn; c++) {
			if (getTopRow(c) != -1) {
				return false;
			}
		}

		draw = true;
		return true;
	}

	// returns row that coin would be placed in
	// else returns -1
	/**
	 * 
	 * @param column
	 * @return
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
	 * 
	 * @return
	 */
	public List<Integer> getPossibleMoves(){
		List<Integer> possibleMoves = new LinkedList<Integer>();

        for (int col = 0; col < this.boardColumn; col++) {
            if (isLegalMove(col)) {
            	System.out.println("Column: " + col);
                possibleMoves.add(col);
            }
        }

        return possibleMoves;
	}

	/**
	 * 
	 * @param column
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
	 * 
	 * @param symbol
	 * @return
	 */
	public int checkWinner(int symbol){
		int sum = 0;
		
		//checks vertical win
		for (int c = 0; c < this.boardColumn; c++) {
			sum = 0;
			for (int r = 0; r < this.boardRow; r++) {
				if (boardGame[r][c] == symbol) {
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
				if (boardGame[r][c] == symbol) {
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
				
				if(getCoin(r, c+r) == symbol)
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
				
				if(getCoin(r, c-r) == symbol)
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
