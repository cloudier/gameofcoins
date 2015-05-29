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
		
		gameOver = false;
		
		victoryCondition = 4;
		boardColumn = 7;
		boardRow = 6;
		
		coinProjectile = new CoinProjectile();
		this.addChild(coinProjectile);
		coinProjectile.setActiveVisible(false);
		
		physicsBoard = new PhysicsBoard(7, 6);
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
		
		if(!gameOver)
		{
			coinProjectile.player1 = player1Turn;
			
			if(coinLaunched)
			{
				ticksSinceLastDrop++;
				
				coinProjectile.setActive(true);
				
				Point tile = physicsBoard.getTile(coinProjectile.getWorldPosition());
				
				if(tile != null && physicsBoard.getTopEmptyRow(tile.x) == tile.y) {
					
					physicsBoard.putCoin(tile.x, player1Turn);
					
					if(player1Turn) {
						int result = checkWinner(1);
						if(result == 1) {
							victorious(1);
						}
						else if(result == 0) {
							gameOver = true;
						}
					}
					else
					{
						int result = checkWinner(2);
						if(result == 1) {
							victorious(2);
						}
						else if(result == 0) {
							gameOver = true;
						}
					}
					
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
			}
		}
	}
	
	/**
	 * Creates an alert when a player wins
	 * @param id The ID of the Player
	 */
	private void victorious(int id) {	
		this.removeChild(menu);
//		this.removeChild(reset);
		this.removeChild(back);
		
		gameOver = true;
		
		BoardAlert victory = new BoardAlert(0.5f, 0.3f, new Vec2(0.49f, 0.3f), "Player " + id + " wins the Game!");
		addChild(victory);
		
		this.addChild(menu);
//		this.addChild(reset);
		this.addChild(back);
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
	
	public int checkWinner(int playerID){
		int sum = 0;

		//checks vertical win
		for (int c = 0; c < this.boardColumn; c++) {
			sum = 0;
			
			for (int r = 0; r < this.boardRow; r++) {
				if (getCoin(r,c) == playerID) {
					sum++;

					if(sum == victoryCondition) {
						return 1;
					}
				}
				else {
					sum = 0;
				}
			}			
		}
		
		//checks horizontal win
		for (int r = 0; r < this.boardRow; r++) {
			sum = 0;
			
			for (int c = 0; c < this.boardColumn; c++) {
				if (getCoin(r,c) == playerID) {
					sum++;
					
					if(sum == victoryCondition) {
						return 1;
					}
				}
				else {
					sum = 0;
				}
			}			
		}
		
		//checks diagonal left win
		for (int c = -this.boardRow; c < this.boardColumn; c++) {
			sum = 0;
			
			for (int r = 0; r < this.boardRow; r++) {
				if(getCoin(r, c+r) == playerID){
					sum++;
					
					if(sum == victoryCondition) {
						return 1;
					}
				}
				else{
					sum = 0;
				}
			}			
		}
		
		//checks diagonal right win
		for (int c = 0; c < this.boardColumn + this.boardRow; c++) {
			sum = 0;
			
			for (int r = 0; r < this.boardRow; r++) {
				if(getCoin(r, c-r) == playerID){
					sum++;
					
					if(sum == victoryCondition) {
						return 1;
					}
				}
				else{
					sum = 0;
				}
			}			
		}
		
		if (!physicsBoard.isBoardFull()){
			return 0;
		}

		return -1;
	}
	
	private int getCoin(int row, int column) {
		if(row < 0 || column < 0 || row >= boardRow || column >= boardColumn) return 0;
		return physicsBoard.getCoin(row, column);
	}
	
	@Override
	public void initialiseColumnsRows() {
		
		coinLaunched = false;
		
		this.removeChild(physicsBoard);
		physicsBoard = new PhysicsBoard(7, 6);
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
		
//		reset = new RectButton("restart", "restartSelected", x + 0.6f, 0.85f, 0.2f, 0.1f) {
//			@Override
//			public void onMouseDown() {
//				physicsBoard = new PhysicsBoard(boardColumn, boardRow);
//			}
//		};
		
		back = new RectButton("back", "backSelected", x, 0.85f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				GAME_MANAGER.back();
			}
		};
		
		this.addChild(menu);
//		this.addChild(reset);
		this.addChild(back);
	}
	
	
	private BoardAlert boardAlert;
	
	private boolean gameOver;
	
	private int victoryCondition;
	private int boardColumn;
	private int boardRow;
	
	private CoinCannon coinCannon;
	
	private int ticksSinceLastDrop;
	private boolean player1Turn;
	private boolean humanVsAI;
	private boolean coinLaunched;
	
	private RectButton menu;
//	private RectButton reset;
	private RectButton back;
	
	private PhysicsBoard physicsBoard;
	
	private CoinProjectile coinProjectile;

}
