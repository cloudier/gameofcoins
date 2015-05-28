package gameObjects;

import java.awt.Graphics2D;

import gameEngine.UIObject;
import gameEngine.Vec2;

public class ModeMenuVictory extends UIObject {
	// turn off inappropriate numbers depending on board size
	private ModeMenuVictoryNumber four;
	private ModeMenuVictoryNumber five;
	private ModeMenuVictoryNumber six;
	private ModeMenuVictoryNumber seven;
	
	/**
	 * Constructor to define the position of the winning condition on the panel
	 */
	public ModeMenuVictory() {
		position = new Vec2(0.5f, 0.8f);
		// 4 <= n <= 7
		four = new ModeMenuVictoryNumber(4);
		addChild(four);
		four.position = new Vec2(-0.15f, 0f);

		five = new ModeMenuVictoryNumber(5);
		addChild(five);
		five.position = new Vec2(-0.05f, 0f);
		
		six = new ModeMenuVictoryNumber(6);
		addChild(six);
		six.position = new Vec2(0.05f, 0f);

		seven = new ModeMenuVictoryNumber(7);
		addChild(seven);
		seven.position = new Vec2(0.15f, 0f);
	}
	
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
