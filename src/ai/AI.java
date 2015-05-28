package ai;

import gameEngine.BoardState;

/**
 * AI provides an interface for the Artificial Intelligence that
 * the game may have. This interface provides the functionality to
 * determine which column should the AI choose to get a win or a draw.
 * 
 * @author Timothy Putra Pringgondhani
 *
 */
public interface AI{
	
	/**
	 * 
	 * This function receives the latest BoardState object and calculates which
	 * column the AI should put the coin into.
	 * 
	 * @param latest The latest BoardState
	 * @param player The ID of the AI as a player
	 * @return The Column that the AI has chosen
	 */
	public int putCoin(BoardState latest, int player);
	
}