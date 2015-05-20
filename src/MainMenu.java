import java.awt.Graphics2D;


public class MainMenu extends UIObject {
	private UIObject MainMenuNewGame;
	private UIObject MainMenuExit;

	public MainMenu() {
		super();
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		// render some text
	}

}
