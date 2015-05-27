package ai;

import java.util.*;

import gameEngine.*;

public class RandomAI implements AIX{
	
	private BoardState boardState;
	
	public RandomAI(BoardState latest){
		this.boardState = latest;
	}

	public int putCoin(){
		//System.out.println("Calculating...");
		int column = 0;

		List<Integer> columns = boardState.getPossibleMoves();
		Random random = new Random();
		column = random.nextInt(columns.size());

		//System.out.println("Column chosen : " + column);
		return columns.get(column);
	}
}