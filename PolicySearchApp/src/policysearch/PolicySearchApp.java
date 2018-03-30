package policysearch;

import java.io.IOException;
import java.util.Random;

/**
 * PolicySearchApp
 * Application Class that is used to run the policysearch application
 * Contains the main, valueIteration, policyIteration and policyEval methods
 * 
 * @author  Jonathan Kevin Chandra
 * @version 1.0
 * @since   2018-03-08
 */
public class PolicySearchApp {
	
	//(0,0) (0,5) (1,5) (3,3) (4,5)
	public static double[][] utilityIterationsValue = new double[Constant.NUM_STATES][Constant.ITERATIONS];
	public static double[][] utilityIterationsPolicy = new double[Constant.NUM_STATES][Constant.ITERATIONS];

	public static void main(String[] args) {
		

		//Part One
		 
		//Generates new StateGrid
		StateGrid stateGrid = new StateGrid(Constant.GRID_A_SIZE,Constant.GRID_A_SIZE);
		stateGrid.generateA();
		stateGrid.seeStateGrid();
		
		
		//Initializing results
		ActionUtility[][] result = new ActionUtility[Constant.GRID_A_SIZE][Constant.GRID_A_SIZE];
		ActionUtility[][] result2 = new ActionUtility[Constant.GRID_A_SIZE][Constant.GRID_A_SIZE];
		
		//Value Iteration
		result = valueIteration(stateGrid, 0.1);
		//seeUtil(result);
		seeUtilGrid(result);
		seeAction(result);

		seeUtilityIterationsValue();
		try {
			Functions.Arr2DtoCSV(utilityIterationsValue, "utilityIterationsValue");
		} catch (IOException e) {
			e.printStackTrace();
		}


	
		//Policy Iteration
		result2 = policyIteration(stateGrid);
		//seeUtil(result2);
		seeUtilGrid(result2);
		seeAction(result2);

		seeUtilityIterationsPolicy();
		try {
			Functions.Arr2DtoCSV(utilityIterationsPolicy, "utilityIterationsPolicy");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Part Two
		
		//Generates new StateGrid
		StateGrid stateGrid2 = new StateGrid(Constant.GRID_B_SIZE,Constant.GRID_B_SIZE);
		stateGrid2.generateB();
		stateGrid2.seeStateGrid();
		
		ActionUtility[][] result3 = new ActionUtility[Constant.GRID_B_SIZE][Constant.GRID_B_SIZE];
		ActionUtility[][] result4 = new ActionUtility[Constant.GRID_B_SIZE][Constant.GRID_B_SIZE];
		
		result3 = valueIteration(stateGrid2, 0.1);
		seeUtilGrid(result3);
		seeAction(result3);
		
		seeUtilityIterationsValue();
		try {
			Functions.Arr2DtoCSV(utilityIterationsValue, "utilityIterationsValue2");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		result4 = policyIteration(stateGrid2);
		seeUtilGrid(result4);
		seeAction(result4);
		
		seeUtilityIterationsPolicy();
		try {
			Functions.Arr2DtoCSV(utilityIterationsPolicy, "utilityIterationsPolicy2");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * valueIteration Method
	 * Uses Value Iteration to find the optimal policy and the best utility at each State of the Grid given a StateGrid
	 * and an error value epsilon
	 * 
	 * @param stateGrid the StateGrid that will be used in the policy search
	 * @param epsilon the minimum error value that each ActionUtility can differ by
	 * @return ActionUtility[][] a 2D array of ActionUtility objects which shows the optimal policy and the best utility at each State
	 */
	public static ActionUtility[][] valueIteration(StateGrid stateGrid, double epsilon){
		
		//Initializing U, U', number of iterations, S and delta
		ActionUtility[][] au0 = new ActionUtility[stateGrid.getHeight()][stateGrid.getWidth()];
		ActionUtility[][] au1 = new ActionUtility[stateGrid.getHeight()][stateGrid.getWidth()];
		int numIter = 0;
		double delta;
		
		State[][] tempStates = stateGrid.getStates();
		
		for (int i = 0; i < au1.length ; i++) {
			for(int j = 0; j< au1[i].length ; j++) {
				au1[i][j] = new ActionUtility();
			}
		}
		
		// Exits when delta is smaller than epsilon(1-dr)/dr
		do {
			// Copies U' into U and sets delta to 0
			Functions.arrayCopyTwo(au1, au0);
			delta = 0;
			
			// For s in S, find the Maximum ActionUtility of each State and update delta
			for (int i = 0; i < tempStates.length; i++) {
				for (int j = 0; j < tempStates[i].length; j++) {
					if (tempStates[i][j].getType().isWall())
						continue;
					au1[i][j] = Functions.maxUtilAct(tempStates, au0, i, j);
					if (Math.abs(au1[i][j].getUtil() - au0[i][j].getUtil()) > delta)
						delta = Math.abs(au1[i][j].getUtil() - au0[i][j].getUtil());
				}
			}
			
			if (numIter <= Constant.ITERATIONS & numIter > 0) {
				//(0,0) (0,5) (1,5) (3,3) (4,5)
				utilityIterationsValue[0][numIter-1] = au0[0][0].getUtil();
				utilityIterationsValue[1][numIter-1] = au0[0][5].getUtil();
				utilityIterationsValue[2][numIter-1] = au0[1][5].getUtil();
				utilityIterationsValue[3][numIter-1] = au0[3][3].getUtil();
				utilityIterationsValue[4][numIter-1] = au0[4][5].getUtil();		
			}
			
			numIter++;
		} while (delta > (epsilon * (1 - Constant.DISCOUNT_RATE)/ Constant.DISCOUNT_RATE));
		System.out.println("Number of Iterations: " + numIter);
		return au0;
	}
	
	/**
	 * policyIteration Method
	 * Uses Policy Iteration to find the optimal policy and the best utility at each State of the Grid given a StateGrid
	 * 
	 * @param stateGrid the StateGrid that will be used in the policy search
	 * @return ActionUtility[][] a 2D array of ActionUtility objects which shows the optimal policy and the best utility at each State
	 */
	public static ActionUtility[][] policyIteration(StateGrid stateGrid){
		
		// Initializes U, S, unchanged and number of iterations
		ActionUtility[][] au0 = new ActionUtility[stateGrid.getHeight()][stateGrid.getWidth()];
		
		State[][] tempStates = stateGrid.getStates();
		
		boolean unchanged = true;
		int numIter = 0;
		
		Random rand = new Random();
		
		// Randomly sets policy as the initial policy
		for (int i = 0; i < au0.length; i++) {
			for(int j = 0; j< au0[i].length; j++) {
				au0[i][j] = new ActionUtility();
				if(!tempStates[i][j].getType().isWall()) {
					int randomVal = rand.nextInt(4);
					switch (randomVal) {
					case 0: 
						au0[i][j].setAct(Action.UP);
						break;
					case 1:
						au0[i][j].setAct(Action.RIGHT);
						break;
					case 2:
						au0[i][j].setAct(Action.LEFT);
						break;
					case 3:
						au0[i][j].setAct(Action.DOWN);
						break;	
					}
					au0[i][j].setUtil(0.0);			
				}
			}
		}
		// Exits when unchanged is true
		do {
			//Use policyEval to evaluate the initial utilities given the current policy and sets unchanged as true
			au0 = policyEval(stateGrid, au0);
			
			unchanged = true;
			
			//for s in S, find maximum ActionUtility of each State and compare with the current ActionUtility
			//updates current ActionUtility if maximum ActionUtility is higher
			for(int i = 0; i < au0.length; i++) {
				for(int j = 0; j < au0[i].length; j++) {
					if (tempStates[i][j].getType().isWall())
						continue;
					ActionUtility bestActUtil = Functions.maxUtilAct(tempStates, au0, i, j);
					ActionUtility currActUtil = au0[i][j];
					if (bestActUtil.getUtil() > currActUtil.getUtil()) {
						au0[i][j].setAct(bestActUtil.getAct());
						unchanged = false;
					}	
				}
			}
			
			if (numIter <= Constant.ITERATIONS & numIter > 0) {
				//(0,0) (0,5) (1,5) (3,3) (4,5)
				utilityIterationsPolicy[0][numIter-1] = au0[0][0].getUtil();
				utilityIterationsPolicy[1][numIter-1] = au0[0][5].getUtil();
				utilityIterationsPolicy[2][numIter-1] = au0[1][5].getUtil();
				utilityIterationsPolicy[3][numIter-1] = au0[3][3].getUtil();
				utilityIterationsPolicy[4][numIter-1] = au0[4][5].getUtil();		
			}
			numIter++;
		} while(unchanged != true);
		
		System.out.println("Number of Iterations: " + numIter);
		return au0;
		
	}
	
	/**
	 * policyEval Method
	 * Updates the utilities in the ActionUtility given a specific Policy
	 * 
	 * @param stateGrid the StateGrid that will be used in the policy search
	 * @param au the ActionUtility[][] that is used which includes the Action to take
	 * @return ActionUtility[][] the array of ActionUtility given a specific Policy
	 */
	public static ActionUtility[][] policyEval(StateGrid stateGrid, ActionUtility[][] au){
		
		ActionUtility[][] tempAu0 = new ActionUtility[stateGrid.getHeight()][stateGrid.getWidth()];
		ActionUtility[][] tempAu1 = new ActionUtility[stateGrid.getHeight()][stateGrid.getWidth()];
		State[][] tempStates = stateGrid.getStates();
		Functions.arrayCopyTwo(au, tempAu1);
		
		
		
		for (int k = 0; k < Constant.K_VAL; k++) {
			Functions.arrayCopyTwo(tempAu1, tempAu0);
			for (int i = 0; i < tempAu1.length; i++) {
				for(int j = 0; j < tempAu1[i].length; j++) {
					if (tempStates[i][j].getType().isWall())
						continue;
					tempAu1[i][j] = Functions.getUtilAct(tempStates, tempAu0, i, j, tempAu0[i][j].getAct());
				}
			}
		}
		
		return tempAu1;
	}
	
	/**
	 * seeUtil Method
	 * Displays the Utility of the States on the console in List format
	 * 
	 * @param res the array of ActionUtility to be displayed
	 */
	public static void seeUtil(ActionUtility[][] res) {
		System.out.println("Reference Utilities of States (List):");
		System.out.println("Coordinates are in (row, column) format with the top left corner being (0,0) and walls are "
				+ "set to 0");
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				System.out.printf("(" + i + "," + j + "): %.6f " + "\n",res[i][j].getUtil());
			}
		}
	}
	
	/**
	 * seeUtilGrid Method
	 * Displays the Utility of the States on the console in Grid format
	 * 
	 * @param res the array of ActionUtility to be displayed
	 */
	public static void seeUtilGrid(ActionUtility[][] res) {
		System.out.println("Reference Utilities of States (Grid):");
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				System.out.printf("%.2f      ",res[i][j].getUtil());
			}
			System.out.println();
		}
	}
	
