import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;



public class View {
	
	private Board sodukuPanel;
	private JButton newGameButton;
	
	public View() {
		mainFrame = new JFrame("Soduku GUI Demo");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create the soduku panel
		sodukuPanel = new Board();
		
		// Create a new button and add the action listener.
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(sodukuPanel);
		
		// Or do it through an anonymous inner class
//		newGameButton = new JButton(new AbstractAction("New Game") {
//		    public void actionPerformed(ActionEvent e) {
//		        sodukuPanel.generateBoard();
//		        sodukuPanel.repaint();
//		    }
//		});
	}
	
	private JFrame mainFrame;
	

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
