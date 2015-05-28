package gameEngine;

/**
 * 
 * @author 
 *
 */
public enum GameState {
	START (1),
	MODE (2),
	PLAYERS (3),
	BOARD (4);
	
	private int id;
	
	/**
	 * 
	 * @param id
	 */
	private GameState(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @param ID
	 * @return
	 */
    public static GameState getStateByID(int ID) {
	    switch(ID) {
	    case 1:
	        return START;
	    case 2:
	        return MODE;
	    case 3:
	        return PLAYERS;
	    case 4:
	    	return BOARD;
	    }
	    return START;
    }
    
    /**
     * 
     * @param gs
     * @return
     */
	public static GameState nextState(GameState gs) {
		if (gs.id < 4) {
			return getStateByID(gs.id + 1);
		} else {
			return START;
		}
	}
	
	/**
	 * 
	 * @param gs
	 * @return
	 */
	public static GameState prevState(GameState gs) {
		if (gs.id > 0) {
			return getStateByID(gs.id - 1);
		} else {
			return BOARD;
		}
	}
}
