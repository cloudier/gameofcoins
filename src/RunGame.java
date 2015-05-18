import javax.swing.SwingUtilities;

public class RunGame {
	/**
	 * Method to bootstrap the main frame
	 * @param args
	 */
	public static void main(String[] args) {
		final MainGame g = new MainGame();
		
		// display the main window in a different thread.
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	g.display();
            }
        });
	}
	
	/**
	 * Constructor for the main window.
	 */
	public RunGame() {
		
	}
}