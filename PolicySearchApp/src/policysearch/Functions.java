package policysearch;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Functions Class
 * Helper class that contains most of the functions required by application
 * 
 * @author  Jonathan Kevin Chandra
 * @version 1.0
 * @since   2018-03-08
 */
public class Functions {
	
	/**
	 * maxUtilAct Method
	 * Finds the ActionUtility with the highest Utility by using an ArrayList and sorting it using
	 * UtilityComparator. Calls the getUtilAct(states, au, i, j, act) method
	 * 
	 * @param states the states of the StateGrid to be used
	 * @param au the array of ActionUtility to be used
	 * @param i the x coordinate starting from the top left corner of the grid
	 * @param j the y coordinate starting from the top left corner of the grid
	 * @return ActionUtility the ActionUtility with the highest Utility
	 */
	public static ActionUtility maxUtilAct(State[][] states, ActionUtility[][] au, int i, int j) {
		List<ActionUtility> tempAus = new ArrayList<ActionUtility>();

		tempAus.add(getUtilAct(states, au, i, j, Action.UP));
		tempAus.add(getUtilAct(states, au, i, j, Action.RIGHT));
		tempAus.add(getUtilAct(states, au, i, j, Action.DOWN));
		tempAus.add(getUtilAct(states, au, i, j, Action.LEFT));
		
		tempAus.sort(new UtilityComparator());
		
		return tempAus.get(0);
	}
	
	/**
	 * getUtilAct Method
	 * Finds the ActionUtility of the State using a specific action and the probability of taking the intended 
	 * action. Calls upUtil, downUtil, leftUtil, rightUtil methods
	 * 
	 * @param states the states of the StateGrid to be used
	 * @param au the array of ActionUtility to be used
	 * @param i the x coordinate starting from the top left corner of the grid
	 * @param j the y coordinate starting from the top left corner of the grid
	 * @param act the intended action to be taken
	 * @return ActionUtility the ActionUtility of the specific intended action
	 */
	public static ActionUtility getUtilAct(State[][] states, ActionUtility[][] au, int i, int j, Action act) {
		ActionUtility tempAu = new ActionUtility(act, 0.0);
		
		switch(act) {
		case UP: 
			tempAu.setUtil((Constant.PROB_INT * upUtil(states, au, i, j)) + (Constant.PROB_CW * rightUtil(states, au, i, j)) + 
					(Constant.PROB_CCW * leftUtil(states, au, i, j)));
			break;
		case DOWN:
			tempAu.setUtil((Constant.PROB_INT * downUtil(states, au, i, j)) + (Constant.PROB_CW * leftUtil(states, au, i, j)) + 
					(Constant.PROB_CCW * rightUtil(states, au, i, j)));			
			break;
		case LEFT:
			tempAu.setUtil((Constant.PROB_INT * leftUtil(states, au, i, j)) + (Constant.PROB_CW * upUtil(states, au, i, j)) + 
					(Constant.PROB_CCW * downUtil(states, au, i, j)));			
			break;
		case RIGHT:
			tempAu.setUtil((Constant.PROB_INT * rightUtil(states, au, i, j)) + (Constant.PROB_CW * downUtil(states, au, i, j)) + 
					(Constant.PROB_CCW * upUtil(states, au, i, j)));			
			break;
		default:
			break;
		}
		
		return tempAu;
	}
	
	/**
	 * upUtil Method
	 * Calculates the Utility of taking the Up action
	 * 
	 * @param states the states of the StateGrid to be used
	 * @param au the array of ActionUtility to be used
	 * @param i the x coordinate starting from the top left corner of the grid
	 * @param j the y coordinate starting from the top left corner of the grid
	 * @return double the utility of taking the Up action
	 */
	public static double upUtil(State[][] states, ActionUtility[][] au, int i , int j) {
		double nextUtil;
		int i1 = i - 1;
		int j1 = j;
		if(i1 < 0 | i1 >= states.length | j1 < 0 | j1 >= states[i].length )
			nextUtil = au[i][j].getUtil();
		else if(states[i1][j1].getType().isWall())
			nextUtil = au[i][j].getUtil();
		else
			nextUtil = au[i1][j1].getUtil();
		
		return states[i][j].getType().getReward() + (Constant.DISCOUNT_RATE * nextUtil);
	}
	
