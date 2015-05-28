package ai;

import java.util.*;

import gameEngine.*;

/**
 * 
 * This class implements AI interface.
 * 
 * This AI determines the column that it wants to choose randomly.
 * 
 * @author Timothy
 *
 */
public class RandomAI implements AI{
	
	private BoardState boardState;

	/**
	 * Construct a RandomAI Object
	 */
	public RandomAI(){
		
	}

	/**
	 * This AI determines the column that it wants to choose based
	 * on a random number. First, it checks which column can the AI
	 * put the coin into and put it into a list. Then the AI generates 
	 * a random number which is the index of the possible column(s).
	 * It then gets the particular column and returns that column.
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

		return columns.get(column);
	}
}