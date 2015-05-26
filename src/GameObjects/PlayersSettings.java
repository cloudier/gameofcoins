package gameObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import gameEngine.UIObject;
import gameEngine.Vec2;

public class PlayersSettings extends UIObject {
	
	private int id;
	private float width;
	private float height;
	private Font font;
	private Player player;
	private PlayersSettingsType type;

	public PlayersSettings(int id) {
		this.id = id;
		this.width = 0.3f;
		this.height = 0.1f;

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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		
		if (player != null) {
			this.type = new PlayersSettingsType();
			type.position = new Vec2(0.3f, 0f);
			addChild(type);
		}
		// make players setting color
	}
	
	@Override
	public boolean mouseSelected() {
		Vec2 mousePos = getScaledMousePosition();
		if (mousePos == null)
			return false;

		Vec2 worldPosition = getWorldPosition();

		if ((mousePos.x >= worldPosition.x - width/2 &&
				mousePos.y >= worldPosition.y - height/2) &&
				(mousePos.x <= worldPosition.x + width/2) &&
				(mousePos.y <= worldPosition.y + height/2)) {
			return true;
		}
		return false;
	}

	@Override
	public void onMouseDown() {
		if (player == null) {
			this.player = ((PlayersMenu) this.getParent()).makePlayer(id);
			// make PlayersSettingsType
			this.type = new PlayersSettingsType();
			type.position = new Vec2(0.24f, 0f);
			addChild(type);
			// make PlayersSettingsColor
		} else if (player != null && id >= 3) {
			this.player = null;
		}
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

		int pixelWidth = (int) (width * JPANEL.getWidth());
		int pixelHeight = (int) (height * JPANEL.getHeight());

		if (player != null) {
			if (mouseSelected() && id >= 3) {
				g2d.setColor(Color.RED);
				g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
				g2d.setColor(Color.BLACK);
				g2d.setFont(this.font.deriveFont((float) JPANEL.getWidth()/20));
				FontMetrics fm = g2d.getFontMetrics();
		        int x = ((fm.stringWidth("Delete?")) / 2);
		        int y = fm.getHeight()/4;
				g2d.drawString("Delete?", pixelX - x, pixelY + y);
			} else {
				g2d.setColor(player.getColor());
				g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
				g2d.setColor(Color.BLACK);
				g2d.setFont(this.font.deriveFont((float) JPANEL.getWidth()/20));
				FontMetrics fm = g2d.getFontMetrics();
		        int x = ((fm.stringWidth(player.getName())) / 2);
		        int y = fm.getHeight()/4;
				g2d.drawString(player.getName(), pixelX - x, pixelY + y);
			}
		} else if (player == null) {
			if (mouseSelected()) {
				g2d.setColor(Color.RED);
				g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
				g2d.setColor(Color.BLACK);
				g2d.setFont(this.font.deriveFont((float) JPANEL.getWidth()/20));
				FontMetrics fm = g2d.getFontMetrics();
		        int x = ((fm.stringWidth("Add player")) / 2);
		        int y = fm.getHeight()/4;
				g2d.drawString("Add player", pixelX - x, pixelY + y);			
			} else {
				g2d.setColor(Color.BLUE);
				g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
				g2d.setColor(Color.WHITE);
				g2d.setFont(this.font.deriveFont((float) JPANEL.getWidth()/20));
				FontMetrics fm = g2d.getFontMetrics();
		        int y = fm.getHeight()/4;
		        if (player != null) {
			        int x = ((fm.stringWidth(player.getName())) / 2);
					g2d.drawString(player.getName(), pixelX - x, pixelY + y);			
		        } else {
			        int x = ((fm.stringWidth("Add player")) / 2);
					g2d.drawString("Add player", pixelX - x, pixelY + y);			
		        }
			}
		}
	}

}
