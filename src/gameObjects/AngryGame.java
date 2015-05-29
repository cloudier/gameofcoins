package gameObjects;

import gameEngine.BoardState;
import gameEngine.Game;
import gameEngine.RectButton;
import gameEngine.Vec2;

import java.awt.Graphics2D;
import java.awt.Point;


public class AngryGame extends Game{

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
		
		player1Turn = true;
	}
	

	@Override
	public boolean mouseSelected() { return true; }

	@Override
	public void onMouseDown() {
		
		if(!coinLaunched)
		{
			//Launch a coin

			float xPosition = getScaledMousePosition().x;
			launchCoin(xPosition);
			
			player1Turn = !player1Turn;
			
		}
	}

	@Override
	public void onMouseUp() { }

	@Override
	protected void onUpdate() { 
		
		if(coinLaunched)
		{
			//If coin has been launched for more than 15sec, do next turn
			
			Point tile = physicsBoard.getTile(coinProjectile.getWorldPosition());
			
			if(tile != null) {
				//Coin landed in a slot
				
				System.out.println("Coin landed m8");
				
				doNextTurn();
			}
			else if(outOfBounds(coinProjectile))
			{
				System.out.println("U missed");
				
				doNextTurn();
			}


		}
	}
	
	private void doNextTurn()
	{
		coinLaunched = false;
		
		if(humanVsAI && !player1Turn)
		{
			//Do ai launch
			launchCoin((float)Math.random());
		}
	}
	
	private boolean outOfBounds(CoinProjectile cp)
	{
		Vec2 cpPos = cp.getWorldPosition();
		float cpRad = cp.circleRadius;
		
		if(cpPos.x + cpRad < 0 || cpPos.y + cpRad < 0 || 
				cpPos.x - cpRad > 1f || cpPos.y - cpRad > 1f)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void launchCoin(float xPosition)
	{
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
		float x = 0.14f;
		
		menu = new RectButton("mainmenu", "mainmenuSelected", x + 0.25f, 0.915f, 0.3f, 0.1f) {
			@Override
			public void onMouseDown() {
				GAME_MANAGER.activateStart();
			}
		};
		
		reset = new RectButton("restart", "restartSelected", x + 0.6f, 0.915f, 0.3f, 0.1f) {
			@Override
			public void onMouseDown() {
				GAME_MANAGER.activateReset();
			}
		};
		
		back = new RectButton("back", "backSelected", x, 0.915f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				GAME_MANAGER.back();
			}
		};
		
		this.addChild(menu);
		this.addChild(reset);
		this.addChild(back);
	}
	
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
