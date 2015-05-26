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
	private HashMap<Integer, Player> players;
	private PlayersSettings one;
	private PlayersSettings two;
	private PlayersSettings three;
	private PlayersSettings four;
	private PlayersMenuNext playersMenuNext;
	private Font font;
	
	public PlayersMenu() {
		this.position = new Vec2(0.5f, 0.15f);
		this.numPlayers = 2;
		
		players = new HashMap<Integer, Player>(5);
		players.put(1, new Player("Player 1", 1, Color.RED, PlayerType.HUMAN));
		players.put(2, new Player("Player 2", 2, Color.YELLOW, PlayerType.HUMAN));
		players.put(3, null);
		players.put(4, null);
		
		one = new PlayersSettings(1);
		one.position = new Vec2(-0.3f, 0.15f);
		one.setPlayer(players.get(1));
		this.addChild(one);
		two = new PlayersSettings(2);
		two.position = new Vec2(-0.3f, 0.3f);
		two.setPlayer(players.get(2));
		this.addChild(two);
		three = new PlayersSettings(3);
		three.position = new Vec2(-0.3f, 0.45f);
		three.setPlayer(null);
		this.addChild(three);
		four = new PlayersSettings(4);
		four.position = new Vec2(-0.3f, 0.6f);
		this.addChild(four);
		four.setPlayer(null);
		
		this.playersMenuNext = new PlayersMenuNext();
		this.addChild(playersMenuNext);
		
		try {
			this.font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("assets/fonts/Raleway-Regular.ttf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void activateBoard() {
		GAME_MANAGER.activateBoard(numPlayers, players);
	}
	
	public Player makePlayer(int id){
		this.numPlayers++;
		Color c = null;
		if (id == 3) {
			c = Color.GREEN;
		} else if (id == 4) {
			c = Color.MAGENTA;
		}
		Player p = new Player("Player " + id, id, c, PlayerType.HUMAN);
		players.put(id, p);
		return p;
	}
	
	public void deletePlayer(int id) {
		this.numPlayers--;
		players.put(id, null);
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

	@Override
	protected void onUpdate() {
	}

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

}
