package gameObjects;

import gameEngine.*;

import java.awt.Color;
import java.awt.Graphics2D;

public class Board extends UIObject {

	private float width;
	private float height;
	private float circleRadius;
	private Vec2 cellSize;
	private Vec2 coinOffset;
	private boolean animated;
	private boolean gameOver;

	// private BufferedImage boardImg;

	private BoardModel boardModel;
	private int columns;
	private int rows;

	public Coin[][] coins;
	
	private UIObject reset;
	private UIObject back;
	private UIObject menu;
	
	private UIObject victory;
	private UIObject draw;
	
	public Board(BoardModel boardModel) {

		this.boardModel = boardModel;
		position = new Vec2(0.01f, 0.01f);
		animated = false;
		
		float x = 0.14f;
		menu = new BoardMainMenu(0.3f, 0.1f, new Vec2(x + 0.25f, 0.915f), "Main Menu");
		this.addChild(menu);
		reset = new BoardReset(0.3f, 0.1f, new Vec2(x + 0.6f, 0.915f), "Restart");
		this.addChild(reset);
		back = new BackButton(0.1f, 0.1f, new Vec2(x, 0.915f));
		this.addChild(back);
		
		/*
		 * try { boardImg = ImageIO.read(new File("assets/board.png")); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
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
		this.rows = boardModel.getRows();
		this.columns = boardModel.getColumns();
		
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
		if (boardModel.getTopRow(column) != -1) {
			Vec2 endPosition = new Vec2(cellSize.x * column,
					cellSize.y * (rows - 1 - boardModel.getTopRow(column)));
			endPosition = endPosition.plus(coinOffset);
			
			AnimatedCoin animatedCoin = new AnimatedCoin(endPosition,
					column, coins[rows - 1 - boardModel.getTopRow(column)][column]);
			animatedCoin.setCircleRadius(this.circleRadius);
			animatedCoin.position = new Vec2(cellSize.x * column, cellSize.y * 0);
			animatedCoin.position = animatedCoin.position.plus(coinOffset);
			if (boardModel.getCurrentPlayer() == 0) {
				animatedCoin.setColor(Color.WHITE);
			} else if (boardModel.getCurrentPlayer() == 1) {
				animatedCoin.setColor(Color.RED);
			} if (boardModel.getCurrentPlayer() == 2) {
				animatedCoin.setColor(Color.YELLOW);
			}
			coins[rows - 1 - boardModel.getTopRow(column)][column].setColor(Color.WHITE);
			this.addChild(animatedCoin);
			this.animated = true;
			boardModel.putCoin(column);
			
			if (boardModel.gameOver()) {
				if (boardModel.isDraw()) {
					isDraw();
				} else {
					victorious(boardModel.getCurrentPlayer());
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
				if (boardModel.getCoin(rows - 1, i) == 0) {
					coins[0][i].setColor(Color.WHITE);				
				}
			}
			
			
			int column = (int) ((mousePos.x - position.x) / (width / columns));
			if (column < columns && column >= 0 && 
					coins[0][column] != null && 
					coins[0][column].getColor().equals(Color.WHITE) &&
					!gameOver && !animated) { // top row of column is empty
				// set coin color to the color of the current player
				if (boardModel.getCurrentPlayer() == 0) {
					coins[0][column].setColor(Color.WHITE);
				} else if (boardModel.getCurrentPlayer() == 1) {
					coins[0][column].setColor(Color.RED);
				} if (boardModel.getCurrentPlayer() == 2) {
					coins[0][column].setColor(Color.YELLOW);
				}
			}
		}
	}
}
