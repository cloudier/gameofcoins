package gameObjects;

import gameEngine.GameObject;
import gameEngine.Vec2;

import java.awt.Color;
import java.awt.Graphics2D;

public class EyeFace extends GameObject {
	
	public EyeFace() {
		position = new Vec2(0.5f, 0.5f);
		velocity = new Vec2(0.001f, 0.001f);
		this.addChild(mouth);
		this.addChild(leftEye);
		this.addChild(rightEye);
	}

	@Override
	protected void onUpdate() {
		position = position.plus(velocity);
	}

	@Override
	protected void onRender(Graphics2D g2d) {
		Vec2 worldPos = getWorldPosition();
		
		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());
		
		int pixelWidth = (int) (faceRadius * JPANEL.getWidth() * 2f);
		int pixelHeight = (int) (faceRadius * JPANEL.getHeight() * 2f);
		
		g2d.setColor(Color.blue);
		
		g2d.drawOval(pixelX-pixelWidth/2, pixelY-pixelHeight/2, pixelWidth, pixelHeight);
	}
	
	class Eye extends GameObject {

		class Pupil extends GameObject {

			@Override
			protected void onUpdate() { }

			@Override
			protected void onRender(Graphics2D g2d) { }
			
		}
		
		@Override
		protected void onUpdate() {	}

		@Override
		protected void onRender(Graphics2D g2d) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class Mouth extends GameObject {

		public Mouth() {
			position = new Vec2(0.0f, 0.05f); // A little under center of parent
		}
		
		@Override
		protected void onUpdate() {	}

		@Override
		protected void onRender(Graphics2D g2d) {
			
			Vec2 worldPos = getWorldPosition();
			
			int pixelX = (int) (worldPos.x * JPANEL.getWidth());
			int pixelY = (int) (worldPos.y * JPANEL.getHeight());
			
			int pixelWidth = (int) (oval.x * JPANEL.getWidth() * 2f);
			int pixelHeight = (int) (oval.y * JPANEL.getHeight() * 2f);
			
			g2d.setColor(Color.blue);
			
			g2d.drawOval(pixelX-pixelWidth/2, pixelY-pixelHeight/2, pixelWidth, pixelHeight);
		}
		
		Vec2 oval = new Vec2(0.04f, 0.02f);
		
	}
	
	public Vec2 velocity = new Vec2();
	public float faceRadius = 0.08f;

	
	private Eye leftEye = new Eye();
	private Eye rightEye = new Eye();
	private Mouth mouth = new Mouth();


}
