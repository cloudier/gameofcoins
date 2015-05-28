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
	private int ai1;
	private int ai2;
	// 0 -> easy
	// 1 -> medium
	// 2 -> hard
	
	private RectButton back;
	private RectButton confirm;
	
	private RectButton humanhuman;
	private RectButton humanai;
	private RectButton aiai;

	private RectButton ai1Easy;
	private RectButton ai1Medium;
	private RectButton ai1Hard;
	private RectButton ai2Easy;
	private RectButton ai2Medium;
	private RectButton ai2Hard;
	
	public PlayersMenu() {
		this.position = new Vec2(0.5f, 0.15f);
		this.numPlayers = 2;
		this.playerTypes = 0;
		this.ai1 = 0;
		this.ai2 = 0;
		
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
		if (playerTypes == 0) {
			players.put (1, new Player("Player 1", 1, Color.RED, PlayerType.HUMAN));
			players.put (2, new Player("Player 2", 2, Color.YELLOW, PlayerType.HUMAN));
		} else if (playerTypes == 1) {
			players.put (1, new Player("Player 1", 1, Color.RED, PlayerType.HUMAN));
			Player ai = new Player("Player 2", 2, Color.YELLOW, PlayerType.AI);
			ai.setDifficulty(ai1);
			players.put (2, ai);
		} else if (playerTypes == 2) {
			Player p1 = new Player("Player 1", 1, Color.RED, PlayerType.AI);
			p1.setDifficulty(ai1);
			Player p2 = new Player("Player 2", 2, Color.YELLOW, PlayerType.AI);
			p2.setDifficulty(ai2);
			players.put (1, p1);
			players.put (2, p2);
		}
		
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
		Font headerFont = UIObject.font.deriveFont((float) JPANEL.getWidth()/10);
		g2d.setFont(headerFont);
		FontMetrics fm = g2d.getFontMetrics();
        int x = ((fm.stringWidth("Player Settings")) / 2);
		g2d.drawString("Player Settings", pixelX - x, pixelY);
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
		
		// need to change assets and dimensions
		humanhuman = new RectButton("back", "backSelected", -0.3f, 0.15f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				setPlayerTypes(0);
				PlayersMenu.this.setActiveVisible(false, 1);
				PlayersMenu.this.setActiveVisible(false, 2);
			}
			public boolean modeSelected() {
				return playerTypes == 0;
			}
		}; 
		
		humanai = new RectButton("back", "backSelected", -0.3f, 0.3f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				setPlayerTypes(1);
				PlayersMenu.this.setActiveVisible(true, 1);
				PlayersMenu.this.setActiveVisible(false, 2);
			}
			public boolean modeSelected() {
				return playerTypes == 1;
			}
		};
		
		aiai = new RectButton("back", "backSelected", -0.3f, 0.45f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				setPlayerTypes(2);
				PlayersMenu.this.setActiveVisible(true, 1);
				PlayersMenu.this.setActiveVisible(true, 2);
			}
			public boolean modeSelected() {
				return playerTypes == 2;
			}
		};
		
		ai1Easy = new RectButton("back", "backSelected", 0f, 0.15f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				setAIDifficulty(1, 0);
			}
			public boolean modeSelected() {
				return ai1 == 0;
			}
		};
		
		ai1Medium = new RectButton("back", "backSelected", 0.1f, 0.15f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				setAIDifficulty(1, 1);
			}
			public boolean modeSelected() {
				return ai1 == 1;
			}
		};
		
		ai1Hard = new RectButton("back", "backSelected", 0.2f, 0.15f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				setAIDifficulty(1, 2);
			}
			public boolean modeSelected() {
				return ai1 == 2;
			}
		};
		
		ai2Easy = new RectButton("back", "backSelected", 0f, 0.3f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				setAIDifficulty(2, 0);
			}
			public boolean modeSelected() {
				return ai2 == 0;
			}
		};
		
		ai2Medium = new RectButton("back", "backSelected", 0.1f, 0.3f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				setAIDifficulty(2, 1);
			}
			public boolean modeSelected() {
				return ai2 == 1;
			}
		};
		
		ai2Hard = new RectButton("back", "backSelected", 0.2f, 0.3f, 0.1f, 0.1f) {
			@Override
			public void onMouseDown() {
				setAIDifficulty(2, 2);
			}
			public boolean modeSelected() {
				return ai2 == 2;
			}
		};
		
		
		this.addChild(back);
		this.addChild(confirm);
		this.addChild(humanhuman);
		this.addChild(humanai);
		this.addChild(aiai);
		// why doesn't this work
		setActiveVisible(false, 1);
		this.addChild(ai1Easy);
		this.addChild(ai1Medium);
		this.addChild(ai1Hard);
		setActiveVisible(false, 2);
		this.addChild(ai2Easy);
		this.addChild(ai2Medium);
		this.addChild(ai2Hard);
	}
	
	public void setActiveVisible(boolean visibility, int ai) {
		if (ai == 1) {
			ai1Easy.setActiveVisible(visibility);
			ai1Medium.setActiveVisible(visibility);
			ai1Hard.setActiveVisible(visibility);
		} else if (ai == 2) {
			ai2Easy.setActiveVisible(visibility);
			ai2Medium.setActiveVisible(visibility);
			ai2Hard.setActiveVisible(visibility);
		}
	}

	protected void setAIDifficulty(int ai, int difficulty) {
		if (ai == 1) {
			ai1 = difficulty;
		} if (ai == 2) {
			ai2 = difficulty;
		}
	}

	@Override
	public boolean mouseSelected() {
		return false;
	}

	@Override
	public void onMouseDown() {
	}

	@Override
	public void onMouseUp() {
	}
	
}
