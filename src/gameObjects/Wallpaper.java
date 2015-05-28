package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;

import gameEngine.GameObject;
import gameEngine.Vec2;

/**
 * Generates a wooden background for the game.
 * @author ivormetcalf
 *
 */
public class Wallpaper extends GameObject {

	public Wallpaper() {
	}

	@Override
	protected void onUpdate() {
	}

	@Override
	protected void onRender(Graphics2D g2d) {
		Image bgImage = IMAGE_STORE.GetScaledImage("background", 1280, 800);
		g2d.drawImage(bgImage, 0, 0, null);
	}
}
