import javax.swing.SwingUtilities;

public class MainGame {
	/**
	 * Method to bootstrap the main frame
	 * @param args
	 */
	public static void main(String[] args) {
		final GameWindow v = new GameWindow();
		
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
	public MainGame() {
		
	}
}