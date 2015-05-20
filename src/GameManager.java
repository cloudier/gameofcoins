/**
 * Manages the state of the game.
 * @author ivormetcalf
 *
 */
public class GameManager {

	private String state;
	/*
	 * States:
	 * 	start - menu that you start in. new game and exit button.
	 *  mode - menu that you select mode, board size and victory condition in.
	 *  players - select number of players.
	 *  player settings - select settings for a player - AI and difficulty, or human and coin.
	 */
	private UIObject mainMenu;
	private UIObject modeMenu;
	private int[][] board;
	private MainGame mg;
	
	public GameManager(MainGame mg) {
		
		this.mg = mg;
		this.state = "start";
		this.mainMenu = new MainMenu(); // active and visible
		this.modeMenu = new ModeMenu(); // active and visible
		
		mg.AddGameObject(mainMenu);
				
//		mg.AddGameObject(ModeMenu);
//		ModeMenu.visible = false;
	}
	
	public void activateNextState() {
		System.out.println("activate");
		// if start, activate mode
		if (this.state.equals("start")) {
			modeMenu.active = true;
			modeMenu.visible = true;
			this.state = "mode";
			System.out.println("stuff");
		}
		// if mode, activate players
		if (this.state.equals("mode")) {
			
		}
	}
}
