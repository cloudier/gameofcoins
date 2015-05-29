package gameObjects;

import gameEngine.Vec2;

public class BackButton extends Button{

	/**
	 * Create "Back" button 
	 * @param width The width of the Back button
	 * @param height The height of the Back button
	 * @param position The position of type Vec2 of the Back button
	 */
	public BackButton(float width, float height, Vec2 position) {
		super(width, height, position, "<");
	}

	/**
	 * Call the back function in the game manager class to get back to previous state
	 */
	public void onMouseDown() {
		GAME_MANAGER.back();
	}
}
