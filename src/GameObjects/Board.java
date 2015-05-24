package gameObjects;

import gameEngine.*;

import java.awt.Color;
import java.awt.Graphics2D;

public class Board extends UIObject {

	private float width;
	private float height;
	private Vec2 cellSize;
	private Vec2 coinOffset;
	private boolean animated;

	// private BufferedImage boardImg;

	private int columns = 7;
	private int rows = 6;

	public Coin[][] coins;
	
	public Board() {

		/*
		 * position = new Vec2(.1f, .1f);
		 * 
		 * width = 1f * 0.8f; height = .857f * 0.8f;
		 */
		position = new Vec2(0.01f, 0.01f);
		width = .07f * 2 * columns;
		height = .07f * 2 * rows;

		coins = new Coin[rows][columns];

		cellSize = new Vec2(width / columns, height / rows);
		coinOffset = cellSize.scale(0.5f);
		
		animated = false;

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				Vec2 coinPos = new Vec2(cellSize.x * c, cellSize.y * r);
				coinPos = coinPos.plus(coinOffset);

				coins[r][c] = new Coin();
				coins[r][c].position = coinPos;
				coins[r][c].setColor(Color.WHITE);

				this.addChild(coins[r][c]);
			}
		}

		/*
		 * try { boardImg = ImageIO.read(new File("assets/board.png")); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
	}

	public void makeColumns(int columns) {
		// move column stuff in constructor here
	}

	@Override
	public boolean mouseSelected() {

		return true;
	}

	@Override
	public void onMouseDown() {
		// Put coin in column
		Vec2 mousePos = getScaledMousePosition();
		if (mousePos == null)
			return;

		int column = (int) ((mousePos.x - position.x) / (width / columns));
		// animation for dropping coin
		if (GAME_MANAGER.getTopRow(column) != -1) {
			Vec2 endPosition = new Vec2(cellSize.x * column,
					cellSize.y * (rows - 1 - GAME_MANAGER.getTopRow(column)));
			endPosition = endPosition.plus(coinOffset);
			AnimatedCoin animatedCoin = new AnimatedCoin(endPosition,
					column, coins[rows - 1 - GAME_MANAGER.getTopRow(column)][column]);
			animatedCoin.position = new Vec2(cellSize.x * column, cellSize.y * 0);
			animatedCoin.position = animatedCoin.position.plus(coinOffset);
			if (GAME_MANAGER.getCurrentPlayer() == 0) {
				animatedCoin.setColor(Color.WHITE);
			} else if (GAME_MANAGER.getCurrentPlayer() == 1) {
				animatedCoin.setColor(Color.RED);
			} if (GAME_MANAGER.getCurrentPlayer() == 2) {
				animatedCoin.setColor(Color.YELLOW);
			}
			coins[rows - 1 - GAME_MANAGER.getTopRow(column)][column].setColor(Color.WHITE);
			this.addChild(animatedCoin);
			this.animated = true;
			GAME_MANAGER.addCoin(column);
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
				if (GAME_MANAGER.getSlot(rows - 1, i) == 0) {
					coins[0][i].setColor(Color.WHITE);				
				}
			}
			
			
			int column = (int) ((mousePos.x - position.x) / (width / columns));
			if (column != 7 && 
					coins[0][column] != null && 
					coins[0][column].getColor().equals(Color.WHITE) &&
					!animated) { // top row of column is empty
				// set coin color to the color of the current player
				if (GAME_MANAGER.getCurrentPlayer() == 0) {
					coins[0][column].setColor(Color.WHITE);
				} else if (GAME_MANAGER.getCurrentPlayer() == 1) {
					coins[0][column].setColor(Color.RED);
				} if (GAME_MANAGER.getCurrentPlayer() == 2) {
					coins[0][column].setColor(Color.YELLOW);
				}
			}
		}
	}
	
	public void setCoinColor(int row, int column, Color color) {
		coins[rows - row - 1][column].setColor(color);
	}
}
