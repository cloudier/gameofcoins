package ai;

import java.util.*;

import gameEngine.*;

/**
 * 
 * This class implements AI interface.
 * 
 * This AI determines the column that it wants to choose using
 * the Alpha Beta MiniMax Algorithm. It is essentially a MiniMax search
 * with pruning feature which speed up the search and it can search deeper
 * in the search tree.
 * 
 * Alpha Beta MiniMax will determine several move ahead
 * to a certain search depth and determines whether it might win, lose 
 * or the game ended up as a draw. MiniMax will always try to make itself
 * win or make the game ending in a draw.
 * 
 * @author Timothy
 *
 */
public class AlphaBetaMiniMaxAI implements AI{
	
	private BoardState boardState;
	private int depthLimit;
	private int player;
	
	/**
	 * Construct a AlphaBetaMiniMaxAI Object
	 * @param depthLimit The depth that the Alpha Beta MiniMax should search until
	 */
	public AlphaBetaMiniMaxAI(int depthLimit){
		this.depthLimit = depthLimit;
	}

	/**
	 * This function firstly gets all possible columns that the AI
	 * can put the coin into. It then creates a new BoardState object
	 * and simulates all possible move that the AI can make. This function then
	 * determines the Alpha Beta search result for each BoardState. Should
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

			int value = alphaBeta(bs, 0, -100000, 100000);
			if (value > maxValue){
				maxValue = value;
				column = col;
			}
		}

		return column;
	}

	/**
	 * This function is where the Alpha Beta Search works. This function
	 * is a recursive function. First it receives the BoardState and the
	 * depth, alpha and beta value of the BoardState. It will check if someone 
	 * has won. Should the player win, it will return a negative value which means 
	 * that the player might win in several move. Should the AI win, it will 
	 * return a positive value which means that the AI might win in several move. 
	 * If a Board is full or the depth reaches its limit, it will return 0 which 
	 * means neither of the player wins. If the BoardState does not pass all the 
	 * checks, it will go on to create the child of the BoardState which will 
	 * go do an Alpha Beta Search. This function is slightly different from MiniMax.
	 * In this function Alpha acts as the maximizing value while beta acts as
	 * the minimizing value.
	 * 
	 * When it reaches to a leaf node, it will work back up the tree, however, it
	 * checks whether alpha is more than or equals than beta. Should it happen,
	 * it will stop searching the other branch. The reason is that when
	 * one possibility has been found that proves the move to be worse than
	 * the previous examined move, it will not go evaluate further as the final
	 * decision has been determined earlier and the later will not be able 
	 * to influence the final result.
	 * 
	 * @param bs The BoardState at each node
	 * @param depth The current depth that the search is in
	 * @param alpha The Alpha value of the BoardState at a certain node
	 * @param beta The Beta value of the BoardState at a certain node
	 * @return The value of the MiniMax at each node
	 */
	private int alphaBeta(BoardState bs, int depth, int alpha, int beta){		
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
			for (BoardState tmp : bs.getChildren()){
				int val = alphaBeta(tmp, (depth + 1), alpha, beta);
				alpha = Math.max(alpha, val);

				if (beta <= alpha){
					break;
				}
			}
			
			return alpha;
		}
		else{
			for (BoardState tmp : bs.getChildren()){
				int val = alphaBeta(tmp, (depth + 1), alpha, beta);
				beta = Math.min(beta, val);

				if (beta <= alpha){
					break;
				}
			}

			return beta;
		}
	}
}