package gameObjects;

import gameEngine.*;

public class PlayersSettingsTypeHuman extends Button {
	
	public PlayersSettingsTypeHuman(float width, float height, Vec2 position, String string) {
		super(width, height, position, string);
	}

	@Override
	public void onMouseDown() {
		((PlayersSettingsType) this.getParent()).setType(string);
	}

}
