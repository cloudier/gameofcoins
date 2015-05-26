package gameObjects;

import gameEngine.*;

public class ModeMenuNext extends Button {
	
	public ModeMenuNext(float width, float height, Vec2 position, String string) {
		super(width, height, position, string);
	}

	@Override
	public void onMouseDown() {
		((ModeMenu) this.getParent()).activatePlayers();
	}
}
