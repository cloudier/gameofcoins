package gameObjects;

import java.awt.Graphics2D;

import gameEngine.*;

public class PlayersSettingsType extends UIObject {
	private PlayersSettingsTypeHuman human;
	private PlayersSettingsTypeAI ai;
	
	public PlayersSettingsType() {
		human = new PlayersSettingsTypeHuman(0.3f, 0.1f, new Vec2(0.1f, 0), "Human");
		addChild(human);
		ai = new PlayersSettingsTypeAI(0.2f, 0.1f, new Vec2(0.35f, 0), "AI");
		addChild(ai);
	}

	public String getType() {
		if (((PlayersSettings) this.getParent()).getPlayer().getPlayerType() == PlayerType.HUMAN) {
			return "Human";
		} else {
			return "AI";
		}
	}
	public void setType(String s) {
		if (s.equals("Human")) {
			((PlayersSettings) this.getParent()).getPlayer().setPlayerType(PlayerType.HUMAN);
		} else if (s.equals("AI")) {
			((PlayersSettings) this.getParent()).getPlayer().setPlayerType(PlayerType.AI);
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

	@Override
	protected void onUpdate() {
	}

	@Override
	protected void onRender(Graphics2D g2d) {
	}

}
