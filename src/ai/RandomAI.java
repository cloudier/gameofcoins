package ai;

import java.util.*;

import gameEngine.*;

/**
 * 
 * @author Timothy
 *
 */
public class RandomAI implements AI{
	
	private BoardState boardState;
	
	/**
	 * 
	 * @param latest
	 */
	public RandomAI(BoardState latest){
		this.boardState = latest;
	}

	/**
	 * @return
	 */
	public int putCoin(){
		int column = 0;

		List<Integer> columns = boardState.getPossibleMoves();
		Random random = new Random();
		column = random.nextInt(columns.size());

		//System.out.println("Column chosen : " + column);
		return columns.get(column);
	}
}