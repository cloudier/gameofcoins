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
	private ArrayList<BoardColumn> boardColumns;
	
	public Board() {
		position = new Vec2(.1f, .1f);
		
		width = 1f * 0.8f;
		height = .857f * 0.8f;
		
		boardColumns = new ArrayList<BoardColumn>();
		
		try {
			boardImg = ImageIO.read(new File("assets/board.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void makeColumns(int columns) {
		for (int i = 0; i < columns; i++) {
			boardColumns.add(new BoardColumn());
		}
	}
	
	@Override
	public boolean mouseSelected() {
		return false;
	}

	@Override
	public void onMouseDown() { }

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
