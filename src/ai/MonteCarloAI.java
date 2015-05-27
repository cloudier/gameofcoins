package ai;

import java.util.*;
import gameEngine.*;
//work on
public class MonteCarloAI implements AIX{

	private BoardState boardState;
	private int numberSearch;
	private int player;
	
	
	
	public MonteCarloAI(BoardState latest, int searchNum, int player){
		this.boardState = latest;
		this.numberSearch = searchNum;
		this.player = player;
		System.out.println("Player ID : " + player);
	}

	public int putCoin(){
		System.out.println("Calculating...");
		int column = 0;

		List<Integer> columns = boardState.getPossibleMoves();
		ArrayList<Integer> points = new ArrayList<Integer>();

		for (int col : columns){
			int point = columnSimulation(col);
			points.add(point);
			//System.out.println(point);
			//System.out.println(".");
		}
		
		column = bestOption(points, columns);
		//printOutWinningPoint(points, columns);

		System.out.println("Column chosen : " + column);
		return column;
	}

	private int columnSimulation(int col){
		int wins = 0;

		BoardState temp = new BoardState(this.boardState, this.player);
		temp.putCoinForAI(col, this.player);
		
		int i = 0;
		
		while (i < numberSearch){
			BoardState copyboard = new BoardState(temp, this.player);
			
			while (true){
				int random = simMove();
				//copyboard.putCoinForAI(random, pc2);
				//copyboard.putCoinForAI(random, pc2);

				
			}
		}
		
		/*
		while (i < numberSearch){
			BoardState copyboard = new BoardState(temp, 'O');

			while (true){
				int random = simMove();
				copyboard.putCoin(random, pc2);
				if (copyboard.checkWinner(pc2) == 1 || copyboard.checkWinner(pc1) == -1){
					break;
				}

				random = simMove();
				copyboard.putCoin(random, pc1);
				if (copyboard.checkWinner(pc1) == 1 || copyboard.checkWinner(pc2) == -1){
					wins = wins + 1;
					break;
				}
			}
			
			i = i + 1;
		}
*/
		return wins;
	}

	private int helpChecker(){
		
		
		
		return 0;
	}
	
	private int simMove(){
		Random random = new Random();
		int column = random.nextInt(6 - 0 + 1) + 0;
		return column;
	}

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

	/*private void printOutWinningPoint(ArrayList<Integer> d, List<Integer> columns){
		String makeString = "Result (per Group) : {";

		for (int col = 0; col < d.size(); col++){
			makeString = makeString + columns.get(col) + ": " + calculatePercentage(d.get(col)) + "%, ";
		}

		makeString = makeString.substring(0, makeString.length() - 2);
		makeString = makeString + "}";
		System.out.println(makeString);
	}*/

	/*private int calculatePercentage(int value){
		float percentage = ((float)value / (float)numberSearch) * 100;
		return Math.round(percentage);
	}*/
}