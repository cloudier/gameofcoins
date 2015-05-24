package gameObjects;

import java.awt.Graphics2D;

import gameEngine.UIObject;
import gameEngine.Vec2;

public class ModeMenuBoardWidth extends UIObject {

	private ModeMenuBoardWidthNumber seven;
	private ModeMenuBoardWidthNumber nine;
	private ModeMenuBoardWidthNumber eleven;
	private ModeMenuBoardWidthNumber thirteen;
	private ModeMenuBoardWidthNumber fifteen;
	private ModeMenuBoardWidthNumber seventeen;
	private ModeMenuBoardWidthNumber nineteen;
	
	public ModeMenuBoardWidth() {
		position = new Vec2(0, 0.45f);
		seven = new ModeMenuBoardWidthNumber(7);
		addChild(seven);
		seven.position = new Vec2(-0.299f, 0f);

		nine = new ModeMenuBoardWidthNumber(9);
		addChild(nine);
		nine.position = new Vec2(-0.2f, 0f);

		eleven = new ModeMenuBoardWidthNumber(11);
		addChild(eleven);
		eleven.position = new Vec2(-0.1f, 0f);

		thirteen = new ModeMenuBoardWidthNumber(13);
		addChild(thirteen);
		thirteen.position = new Vec2(0f, 0f);

		fifteen = new ModeMenuBoardWidthNumber(15);
		addChild(fifteen);
		fifteen.position = new Vec2(0.1f, 0f);

		seventeen = new ModeMenuBoardWidthNumber(17);
		addChild(seventeen);
		seventeen.position = new Vec2(0.2f, 0f);

		nineteen = new ModeMenuBoardWidthNumber(19);
		addChild(nineteen);
		nineteen.position = new Vec2(0.3f, 0f);
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
