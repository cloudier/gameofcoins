package gameEngine;

import java.util.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * ImageStore class is a database of images. There should only be one instance
 * of this class. Users request scaled images from this class.
 * 
 * @author ivormetcalf
 */

public final class ImageStore {

	public ImageStore() {
		rawImages = new HashMap<String, Image>();
		scaledImages = new HashMap<ScaledImageRequest, Image>();

		try {
			Image background = ImageIO.read(new File("assets/background.jpg"));
			rawImages.put("background", background);

			// Game Board Images
			AddFile("coin_Red", "Board");
			AddFile("coin_Yellow", "Board");
			AddFile("tile_Blue", "Board");
			AddFile("tile_Yellow", "Board");

			// Main Menu Images
			AddFile("mainTitle", "MainMenu");
			AddButtonFiles("newGame", "MainMenu");

			// Add Global Images
			AddButtonFiles("back", "Global");
			AddButtonFiles("confirm", "Global");

			// Mode Menu Images
			AddButtonFiles("normal", "ModeMenu");
			AddButtonFiles("angry", "ModeMenu");

			AddButtonFiles("btn7by6", "ModeMenu");
			AddButtonFiles("btn14by12", "ModeMenu");
			AddButtonFiles("btn21by18", "ModeMenu");

			// Add PlayerMenu buttons
			AddButtonFiles("ai", "PlayerMenu");
			AddButtonFiles("easy", "PlayerMenu");
			AddButtonFiles("medium", "PlayerMenu");
			AddButtonFiles("hard", "PlayerMenu");
			AddButtonFiles("human", "PlayerMenu");
			AddButtonFiles("join", "PlayerMenu");

			// Add Board images
			AddButtonFiles("mainmenu", "Board");
			AddButtonFiles("restart", "Board");
			AddFile("undo", "Board");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Resizes the current image
	 */
	public void OnResize() {
		for (Image img : scaledImages.values()) {
			img.flush();
		}
	}

	/**
	 * Returns the image scaled to the given width and height
	 * @param name
	 * @param width
	 * @param height
	 * @return the scaled image
	 */
	public Image GetScaledImage(String name, int width, int height) {
		ScaledImageRequest req = new ScaledImageRequest(name, width, height);
		Image retVal = scaledImages.get(req);

		if (retVal == null) {
			retVal = createScaledImage(name, width, height);
			scaledImages.put(req, retVal);
		}

		return retVal;
	}

	/**
	 * Returns the raw image that matches the given name
	 * @param name
	 * @return raw image
	 */
	public Image GetRawImage(String name) {
		return rawImages.get(name);
	}

	/**
	 * Adds a file to the store located at the given fileName
	 * @param fileName
	 */
	private void AddFile(String fileName) {
		AddFile(fileName, "");
	}

	/**
	 * Adds a file to the store located at the given file name
	 * under the given subdirectory
	 * @param fileName
	 * @param subDirectory
	 */
	private void AddFile(String fileName, String subDirectory) {
		String assetsDirectory = "assets/" + subDirectory + "/";
		String extension = ".png";

		String fileDir = assetsDirectory + fileName + extension;

		Image file = null;

		try {
			file = ImageIO.read(new File(fileDir));
		} catch (IOException e) {
			e.printStackTrace();
		}

		rawImages.put(fileName, file);
	}

	/**
	 * Sets an image to a button
	 * @param buttonFileName
	 * @param subDirectory
	 */
	private void AddButtonFiles(String buttonFileName, String subDirectory) {
		String assetsDirectory = "assets/" + subDirectory + "/";
		String extension = ".png";
		String selectedFileName = buttonFileName + "Selected";

		String buttonDir = assetsDirectory + buttonFileName + extension;
		String selectedDir = assetsDirectory + selectedFileName + extension;

		Image button = null;
		Image selected = null;

		try {
			button = ImageIO.read(new File(buttonDir));
			selected = ImageIO.read(new File(selectedDir));
		} catch (IOException e) {
			e.printStackTrace();
		}

		rawImages.put(buttonFileName, button);
		rawImages.put(selectedFileName, selected);
	}

	/**
	 * Supports scaling of images
	 * @author ivormetcalf
	 *
	 */
	class ScaledImageRequest {

		public ScaledImageRequest(String ImageName, int width, int height) {
			imageName = ImageName;
			imageWidth = width;
			imageHeight = height;
		}

		@Override
		public boolean equals(Object other) {
			if (other instanceof ScaledImageRequest) {
				ScaledImageRequest req = (ScaledImageRequest) other;
				return this.imageName.equals(req.imageName)
						&& this.imageWidth == req.imageWidth
						&& this.imageHeight == req.imageHeight;
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			return this.imageName.hashCode() + this.imageHeight
					+ this.imageWidth;
		}

		public String imageName;
		public int imageWidth;
		public int imageHeight;
	}

	/**
	 * Scales the given image to the given width and height
	 * @param ImageName
	 * @param width
	 * @param height
	 * @return the scaled image
	 */
	private Image createScaledImage(String ImageName, int width, int height) {
		Image raw = GetRawImage(ImageName);
		if (raw == null)
			System.err.println("Invalid Image Name: " + ImageName);
		return raw.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	private HashMap<String, Image> rawImages;
	private HashMap<ScaledImageRequest, Image> scaledImages;

}
