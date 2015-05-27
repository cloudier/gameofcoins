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
			
			Image modeMenu_Angry = ImageIO.read(new File("assets/modeMenu_Angry.png"));
			rawImages.put("modeMenu_Angry", modeMenu_Angry);
			
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
		return GetRawImage(ImageName).getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	private HashMap<String, Image> rawImages;
	private HashMap<ScaledImageRequest, Image> scaledImages;

}
