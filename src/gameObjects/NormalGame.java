package gameObjects;

import gameEngine.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.Timer;

public class NormalGame extends Game{

	private float width;
	private float height;
	private float circleRadius;
	private Vec2 cellSize;
	private Vec2 coinOffset;
	private boolean animated;
	private boolean gameOver;
	private boolean isCalculating;

	private BoardState boardState;
	private int columns;
	private int rows;

	public Coin[][] coins;
	
	private RectButton menu;
	private RectButton reset;
	private RectButton back;

	private UIObject victory;
	private UIObject draw;
	private Timer timer;
	
	public NormalGame(BoardState boardModel) {
		this.boardState = boardModel;
		position = new Vec2(0.01f, 0.01f);
		animated = false;	
		createButtons();
		
		int delay = 2000;
		timer = new Timer( delay, new ActionListener(){
		  @Override
		  public void actionPerformed( ActionEvent e ){
			  animateAI();
		  }
		} );
		
		timer.setRepeats(false);
	}
	
	/**
	 * Creates an alert when the game ends up in a draw
	 */
	public void isCalculating() {
		this.removeChild(menu);
		this.removeChild(reset);
		this.removeChild(back);
		
		isCalculating = true;
		draw = new BoardAlert(0.5f, 0.3f, new Vec2(0.49f, 0.3f), "Computer is Thinking...");
		addChild(draw);
		
		this.addChild(menu);
		this.addChild(reset);
		this.addChild(back);
	}
	
	/**
	 * Creates an alert when the game ends up in a draw
	 */
	public void isDraw() {
		this.removeChild(menu);
		this.removeChild(reset);
		this.removeChild(back);
		
		gameOver = true;
		isCalculating = false;
		
		draw = new BoardAlert(0.5f, 0.3f, new Vec2(0.49f, 0.3f), "The Game is Draw!");
		addChild(draw);
		
		this.addChild(menu);
		this.addChild(reset);
		this.addChild(back);
	}
	
	/**
	 * Creates an alert when a player wins
	 * @param id The ID of the Player
	 */
	public void victorious(int id) {	
		this.removeChild(menu);
		this.removeChild(reset);
		this.removeChild(back);
		
		gameOver = true;
		isCalculating = false;
		
		if(boardState.getCurrentPlayer().getPlayerType().equals(PlayerType.AI) && 
				boardState.getOtherPlayer().getPlayerType().equals(PlayerType.HUMAN)){
			
			victory = new BoardAlert(0.5f, 0.3f, new Vec2(0.49f, 0.3f), "Computer wins the Game!");
			this.removeChild(draw);
			addChild(victory);
		}
		else if(boardState.getCurrentPlayer().getPlayerType().equals(PlayerType.HUMAN) && 
				boardState.getOtherPlayer().getPlayerType().equals(PlayerType.AI)){
			
			victory = new BoardAlert(0.5f, 0.3f, new Vec2(0.49f, 0.3f), "Player wins the Game!");
			this.removeChild(draw);
			addChild(victory);
		}
		else if(boardState.getCurrentPlayer().getPlayerType().equals(PlayerType.HUMAN) && 
				boardState.getOtherPlayer().getPlayerType().equals(PlayerType.HUMAN)){
			
			victory = new BoardAlert(0.5f, 0.3f, new Vec2(0.49f, 0.3f), "Player " + id + " wins the Game!");
			addChild(victory);
		}
		else{
			victory = new BoardAlert(0.5f, 0.3f, new Vec2(0.49f, 0.3f), "Computer " + id + " wins the Game!");
			addChild(victory);
		}
		
		this.addChild(menu);
		this.addChild(reset);
		this.addChild(back);
	}

