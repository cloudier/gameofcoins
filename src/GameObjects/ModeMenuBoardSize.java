package gameObjects;

import java.awt.Graphics2D;

import gameEngine.UIObject;
import gameEngine.Vec2;

public class ModeMenuBoardSize extends UIObject {

	// select height of board: 4 <= n <= 20
	private ModeMenuBoardSizeNumber small;
	private ModeMenuBoardSizeNumber medium;
	private ModeMenuBoardSizeNumber large;
	
	public ModeMenuBoardSize() {
		position = new Vec2(0, 0.45f);
		
		small = new ModeMenuBoardSizeNumber(7, 6);
		addChild(small);
		small.position = new Vec2(-0.2f, 0f);

		medium = new ModeMenuBoardSizeNumber(14, 12);
		addChild(medium);
		medium.position = new Vec2(0.0f, 0f);

		large = new ModeMenuBoardSizeNumber(21, 18);
		addChild(large);
		large.position = new Vec2(+0.2f, 0f);
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
