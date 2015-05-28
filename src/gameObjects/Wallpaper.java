package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;

import gameEngine.GameObject;
import gameEngine.Vec2;

public class Wallpaper extends GameObject {

	public Wallpaper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onUpdate() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void onRender(Graphics2D g2d) {
		Image bgImage = IMAGE_STORE.GetScaledImage("background", 1280, 800);
		g2d.drawImage(bgImage, 0, 0, null);
	}
}
