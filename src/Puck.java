import java.awt.Dimension;
import java.awt.Graphics2D;


public class Puck extends GameObject {

	public Puck() {
		position = new Vec2(0.5f, 0.5f);
		
		velocity = new Vec2(1.4f, 0.7f);
		
		visible = true;
		active = true;
	}

	@Override
	public void OnUpdate() {
		
		position = position.plus(velocity);
		
		if(position.x < 0 || position.x > 400)
		{
			velocity = new Vec2(-velocity.x, velocity.y);
		}
		
		if(position.y < 0 || position.y > 400)
		{
			velocity = new Vec2(velocity.x, -velocity.y);
		}
	}
	
	@Override
	public void OnRender(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.drawOval((int)position.x, (int)position.y, 50, 50);
	}

	@Override
	public void OnResize(Dimension panelSize) {
		// TODO Auto-generated method stub
		
	}
	
	protected Vec2 velocity;

}
