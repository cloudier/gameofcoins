package gameObjects;

import gameEngine.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Face extends GameObject {
	public Vec2 skullPos;
	public Vec2 skullSize;

	public Vec2 LePos;	// Left eye position
	public Vec2 RePos;	// Right eye position
	public Vec2 eyeSize;
	
	public Vec2 LpPos;	// Left pupil position
	public Vec2 RpPos;	// Right pupil position
	public Vec2 pupilSize;
	
	public Vec2 nosePos;
	public Vec2 noseSize;

	public Vec2 mouthPos;
	public Vec2 mouthSize;
	
	@Override
	protected void OnUpdate() {
		Vec2 mouse = GetScaledMousePosition();
		if ( mouse != null ) {
		System.out.println(mouse.x+" "+mouse.y); 
		System.out.println(mouse.normalised().toString());
		}
		int posX = 0;
		int posY = 0;
		// Update skull size
		posX = ((int) JPANEL.getWidth()/4);
		posY = ((int) JPANEL.getHeight()/4);
		skullSize = new Vec2(posX,posY);
		
		// Update skull position
		posX = (int) ( JPANEL.getWidth()/2  );
		posY = (int) ( JPANEL.getHeight()/2 );
		skullPos = new Vec2(posX,posY);
		
		// Update eye size
		posX = (int) skullSize.x/4;
		posY = (int) skullSize.y/4;
		// Update pupil size
		pupilSize = new Vec2(posX/3, posY/3 );
		eyeSize = new Vec2(posX,posY);
		// Update mouth size
		posX = (int)skullSize.x/2;
		posY = (int)skullSize.y/8;
		mouthSize = new Vec2(posX,posY);
		
		
		// Update left eye position
		posX = (int) ( ((int)skullPos.x) - ((int)eyeSize.x)*3/2 );
		posY = (int) ( ((int)skullPos.y) - ((int)skullSize.y)/4 );
		LePos = new Vec2(posX,posY);
		//System.out.println(LePos.x+" "+LePos.y);
		// Update right eye position
		posX = (int) ( ((int)LePos.x) + ((int)skullSize.x)/3 + ((int)eyeSize.x)/2  );
		posY = (int) ( ((int)skullPos.y) - ((int)skullSize.y)/4 );
		RePos = new Vec2(posX,posY);
		//System.out.println(RePos.x+" "+RePos.y);
		
		
		if(mouse != null)
		{
			Vec2 mousePos = new Vec2(mouse.x*JPANEL.getWidth(), mouse.y*JPANEL.getHeight());
			Vec2 dL = mousePos.minus(LePos);
			Vec2 dR = mousePos.minus(RePos);
			float oldX = LpPos.x;
			float oldY = LpPos.y;
			LpPos = new Vec2(oldX-eyeSize.x/2, oldY-eyeSize.y/2);
			oldX = RpPos.x;
			oldY = RpPos.y;
			RpPos = new Vec2(oldX-eyeSize.x/2, oldY-eyeSize.y/2);
			
			LpPos = dL.normalised().scale(pupilSize.x);
			RpPos = dR.normalised().scale(pupilSize.x);
			LpPos = LpPos.plus(LePos);
			RpPos = RpPos.plus(RePos);
		}
		else 
		{
			// Update left pupil position
			posX = (int)LePos.x + ((int)eyeSize.x)/3;
			posY = (int)LePos.y + ((int)eyeSize.y)/3;
			LpPos = new Vec2(posX,posY);
			
			// Update right pupil position
			posX = (int)RePos.x + ((int)eyeSize.x)/3;
			posY = (int)LpPos.y;
			RpPos = new Vec2(posX,posY);
		}
		

		

		// Update nose
		posX = (int)skullPos.x ;
		posY = (int)skullPos.y ;
		nosePos = new Vec2(posX,posY);
		// Update mouth
		posX = (int)skullPos.x - ((int) skullSize.x)/4;
		posY = (int)skullPos.y + ((int) skullSize.y)/5;
		mouthPos = new Vec2(posX,posY);
	}
	
	protected void drawCentreCircle(Graphics2D g2d, int x, int y, int rx, int ry) {
		x = x-(rx/2);
		y = y-(ry/2);
		g2d.drawOval(x, y, rx, ry);
	}
	
	protected void fillCentreCircle(Graphics2D g2d, int x, int y, int rx, int ry) {
		x = x-(rx/2);
		y = y-(ry/2);
		g2d.fillOval(x, y, rx, ry);
	}
	
	@Override
	protected void OnRender(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int posX = 0;
		int posY = 0;
		int sizeX = 0;
		int sizeY = 0;
		
		// Draw the skull
		posX = (int) skullPos.x;
		posY = (int) skullPos.y;
		sizeX = (int) skullSize.x;
		sizeY = (int) skullSize.y;
		g2d.setColor(Color.getHSBColor(255, 253, 203));
		fillCentreCircle(g2d, posX, posY, sizeX, sizeY);
		g2d.setColor(Color.BLACK);
		drawCentreCircle(g2d, posX, posY, sizeX, sizeY);
		
		// Draw left eye + right eye
		g2d.setColor(Color.BLACK);
		posX = (int) LePos.x;
		posY = (int) LePos.y;
		sizeX = (int) eyeSize.x;
		sizeY = (int) eyeSize.y;
		g2d.setColor(Color.BLACK);
		g2d.drawOval(posX, posY, sizeX, sizeY);
		g2d.setColor(Color.WHITE);
		g2d.fillOval(posX, posY, sizeX, sizeY);
		
		posX = (int) RePos.x;
		posY = (int) RePos.y;
		g2d.setColor(Color.BLACK);
		g2d.drawOval(posX, posY, sizeX, sizeY);
		g2d.setColor(Color.WHITE);
		g2d.fillOval(posX, posY, sizeX, sizeY);
		
		// Draw left pupil
		posX = (int) LpPos.x ;
		posY = (int) LpPos.y ;
		sizeX = (int) pupilSize.x;
		sizeY = (int) pupilSize.y;
		g2d.setColor(Color.BLACK);
		g2d.fillOval(posX, posY, sizeX, sizeY);
		// Draw right pupil
		posX = (int) RpPos.x ;
		posY = (int) RpPos.y ;
		g2d.fillOval(posX, posY, sizeX, sizeY);
		
		// Draw the nose
		posX = (int) nosePos.x-(int)eyeSize.x/6;
		posY = (int) nosePos.y-(int)eyeSize.y/6;
		sizeX = (int) eyeSize.x/3;
		sizeY = (int) eyeSize.y/3;
		
		g2d.setColor(Color.BLACK);
		g2d.drawOval(posX, posY, sizeX, sizeY);
		g2d.setColor(Color.getHSBColor(255, 200, 203));
		g2d.fillOval(posX, posY, sizeX, sizeY);
		
		// Draw the mouth		
		posX = (int) mouthPos.x;
		posY = (int) mouthPos.y;
		sizeX = (int) mouthSize.x;
		sizeY = (int) mouthSize.y;
		
		g2d.setColor(Color.BLACK);
		g2d.drawOval(posX, posY, sizeX, sizeY);
		g2d.setColor(Color.getHSBColor(351, 60, 76));
		g2d.fillOval(posX, posY, sizeX, sizeY);

	}
	
}
