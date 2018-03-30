package policysearch;

import java.util.Comparator;

/**
 * Utility Comparator Class
 * Comparator class that is used to compare ActionUtility Objects using their Utility values
 * 
 * @author  Jonathan Kevin Chandra
 * @version 1.0
 * @since   2018-03-08
 */
public class UtilityComparator implements Comparator<ActionUtility> {
	
	/**
	 * Compares two ActionUtility objects and puts the ActionUtility object with the highest Utility in front
	 * List<ActionUtility> will be sorted in descending order
	 * Eg. { 20, 13, -3, -9 }
	 */
	@Override
	public int compare(ActionUtility a, ActionUtility b) {
		return a.getUtil() > b.getUtil()? -1 : 1;
	}

}
