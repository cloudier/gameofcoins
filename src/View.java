import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JFrame;



public class View {
	
	private JFrame mainFrame;
	private Board sodukuPanel;
	private JButton newGameButton;
	
	public View() {
		mainFrame = new JFrame("Connect4 Demo");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create the soduku panel
		sodukuPanel = new Board();
		
		// Create a new button and add the action listener.
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(sodukuPanel);
		
		sodukuPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			    int x=e.getX();
			    //int y=e.getY();
			    //System.out.println(x+","+y);//these co-ords are relative to the component
				
			    sodukuPanel.putCoin(x/Board.CELL_WIDTH);
			    sodukuPanel.repaint();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
	}

	/**
	 * Method to display the main window
	 */
	public void display() {
		mainFrame.getContentPane().add(sodukuPanel,BorderLayout.CENTER);
		mainFrame.getContentPane().add(newGameButton,BorderLayout.SOUTH);
		mainFrame.pack();
        mainFrame.setVisible(true);
	}
}
