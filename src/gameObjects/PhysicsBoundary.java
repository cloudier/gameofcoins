package gameObjects;

import gameEngine.GameObject;
import gameEngine.Vec2;

import java.awt.Graphics2D;

public class PhysicsBoundary extends GameObject {

	/**
	 * Create boundaries for the board 
	 */
	public PhysicsBoundary() {
		position = new Vec2();
		AddWallBounds();
	}

	@Override
	protected void onUpdate() {	}

	@Override
	protected void onRender(Graphics2D g2d) { }
	
	private void AddWallBounds(){
		Vec2 p1 = new Vec2(0.0f, 0.0f);
		Vec2 p2 = new Vec2(1.0f, 0.0f);
		Vec2 p3 = new Vec2(1.0f, 1.0f);
		Vec2 p4 = new Vec2(0.0f, 1.0f);

		StaticWall sw = new StaticWall(p2, p1);
		this.addChild(sw);
		
		sw = new StaticWall(p3, p2);
		this.addChild(sw);
		
		sw = new StaticWall(p4, p3);
		this.addChild(sw);
		
		sw = new StaticWall(p1, p4);
		this.addChild(sw);
	}
}
