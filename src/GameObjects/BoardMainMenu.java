package gameObjects;

import gameEngine.Vec2;

public class BoardMainMenu extends Button {

	public BoardMainMenu(float width, float height, Vec2 position, String string) {
		super(width, height, position, string);
	}

	public void onMouseDown() {
		GAME_MANAGER.activateStart();
	}
}
