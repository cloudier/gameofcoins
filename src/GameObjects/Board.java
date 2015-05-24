package gameObjects;

import gameEngine.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.util.*;

public class Board extends UIObject {

	private float width, height;
	private Vec2 cellSize;
	private Vec2 coinOffset;

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

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				Vec2 coinPos = new Vec2(cellSize.x * c, cellSize.y * r);
				coinPos = coinPos.plus(coinOffset);

				coins[r][c] = new Coin();
				coins[r][c].position = coinPos;

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
					column, coins[GAME_MANAGER.getTopRow(column)][column]);
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
			GAME_MANAGER.addCoin(column);
		}
	}

	@Override
	public void onMouseUp() {
	}

	@Override
	protected void onUpdate() {
		// TODO Auto-generated method stub

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

		int[][] modelCoins = GAME_MANAGER.getCoins();
		Color coinColor;
		// change this
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				if (modelCoins[r][c] == 0) {
					coinColor = Color.WHITE;			
				} else if (modelCoins[r][c] == 1) {
					coinColor = Color.RED;										
				} else if (modelCoins[r][c] == 2) {
					coinColor = Color.YELLOW;										
				} else {
					coinColor = Color.WHITE;
				}
				coins[rows - r - 1][c].setColor(coinColor);
			}
		}
	}
}
