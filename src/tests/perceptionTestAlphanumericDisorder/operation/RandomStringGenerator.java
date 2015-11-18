package tests.perceptionTestAlphanumericDisorder.operation;

import commonOperations.randomValueMaker.operation.RandomIntegerGenerator;


public class RandomStringGenerator {
	public String[] makeRandomString(char[] numAnswer, char[] charAnswer){
		int amount = numAnswer.length;
		String[] randomString = new String[amount];
		
		char[] numerals = new char[10];
		char[] characters = new char[26];
		char[] charsequence = new char[23];
		char[] finalCharSequence = new char[45];
		
		for(int i=0; i<45; i++){
			finalCharSequence[i] = ' ';
		}
		
		for(int k=0; k<amount; k++){
			for(int i=0; i<10; i++){
				numerals[i] = (char) (i+48);
			}
			for(int i=0; i<26; i++){
				characters[i] = (char) (i+65);
			}
			
			numerals = shuffle(numerals);
			characters = shuffle(characters);
			
			for(int i=0; i<10; i++){
				charsequence[i] = numerals[i];
			}
			for(int i=10; i<23; i++){
				charsequence[i] = characters[i];
			}
			
			numAnswer[k] = charsequence[4];
			charAnswer[k] = charsequence[17];
			
			charsequence[4] = charsequence[17];
			
			charsequence = shuffle(charsequence);
			
			for(int i=0; i<23; i++){
				finalCharSequence[i*2] = charsequence[i];
			}
			
			randomString[k] = String.copyValueOf(finalCharSequence);
			
		}
		
		return randomString;
	}
	
	
	private char[] shuffle(char[] chars){
		int length = chars.length;
		int shuffleTimes = length*3;
		char charTemp;
		
		RandomIntegerGenerator randomIntegerGenerator = new RandomIntegerGenerator();
		int randomNumber1=0, randomNumber2=0;
		
		for(int i=0; i<shuffleTimes; i++){
			randomNumber1 = randomIntegerGenerator.nextInt(length);
			randomNumber2 = randomIntegerGenerator.nextInt(length);
			
			charTemp = chars[randomNumber1];
			chars[randomNumber1] = chars[randomNumber2];
			chars[randomNumber2] = charTemp;
		}
		
		return chars;
	}
}
