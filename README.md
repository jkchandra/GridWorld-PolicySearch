# Grid World Policy Search

Using Value Iteration and Policy Iteration to search for the optimum utility and policy in a Grid World

## Grid World Creation

Grid is created by new StateGrid and running the generateA() method. Each StateGrid has columns and rows of
size Constant.SIZE and each tile is represented by a State. Each State belongs to a StateType which stores the
information on the Rewards, isWall and String of the State.

seeStateGrid() method is used to show the following image on the console. [G] represents Green, [ ]
represents White, [B] represents Brown and [X] represents a Wall.

## Transition Model

![transitionModel](https://raw.githubusercontent.com/jkchandra/policysearch-gridworld/img/transition_model.png)
Action that can be taken is stored as an enumeration with the different actions to be taken as UP, DOWN, LEFT
and RIGHT. Transition Model is modelled in the Functions Class, getUtilAct method where the different utilities
are calculated based on the probability of the direction of intended movement.

## Value Iteration

The following steps are taken in the valueIteration method
1. Initialize U and U’ as a 2D array of ActionUtility
2. Initialize Number of Iterations = 0, States, and delta
3. While (delta > (epsilon*(1-discountRate)/discountRate) do:
   - Delta = 0
   - U  U’
   - For each State in States:
     - If Wall, Continue
     - Find Maximum ActionUtility of State
     - Find the delta as the difference between the utility of state between U and U’
   - Increment Number of Iterations
4. Returns U

## Policy Iteration

The following steps are taken in the policyIteration method
1. Initialize U
2. Initialize Number of Iterations = 0, States, unchanged = true
3. Randomly sets policy as the initial policy
4. While (unchanged = false)
   - U = policyEval(StateGrid, U)
   - Unchanged = true
   - For each State in States:
     - If Wall, Continue
     - Find Maximum ActionUtility of State as BestActionUtility
     - Let CurrentActionUtility be the Current ActionUtility of State based on U
     - If (BestActionUtility > CurrentActionUtility)
       - Set State Action as Action from BestActionUtility
       - Unchanged = false
  - Increment Number of Iterations
5. Returns U

## Policy Evaluation

The following steps are taken in the policyEval method
1. Initialize U and U’
2. For K to K_VAL number of iterations:
   - U  U’
   - For each State in States:
     - If Wall, Continue
     - Calculates the Utility of the given Action
