package gameObjects;

import java.awt.Color;
// use decorator pattern to add AI state/functionality?
public class Player {


	private PlayerType playerType;
	private String name;
	private int boardValue;
	private Color coinColor;
	
	public Player (String name, int boardValue, Color coinColor, PlayerType playerType) {
		this.name = name;
		this.boardValue = boardValue;
		this.coinColor = coinColor;
		this.playerType = playerType;
	}
	
	public PlayerType getPlayerType() {
		return playerType;
	}

	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String playerName) {
		this.name = playerName;
	}

	public Color getCoinColor() {
		return coinColor;
	}

	public void setCoinColor(Color coinColor) {
		this.coinColor = coinColor;
	}

	public void setBoardValue(int boardValue) {
		this.boardValue = boardValue;
	}

	public int getBoardValue() {
		return boardValue;
	}
	
	public Color getColor() {
		return coinColor;
	}
	
	public void setColor(Color coinColor) {
		this.coinColor = coinColor;
	}
}
