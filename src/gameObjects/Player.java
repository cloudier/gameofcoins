package gameObjects;

import java.awt.Color;

public class Player {

	private PlayerType playerType;
	private String name;
	private int boardValue;
	private Color coinColor;
	private int difficulty;
	
	/**
	 * Create and initialise the player
	 * @param name The name of the Player
	 * @param boardValue The value of the board
	 * @param coinColor The color of the Coin
	 * @param playerType The type of player
	 */
	public Player (String name, int boardValue, Color coinColor, PlayerType playerType) {
		this.name = name;
		this.boardValue = boardValue;
		this.coinColor = coinColor;
		this.playerType = playerType;
	}
	
	/**
	 * Get player type
	 * @return Returns the Player Type
	 */
	public PlayerType getPlayerType() {
		return playerType;
	}

	/**
	 * Set type of player
	 * @param playerType The type of the player
	 */
	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}
	
	/**
	 * Get name of player
	 * @return Returns the Name of the Player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set player name
	 * @param playerName The name of the Player
	 */
	public void setName(String playerName) {
		this.name = playerName;
	}

	/**
	 * Get the coin's color
	 * @return Returns the color of the coin
	 */
	public Color getCoinColor() {
		return coinColor;
	}

	/**
	 * Set the coin's color
	 * @param coinColor The color of the coin
	 */
	public void setCoinColor(Color coinColor) {
		this.coinColor = coinColor;
	}

	/**
	 * Set board value
	 * @param boardValue The board value
	 */
	public void setBoardValue(int boardValue) {
		this.boardValue = boardValue;
	}

	/**
	 * Get board value
	 * @return Returns the board value
	 */
	public int getBoardValue() {
		return boardValue;
	}

	/**
	 * Get the difficulty of the player
	 * @return Returns player difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * Set the difficulty of the player
	 * @param difficulty The difficulty of player
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
