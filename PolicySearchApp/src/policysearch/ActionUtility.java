package policysearch;

/**
 * Action Utility Class
 * Class that pairs up the Action and Utility as one Object.
 *
 * @author  Jonathan Kevin Chandra
 * @version 1.0
 * @since   2018-03-08
 */
public class ActionUtility {
	
	/**
	 * act: Action to be taken at the current state
	 * util: Utility if action is taken at the current state
	 */
	private Action act;
	private double util;
	
	/**
	 * Constructor Class
	 */
	public ActionUtility() {
		this.act = null;
		this.util = 0.0;
	}
	
	/**
	 * Constructor Class with parameters action and utility
	 * @param act 
	 * @param util 
	 */
	public ActionUtility(Action act, double util) {
		this.act = act;
		this.util = util;
	}
	
	/**
	 * Get method for Action
	 * @return act
	 */
	public Action getAct() {
		return act;
	}
	
	/**
	 * Get method for Utility
	 * @return util
	 */
	public double getUtil() {
		return util;
	}
	
	/**
	 * Set method for Action
	 * @param act
	 */
	public void setAct(Action act) {
		this.act = act;
	}
	
	/**
	 * Set method for Utility
	 * @param util
	 */
	public void setUtil(double util) {
		this.util = util;
	}
	
	
	
}
