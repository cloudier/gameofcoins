package gameEngine;

/**
 * Enum used to store the different types of game states
 * with methods that returns adjacent game states.
 * 
 * @author cloudier
 *
 */
public enum GameState {
	START (1),
	MODE (2),
	PLAYERS (3),
	BOARD (4);
	
	private int id;
	
	/**
	 * Sets the ID of the game state.
	 * @param id
	 */
	private GameState(int id) {
		this.id = id;
	}
	
	/**
	 * Returns the game state with the given id.
	 * @param id
	 * @return game state with given id
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
     * Returns the state that comes after the given state
     * @param game state
     * @return state that comes after the given state
     */
	public static GameState nextState(GameState gs) {
		if (gs.id < 4) {
			return getStateByID(gs.id + 1);
		} else {
			return START;
		}
	}
	
	/**
	 * Returns the state that comes before the given state
	 * @param game state
	 * @return state that comes before the given state
	 */
	public static GameState prevState(GameState gs) {
		if (gs.id > 0) {
			return getStateByID(gs.id - 1);
		} else {
			return BOARD;
		}
	}
}
