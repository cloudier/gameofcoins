package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameEngine.UIObject;
import gameEngine.Vec2;

public class Coin extends UIObject {
	private BufferedImage image;
	
	private float circleRadius;
	
	public Coin() {
		circleRadius = .03f;
		position = new Vec2();
		
		try {
			image = ImageIO.read(new File("assets/red.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean mouseSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onMouseDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseUp() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onRender(Graphics2D g2d) {
		// TODO Auto-generated method stub

		Vec2 worldPos = getWorldPosition();

		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());

		int pixelWidth = (int) (circleRadius * JPANEL.getWidth() * 2f);
		int pixelHeight = (int) (circleRadius * JPANEL.getHeight() * 2f);

		if (mouseSelected()) {
			g2d.setColor(Color.BLUE);
		} else {
			g2d.setColor(Color.BLACK);
		}

		g2d.drawImage(image, pixelX - pixelWidth / 2, pixelY - pixelHeight / 2,
				pixelWidth, pixelHeight, null);

	}

}
