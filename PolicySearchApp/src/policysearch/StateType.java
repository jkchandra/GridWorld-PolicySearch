package policysearch;

/**
 * StateType Enum
 * Enum that stores the StateType of the State. Contains variables reward, wall, string of the State
 * 
 * @author  Jonathan Kevin Chandra
 * @version 1.0
 * @since   2018-03-08
 */
public enum StateType {
	White(-0.04, false, "[ ]"), Green(1, false, "[G]"), Brown(-1, false, "[B]"), Wall(0,true, "[X]");
	
	/**
	 * reward: Reward of the agent when this State is reached
	 * wall: A boolean value of whether the State is a wall or not
	 * string: A string that is shown when it is printed on the console
	 */
	private final double reward;
	private final boolean wall;
	private final String string;
	
	/**
	 * Class constructor with parameters reward, wall and string
	 * @param reward
	 * @param wall
	 * @param string
	 */
	StateType(double reward, boolean wall, String string) {
		this.reward = reward;
		this.wall = wall;
		this.string = string;
	    }
	
	/**
	 * Get method for wall
	 * @return wall
	 */
	public boolean isWall() {
		return wall;
	}
	
	/**
	 * Get method for reward
	 * @return reward
	 */
	public double getReward() {
		return reward;
	}
	
	/**
	 * Converts the StateType to String
	 * @return string
	 */
	public String toString() {
		return string;
	}


}
