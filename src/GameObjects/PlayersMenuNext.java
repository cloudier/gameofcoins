package gameObjects;

import gameEngine.*;

public class PlayersMenuNext extends Button {
	
	public PlayersMenuNext(float width, float height, Vec2 position, String string) {
		super(width, height, position, string);
	}

	@Override
	public void onMouseDown() {
		((PlayersMenu) this.getParent()).activateBoard();
	}
}