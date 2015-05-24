package gameObjects;

import java.awt.Graphics2D;

import gameEngine.UIObject;
import gameEngine.Vec2;

public class ModeMenuBoardHeight extends UIObject {

	// select height of board: 4 <= n <= 20
	private ModeMenuBoardHeightNumber six;
	private ModeMenuBoardHeightNumber eight;
	private ModeMenuBoardHeightNumber ten;
	private ModeMenuBoardHeightNumber twelve;
	private ModeMenuBoardHeightNumber fourteen;
	private ModeMenuBoardHeightNumber sixteen;
	private ModeMenuBoardHeightNumber eighteen;
	
	public ModeMenuBoardHeight() {
		position = new Vec2(0, 0.6f);
		
		six = new ModeMenuBoardHeightNumber(6);
		addChild(six);
		six.position = new Vec2(-0.299f, 0f);

		eight = new ModeMenuBoardHeightNumber(8);
		addChild(eight);
		eight.position = new Vec2(-0.2f, 0f);

		ten = new ModeMenuBoardHeightNumber(10);
		addChild(ten);
		ten.position = new Vec2(-0.1f, 0f);

		twelve = new ModeMenuBoardHeightNumber(12);
		addChild(twelve);
		twelve.position = new Vec2(0f, 0f);

		fourteen = new ModeMenuBoardHeightNumber(14);
		addChild(fourteen);
		fourteen.position = new Vec2(0.1f, 0f);

		sixteen = new ModeMenuBoardHeightNumber(16);
		addChild(sixteen);
		sixteen.position = new Vec2(0.2f, 0f);

		eighteen = new ModeMenuBoardHeightNumber(18);
		addChild(eighteen);
		eighteen.position = new Vec2(0.3f, 0f);
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
