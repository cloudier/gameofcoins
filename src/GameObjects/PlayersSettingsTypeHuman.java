package gameObjects;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import gameEngine.*;

public class PlayersSettingsTypeHuman extends Button {
	
	public PlayersSettingsTypeHuman(float width, float height, Vec2 position, String string) {
		super(width, height, position, string);
	}

	@Override
	public void onMouseDown() {
		((PlayersSettingsType) this.getParent()).setType(string);
	}

	@Override
	public void onRender(Graphics2D g2d) {
		Vec2 worldPos = getWorldPosition();

		int pixelX = (int) (worldPos.x * JPANEL.getWidth());
		int pixelY = (int) (worldPos.y * JPANEL.getHeight());

		int pixelWidth = (int) (width * JPANEL.getWidth());
		int pixelHeight = (int) (height * JPANEL.getHeight());

		if (mouseSelected() || ((PlayersSettingsType) this.getParent()).getType() == this.string) {
			g2d.setColor(Color.RED);
			g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
			g2d.setColor(Color.BLACK);
			g2d.setFont(this.font.deriveFont((float) JPANEL.getWidth()/20));
			FontMetrics fm = g2d.getFontMetrics();
	        int x = ((fm.stringWidth(string)) / 2);
	        int y = fm.getHeight()/4;
			g2d.drawString(string, pixelX - x, pixelY + y);			
		} else {
			g2d.setColor(Color.BLUE);
			g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
			g2d.setColor(Color.WHITE);
			g2d.setFont(this.font.deriveFont((float) JPANEL.getWidth()/20));
			FontMetrics fm = g2d.getFontMetrics();
	        int x = ((fm.stringWidth(string)) / 2);
	        int y = fm.getHeight()/4;
			g2d.drawString(string, pixelX - x, pixelY + y);
		}
	}
}
