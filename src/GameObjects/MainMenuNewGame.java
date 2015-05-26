package gameObjects;

import gameEngine.*;

public class MainMenuNewGame extends Button {

	public MainMenuNewGame() {
		super(.4f, .1f, new Vec2(0f, 0.3f), "New Game");
	}

	@Override
	public void onMouseDown() {
		GAME_MANAGER.activateMode();
	}
}
