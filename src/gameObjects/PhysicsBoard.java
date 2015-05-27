package gameObjects;

import gameEngine.UIObject;
import gameEngine.Vec2;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;


public class PhysicsBoard extends UIObject{

	public PhysicsBoard() {

		tilesX = 7;
		tilesY = 6;
		
		Vec2 totalBoardSize = new Vec2(0.9f, 0.8f);
		
		tileWidth = totalBoardSize.x/tilesX;
		tileHeight = totalBoardSize.y/tilesY;
		
		AddPhysicsBoard();
		
		Vec2 totalSize = GetTotalSize();
		
		position = new Vec2(1f - totalSize.x, 1f - totalSize.y);
		
		velocity = new Vec2(0.0003f, 0f);
	}
	
	public Vec2 GetTotalSize() {
		return new Vec2(tilesX * tileWidth, tilesY * tileHeight);
	}

	@Override
	public boolean mouseSelected() { return false; }

	@Override
	public void onMouseDown() { }

	@Override
	public void onMouseUp() { }

	@Override
	protected void onUpdate() { 
		position = position.plus(velocity);
		if(position.x + GetTotalSize().x > 1f || position.x < 0f) {
			velocity = velocity.flip();
		}
	}

	@Override
	protected void onRender(Graphics2D g2d) {
		Vec2 worldPos = getWorldPosition();
		
		int pixelWidth = (int) (tileWidth * JPANEL.getWidth());
		int pixelHeight = (int) (tileHeight * JPANEL.getHeight());

		Image blueTile = IMAGE_STORE.GetScaledImage("tile_Blue", pixelWidth, pixelHeight);
		Image yellowTile = IMAGE_STORE.GetScaledImage("tile_Yellow", pixelWidth, pixelHeight);
		
		for(int y = 0; y < tilesY; y++)
		{
			for(int x = 0; x < tilesX; x++)
			{
				Vec2 offset = new Vec2(x * tileWidth, y * tileHeight);
				Point pixelPos = toPixelCoordinates(worldPos.plus(offset));
				
				if((x + y) % 2 == 0) {
					g2d.drawImage(blueTile, pixelPos.x, pixelPos.y, null);
				} else {
					g2d.drawImage(yellowTile, pixelPos.x, pixelPos.y, null);
				}
			}
		}
	}
	
	private void AddPhysicsBoard()
	{
		StaticWall columns[] = new StaticWall[tilesX+1];
		StaticPeg pegs[] = new StaticPeg[tilesX+1];
		
		Vec2 startPosTop = new Vec2();
		Vec2 startPosBottom = new Vec2(0.0f, 1f);
		
		for(int i = 0; i < tilesX+1; i++)
		{
			Vec2 offset = new Vec2(i*tileWidth, 0f);
			pegs[i] = new StaticPeg(startPosTop.plus(offset));
			this.addChild(pegs[i]);
			
			columns[i] = new StaticWall(offset.plus(startPosTop), offset.plus(startPosBottom));
			this.addChild(columns[i]);
		}
	}

	private Vec2 velocity;
	
	private float tileWidth, tileHeight;
	private int tilesX, tilesY;
}
