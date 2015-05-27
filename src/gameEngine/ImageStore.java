package gameEngine;

import java.util.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * ImageStore class is a database of images.
 * There should only be one instance of this class.
 * Users request scaled images from this class.
 * @author ivormetcalf
 */

public final class ImageStore {

	public ImageStore() 
	{
		rawImages = new HashMap<String, Image>();
		scaledImages = new HashMap<ScaledImageRequest, Image>();
		
		try 
		{
			Image background = ImageIO.read(new File("assets/background.jpg"));
			rawImages.put("background", background);
			
			Image boardImage = ImageIO.read(new File("assets/board.png"));
			rawImages.put("board", boardImage);
			
			Image redCoinImage = ImageIO.read(new File("assets/coin_Red.png"));
			rawImages.put("coin_Red", redCoinImage);
			
			Image yellowCoinImage = ImageIO.read(new File("assets/coin_Yellow.png"));
			rawImages.put("coin_Yellow", yellowCoinImage);
			
			Image blueTileImage = ImageIO.read(new File("assets/tile_Blue.png"));
			rawImages.put("tile_Blue", blueTileImage);
			
			Image yellowTileImage = ImageIO.read(new File("assets/tile_Yellow.png"));
			rawImages.put("tile_Yellow", yellowTileImage);
			
			
			//Main Menu Images
			Image newGame = ImageIO.read(new File("assets/MainMenu/newGame.png"));
			rawImages.put("newGame", newGame);
			
			Image newGameHover = ImageIO.read(new File("assets/MainMenu/newGameHover.png"));
			rawImages.put("newGameHover", newGameHover);
			
			
			//Mode Menu Images
			Image normalUnselected = ImageIO.read(new File("assets/ModeMenu/normalUnselected.png"));
			rawImages.put("normalUnselected", normalUnselected);
			
			Image normalSelected = ImageIO.read(new File("assets/ModeMenu/normalSelected.png"));
			rawImages.put("normalSelected", normalSelected);
			
			Image angryUnselected = ImageIO.read(new File("assets/ModeMenu/angryUnselected.png"));
			rawImages.put("angryUnselected", angryUnselected);
			
			Image angrySelected = ImageIO.read(new File("assets/ModeMenu/angrySelected.png"));
			rawImages.put("angrySelected", angrySelected);
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void OnResize() 
	{
		for(Image img : scaledImages.values()) {
			img.flush();
		}
	}
	
	public Image GetScaledImage(String name, int width, int height) {
		ScaledImageRequest req = new ScaledImageRequest(name, width, height);
		Image retVal = scaledImages.get(req);
		
		if(retVal == null) {
			retVal = createScaledImage(name, width, height);
			scaledImages.put(req, retVal);
		}
		
		return retVal;
	}
	
	public Image GetRawImage(String name) {
		return rawImages.get(name);
	}
	
	class ScaledImageRequest {
		
		public ScaledImageRequest(String ImageName, int width, int height) {
			imageName = ImageName;
			imageWidth = width;
			imageHeight = height;
		}
		
		@Override 
		public boolean equals(Object other) {
			if(other instanceof ScaledImageRequest) {
				ScaledImageRequest req = (ScaledImageRequest) other;
				return this.imageName.equals(req.imageName) && 
						this.imageWidth == req.imageWidth &&
						this.imageHeight == req.imageHeight;
			} else { 
				return false;
			}
		}
		
		@Override
		public int hashCode() {
			return this.imageName.hashCode() + this.imageHeight + this.imageWidth;
		}
		
		public String imageName;
		public int imageWidth;
		public int imageHeight;
	}
	
	private Image createScaledImage(String ImageName, int width, int height) {
		Image raw = GetRawImage(ImageName);
		if(raw == null) System.err.println("Invalid Image Name");
		return raw.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	private HashMap<String, Image> rawImages;
	private HashMap<ScaledImageRequest, Image> scaledImages;

}
