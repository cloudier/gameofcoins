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
	//private BufferedImage image;
	private Color color;
	private float circleRadius;
	
	public Coin() {
		circleRadius = .06f;
		position = new Vec2();
		color = Color.WHITE;
		/*try {
			image = ImageIO.read(new File("assets/red.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	public void setColor(Color color) {
		this.color = color;
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
		if (this.color != null) {
			Vec2 worldPos = getWorldPosition();

			int pixelX = (int) (worldPos.x * JPANEL.getWidth());
			int pixelY = (int) (worldPos.y * JPANEL.getHeight());

			int pixelWidth = (int) (circleRadius * JPANEL.getWidth() * 2f);
			int pixelHeight = (int) (circleRadius * JPANEL.getHeight() * 2f);

			g2d.setColor(this.color);
			g2d.fillOval(pixelX - pixelWidth / 2, pixelY - pixelHeight / 2,
					pixelWidth, pixelHeight);
		}
	}

}
