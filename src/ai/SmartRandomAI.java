package ai;

import java.util.*;
import gameEngine.*;

public class SmartRandomAI implements AIX{
	
	private BoardState boardState;
	
	public SmartRandomAI(BoardState latest){
		this.boardState = latest;
	}

	public int putCoin(){
		//System.out.println("Calculating...");
		int column = 0;

		List<Integer> columns = boardState.getPossibleMoves();
		Random random = new Random();
		column = random.nextInt(columns.size());
		column = columns.get(column);
		
		for (int col : columns) {
        	
			System.out.println(col);
			for (int i = 0; i < boardState.getNumPlayers(); i++){
				
				boardState.putCoinForAI(col, (i + 1));
				if (boardState.isWinner((i + 1))){
	                column = col;
	                boardState.undoMovement(col);
	                return column;
				}
                boardState.undoMovement(col);
			}
		}

		//System.out.println("Column chosen : " + column);
		return column;
	}
}