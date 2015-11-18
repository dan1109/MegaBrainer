package tests.reactionTestSymbolCollection.operation;

import commonOperations.randomValueMaker.operation.RandomIntegerGenerator;



public class RandomSymbolGenerator {
	public String[] makeRandomSymbol(Boolean[] mainAnswerIsAlphabet){
		RandomIntegerGenerator randomIntegerGenerator = new RandomIntegerGenerator();
		
		int numberOfSymbols = mainAnswerIsAlphabet.length;
		for(int i=0; i<numberOfSymbols; i++){
			mainAnswerIsAlphabet[i] = false;
		}
		
		int numberOfAlphabets = randomIntegerGenerator.nextInt((numberOfSymbols/2)-(numberOfSymbols/5),
																(numberOfSymbols/2)+(numberOfSymbols/5));
		
		for(int i=0, randomValue; i<numberOfAlphabets; i++){
			randomValue = randomIntegerGenerator.nextInt(numberOfSymbols);
			if(!mainAnswerIsAlphabet[randomValue]){
				mainAnswerIsAlphabet[randomValue]=true;
			}else{
				i--;
			}
		}
		
		String[] symbols = new String[numberOfSymbols];
		
		for(int i=0, randomValue; i<numberOfSymbols; i++){
			if(mainAnswerIsAlphabet[i]){
				randomValue = randomIntegerGenerator.nextInt(65, 91);
			}else{
				randomValue = randomIntegerGenerator.nextInt(48, 58);
			}
			symbols[i] = (char)randomValue + "";
		}
		
		return symbols;
	}
}
