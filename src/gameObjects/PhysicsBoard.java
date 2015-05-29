package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import gameEngine.GameObject;
import gameEngine.Vec2;

public class PhysicsBoard extends GameObject {

	public PhysicsBoard(int tilesX, int tilesY) {
		
		this.tilesX = tilesX;
		this.tilesY = tilesY;
		
		float boardHeight = 0.5f;
		
		totalBoardSize = new Vec2(boardHeight*tilesX/tilesY, boardHeight);
		
		position = new Vec2((1f-totalBoardSize.x)/2, 1f - totalBoardSize.y - 0.15f);
		velocity = new Vec2(0f, 0f);
		
		AddPhysicsBoard();
	}

	
	public Vec2 getTotalBoardSize() { return totalBoardSize; }
	public float getTileWidth() { return totalBoardSize.x/tilesX; }
	public float getTileHeight() { return totalBoardSize.y/tilesY; }
	
	public void putCoin(int column)
	{
		//Implement this
		//If a column is full, raise the floor to the top
	}
	
	/**
	 * Returns the tile of which the specified position locates
	 */
	public Point getTile(Vec2 position)
	{
		Vec2 relPos = position.minus(getWorldPosition()); 
		
		int r, c;
		r = (int) Math.floor(relPos.x / getTileWidth());
		c = (int) Math.floor(relPos.y / getTileHeight());
		
		if(r >= 0 && r < tilesX && c >= 0 && c < tilesY) {
			return new Point(r, c);
		} else {
			return null;
		}
	}
	
	@Override
	protected void onUpdate() {
		position = position.plus(velocity);
		if(position.x + totalBoardSize.x > 1f || position.x < 0f) {
			velocity = velocity.flip();
		}
	}

	@Override
	protected void onRender(Graphics2D g2d) {
		Vec2 worldPos = getWorldPosition();
		
		float tileWidth = getTileWidth();
		float tileHeight = getTileHeight();
		
		int pixelWidth = (int) (tileWidth * JPANEL.getWidth());
		int pixelHeight = (int) (tileHeight * JPANEL.getHeight());

		Image blueTile = IMAGE_STORE.GetScaledImage("tile_Blue", pixelWidth, pixelHeight);
		Image yellowTile = IMAGE_STORE.GetScaledImage("tile_Yellow", pixelWidth, pixelHeight);
		
		for(int y = 0; y < tilesY; y++){
			for(int x = 0; x < tilesX; x++){
				Vec2 offset = new Vec2(x * tileWidth, y * tileHeight);
				Point pixelPos = toPixelCoordinates(worldPos.plus(offset));
				
				if((x + y) % 2 == 0) {
					g2d.drawImage(blueTile, pixelPos.x, pixelPos.y, null);
				} 
				else {
					g2d.drawImage(yellowTile, pixelPos.x, pixelPos.y, null);
				}
			}
		}

	}
	
	private void AddPhysicsBoard(){
		float tileWidth = getTileWidth();
		
		columnFloors = new StaticWall[tilesX];
		columns = new StaticWall[tilesX+1];
		pegs = new StaticPeg[tilesX+1];
		
		Vec2 startPosTop = new Vec2();
		Vec2 startPosBottom = new Vec2(0.0f, totalBoardSize.y);
		
		for(int i = 0; i < tilesX+1; i++){
			Vec2 offset = new Vec2(i*tileWidth, 0f);
//			pegs[i] = new StaticPeg(startPosTop.plus(offset));
//			this.addChild(pegs[i]);
			
			columns[i] = new StaticWall(offset.plus(startPosTop), offset.plus(startPosBottom));
			this.addChild(columns[i]);
		}
		
		for(int i = 0; i < tilesX; i++)
		{
			Vec2 offset = new Vec2(i*tileWidth, 0f);
			Vec2 offset2 = offset.plus(new Vec2(tileWidth, 0f));
			columnFloors[i] = new StaticWall(startPosBottom.plus(offset), startPosBottom.plus(offset2));
			this.addChild(columnFloors[i]);
		}
		
	}
	
	private StaticWall columnFloors[];
	private StaticWall columns[];
	private StaticPeg pegs[];
	
	private Coin[][] coins;
	
	private Vec2 velocity;
	
	private Vec2 totalBoardSize;
	private int tilesX, tilesY;

}
