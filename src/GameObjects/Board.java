package gameObjects;

import gameEngine.*;

import java.awt.Color;
import java.awt.Graphics2D;

/*
 * StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
    StackTraceElement e = stacktrace[1];//coz 0th will be getStackTrace so 1st
    String methodName = e.getMethodName();
    System.out.println(methodName);
 */

public class Board extends UIObject {

	private float width;
	private float height;
	private float circleRadius;
	private Vec2 cellSize;
	private Vec2 coinOffset;
	private boolean animated;
	private boolean gameOver;

	private BoardState boardState;
	private int columns;
	private int rows;

	public Coin[][] coins;
	
	private UIObject reset;
	private UIObject back;
	private UIObject menu;
	
	private UIObject victory;
	private UIObject draw;
	
	public Board(BoardState boardModel) {

		this.boardState = boardModel;
		position = new Vec2(0.01f, 0.01f);
		animated = false;
		
		float x = 0.14f;
		menu = new BoardMainMenu(0.3f, 0.1f, new Vec2(x + 0.25f, 0.915f), "Main Menu");
		this.addChild(menu);
		reset = new BoardReset(0.3f, 0.1f, new Vec2(x + 0.6f, 0.915f), "Restart");
		this.addChild(reset);
		back = new BackButton(0.1f, 0.1f, new Vec2(x, 0.915f));
		this.addChild(back);
	}
	
	public void isDraw() {
		gameOver = true;
		draw = new BoardAlert(0.5f, 0.3f, new Vec2(0.49f, 0.3f), "Draw!");
		addChild(draw);
	}
	
	public void victorious(int id) {
		gameOver = true;
		victory = new BoardAlert(0.5f, 0.3f, new Vec2(0.49f, 0.3f), "Player " + id + " wins!");
		addChild(victory);
	}

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

				this.addChild(coins[r][c]);
			}
		}
	}
	
	public void reset() {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				coins[r][c].setColor(Color.WHITE);
			}
		}
		gameOver = false;
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
		if (boardState.getTopRow(column) != -1) {
			Vec2 endPosition = new Vec2(cellSize.x * column,
					cellSize.y * (rows - 1 - boardState.getTopRow(column)));
			endPosition = endPosition.plus(coinOffset);
			
			AnimatedCoin animatedCoin = new AnimatedCoin(endPosition,
					column, coins[rows - 1 - boardState.getTopRow(column)][column]);
			animatedCoin.setCircleRadius(this.circleRadius);
			animatedCoin.position = new Vec2(cellSize.x * column, cellSize.y * 0);
			animatedCoin.position = animatedCoin.position.plus(coinOffset);
			if (boardState.getCurrentPlayer() == 0) {
				animatedCoin.setColor(Color.WHITE);
			} else if (boardState.getCurrentPlayer() == 1) {
				animatedCoin.setColor(Color.RED);
			} else if (boardState.getCurrentPlayer() == 2) {
				animatedCoin.setColor(Color.YELLOW);
			} else if (boardState.getCurrentPlayer() == 3) {
				animatedCoin.setColor(Color.GREEN);
			} else if (boardState.getCurrentPlayer() == 4) {
				animatedCoin.setColor(Color.MAGENTA);
			}
			
			coins[rows - 1 - boardState.getTopRow(column)][column].setColor(Color.WHITE);
			this.addChild(animatedCoin);
			this.animated = true;
			gameOver = boardState.putCoin(column);
			
			if (gameOver) {
				if (boardState.isDraw()) {
					isDraw();
				} else {
					victorious(boardState.getCurrentPlayer());
				}
				return;
			}
			
			// animate AI move
			int aiChoice = boardState.ai_Move();
			
			endPosition = new Vec2(cellSize.x * aiChoice,
					cellSize.y * (rows - 1 - boardState.getTopRow(aiChoice)));
			endPosition = endPosition.plus(coinOffset);
			System.out.println(aiChoice);
			animatedCoin = new AnimatedCoin(endPosition,
					aiChoice, coins[rows - 1 - boardState.getTopRow(aiChoice)][aiChoice]);
			animatedCoin.setCircleRadius(this.circleRadius);
			animatedCoin.position = new Vec2(cellSize.x * aiChoice, cellSize.y * 0);
			animatedCoin.position = animatedCoin.position.plus(coinOffset);
			if (boardState.getCurrentPlayer() == 0) {
				animatedCoin.setColor(Color.WHITE);
			} else if (boardState.getCurrentPlayer() == 1) {
				animatedCoin.setColor(Color.RED);
			} else if (boardState.getCurrentPlayer() == 2) {
				animatedCoin.setColor(Color.YELLOW);
			} else if (boardState.getCurrentPlayer() == 3) {
				animatedCoin.setColor(Color.GREEN);
			} else if (boardState.getCurrentPlayer() == 4) {
				animatedCoin.setColor(Color.MAGENTA);
			}
			
			coins[rows - 1 - boardState.getTopRow(aiChoice)][aiChoice].setColor(Color.WHITE);
			this.addChild(animatedCoin);
			this.animated = true;
			gameOver = boardState.putCoin(aiChoice);
			
			if (gameOver) {
				if (boardState.isDraw()) {
					isDraw();
				} else {
					victorious(boardState.getCurrentPlayer());
				}
			}
		}
		
	}

	public boolean isAnimated() {
		return animated;
	}

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

		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());

		int pixelWidth = (int) (width * JPANEL.getWidth());
		int pixelHeight = (int) (height * JPANEL.getHeight());

		// draw board background
		// g2d.drawImage(boardImg, pixelX, pixelY, pixelWidth, pixelHeight, null);
		g2d.setColor(Color.BLUE);
		g2d.fillRect(pixelX, pixelY, pixelWidth, pixelHeight);
		
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
					!gameOver && !animated) { // top rows of column is empty
				// set coin color to the color of the current player
				if (boardState.getCurrentPlayer() == 0) {
					coins[0][column].setColor(Color.WHITE);
				} else if (boardState.getCurrentPlayer() == 1) {
					coins[0][column].setColor(Color.RED);
				} else if (boardState.getCurrentPlayer() == 2) {
					coins[0][column].setColor(Color.YELLOW);
				} else if (boardState.getCurrentPlayer() == 3) {
					coins[0][column].setColor(Color.GREEN);
				} else if (boardState.getCurrentPlayer() == 4) {
					coins[0][column].setColor(Color.MAGENTA);
				}
			}
		}
	}
}
