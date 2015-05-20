package GameObjects;
import java.awt.Color;
import java.awt.Graphics2D;

import GameEngine.UIObject;
import GameEngine.Vec2;


public class MainMenu extends UIObject {
	private UIObject MainMenuNewGame;
	private UIObject MainMenuExit;
	private float width;
	private float height;
	
	public MainMenu() {
		super();
		
		position = new Vec2(0.5f, 0.2f);
		this.width = 0.04f;
		this.height = 0.05f;
		UIObject MainMenuNewGame = new MainMenuNewGame();
		this.AddChild(MainMenuNewGame);
		//UIObject MainMenuExit = new MainMenuExit(); // exit button
	}
	
	@Override
	public boolean MouseSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void OnMouseDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnMouseUp() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void OnUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void OnRender(Graphics2D g2d) {
		Vec2 worldPos = GetWorldPosition();
		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());

		g2d.setColor(Color.BLACK);
		g2d.drawString("Connect 4", pixelX - width*JPANEL.getWidth(), pixelY);
	}

}
