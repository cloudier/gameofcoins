package ai;

import java.util.*;
import gameEngine.*;

/**
 * 
 * This class implements AI interface.
 * 
 * This AI determines the column that it wants to choose randomly.
 * However, when the AI detects that a player might win, it will try
 * to stop the player from winning but when the AI detects that it
 * will win it will put the coin in the column that will make the AI win.
 * 
 * @author Timothy
 *
 */
public class SmartRandomAI implements AI{
	
	private BoardState boardState;
	
	/**
	 * Construct a SmartRandomAI Object
	 */
	public SmartRandomAI(){
		
	}

	/**
	 * First, it checks which column can the AI
	 * put the coin into and put it into a list. The AI then determines 
	 * the column that it wants to choose based
	 * on a random number. But before returning the random column,
	 * it will check whether a column might make the player win
	 * or make the AI win. If it detects that it might lose to the player,
	 * it will get the column that make the AI lose, and return the column
	 * to prevent the player from winning. However, if it detects that it might 
	 * win in a particular column, it will put in that column. However,
	 * if the AI will neither win nor lose, it will return the random column.
	 * 
	 * @param latest The latest BoardState
	 * @param player The ID of the AI as a player
	 * @return The Column that the AI has chosen
	 */
	public int putCoin(BoardState latest, int player){
		this.boardState = latest;
		
		int column = 0;

		List<Integer> columns = boardState.getPossibleMoves();
		Random random = new Random();
		column = random.nextInt(columns.size());
		column = columns.get(column);
		
		for (int col : columns) {        	
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

		return column;
	}
}