import javax.swing.*;

public class Game {

	/**
	 * Method to bootstrap the main frame
	 * @param args
	 */
	public static void main(String[] args) {
		final View v = new View();
		
		// display the main window in a different thread.
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	v.display();
            }
        });
	}
	
	/**
	 * Constructor for the main window.
	 */
	public Game() {
		
	}


}
