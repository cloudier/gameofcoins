package gameObjects;
import gameEngine.UIObject;
import gameEngine.Vec2;

import java.awt.Graphics2D;


public class ModeMenu extends UIObject{
	
	private String mode;

	public ModeMenu() {
		position = new Vec2(0.5f, 0.2f);

		// TODO Auto-generated constructor stub
		// select mode: normal/blitz
		this.mode = "Normal";
		UIObject modeMenuNormal = new ModeMenuNormal();
		UIObject modeMenuBlitz = new ModeMenuBlitz();
		
		this.AddChild(modeMenuNormal);
		this.AddChild(modeMenuBlitz);
		// select victory condition: 4 <= n <= 10
		
		// select width of board: 4 <= n <= 20
		
		// select height of board: 4 <= n <= 20
	}
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	@Override
	public boolean MouseSelected() {
		return false;
	}

	@Override
	public void OnMouseDown() {
	}

	@Override
	public void OnMouseUp() {
	}

	@Override
	protected void OnUpdate() {
	}

	@Override
	protected void OnRender(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

}
