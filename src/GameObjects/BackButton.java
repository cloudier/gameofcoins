package gameObjects;

import gameEngine.Vec2;

public class BackButton extends Button{
	public BackButton(float width, float height, Vec2 position) {
		super(width, height, position, "‹");
	}

	public void onMouseDown() {
		GAME_MANAGER.back();
	}
}
