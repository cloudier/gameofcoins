package ai;

import java.util.*;

import gameEngine.*;

/**
 * 
 * This class implements AI interface.
 * 
 * This AI determines the column that it wants to choose using
 * the MiniMax Algorithm. MiniMax will determine several move ahead
 * to a certain search depth and determines whether it might win, lose 
 * or the game ended up as a draw. MiniMax will always try to make itself
 * win or make the game ending in a draw.
 * 
 * @author Timothy
 *
 */
public class MiniMaxAI implements AI{
	
	private BoardState boardState;
	private int depthLimit;
	private int player;
	
	/**
	 * Construct a MiniMaxAI Object
	 * @param depthLimit The depth that the MiniMax should search until
	 */
	public MiniMaxAI(int depthLimit){
		this.depthLimit = depthLimit;
	}

	/**
	 * This function firstly gets all possible columns that the AI
	 * can put the coin into. It then creates a new BoardState object
	 * and simulates all possible move that the AI can make. This function then
	 * determines the MiniMax search result for each BoardState. Should
	 * the result be better than the current result, it will update it and
	 * choose that particular column.
	 * 
	 * @param latest The latest BoardState
	 * @param player The ID of the AI as a player
	 * @return The Column that the AI has chosen
	 */
	public int putCoin(BoardState latest, int player){
		this.boardState = latest;
		this.player = player;
		
		int maxValue = - 10000;
		int column = 0;

		List<Integer> columns = boardState.getPossibleMoves();

		for (int col : columns){
			BoardState bs = new BoardState(boardState, this.player);
			bs.putCoinForAI(col, this.player);

			int value = miniMax(bs, 0);
			if (value > maxValue){
				maxValue = value;
				column = col;
			}
		}

		return column;
	}

	/**
	 * This function is where the MiniMax Search works. This function
	 * is a recursive function. First it receives the BoardState and the
	 * depth of the BoardState. It will check if someone has won. Should
	 * the player win, it will return a negative value which means that the player
	 * might win in several move. Should the AI win, it will return a positive
	 * value which means that the AI might win in several move. If a Board is full
	 * or the depth reaches its limit, it will return 0 which means neither
	 * of the player wins. If the BoardState does not pass all the checks,
	 * it will go on to create the child of the BoardState which will go do a MiniMax
	 * search again. As MiniMax is a tree search algorithm, it will reach
	 * to a leaf at some point. It will then works back up the tree. As the name
	 * suggest, this function will do minimizing and maximizing of the result.
	 * Player always wants to minimize the chances of the AI to win while AI
	 * wants to maximize it chances.
	 * 
	 * In this function, there is a simple heuristic of determining the value.
	 * The heuristic determines the value based on the depth. The lower the depth,
	 * the higher the value means that the AI is going to win and the lower the value
	 * means that the AI is going to low.
	 * 
	 * @param bs The BoardState at each node
	 * @param depth The current depth that the search is in
	 * @return The value of the MiniMax at each node
	 */
	private int miniMax(BoardState bs, int depth){
		int returnValue;
		
		if (bs.isWinner(this.player - 1)){
			return depth - 10;
		}
		else if (bs.isWinner(this.player)){
			return 10 - depth;	
		}
		
		if (depth == depthLimit || bs.isBoardFull()){
			return 0;
		}

		if (bs.getCurrentPlayerID() == (this.player - 1)){
			returnValue = -100000;
			
			for (BoardState tmp : bs.getChildren()){
				int val = miniMax(tmp, (depth + 1));
				returnValue = Math.max(returnValue, val);
			}
		}
		else{
			returnValue = 100000;

			for (BoardState tmp : bs.getChildren()){
				int val = miniMax(tmp, (depth + 1));
				returnValue = Math.min(returnValue, val);
			}
		}

		return returnValue;
	}
}