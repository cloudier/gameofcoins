import java.awt.Dimension;
import java.awt.Graphics2D;


public class Puck extends GameObject {

	public Puck() {
		float px = (float) Math.random() * 400;
		float py = (float) Math.random() * 400;
		float vx = (float) Math.random() * 5;
		float vy = (float) Math.random() * 5;
		
		position = new Vec2(px, py);
		velocity = new Vec2(vx, vy);
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
		g2d.drawOval((int)position.x, (int)position.y, 50, 50);
	}

	@Override
	public void OnResize(Dimension panelSize) {
		// TODO Auto-generated method stub
		
	}
	
	protected Vec2 velocity;

}
