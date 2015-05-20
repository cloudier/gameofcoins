import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JFrame;

import java.util.*;

public class MainGame {

	public final static int TICK_RATE = 1000/60; // 60 times per second
	
	public MainGame() {
		
		//Initialize window
		gamePanel = new JPanel(false);
		mainFrame = new JFrame("Game of Coins");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gamePanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) { MouseUp(); }
			
			@Override
			public void mousePressed(MouseEvent e) { MouseDown(); }
			
			@Override
			public void mouseExited(MouseEvent e) { }
			
			@Override
			public void mouseEntered(MouseEvent e) { }
			
			@Override
			public void mouseClicked(MouseEvent e) { }
		});
		
		timer = new Timer( TICK_RATE, new ActionListener() {
			boolean skipNextDraw = false;
			public void actionPerformed(ActionEvent evt) {
				
				long startTime = System.nanoTime()/1000;
				
				GameTick();
				
				if(!skipNextDraw) { GameRender(); }
				
				long totalTime = (System.nanoTime()/1000 - startTime);
				
				//Skip next render pass if lagging
				if(totalTime/1000 > TICK_RATE) 
				{
					System.out.println("Lagging!");
					skipNextDraw = true;
				} 
				else { skipNextDraw = false; }
				
			}
		});
		
		//Initialize Game objects
		StartGame();
		
		timer.start();
	}
	
	/**
	 * Method to display the main window
	 */
	public void display() {
		
		gamePanel.setPreferredSize(new Dimension(600, 600));
		gamePanel.setIgnoreRepaint(true);
		
		mainFrame.setIgnoreRepaint(true);
		mainFrame.getContentPane().add(gamePanel,BorderLayout.CENTER);
		mainFrame.pack();
        mainFrame.setVisible(true);
        
		mainFrame.createBufferStrategy(2); //Double Buffering
		strategy = mainFrame.getBufferStrategy();
        
	}
	
	
	private void StartGame()
	{
		GameObject.JPANEL = gamePanel;
		GameObject.TICK_RATE = TICK_RATE;
		
		//Initialize game objects
		gameObjectList = new LinkedList<GameObject>();
		
		CircleButton crc = new CircleButton();
		gameObjectList.add(crc);
		
		for(int i = 0; i < 50; i++)
		{
			Puck p = new Puck();
			gameObjectList.add(p);
		}
		
		//60 draws without lag
		for(int y = 0; y < 2; y++)
		{
			for(int x = 0; x < 2; x++)
			{
				BraidGuy bg = new BraidGuy();
				bg.position = new Vec2(x*0.1f, y*0.1f);
				gameObjectList.add(bg);
			}
		}
		
		//Face fa = new Face();
		//gameObjectList.add(fa);
		
	}
	
	private void GameTick()
	{
		for(GameObject obj : gameObjectList)
		{
			obj.Tick();
		}
	}
	
	private void GameRender()
	{
		Graphics2D g2d = (Graphics2D) strategy.getDrawGraphics();
		
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        		RenderingHints.VALUE_ANTIALIAS_ON);
        
        //Enforce only 1 component - the jpanel
        if(mainFrame.getComponentCount() != 1) System.err.println("More than 1 component!");
        
        //Get position of the panel within the jframe
        int panelX = mainFrame.getComponent(0).getX();
        int panelY = mainFrame.getComponent(0).getY();
        
        g2d.translate(panelX, panelY); //Translate the graphics to panel coordinates
        
		int screenWidth = gamePanel.getWidth();
		int screenHeight = gamePanel.getHeight(); 
				
		//Fill screen white
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, screenWidth, screenHeight);
		
		g2d.setColor(Color.black); //Set render color to black
		
		//Draw each game object
		for(GameObject obj : gameObjectList) {
			obj.Draw(g2d);
		}
		
		g2d.dispose();
		
		strategy.show();
	}
	
	private void MouseDown()
	{
		UIObject selected = GetSelectedUIObject();
		if(selected != null) {
			selected.OnMouseDown();
		}
		
	}
	
	private void MouseUp() 
	{
		UIObject selected = GetSelectedUIObject();
		if(selected != null) {
			selected.OnMouseUp();
		}
	}
	
	private UIObject GetSelectedUIObject() {
		UIObject selected = null;
		Point mousePos = gamePanel.getMousePosition();
		
		if(mousePos != null)
		{
			for(GameObject obj : gameObjectList) {
				if(obj instanceof UIObject)	{
					UIObject uiObj = (UIObject) obj;
					
					if(uiObj.MouseSelected()) {
						selected = uiObj;
					}
				}
			}
		}
		return selected;
	}
	
	private LinkedList<GameObject> gameObjectList; //List of all game objects
	
	private Timer timer; //Timer for regulating frame rate
	private BufferStrategy strategy; //BufferStrategy for managing redraw
	
	private JFrame mainFrame;
	private JPanel gamePanel;	
}
