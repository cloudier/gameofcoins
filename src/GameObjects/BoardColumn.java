package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameEngine.UIObject;
import gameEngine.Vec2;

public class BoardColumn extends UIObject {
	
	public BoardColumn() {
		// make child coins
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
		if (mouseSelected()) {
			// hover - show arrow above column
		}
	}

}
