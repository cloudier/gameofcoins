package ai;

import java.util.*;
import gameEngine.*;

/**
 * 
 * @author Timothy
 *
 */
public class MonteCarloAI implements AI{

	private BoardState boardState;
	private int numberSearch;
	private int player;
	
	/**
	 * 
	 * @param latest
	 * @param searchNum
	 * @param player
	 */
	public MonteCarloAI(BoardState latest, int searchNum, int player){
		this.boardState = latest;
		this.numberSearch = searchNum;
		this.player = player;
	}

	/**
	 * 
	 */
	public int putCoin(){
		int column = 0;

		List<Integer> columns = boardState.getPossibleMoves();
		ArrayList<Integer> points = new ArrayList<Integer>();

		for (int col : columns){
			int point = columnSimulation(col);
			points.add(point);
		}
		
		column = bestOption(points, columns);
		//printOutWinningPoint(points, columns);

		//System.out.println("Column chosen : " + column);
		return column;
	}
	
	/**
	 * 
	 * @param col
	 * @return
	 */
	private int columnSimulation(int col){
		int wins = 0;
		int i = 0;

		BoardState temp = new BoardState(this.boardState, this.player);
		temp.putCoinForAI(col, this.player);
			
		while (i < numberSearch){
			BoardState copyboard = new BoardState(temp, this.player);
			
			while (true){
				int random = simMove();
				copyboard.putCoinForAI(random, (this.player - 1));
				if (copyboard.checkWinner(this.player - 1) == 1 || copyboard.checkWinner(this.player) == -1){
					break;
				}

				random = simMove();
				copyboard.putCoinForAI(random, (this.player));
				if (copyboard.checkWinner(this.player) == 1 || copyboard.checkWinner(this.player -1) == 1){
					wins++;
					break;
				}
			}

			i++;
		}

		return wins;
	}
	
	/**
	 * 
	 * @return
	 */
	private int simMove(){
		Random random = new Random();
		int column = random.nextInt(6 - 0 + 1) + 0;
		return column;
	}
	
	/**
	 * 
	 * @param d
	 * @param columns
	 * @return
	 */
	private int bestOption(ArrayList<Integer> d, List<Integer> columns){
		int wins = 0;
		int column = 0;

		for (int col = 0; col < d.size(); col++){
			int win_on_column = d.get(col);

			if (win_on_column > wins){
				wins = win_on_column;
				column = columns.get(col);
			}
		}

		return column;
	}
	
	/*
	private void printOutWinningPoint(ArrayList<Integer> d, List<Integer> columns){
		String makeString = "Result (per Group) : {";

		for (int col = 0; col < d.size(); col++){
			makeString = makeString + columns.get(col) + ": " + calculatePercentage(d.get(col)) + "%, ";
		}

		makeString = makeString.substring(0, makeString.length() - 2);
		makeString = makeString + "}";
		System.out.println(makeString);
	}

	private int calculatePercentage(int value){
		float percentage = ((float)value / (float)numberSearch) * 100;
		return Math.round(percentage);
	}*/
}