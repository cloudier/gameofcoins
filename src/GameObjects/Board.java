package GameObjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameEngine.UIObject;
import GameEngine.Vec2;


public class Board extends UIObject{

	public Board() {
		
		position = new Vec2(.1f, .1f);
		
		width = 1f * 0.8f;
		height = .857f * 0.8f;
		
		try {
			boardImg = ImageIO.read(new File("assets/board.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean MouseSelected() { return false; }

	@Override
	public void OnMouseDown() { }

	@Override
	public void OnMouseUp() { }

	@Override
	protected void OnUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void OnRender(Graphics2D g2d) {
		
		Vec2 worldPos = GetWorldPosition();
		
		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());

		int pixelWidth = (int) (width * JPANEL.getWidth());
		int pixelHeight = (int) (height * JPANEL.getHeight());

		g2d.drawImage(boardImg, pixelX, pixelY, pixelWidth, pixelHeight, null);
		
	}

	private float width, height;
	
	private BufferedImage boardImg;
	
}
