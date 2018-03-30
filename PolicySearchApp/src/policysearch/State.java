package policysearch;

/**
 * State Class
 * Class that stores the information of the State in the Markovian Decision Process
 * 
 * @author  Jonathan Kevin Chandra
 * @version 1.0
 * @since   2018-03-08
 */
public class State {
	
	/**
	 * type: StateType of the State
	 */
	private StateType type;
	
	/**
	 * Class Constructor
	 * @param type
	 */
	public State(StateType type) {
		this.type = type;
	}
	
	/**
	 * Set method for type
	 * @param type
	 */
	public void setType(StateType type) {
		this.type = type;
	}
	
	/**
	 * Get method for type
	 * @return type
	 */
	public StateType getType() {
		return type;
	}

}
