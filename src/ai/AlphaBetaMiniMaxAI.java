package ai;

import java.util.*;

import gameEngine.*;

/**
 * 
 * @author Timothy
 *
 */
public class AlphaBetaMiniMaxAI implements AI{
	
	private BoardState boardState;
	private int depthLimit;
	private int player;
	
	/**
	 * 
	 * @param latest
	 * @param depthLimit
	 * @param player
	 */
	public AlphaBetaMiniMaxAI(BoardState latest, int depthLimit, int player){
		this.boardState = latest;
		this.depthLimit = depthLimit;
		this.player = player;
	}

	/**
	 * @return
	 */
	public int putCoin(){
		int maxValue = - 1;
		int column = 0;

		List<Integer> columns = boardState.getPossibleMoves();

		for (int col : columns){
			BoardState bs = new BoardState(boardState, this.player);
			bs.putCoinForAI(col, this.player);
			//bs.output();	

			int value = alphaBeta(bs, 0, -100000, 100000);
			if (value > maxValue){
				maxValue = value;
				column = col;
			}

			//System.out.println("======================");
			//System.out.println("Result for Column " + col + " : " + value);
		}

		//System.out.println("Column chosen : " + column);
		return column;
	}

	/**
	 * 
	 * @param bs
	 * @param depth
	 * @param alpha
	 * @param beta
	 * @return
	 */
	private int alphaBeta(BoardState bs, int depth, int alpha, int beta){
		//bs.output();

		if (bs.isWinner(this.player - 1)){
			return -1;
		}
		else if (bs.isWinner(this.player)){
			return 1;	
		}

		if (depth == depthLimit || bs.isBoardFull()){
			return 0;
		}

		if (bs.getCurrentPlayer() == (this.player - 1)){
			//max
			//System.out.println("Printing out Max Children of Depth : " + depth);
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
			//min
			//System.out.println("Printing out Min Children of Depth : " + depth);
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