	/**
	 * Initialises the board's rows and columns
	 */
	public void initialiseColumnsRows() {
		this.rows = boardState.getBoardRow();
		this.columns = boardState.getBoardColumn();
		
		width = 0.98f;
		circleRadius = width / columns / 2 - 0.01f;
		height = (circleRadius + 0.01f) * 2 * rows;
		
		coins = new Coin[rows][columns];

		cellSize = new Vec2(width / columns, height / rows);
		coinOffset = cellSize.scale(0.5f);

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				Vec2 coinPos = new Vec2(cellSize.x * c, cellSize.y * r);
				coinPos = coinPos.plus(coinOffset);

				coins[r][c] = new Coin();
				coins[r][c].position = coinPos;
				coins[r][c].setColor(Color.WHITE);
				coins[r][c].setCircleRadius(circleRadius);

				coins[r][c].setActiveVisible(false);
				
				this.addChild(coins[r][c]);
			}
		}
	}
	
	/**
	 * Resets the Game (when the reset button is pressed)
	 */
	public void reset() {
		timer.stop();

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				coins[r][c].setColor(Color.WHITE);
			}
		}

		gameOver = false;
		isCalculating = false;
		
		//clear out everything
		ArrayList<Integer> rows = boardState.getWinningRow();
		ArrayList<Integer> cols = boardState.getWinningColumn();
		
		if (rows.size() > 0 && cols.size() > 0){
			for (int i = 0; i < boardState.getVictoryCondition(); i++) {
				coins[this.rows - rows.get(i) - 1][cols.get(i)].setWinning(false);
			}	
			
			boardState.getWinningRow().clear();
			boardState.getWinningColumn().clear();
		}
		
		this.removeChild(draw);
		this.removeChild(victory);
		
		if (victory != null) {
			victory.setActiveVisible(false);
		}
	}

	@Override
	public boolean mouseSelected() {
		Vec2 mousePos = getScaledMousePosition();
		if (mousePos == null)
			return false;

		Vec2 worldPosition = getWorldPosition();

		if ((mousePos.x >= worldPosition.x - width &&
				mousePos.y >= worldPosition.y - height) &&
				(mousePos.x <= worldPosition.x + width) &&
				(mousePos.y <= worldPosition.y + height)) {
			return true;
		}
		return false;
	}

	@Override
	public void onMouseDown() {
		// Put coin in column
		Vec2 mousePos = getScaledMousePosition();
		if (mousePos == null)
			return;

		int column = (int) ((mousePos.x - position.x) / (width / columns));
		// animation for dropping coin
		if (boardState.getTopRow(column) != -1 &&
				!boardState.getCurrentPlayer().getPlayerType().equals(PlayerType.AI)) {
			Vec2 endPosition = new Vec2(cellSize.x * column,
					cellSize.y * (rows - 1 - boardState.getTopRow(column)));
			endPosition = endPosition.plus(coinOffset);
			
			AnimatedCoin animatedCoin = new AnimatedCoin(endPosition,
					column, coins[rows - 1 - boardState.getTopRow(column)][column]);
			animatedCoin.setCircleRadius(this.circleRadius);
			animatedCoin.position = new Vec2(cellSize.x * column, cellSize.y * 0);
			animatedCoin.position = animatedCoin.position.plus(coinOffset);
			animatedCoin.setColor(boardState.getCurrentPlayer().getCoinColor());
			
			coins[rows - 1 - boardState.getTopRow(column)][column].setColor(Color.WHITE);
			this.addChild(animatedCoin);
			this.animated = true;
			gameOver = boardState.putCoin(column);
			
			if (gameOver) {
				if (boardState.isDraw()) {
					isDraw();
				} 
				else {
					victorious(boardState.getCurrentPlayerID());
				}
				return;
			}
		}
		
		if(boardState.getCurrentPlayer().getPlayerType().equals(PlayerType.AI) &&
				boardState.getOtherPlayer().getPlayerType().equals(PlayerType.HUMAN)){
			
			if (!isCalculating){
				isCalculating();
				timer.start();
			}
		}
	}

	/**
	 * Animates the AI's move
	 */
	public void animateAI() {
		int aiChoice = boardState.ai_Move();
		
		Vec2 endPosition = new Vec2(cellSize.x * aiChoice,
				cellSize.y * (rows - 1 - boardState.getTopRow(aiChoice)));
		endPosition = endPosition.plus(coinOffset);
		
		endPosition = new Vec2(cellSize.x * aiChoice,
				cellSize.y * (rows - 1 - boardState.getTopRow(aiChoice)));
		endPosition = endPosition.plus(coinOffset);
		
		AnimatedCoin animatedCoin = new AnimatedCoin(endPosition,
				aiChoice, coins[rows - 1 - boardState.getTopRow(aiChoice)][aiChoice]);
		animatedCoin.setCircleRadius(this.circleRadius);
		animatedCoin.position = new Vec2(cellSize.x * aiChoice, cellSize.y * 0);
		animatedCoin.position = animatedCoin.position.plus(coinOffset);
		animatedCoin.setColor(boardState.getCurrentPlayer().getCoinColor());
		
		coins[rows - 1 - boardState.getTopRow(aiChoice)][aiChoice].setColor(Color.WHITE);
		this.addChild(animatedCoin);
		this.animated = true;
		gameOver = boardState.putCoin(aiChoice);
		
		if(!boardState.getCurrentPlayer().getPlayerType().equals(PlayerType.AI) &&
				!boardState.getOtherPlayer().getPlayerType().equals(PlayerType.AI)){
			isCalculating = false;
			this.removeChild(draw);
		}

		if (gameOver) {
			if (boardState.isDraw()) {
				isDraw();
			} 
			else {
				victorious(boardState.getCurrentPlayerID());
			}
		}
	}
	
	/**
	 * Gets a boolean describing whether there is a currently falling animated coin
	 * @return boolean describing whether there is currently a falling animated coin
	 */
	public boolean isAnimated() {
		return animated;
	}

	/**
	 * Sets the boolean that indicates if there a currently falling animated coin
	 * @param the boolean indicating if there is a currently falling animated coin
	 */
	public void setAnimated(boolean animated) {
		this.animated = animated;
	}

	@Override
	public void onMouseUp() {
	}

	@Override
	protected void onUpdate() {
	}

	@Override
	protected void onRender(Graphics2D g2d) {
		Vec2 worldPos = getWorldPosition();
		
		Point pixelDim = toPixelCoordinates(cellSize);

		Image blueTile = IMAGE_STORE.GetScaledImage("tile_Blue", pixelDim.x, pixelDim.y);
		Image yellowTile = IMAGE_STORE.GetScaledImage("tile_Yellow", pixelDim.x, pixelDim.y);
		
		for(int y = 0; y < rows; y++){
			for(int x = 0; x < columns; x++){
				Vec2 offset = new Vec2(x * cellSize.x, y * cellSize.y);
				Point pixelPos = toPixelCoordinates(worldPos.plus(offset));
				
				if((x + y) % 2 == 0) {
					g2d.drawImage(blueTile, pixelPos.x, pixelPos.y, null);
				} 
				else {
					g2d.drawImage(yellowTile, pixelPos.x, pixelPos.y, null);
				}
			}
		}
		
		if (mouseSelected()) {
			Vec2 mousePos = getScaledMousePosition();
			if (mousePos == null)
				return;

			for (int i = 0; i < columns; i++) {
				if (boardState.getCoin(rows - 1, i) == 0) {
					coins[0][i].setColor(Color.WHITE);				
				}
			}
			
			int column = (int) ((mousePos.x - position.x) / (width / columns));
			if (column < columns && column >= 0 && 
					coins[0][column] != null && 
					coins[0][column].getColor().equals(Color.WHITE) &&
					!gameOver && !isCalculating && !animated) {
				// hover
				// top rows of column is empty
				// set coin color to the color of the current player
				
				if(!boardState.getCurrentPlayer().getPlayerType().equals(PlayerType.AI) &&
						!boardState.getOtherPlayer().getPlayerType().equals(PlayerType.AI)){

					Color color = boardState.getCurrentPlayer().getCoinColor();
					coins[0][column].setColor(color);
				}
			}
		}
		
		//to make ai work is in here
		
		if(boardState.getCurrentPlayer().getPlayerType().equals(PlayerType.AI) &&
				boardState.getOtherPlayer().getPlayerType().equals(PlayerType.AI) &&
				!animated &&
				!gameOver) { // current player is an ai and there is no animated coin
			animateAI();
		}
		
		if (gameOver) {
			ArrayList<Integer> rows = boardState.getWinningRow();
			ArrayList<Integer> cols = boardState.getWinningColumn();
			for (int i = 0; i < boardState.getVictoryCondition(); i++) {
				coins[this.rows - rows.get(i) - 1][cols.get(i)].setWinning(true);
			}			
		}
	}
	
	/**
	 * Generates reset, main menu and back buttons in the board window.
	 */
	private void createButtons(){
		float x = 0.1f;
		
		menu = new RectButton("mainmenu", "mainmenuSelected", x + 0.25f, 0.85f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				GAME_MANAGER.activateStart();
			}
		};
		
		reset = new RectButton("restart", "restartSelected", x + 0.6f, 0.85f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				GAME_MANAGER.activateReset();
			}
		};
		
		back = new RectButton("back", "backSelected", x, 0.85f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				GAME_MANAGER.back();
			}
		};
		
		this.addChild(menu);
		this.addChild(reset);
		this.addChild(back);
	}
	
}
