package policysearch;

/**
 * Action Enum
 * Enum that stores the Action that can be taken. Contains variable string.
 * 
 * @author  Jonathan Kevin Chandra
 * @version 1.0
 * @since   2018-03-08
 */
public enum Action {
	UP("[U]"), DOWN("[D]"), LEFT("[L]"), RIGHT("[R]");

	/**
	 * string: A string that is shown when it is printed on the console
	 */
	private final String string;
	
	/**
	 * Class constructor with parameters string
	 * @param string
	 */
	Action(String string) {
		this.string = string;
	    }
	
	/**
	 * Converts the Action to String
	 * @return string
	 */
	public String toString() {
		return string;
	}

}
