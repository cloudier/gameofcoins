package ai;

import java.util.*;
import gameEngine.*;

public class MiniMaxAI implements AIX{
	
	private BoardState boardState;
	private int depthLimit;
	private int player;
	
	public MiniMaxAI(BoardState latest, int depthLimit, int player){
		this.boardState = latest;
		this.depthLimit = depthLimit;
		this.player = player;
	}

	public int putCoin(){
		System.out.println("Calculating...");

		int maxValue = - 1;
		int column = 0;
/*
		List<Integer> columns = boardState.getPossibleMoves();

		for (int col : columns){
			BoardState bs = new BoardState(boardState, player);
			bs.putCoin(col, player);
			//bs.output();	

			int value = miniMax(bs, 0);
			if (value > maxValue){
				maxValue = value;
				column = col;
			}

			//System.out.println("======================");
			System.out.println("Result for Column " + col + " : " + value);
		}

		System.out.println("Column chosen : " + column);*/
		return column;
	}
/*
	private int miniMax(BoardState bs, int depth){
		//bs.output();

		int returnValue;

		if (bs.isWinner(this.player)){
			//System.out.println("Above AI win");
			return 1;
		}
		else{
			for (int i = 0; i < boardState.getNumPlayers(); i++){
				if ((i+ 1) != this.player){
					if (bs.isWinner(i + 1)){
						//System.out.println("Above Player win");
						return -1;
					}
				}
			}
		}
		
		if (depthLimit == depth || bs.isBoardFull()){
			return 0;
		}

		if (bs.getCurrentPlayer() == 'X'){
			//max
			returnValue = -100000;

			//System.out.println("Printing out Children of Depth : " + depth);
			for (BoardState tmp : bs.getChildren()){
				int val = miniMax(tmp, (depth + 1));
				returnValue = Math.max(returnValue, val);
			}
			//System.out.println("~~~~~~~~~~~~~~~~~");
		}
		else{
			//min
			returnValue = 100000;

			//System.out.println("Printing out Children of Depth : " + depth);
			for (BoardState tmp : bs.getChildren()){
				int val = miniMax(tmp, (depth + 1));
				returnValue = Math.min(returnValue, val);
			}
			//System.out.println("~~~~~~~~~~~~~~~~~");
		}

		return returnValue;
	}*/
}