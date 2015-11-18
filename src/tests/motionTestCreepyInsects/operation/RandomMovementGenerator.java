package tests.motionTestCreepyInsects.operation;

import commonOperations.randomValueMaker.operation.RandomIntegerGenerator;


public class RandomMovementGenerator {
	public static final int SOFT_MOVEMENT=0;
	public static final int MODERATE_MOVEMENT=1;
	public static final int RAPID_MOVEMENT=2;
	
	/**
	 * 
	 * @param numberOfMoves
	 * @param MovementType
	 * @return
	 */
	public int[][] generateRandomMovement(int numberOfMoves, int MovementType){
		if(MovementType>2){			//error check
			MovementType=2;
		}
		
		int[][] changeInMovement = new int[2][numberOfMoves];
		
		int[][] changeInChangeOfMovements = {	{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0},
												{0, 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0, 0, 0},
												{1, 0, 0, -1, 0, 1, 0, 0, -1, 0, 1, 0, 0, -1, 0, 1, 0, 0, -1, 0}	};
		
		changeInMovement[0][0] = 1;
		changeInMovement[1][0] = 1;
		for(int i=1; i<numberOfMoves; i++){
			changeInMovement[0][i] = changeInMovement[0][i-1] + getRandomArrayMember(changeInChangeOfMovements[MovementType]);
			
			if(changeInMovement[0][i]>3){
				changeInMovement[0][i]--;
			}if(changeInMovement[0][i]<-3){
				changeInMovement[0][i]++;
			}
			
			
			changeInMovement[1][i] = changeInMovement[0][i-1] + getRandomArrayMember(changeInChangeOfMovements[MovementType]);
			
			if(changeInMovement[1][i]>3){
				changeInMovement[1][i]--;
			}if(changeInMovement[1][i]<-3){
				changeInMovement[1][i]++;
			}
		}
		
		return changeInMovement;
	}
	
	/**
	 * 
	 * @param array
	 * @return
	 */
	private int getRandomArrayMember(int[] array){
		int randomMember = array[new RandomIntegerGenerator().nextInt(array.length)];
		
		return randomMember;
	}
}
