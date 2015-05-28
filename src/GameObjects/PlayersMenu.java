package gameObjects;

import gameEngine.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class PlayersMenu extends UIObject {
	private int numPlayers;
	private int playerTypes;
	// 0 -> human v human (default)
	// 1 -> human v ai
	// 2 -> ai v ai
	
	private RectButton back;
	private RectButton confirm;
	private RectButton humanhuman;
	private RectButton humanai;
	private RectButton aiai;
	
	//
	
	public PlayersMenu() {
		this.position = new Vec2(0.5f, 0.15f);
		this.numPlayers = 2;
		this.playerTypes = 0;
		
		createButtons();
		
		try {
			UIObject.font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("assets/fonts/Raleway-Regular.ttf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPlayerTypes(int playerTypes) {
		this.playerTypes = playerTypes;
	}
	
	public void activateBoard() {
		HashMap<Integer, Player> players = new HashMap<Integer, Player>();
		GAME_MANAGER.activateBoard(numPlayers, players);
	}

	@Override
	protected void onUpdate() {	}
	
	@Override
	protected void onRender(Graphics2D g2d) {
		Vec2 worldPos = getWorldPosition();
		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());

		g2d.setColor(Color.BLACK);
		// replace this with an image
		Font headerFont = this.font.deriveFont((float) JPANEL.getWidth()/10);
		g2d.setFont(headerFont);
		FontMetrics fm = g2d.getFontMetrics();
        int x = ((fm.stringWidth("Player Settings")) / 2);
		g2d.drawString("Player Settings", pixelX - x, pixelY);
	}
	
	class PlayerSetting extends GameObject
	{
		public RectButton joinButton;
		public RectButton humanButton;
		public RectButton aiButton;
		
//		public PlayerSetting() {
//			joinButton = new RectButton() {
//				
//				@Override
//				public void onMouseDown() {
//					
//				}
//			};
//		}
		
		@Override
		protected void onUpdate() {	}

		@Override 
		protected void onRender(Graphics2D g2d) { }
	}

	private void createButtons()
	{
		back = new RectButton("back", "backSelected", -0.4f, 0.7f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				GAME_MANAGER.back();
			}
		};
		
		confirm = new RectButton("confirm", "confirmSelected", -0.1f, 0.7f, 0.2f, 0.1f) {
			@Override
			public void onMouseDown() {
				activateBoard();
			}
		};
		
		humanhuman = new RectButton("back", "back", -0.3f, 0.15f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				
			}
		}; // need to change asset and dimension
		
		one.position = new Vec2(-0.3f, 0.15f);
		two.position = new Vec2(-0.3f, 0.3f);
		three.position = new Vec2(-0.3f, 0.45f);
		four.position = new Vec2(-0.3f, 0.6f);
		

		
		this.addChild(back);
		this.addChild(confirm);
	}

	@Override
	public boolean mouseSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onMouseDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseUp() {
		// TODO Auto-generated method stub
		
	}
	
}
