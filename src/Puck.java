import java.awt.Graphics2D;


public class Puck extends GameObject {

	public Puck() 
	{
		float px = (float) Math.random();
		float py = (float) Math.random();
		float vx = (float) Math.random()/200;
		float vy = (float) Math.random()/200;
		
		circleRadius = 0.03f;
		
		position = new Vec2(px, py);
		velocity = new Vec2(vx, vy);
		
		Moon moonChild = new Moon();
		AddChild(moonChild);
		
	}

	@Override
	protected void OnUpdate() {

		position = position.plus(velocity);
		
		if(position.x < circleRadius)
		{
			position = new Vec2(circleRadius, position.y);
			velocity = new Vec2(-velocity.x, velocity.y);
		}
		else if(position.x > 1-circleRadius)
		{
			position = new Vec2(1-circleRadius, position.y);
			velocity = new Vec2(-velocity.x, velocity.y);
		}
		
		if(position.y < circleRadius)
		{
			position = new Vec2(position.x, circleRadius);
			velocity = new Vec2(velocity.x, -velocity.y);
		}
		else if(position.y > 1-circleRadius)
		{
			position = new Vec2(position.x, 1-circleRadius);
			velocity = new Vec2(velocity.x, -velocity.y);
		}
	}
	
	@Override
	protected void OnRender(Graphics2D g2d) {
		
		Vec2 worldPos = GetWorldPosition();
		
		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());
		
		int pixelWidth = (int) (circleRadius * JPANEL.getWidth() * 2f);
		int pixelHeight = (int) (circleRadius * JPANEL.getHeight() * 2f);
		
		g2d.drawOval(pixelX-pixelWidth/2, pixelY-pixelHeight/2, pixelWidth, pixelHeight);
		
	}

	protected float circleRadius;
	protected Vec2 velocity;
}
