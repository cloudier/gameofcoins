import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The BraidGuy GameObject is intended to test image
 * animation in Java. 
 * 
 * @author ivormetcalf
 */

public class BraidGuy extends GameObject {

	public BraidGuy() {
		
//		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
//	    GraphicsDevice device = env.getDefaultScreenDevice();
//	    GraphicsConfiguration config = device.getDefaultConfiguration();
//	    spriteSheet = config.createCompatibleImage(1024, 1024, Transparency.TRANSLUCENT);
		
		try {
			spriteSheet = ImageIO.read(new File("braidsheet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void OnUpdate() {
		
		
	}

	@Override
	protected void OnRender(Graphics2D g2d) {
		
		g2d.drawImage(spriteSheet, null, 0, 0);
		//g2d.drawImage
	}
	
	BufferedImage spriteSheet;

}
