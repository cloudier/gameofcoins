package gameObjects;

import java.awt.Graphics2D;

import gameEngine.UIObject;

public class ModeMenuBoard extends UIObject {

	// select width of board: 4 <= n <= 20
	// select height of board: 4 <= n <= 20
	
	@Override
	public boolean mouseSelected() {
		return false;
	}

	@Override
	public void onMouseDown() {
	}

	@Override
	public void onMouseUp() {
	}

	@Override
	protected void onUpdate() {
	}

	@Override
	protected void onRender(Graphics2D g2d) {
	}

}
