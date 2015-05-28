package gameEngine;

import gameObjects.*;
import ai.*;	

import java.util.*;

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

	public BoardState() {
		this.gameOver = false;
		this.draw = false;
	}

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

	public void reset() {
		boardGame = new int[boardRow][boardColumn];
		this.currentPlayer = 1;
		gameOver = false;
	}
	
	public void initialiseMode(int boardRow, int boardColumn, int victoryCondition, String mode) {
	    	    
		this.boardRow = boardRow;
		this.boardColumn = boardColumn;
		this.boardGame = new int[boardRow][boardColumn];
		this.victoryCondition = victoryCondition;
		this.mode = mode;		
	}

	public void initialisePlayers(int numPlayers, HashMap<Integer, Player> players) {
		this.numPlayers = numPlayers;
		this.currentPlayer = 1;
		
    	//artificialIntelligence = new RandomAI(this);					//Random AI
//    	artificialIntelligence = new SmartRandomAI(this);				//Smart Random AI
		//artificialIntelligence = new MonteCarloAI(this, 1000, 2);		//Monte Carlo AI (Search 1000)
//		artificialIntelligence = new MiniMaxAI(this, 5, 2);			//Mini Max AI (Depth 5)
		artificialIntelligence = new AlphaBetaMiniMaxAI(this, 7, 2);			//Alpha Beta AI (Depth 7)
		//artificialIntelligence = new ManualAI(this);					//Manual
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

	public int getBoardRow() {
		return boardRow;
	}

	public void setBoardRow(int boardRow) {
		this.boardRow = boardRow;
	}
	
	public int getBoardColumn() {
		return boardColumn;
	}

	public void setBoardColumn(int boardColumn) {
		this.boardColumn = boardColumn;
	}
	
	public int getNumPlayers() {
		return this.numPlayers;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public int getCurrentPlayer() {
		return this.currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int[][] getBoard(){
		return this.boardGame;
	}
	
	public boolean isDraw() {
		return draw;
	}


	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	












	public void putCoinForAI(int column, int player){
		for(int row = 0; row < this.boardRow; row++){
			if(boardGame[row][column] == 0){
				boardGame[row][column] = player;
				break;
			}
		}
	}

	//work on coins here
	// put coin into the board
	public boolean putCoin(int column) {		
		//user move first
		user_Move(column);

		if (gameOver)
			return true;
		else
			return false;
	}

	private void user_Move(int column){
		for(int row = 0; row < this.boardRow; row++){
			if(boardGame[row][column] == 0){
				boardGame[row][column] = this.currentPlayer;
//				System.out.println("User Move : " + column);
//				output();
//				System.out.println("\n");
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
	
	public int ai_Move(){ // returns column
		int columnX = artificialIntelligence.putCoin();	
		
		output();
		return columnX;
	}

	public int getLastAIMove(){
		return this.ai_Selection;
	}

	private int ai_Selection;







	public LinkedList<BoardState> getChildren(){
		LinkedList<BoardState> children = new LinkedList<BoardState>();

		for (int col = 0; col < this.boardColumn; col++){
			if (isLegalMove(col)){

				int newPlayer;

				if(this.currentPlayer == 1){
					newPlayer = 2;
				} else{
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

	public int getCoin(int row, int col){
		if(row < 0 || col < 0 || row >= this.boardRow || col >= this.boardColumn) 
			return 0;

		return this.boardGame[row][col];
	}

	public boolean isWinner(int player){
		if (checkWinner(player) == 1){
			return true;
		}
		else
			return false;
	}

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
	
	public boolean isLegalMove(int column){
        if (column < 0 || column >= this.boardColumn){
        	return false;
        }
		if (boardGame[(this.boardRow - 1)][column] == 0) {
			return true;
		}

        return false;
	}

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

	public void undoMovement(int column){
		for (int row = (this.boardRow - 1); row >= 0; row--){
			if(boardGame[row][column] != 0){
				boardGame[row][column] = 0;
				return;
			}
		}
	}

















	public int checkWinner(int symbol){
		int sum = 0;
		
		//checks vertical
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
		
		//checks horizontal
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
		
		//checks diagonal left
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
		
		//checks diagonal right
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
