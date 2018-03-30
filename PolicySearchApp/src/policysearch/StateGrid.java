package policysearch;

/**
 * StateGrid Class
 * Class that stores the State Grid and the methods used to generate and display the State Grid of the object
 * 
 * @author  Jonathan Kevin Chandra
 * @version 1.0
 * @since   2018-03-08
 */
public class StateGrid {
	
	/**
	 * states[][]: The States in the StateGrid of the form SIZE*SIZE
	 */
	private State states[][];
	private int height;
	private int width;
	
	/**
	 * Class Constructor
	 * Creates a new State in the grid with the default StateType White
	 * @param height 
	 */
	public StateGrid(int h,int w) {
		states = new State[h][w];
		this.height = h;
		this.width = w;
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				states[i][j] = new State(StateType.White);
			}
		}
	}
	
	/**
	 * Get method for states[][]
	 * @return states
	 */
	public State[][] getStates(){
		return states;
	}
	
	/**
	 * Get method for height
	 * @return states
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * Get method for width
	 * @return states
	 */
	public int getWidth(){
		return width;
	}
	
	
	/**
	 * Displays the StateGrid with their respective States on the console
	 */
	public void seeStateGrid() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (states[i][j].getType().isWall() == true) {
					System.out.print(states[i][j].getType().toString() + "");
					continue;
				}
				System.out.print(states[i][j].getType().toString() + "");
			}
			System.out.println();
		}
	}
	
	/**
	 * Generates the StateGrid as per Assignment Part 1. Each State in the grid is allocated a StateType based on diagram
	 * shown
	 */
	public void generateA() {
		//Row0
		states[0][0].setType(StateType.Green);
		states[0][1].setType(StateType.Wall);
		states[0][2].setType(StateType.Green);
		states[0][5].setType(StateType.Green);
		//Row1
		states[1][1].setType(StateType.Brown);
		states[1][3].setType(StateType.Green);
		states[1][4].setType(StateType.Wall);
		states[1][5].setType(StateType.Brown);
		//Row2
		states[2][2].setType(StateType.Brown);
		states[2][4].setType(StateType.Green);
		//Row3
		states[3][3].setType(StateType.Brown);
		states[3][5].setType(StateType.Green);
		//Row4
		states[4][1].setType(StateType.Wall);
		states[4][2].setType(StateType.Wall);
		states[4][3].setType(StateType.Wall);
		states[4][4].setType(StateType.Brown);
	}
	
	/**
	 * Generates the StateGrid as per Assignment Part 2. Each State in the grid is allocated a StateType based on diagram
	 * shown
	 */
	public void generateB() {
		//Row0
		states[0][6].setType(StateType.Wall);
		states[0][9].setType(StateType.Brown);
		states[0][10].setType(StateType.Green);
		//Row1
		states[1][1].setType(StateType.Brown);
		states[1][2].setType(StateType.Green);
		states[1][4].setType(StateType.Wall);
		states[1][5].setType(StateType.Brown);
		states[1][6].setType(StateType.Wall);
		states[1][9].setType(StateType.Green);
		states[1][10].setType(StateType.Brown);
		//Row2
		states[2][6].setType(StateType.Wall);
		states[2][7].setType(StateType.Brown);
		//Row3
		states[3][0].setType(StateType.Brown);
		states[3][1].setType(StateType.Wall);
		states[3][2].setType(StateType.Wall);
		states[3][3].setType(StateType.Wall);
		states[3][4].setType(StateType.Wall);
		states[3][5].setType(StateType.Brown);
		states[3][6].setType(StateType.Wall);
		states[3][7].setType(StateType.Brown);
		states[3][8].setType(StateType.Wall);
		states[3][9].setType(StateType.Wall);
		states[3][10].setType(StateType.Wall);
		//Row4
		states[4][3].setType(StateType.Wall);
		states[4][4].setType(StateType.Wall);
		states[4][6].setType(StateType.Wall);
		//Row5
		states[5][6].setType(StateType.Green);
		//Row6
		states[6][0].setType(StateType.Green);
		states[6][1].setType(StateType.Brown);
		states[6][2].setType(StateType.Wall);
		states[6][7].setType(StateType.Wall);
		//Row7
		states[7][2].setType(StateType.Brown);
		states[7][5].setType(StateType.Green);
		states[7][6].setType(StateType.Wall);
		states[7][7].setType(StateType.Wall);
		states[7][8].setType(StateType.Wall);
		states[7][9].setType(StateType.Wall);
		states[7][10].setType(StateType.Wall);
		//Row8
		states[8][2].setType(StateType.Wall);
		states[8][5].setType(StateType.Brown);
		//Row9
		states[9][4].setType(StateType.Wall);
		states[9][8].setType(StateType.Brown);
		states[9][9].setType(StateType.Green);
		states[9][10].setType(StateType.Brown);
		//Row10
		states[10][4].setType(StateType.Wall);
		

	}
	
	

}
