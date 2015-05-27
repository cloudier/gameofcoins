package ai;

import java.util.*;
import gameEngine.*;

public class AlphaBetaMiniMaxAI implements AIX{
	
	//private final char playerSymbol = 'X';
	//private final char pcSymbol = 'O';

	private int currentPlayer;
	
	private BoardState boardState;
	private int depthLimit;
	
	public AlphaBetaMiniMaxAI(BoardState latest, int depthLimit){
		this.boardState = latest;
		this.depthLimit = depthLimit;
	}

	public int putCoin(){
		System.out.println("Calculating...");

		int maxValue = - 1;
		int column = 0;
/*
		List<Integer> columns = boardState.getPossibleMoves();

		for (int col : columns){
			BoardState bs = new BoardState(boardState, 'O');
			bs.putCoin(col, 'O');
			//bs.output();	

			int value = alphaBeta(bs, 0, -100000, 100000);
			if (value > maxValue){
				maxValue = value;
				column = col;
			}

			//System.out.println("======================");
			//System.out.println("Result for Column " + col + " : " + value);
		}

		System.out.println("Column chosen : " + column);*/
		return column;
	}

	/*private int alphaBeta(BoardState bs, int depth, int alpha, int beta){
		//bs.output();

		int returnValue;

		if (bs.isWinner(playerSymbol)){
			//System.out.println("Above Player win");
			return -1;
		}
		else if (bs.isWinner(pcSymbol)){
			//System.out.println("Above AI win");
			return 1;
		}
		if (depth == depthLimit || bs.isBoardFull()){
			return 0;
		}

		if (bs.getCurrentPlayer() == 'X'){
			//max
			returnValue = -100000;

			//System.out.println("Printing out Max Children of Depth : " + depth);
			for (BoardState tmp : bs.getChildren()){
				int val = alphaBeta(tmp, (depth + 1), alpha, beta);
				alpha = Math.max(alpha, val);
				//returnValue = alpha;

				if (beta <= alpha){
					break;
				}
			}

			return alpha;
			//System.out.println("~~~~~~~~~~~~~~~~~");
		}
		else{
			//min
			returnValue = 100000;

			//System.out.println("Printing out Min Children of Depth : " + depth);
			for (BoardState tmp : bs.getChildren()){
				int val = alphaBeta(tmp, (depth + 1), alpha, beta);
				beta = Math.min(beta, val);
				returnValue = beta;

				if (beta <= alpha){
					break;
				}
			}

			//System.out.println("~~~~~~~~~~~~~~~~~");
			return beta;
		}
	}*/
}