	/**
	 * downUtil Method
	 * Calculates the Utility of taking the Down action
	 * 
	 * @param states the states of the StateGrid to be used
	 * @param au the array of ActionUtility to be used
	 * @param i the x coordinate starting from the top left corner of the grid
	 * @param j the y coordinate starting from the top left corner of the grid
	 * @return double the utility of taking the Down action
	 */
	public static double downUtil(State[][] states, ActionUtility[][] au, int i , int j) {
		double nextUtil;
		int i1 = i + 1;
		int j1 = j;
		if(i1 < 0 | i1 >= states.length | j1 < 0 | j1 >= states[i].length )
			nextUtil = au[i][j].getUtil();
		else if(states[i1][j1].getType().isWall())
			nextUtil = au[i][j].getUtil();
		else
			nextUtil = au[i1][j1].getUtil();
		
		return states[i][j].getType().getReward() + (Constant.DISCOUNT_RATE * nextUtil);
	}
	
	/**
	 * rightUtil Method
	 * Calculates the Utility of taking the Right action
	 * 
	 * @param states the states of the StateGrid to be used
	 * @param au the array of ActionUtility to be used
	 * @param i the x coordinate starting from the top left corner of the grid
	 * @param j the y coordinate starting from the top left corner of the grid
	 * @return double the utility of taking the Right action
	 */
	public static double rightUtil(State[][] states, ActionUtility[][] au, int i , int j) {
		double nextUtil;
		int i1 = i;
		int j1 = j + 1;
		if(i1 < 0 | i1 >= states.length | j1 < 0 | j1 >= states[i].length)
			nextUtil = au[i][j].getUtil();
		else if(states[i1][j1].getType().isWall())
			nextUtil = au[i][j].getUtil();
		else
			nextUtil = au[i1][j1].getUtil();
		
		return states[i][j].getType().getReward() + (Constant.DISCOUNT_RATE * nextUtil);
	}
	
	/**
	 * leftUtil Method
	 * Calculates the Utility of taking the Left action
	 * 
	 * @param states the states of the StateGrid to be used
	 * @param au the array of ActionUtility to be used
	 * @param i the x coordinate starting from the top left corner of the grid
	 * @param j the y coordinate starting from the top left corner of the grid
	 * @return double the utility of taking the Left action
	 */
	public static double leftUtil(State[][] states, ActionUtility[][] au, int i , int j) {
		double nextUtil;
		int i1 = i;
		int j1 = j - 1;
		if(i1 < 0 | i1 >= states.length | j1 < 0 | j1 >= states[i].length)
			nextUtil = au[i][j].getUtil();
		else if(states[i1][j1].getType().isWall())
			nextUtil = au[i][j].getUtil();
		else
			nextUtil = au[i1][j1].getUtil();
		
		return states[i][j].getType().getReward() + (Constant.DISCOUNT_RATE * nextUtil);
	}
	
	/**
	 * arrayCopyTwo Method
	 * Copies a 2D array of objects from the source to the destination
	 * 
	 * @param src source array of objects
	 * @param dest destination array of objects
	 */
	public static void arrayCopyTwo(Object[][] src, Object[][] dest) {
		for (int i = 0; i < src.length; i++) {
			System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
		}
	}
	
	/**
	 * Arr2DtoCSV method
	 * Copies a 2D array of type double to a csv file in the same directory
	 * 
	 * @param arr 2D array of objects to be copied into the csv
	 * @param fileName String of the filename to be written into
	 * @throws IOException 
	 */
	public static void Arr2DtoCSV(double[][] arr, String fileName) throws IOException {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < arr.length; i++){
		   for(int j = 0; j < arr[i].length; j++){
		      builder.append(arr[i][j] + "");
		      if(j < arr[i].length - 1)
		         builder.append(",");
		   }
		   builder.append("\n");
		}
		FileWriter writer = new FileWriter("INSERT PATH HERE" + fileName + ".csv");
		writer.write(builder.toString());
		writer.close();
	}

}
