package demos;

import gameEngine.UIObject;
import gameEngine.Vec2;

import java.awt.Color;
import java.awt.Graphics2D;

public class CircleButton extends UIObject {

	public CircleButton() {
		position = new Vec2(.5f, .5f);
		circleRadius = .03f;
	}

	@Override
	protected void onUpdate() {
	}

	@Override
	protected void onRender(Graphics2D g2d) {
		
		Vec2 worldPos = getWorldPosition();
		
		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());
		
		int pixelWidth = (int) (circleRadius * JPANEL.getWidth() * 2f);
		int pixelHeight = (int) (circleRadius * JPANEL.getHeight() * 2f);
		
		if(mouseSelected())
		{
			g2d.setColor(Color.BLUE);
		}
		else 
		{
			g2d.setColor(Color.BLACK);
		}
		
		g2d.drawOval(pixelX-pixelWidth/2, pixelY-pixelHeight/2, pixelWidth, pixelHeight);
		
	}
	
	@Override
	public void onMouseDown() { }

	@Override
	public void onMouseUp() { }
	
	@Override
	public boolean mouseSelected() {
		Vec2 mousePos = getScaledMousePosition();
		if(mousePos == null) return false;
		
		Vec2 displacement = position.minus(mousePos);
		
		if(displacement.magnitude() < circleRadius)
			return true;
		else 
			return false;
	}
	
	protected float circleRadius;
}
