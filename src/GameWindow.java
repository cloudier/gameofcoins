import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.Timer;
import javax.swing.JFrame;

import java.util.*;

public class GameWindow {

	public final static int TICK_RATE = 1000/60; // 60 times per second
	
	public GameWindow() {
		
		//Initialize window
		gamePanel = new GamePanel();
		mainFrame = new JFrame("Game of Coins");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		timer.start();
		
		//Initialize Game objects
		StartGame();
	}
	
	/**
	 * Method to display the main window
	 */
	public void display() {
		mainFrame.getContentPane().add(gamePanel,BorderLayout.CENTER);
		mainFrame.pack();
        mainFrame.setVisible(true);
	}
	
	
	private void StartGame()
	{
		//Initialize game objects
		gameObjectList = new LinkedList<GameObject>();
		
		gamePanel.SetGameObjectList(gameObjectList);
		
		for(int i = 0; i < 40; i++)
		{
			Puck p = new Puck();
			gameObjectList.add(p);
		}
		

		
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
		gamePanel.paintImmediately(gamePanel.getBounds());;
	}
	
	
	private Timer timer;
	private LinkedList<GameObject> gameObjectList;
	private JFrame mainFrame;
	private GamePanel gamePanel;	
}