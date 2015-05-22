package gameObjects;

import gameEngine.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.util.*;

public class Board extends UIObject{

	private float width, height;
	
	private BufferedImage boardImg;
	
	public final int columns = 7;
	public final int rows = 6;
	
	public Coin[][] coins; 
	
	public Board() {
		
		position = new Vec2(.1f, .1f);
		
		width = 1f * 0.8f;
		height = .857f * 0.8f;
		
		
		coins = new Coin[rows][columns];
		
		Vec2 cellSize = new Vec2(width/columns, height/rows);
		Vec2 coinOffset = cellSize.scale(0.5f);
		
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < columns; c++)
			{
				Vec2 coinPos = new Vec2(cellSize.x*c, cellSize.y*r);
				coinPos = coinPos.plus(coinOffset);
				
				coins[r][c] = new Coin();
				coins[r][c].position = coinPos;
				
				System.out.println(cellSize);
				
				this.addChild(coins[r][c]);
			}
		}
		

		
		try {
			boardImg = ImageIO.read(new File("assets/board.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void makeColumns(int columns) {
		
	}
	
	@Override
	public boolean mouseSelected() {
		
		return true;
	}

	@Override
	public void onMouseDown() {
		//Put coin in column
		Vec2 mousePos = getScaledMousePosition();
		if (mousePos == null) return;
		
		int column = (int) ((mousePos.x-position.x)/(width/columns));
		GAME_MANAGER.addCoin(column);
		
	}

	@Override
	public void onMouseUp() { }

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
		g2d.drawImage(boardImg, pixelX, pixelY, pixelWidth, pixelHeight, null);
		
		
	}
}
