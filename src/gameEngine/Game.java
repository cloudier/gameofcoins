package gameEngine;

public abstract class Game extends UIObject{

	/**
	 * Initialises the visual representation of columns
	 * and rows in a game.
	 */
	public abstract void initialiseColumnsRows();

	/**
	 * Resets the visual representation of columns, rows
	 * and coins in a game.
	 */
	public abstract void reset();

}
