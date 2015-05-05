import java.awt.*;
import javax.swing.*;

public class View {
	
	private JFrame mainFrame;
	private Board sodukuPanel;
	private JButton newGameButton;

	/**
	 * Method to bootstrap the main frame
	 * @param args
	 */
	public static void main(String[] args) {
		final View mw = new View();
		
		// display the main window in a different thread.
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	mw.display();
            }
        });
	}
	
	/**
	 * Constructor for the main window.
	 */
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

	/**
	 * Method to display the main window
	 */
	private void display() {
		mainFrame.getContentPane().add(sodukuPanel,BorderLayout.CENTER);
		mainFrame.getContentPane().add(newGameButton,BorderLayout.SOUTH);
		mainFrame.pack();
        mainFrame.setVisible(true);
	}

}
