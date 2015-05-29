package gameObjects;

import gameEngine.BoardState;
import gameEngine.Game;
import gameEngine.RectButton;
import gameEngine.Vec2;

import java.awt.Graphics2D;
import java.awt.Point;


public class AngryGame extends Game{

	public static final int DROPDELAY = 300;
	
	/**
	 * Construct a AngryGame object
	 * @param boardState The state of the board
	 */
	public AngryGame(BoardState boardState) {
		this.boardState = boardState;
		
		coinProjectile = new CoinProjectile();
		this.addChild(coinProjectile);
		coinProjectile.setActiveVisible(false);
		
		physicsBoard = new PhysicsBoard(boardState.getBoardColumn(), boardState.getBoardRow());
		this.addChild(physicsBoard);
		
		coinProjectile.setRadius(physicsBoard.getTileWidth()/2.5f);
		
		initialiseColumnsRows();
		
		createButtons();
		
		coinCannon = new CoinCannon();
		coinCannon.position = new Vec2(0.1f, 0.7f);
		this.addChild(coinCannon);
		
		player1Turn = true;
	}
	

	@Override
	public boolean mouseSelected() { return true; }

	@Override
	public void onMouseDown() {
		
		if(!coinLaunched)
		{
			//Launch a coin
			Vec2 launchDir = getScaledMousePosition().minus(coinCannon.getWorldPosition());
			launchCoin(launchDir);
			
		}
	}

	@Override
	public void onMouseUp() { }

	@Override
	protected void onUpdate() { 
		
		coinProjectile.player1 = player1Turn;
		
		if(coinLaunched)
		{
			ticksSinceLastDrop++;
			
			coinProjectile.setActive(true);
			
			Point tile = physicsBoard.getTile(coinProjectile.getWorldPosition());
			
			if(tile != null && physicsBoard.getTopEmptyRow(tile.x) == tile.y) {
				
				physicsBoard.putCoin(tile.x, player1Turn);
				boardState.putCoin(tile.x);
				
				if(boardState.isGameOver())
				{
					
				}
				
				boardState.output();
				
				doNextTurn();
			}
			else if(outOfBounds(coinProjectile))
			{
				doNextTurn();
			}
			else if(ticksSinceLastDrop > DROPDELAY)
			{
				doNextTurn();
			}
		}
		else 
		{
			coinProjectile.setWorldPosition(coinCannon.getCoinHolderWorldPosition());
			coinProjectile.setActive(false);
//			Vec2 mousePos = getScaledMousePosition();
//			if(mousePos != null)
//			{
//				coinProjectile.setActive(false);
//				coinProjectile.setWorldPosition(new Vec2(mousePos.x, 0.1f));
//			}
		}
	}
	
	private void doNextTurn()
	{
		player1Turn = !player1Turn;
		coinLaunched = false;
		
		if(humanVsAI && !player1Turn)
		{
			//Do ai launch
			dropCoin((float)Math.random());
		}
	}
	
	private boolean outOfBounds(CoinProjectile cp)
	{
		Vec2 cpPos = cp.getWorldPosition();
		float cpRad = cp.circleRadius;
		
		if(cpPos.x + cpRad < 0 || 
				cpPos.x - cpRad > 1f || cpPos.y - cpRad > 1f)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void launchCoin(Vec2 direction)
	{
		ticksSinceLastDrop = 0;
		
		coinLaunched = true;
		coinProjectile.setActive(true);
		
		coinProjectile.velocity = direction.normalised().scale(0.48f);
		//coinProjectile.setWorldPosition(position);
	}
	
	private void dropCoin(float xPosition)
	{
		ticksSinceLastDrop = 0;
		
		coinLaunched = true;
		coinProjectile.setActiveVisible(true);
		coinProjectile.velocity = new Vec2();
		
		Vec2 position = new Vec2(xPosition, 0.1f);
		coinProjectile.setWorldPosition(position);
	}

	@Override
	protected void onRender(Graphics2D g2d) {
		
	}
	
	@Override
	public void initialiseColumnsRows() {
		
		coinLaunched = false;
		
		this.removeChild(physicsBoard);
		physicsBoard = new PhysicsBoard(boardState.getBoardColumn(), boardState.getBoardRow());
		this.addChild(physicsBoard);
	}

	@Override
	public void reset() {
		
		initialiseColumnsRows();
	}
	
	/**
	 * Generates reset, main menu and back buttons in the board window.
	 */
	private void createButtons(){
		float x = 0.1f;
		
		menu = new RectButton("mainmenu", "mainmenuSelected", x + 0.25f, 0.85f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				GAME_MANAGER.activateStart();
			}
		};
		
		reset = new RectButton("restart", "restartSelected", x + 0.6f, 0.85f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				GAME_MANAGER.activateReset();
			}
		};
		
		back = new RectButton("back", "backSelected", x, 0.85f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				GAME_MANAGER.back();
			}
		};
		
		this.addChild(menu);
		this.addChild(reset);
		this.addChild(back);
	}
	
	private CoinCannon coinCannon;
	
	private int ticksSinceLastDrop;
	private boolean player1Turn;
	private boolean humanVsAI;
	private boolean coinLaunched;
	
	private RectButton menu;
	private RectButton reset;
	private RectButton back;
	
	private PhysicsBoard physicsBoard;
	
	private CoinProjectile coinProjectile;
	
	private BoardState boardState;

}
