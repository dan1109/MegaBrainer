package tests.concentrationTestMixedNumbers.operation;

import commonOperations.randomValueMaker.operation.RandomIntegerGenerator;

public class RandomCharacterMaker {
	public char[] creatRandomNumeralInChar(int amount){
		char[] charSet = new char[amount];
		RandomIntegerGenerator randomIntegerGenerator = new RandomIntegerGenerator();
		
		int[] numerals = new int[2];
		numerals[0] = randomIntegerGenerator.nextInt(amount/2);
		numerals[1] = randomIntegerGenerator.nextInt(amount/2, amount);
		
		for(int i=0; i<amount; i++){
			if(i==numerals[0]){
				charSet[i] = (char) randomIntegerGenerator.nextInt(48, 58);
			}else if(i==numerals[1]){
				charSet[i] = (char) randomIntegerGenerator.nextInt(48, 58);
			}else{
				charSet[i] = (char) randomIntegerGenerator.nextInt(65, 91);
			}
		}
		
		return charSet;
	}
}
