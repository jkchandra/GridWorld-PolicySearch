package policysearch;

/**
 * Constant Class
 * Stores all the constants that are required by the application
 * 
 * @author  Jonathan Kevin Chandra
 * @version 1.0
 * @since   2018-03-08
 */
public class Constant {
	
	/**
	 * Variables
	 * K_VAL: Stores the value to iterate until for policyEval
	 * PROB_INT: Stores the probability that the intended action is taken
	 * PROB_CW: Stores the probability that the clockwise action is taken
	 * PROB_CCW: Stores the probability that the anti-clockwise action is taken
	 * DISCOUNT_RATE: Stores the discount rate in valueIteration
	 * 
	 * ITERATIONS: Stores the value of number of iterations that will be taken into account for the Plot of Estimated Utilities
	 * NUM_STATES: Stores the value of the number of states that will be used for the Plot of Estimted Utilities
	 * 
	 * GRID_A_SIZE: Stores the size of Grid A
	 * GRID_B_SIZE: Stores the size of Grid B
	 */
	public final static int K_VAL = 12;
	public final static double PROB_INT = 0.8;
	public final static double PROB_CW = 0.1;
	public final static double PROB_CCW = 0.1;
	public final static double DISCOUNT_RATE = 0.99;

	public final static int ITERATIONS = 200;
	public final static int NUM_STATES = 5;
	
	public final static int GRID_A_SIZE = 6;
	public final static int GRID_B_SIZE = 11;
}
