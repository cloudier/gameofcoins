package gameObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import gameEngine.GameObject;
import gameEngine.UIObject;
import gameEngine.Vec2;

public class ModeMenuBoardSizeNumber extends UIObject{

	private int boardHeight;
	private String stringOfBoardHeight;
	private int boardWidth;
	private String stringOfBoardWidth;

	private float width;
	private float height;
	
	private Font font;
	
	public ModeMenuBoardSizeNumber (int boardWidth, int boardHeight) {
		this.boardWidth = boardWidth;
		this.stringOfBoardWidth = Integer.toString(boardWidth);
		this.boardHeight = boardHeight;
		this.stringOfBoardHeight = Integer.toString(boardHeight);
		width = .2f;
		height = .1f;
		
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
		GameObject gameObjParent = this.getParent().getParent();
		if (gameObjParent instanceof ModeMenu) {
			ModeMenu parent = (ModeMenu) gameObjParent;
			parent.setBoardWidth(boardWidth);
			parent.setBoardHeight(boardHeight);
			parent.checkVictory();
		} else {
			System.err.println("Parent of parent is not instance of ModeMenu");
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

//		BufferedImage currentSprite = getCurrentSprite();
		
		if (checkSelected() || mouseSelected()) {
			g2d.setColor(Color.RED);
			g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
			g2d.setColor(Color.BLACK);
			g2d.setFont(this.font.deriveFont((float) JPANEL.getWidth()/30));
			FontMetrics fm = g2d.getFontMetrics();
			int x = ((fm.stringWidth(stringOfBoardWidth + "x" + stringOfBoardHeight)) / 2);
		    int y = fm.getHeight()/4;
			g2d.drawString(stringOfBoardWidth + "x" + stringOfBoardHeight, pixelX - x, pixelY + y);			
		} else if (!checkSelected()) {
			g2d.setColor(Color.BLUE);
			g2d.fillRect(pixelX - (pixelWidth/2), pixelY - (pixelHeight/2), pixelWidth, pixelHeight);
			g2d.setColor(Color.WHITE);
			g2d.setFont(this.font.deriveFont((float) JPANEL.getWidth()/30));
			FontMetrics fm = g2d.getFontMetrics();
	        int x = ((fm.stringWidth(stringOfBoardWidth + "x" + stringOfBoardHeight)) / 2);
	        int y = fm.getHeight()/4;
			g2d.drawString(stringOfBoardWidth + "x" + stringOfBoardHeight, pixelX - x, pixelY + y);			
		}
	}
	
	private boolean checkSelected() {
		return ((ModeMenu) this.getParent().getParent()).getBoardHeight() == boardHeight
				&& ((ModeMenu) this.getParent().getParent()).getBoardWidth() == boardWidth;
	}

}
