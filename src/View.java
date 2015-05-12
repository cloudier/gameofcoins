import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class View {

	private JFrame mainFrame;
	private GamePanel gamePanel;
	public final static int INTERVAL = 1000/60; // 60 times per second
	public Timer timer;
	
	public View() {
		gamePanel = new GamePanel();
		mainFrame = new JFrame("Connect4 Demo");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				gamePanel.repaint();
				
				System.out.println("Tick tock");
				
				// some condition
				if (true) {
					timer.stop();
				}
			}
		});
		
		timer.start();
		
	}
	
	/**
	 * Method to display the main window
	 */
	public void display() {
		mainFrame.getContentPane().add(gamePanel,BorderLayout.CENTER);
		mainFrame.pack();
        mainFrame.setVisible(true);
	}
	
}
