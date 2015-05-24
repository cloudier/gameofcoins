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
	private ModeMenuVictoryNumber eight;
	private ModeMenuVictoryNumber nine;
	private ModeMenuVictoryNumber ten;
	
	public ModeMenuVictory() {
		position = new Vec2(0, 0.3f);
		// 4 <= n <= 10
		four = new ModeMenuVictoryNumber(4);
		addChild(four);
		four.position = new Vec2(-0.295f, 0f);

		five = new ModeMenuVictoryNumber(5);
		addChild(five);
		five.position = new Vec2(-0.2f, 0f);
		
		six = new ModeMenuVictoryNumber(6);
		addChild(six);
		six.position = new Vec2(-0.1f, 0f);

		seven = new ModeMenuVictoryNumber(7);
		addChild(seven);
		seven.position = new Vec2(0.0f, 0f);

		eight = new ModeMenuVictoryNumber(8);
		addChild(eight);
		eight.position = new Vec2(0.1f, 0f);

		nine = new ModeMenuVictoryNumber(9);
		addChild(nine);
		nine.position = new Vec2(+0.2f, 0f);

		ten = new ModeMenuVictoryNumber(10);
		addChild(ten);
		ten.position = new Vec2(+0.3f, 0f);
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
