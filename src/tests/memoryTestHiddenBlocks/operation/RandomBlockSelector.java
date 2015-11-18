package tests.memoryTestHiddenBlocks.operation;

import commonOperations.randomValueMaker.operation.RandomIntegerGenerator;

public class RandomBlockSelector {
	public int[] selectRandomBlocks(int levelOfDifficulty, int numberOfBlocks){
		int noOfBlocks = levelOfDifficulty/2 + 3;

		
		int[] selectedBlocks = new int[noOfBlocks];
		RandomIntegerGenerator randomIntegerGenerator = new RandomIntegerGenerator();
		
		for(int i=0; i<noOfBlocks; i++){
			selectedBlocks[i] = -1;
		}
		
		for(int i=0; i<noOfBlocks; i++){
			selectedBlocks[i] = randomIntegerGenerator.nextInt(numberOfBlocks);
			for(int j=0; j<i; j++){
				if(selectedBlocks[i]==selectedBlocks[j]){
					i--;
					break;
				}
			}
		}
		
		return selectedBlocks;
	}
}
