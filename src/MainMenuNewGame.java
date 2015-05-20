import java.awt.Color;
import java.awt.Graphics2D;


public class MainMenuNewGame extends UIObject {
	private float circleRadius;

	public MainMenuNewGame() {
		// TODO Auto-generated constructor stub
		position = new Vec2(.5f, .5f);
		circleRadius = .03f;
	}

	@Override
	public boolean MouseSelected() {
		Vec2 mousePos = GetScaledMousePosition();
		if(mousePos == null) return false;
		
		Vec2 displacement = position.minus(mousePos);
		
		if(displacement.magnitude() < circleRadius)
			return true;
		else 
			return false;
	}

	@Override
	public void OnMouseDown() {
	}

	@Override
	public void OnMouseUp() {
		System.out.println("Clicked");
		// make ModeMenu visible and active
		
		GAME_MANAGER.activateNextState();
		// make MainMenu invisible and inactive
		
		GetParent().visible = false;
		GetParent().active = false;
		this.visible = false;
		
	}

	@Override
	protected void OnUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void OnRender(Graphics2D g2d) {
		// TODO Auto-generated method stub
		Vec2 worldPos = GetWorldPosition();
		
		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());
		
		int pixelWidth = (int) (circleRadius * JPANEL.getWidth() * 2f);
		int pixelHeight = (int) (circleRadius * JPANEL.getHeight() * 2f);
		
		if(MouseSelected())
		{
			g2d.setColor(Color.BLUE);
		}
		else 
		{
			g2d.setColor(Color.BLACK);
		}
		
		g2d.fillOval(pixelX-pixelWidth/2, pixelY-pixelHeight/2, pixelWidth, pixelHeight);
		
	}

}
