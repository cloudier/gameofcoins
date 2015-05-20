import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.RenderingHints;

/**
 * Face game object
 * @author Max Huynh
 */

public class Face extends GameObject {
	public int Xskull;
	public int Yskull;
	public int Xeye;
	public int Yeye;
	public int mouth;
	public double pupilX;
	public double pupilY;
	
	
	@Override
	protected void OnUpdate() {
		int LeX = (int) ( JPANEL.getWidth()/2 - 1.75*Xeye );
		int LeY = (int) ( JPANEL.getHeight()/2 - Yeye );
		
		int ReX = (int) ( JPANEL.getWidth()/2 + Xeye  );
		int ReY = (int) ( JPANEL.getHeight()/2 - Yeye  );
		
		double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
		double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
		
		double ratioX = mouseX/JPANEL.getWidth();
		double ratioY = mouseY/JPANEL.getWidth();
		
		
	}
	
	@Override
	protected void OnRender(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Xskull = ((int) JPANEL.getWidth()/4);
		Yskull = ((int) JPANEL.getHeight()/4);
		Xeye = Xskull/4;
		Yeye = Yskull/4;
		mouth = Xskull/2;
		
		
		int skullX = (int) ( JPANEL.getWidth()/2 - Xskull/2);
		int skullY = (int) ( JPANEL.getHeight()/2 - Yskull/2);
		
		g2d.setColor(Color.getHSBColor(255, 253, 203));
		g2d.fillOval(skullX, skullY, Xskull, Yskull);
		g2d.setColor(Color.BLACK);
		g2d.drawOval(skullX, skullY, Xskull, Yskull);
		
		// Draw left eye + right eye
		int LeX = (int) ( skullX + Xeye/2 );
		int ReX = (int) ( LeX + Xskull/3 + Xeye*2/3 );
		int eY = (int) ( skullY + Yskull/4 );
	
		g2d.setColor(Color.BLACK);
		g2d.drawOval(LeX, eY, Xeye, Yeye);
		g2d.drawOval(ReX, eY, Xeye, Yeye);
		g2d.setColor(Color.WHITE);
		g2d.fillOval(LeX, eY, Xeye, Yeye);
		g2d.fillOval(ReX, eY, Xeye, Yeye);
		
		// Draw the mouth
		g2d.setColor(Color.getHSBColor(351, 60, 76));
		int mX = skullX+Xskull/4;
		int mY = skullY+Yskull*3/4;
		g2d.fillOval(mX, mY, mouth, Yskull/8);
		g2d.setColor(Color.BLACK);
		g2d.drawOval(mX, mY, mouth, Yskull/8);
		
		// Draw the nose
		g2d.setColor(Color.BLACK);
		int endX = skullX+Xskull/2;
		int endY = skullY+Yskull/2;
		g2d.drawOval(endX-Xeye/6, endY, Xeye/3, Yeye/3);
		g2d.setColor(Color.getHSBColor(255, 200, 203));
		g2d.fillOval(endX-Xeye/6, endY, Xeye/3, Yeye/3);
		
		g2d.setColor(Color.BLACK);
		// Draw left pupil
		int pY = eY + Yeye/3;
		g2d.fillOval(LeX+Xeye/3, pY, Xeye/3, Yeye/3);
		// Draw right pupil
		g2d.fillOval(ReX+Xeye/3, pY, Xeye/3, Yeye/3);
	}
	
}
