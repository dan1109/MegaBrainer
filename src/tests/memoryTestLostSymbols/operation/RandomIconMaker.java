package tests.memoryTestLostSymbols.operation;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import commonOperations.randomValueMaker.operation.RandomIntegerGenerator;


public class RandomIconMaker {
	public Icon[] generateRandomIcon(int number){
		Icon[] icons = new Icon[number];
		int random=0;
		int totalStockOfIcons = 80;
		RandomIntegerGenerator randomIntegerGenerator = new RandomIntegerGenerator();
		
		int[] randomIconNumber = new int[totalStockOfIcons];
		for(int i=0; i<totalStockOfIcons; i++){
			randomIconNumber[i]=i;
		}
		
		for(int i=0; i<number; i++){
			random = randomIntegerGenerator.nextInt(totalStockOfIcons);
			
			if(randomIconNumber[random]>=0){
				icons[i] = new ImageIcon(getClass().
						getResource("/tests/memoryTestLostSymbols/pictures/Symbol" + randomIconNumber[random] + ".png"));
				randomIconNumber[random]=-1;
			}else{
				i--;
			}
		}
		
		return icons;
	}
}