	/**
	 * seeAction Method
	 * Displays the Action of the States on the console in Grid format (Policy)
	 * 
	 * @param res the array of ActionUtility to be displayed
	 */
	public static void seeAction(ActionUtility[][] res) {
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				if (res[i][j].getAct() == null)
					System.out.print("[X]" + "");
				else
					System.out.print(res[i][j].getAct() + "");
			}
			System.out.println();
		}
	}
	
	/**
	 * seeUtilityIterationsValue method
	 * Displays the Number of Iterations and the estimated utilities of the State for the following state: 
	 * (0,0) (0,5) (1,5) (3,3) (4,5) for Value Iteration
	 */
	public static void seeUtilityIterationsValue() {
		
		int[][] selectState = {{0,0},{0,5},{1,5},{3,3},{4,5}};
		
		for (int i = 0; i < Constant.NUM_STATES; i++){
			System.out.print("State at Location (" + selectState[i][0] + "," + selectState[i][1] + ") -> ");
			for(int j = 0; j < Constant.ITERATIONS; j++) {
				System.out.printf("Iter " + (j+1) + ": %.3f ", utilityIterationsValue[i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * seeUtilityIterationsPolicy method
	 * Displays the Number of Iterations and the estimated utilities of the State for the following state: 
	 * (0,0) (0,5) (1,5) (3,3) (4,5) for Policy Iteration
	 */
	public static void seeUtilityIterationsPolicy() {
		
		int[][] selectState = {{0,0},{0,5},{1,5},{3,3},{4,5}};
		
		for (int i = 0; i < Constant.NUM_STATES; i++){
			System.out.print("State at Location (" + selectState[i][0] + "," + selectState[i][1] + ") -> ");
			for(int j = 0; j < Constant.ITERATIONS; j++) {
				System.out.printf("Iter " + (j+1) + ": %.3f ", utilityIterationsPolicy[i][j]);
			}
			System.out.println();
		}
	}
}
