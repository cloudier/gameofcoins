package ai;

import java.util.*;

import gameEngine.*;


/**
 * 
 * This class implements AI interface.
 * 
 * This AI determines the column that it wants to choose using
 * the Monte Carlo Algorithm. Monte Carlo uses probability to
 * determine the chances of winning for each column using a random
 * simulated movement. The higher the probability of a column,
 * the higher the chances of the AI to win.
 * 
 * @author Timothy
 *
 */
public class MonteCarloAI implements AI{

	private BoardState boardState;
	private int numberSearch;
	private int player;
	
	/**
	 * Construct a MonteCarloAI Object
	 * @param searchNum The number of search the AI should make
	 */
	public MonteCarloAI(int searchNum){
		this.numberSearch = searchNum;
	}

	/**
	 * This function firstly gets all possible columns that the AI
	 * can put the coin into. It then simulates every possible columns to
	 * a Monte Carlo Search. The Monte Carlo search will return the probability
	 * of that column. Each probability will be put into a List.
	 * 
	 * This function then gets the best column from the probability list and return
	 * it as the chosen column.
	 * 
	 * @param latest The latest BoardState
	 * @param player The ID of the AI as a player
	 * @return The Column that the AI has chosen
	 */
	public int putCoin(BoardState latest, int player){
		this.boardState = latest;
		this.player = player;
		
		int column = 0;

		List<Integer> columns = boardState.getPossibleMoves();
		ArrayList<Integer> points = new ArrayList<Integer>();

		for (int col : columns){
			int point = columnSimulation(col);
			points.add(point);
		}
		
		column = bestOption(points, columns);
		
		return column;
	}
	
	/**
	 * This function firstly generates a BoardState from
	 * the given BoardState and simulate a movement for the AI given
	 * the column. It then simulates a movement for both the player and the AI
	 * until either the player or the AI win or lose. If the AI win,
	 * the winning point is incremented by one however if the AI lose, it will
	 * not get any point. When either of the players win, it will stop to loop
	 * and do a new simulations. This function will simulates until it reaches
	 * the given required search.
	 * 
	 * The simulations made are based on random columns. So each player will
	 * get a random column and puts it into that random column. It then
	 * checks if the player win or lose.
	 * 
	 * @param column The column used to determine the winning points.
	 * @return The winning points of the given column.
	 */
	private int columnSimulation(int column){
		int wins = 0;
		int i = 0;

		BoardState temp = new BoardState(this.boardState, this.player);
		temp.putCoinForAI(column, this.player);
			
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
	 * This function gets a random column.
	 * 
	 * @return Return a random column
	 */
	private int simMove(){
		Random random = new Random();
		int column = random.nextInt(boardState.getBoardColumn());
		return column;
	}
	
	/**
	 * This function determines which column has a higher probability of winning.
	 * 
	 * @param points List of points per column
	 * @param columns List of columns that the coin can be put in
	 * @return The column with a higher winning probability
	 */
	private int bestOption(ArrayList<Integer> points, List<Integer> columns){
		int wins = -10000;
		int column = 0;

		for (int col = 0; col < points.size(); col++){
			int win_on_column = points.get(col);

			if (win_on_column > wins){
				wins = win_on_column;
				column = columns.get(col);
			}
		}

		return column;
	}
}