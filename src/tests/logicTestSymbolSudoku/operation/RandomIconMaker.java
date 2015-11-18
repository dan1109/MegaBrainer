/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 07-Jul-2014																							*
* Comment: 	*									
* 		*
* 		*
*************************************************************************************************************/

package tests.logicTestSymbolSudoku.operation;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import commonOperations.randomValueMaker.operation.RandomIntegerGenerator;


public class RandomIconMaker {
	public Icon[] generateRandomIcon(int primaryIconNumber){
		int iconNumber = primaryIconNumber*primaryIconNumber + primaryIconNumber;
		
		Icon[] primaryIcons = new Icon[primaryIconNumber];
		Icon[][] questionIcons = new Icon[primaryIconNumber][primaryIconNumber];
		Icon[] icons = new Icon[iconNumber];
		
		int random=0;
		RandomIntegerGenerator randomIntegerGenerator = new RandomIntegerGenerator();
		int totalStockOfPictures=16;
		
		int[] randomIconNumber = new int[totalStockOfPictures];
		for(int i=0; i<totalStockOfPictures; i++){
			randomIconNumber[i]=i;
		}
		
		//determine the primary icons///////////////////////////////////////////////////////////////////////////////
		for(int i=0; i<primaryIconNumber; i++){
			random = randomIntegerGenerator.nextInt(totalStockOfPictures);
			
			if(randomIconNumber[random]>=0){
				primaryIcons[i] = new ImageIcon(getClass().
						getResource("/tests/logicTestSymbolSudoku/pictures/Symbol" + randomIconNumber[random] + ".png"));
				randomIconNumber[random]=-1;
			}else{
				i--;
			}
		}
		
		//making the question///////////////////////////////////////////////////////////////////////////////////////
		//int  leak=0;
		for(int i=0, j, k; i<primaryIconNumber; i++){		//assignment
			for(j=0; j<primaryIconNumber; j++){
				questionIcons[i][j] = primaryIcons[j];
			}
			
			boolean matched;
			int rand;
			Icon tempIcon = new ImageIcon();
			for(j=0; j<primaryIconNumber; j++){				//error detection and resolve
				matched = false;
				
				for(k=0; k<i; k++){
					if(questionIcons[k][j].equals(questionIcons[i][j])){
						//leak++;
						matched = true;
						break;
					}
				}
				
				if(matched){		//swap with random icon
					rand = randomIntegerGenerator.nextInt(primaryIconNumber);
					
					tempIcon = questionIcons[i][rand];
					questionIcons[i][rand] = questionIcons[i][j];
					questionIcons[i][j] = tempIcon;
					
					j=-1;
				}
			}
			
			shuffle(primaryIcons);
		}
		//System.out.println(leak);///test
		
		//assigning the value///////////////////////////////////////////////////////////////////////////////////////
		
		for(int i=0; i<primaryIconNumber; i++){
			icons[i] = primaryIcons[i];
		}
		for(int i=0; i<primaryIconNumber; i++){
			for(int j=0; j<primaryIconNumber; j++){
				icons[i*primaryIconNumber + j + primaryIconNumber] = questionIcons[i][j];
			}
		}
		
		return icons;
	}
	
	/**
	 * Randomly shuffles the icons.
	 * @param symbols icons to be shuffled
	 */
	private void shuffle(Icon[] symbols){
		int length = symbols.length;
		int noOfShuffle = length*3;
		
		Icon iconTemp;
		
		RandomIntegerGenerator randomIntegerGenerator = new RandomIntegerGenerator();
		int randomNumber1=0, randomNumber2=0;
		for(int i=0; i<noOfShuffle; i++){
			randomNumber1 = randomIntegerGenerator.nextInt(length);
			randomNumber2 = randomIntegerGenerator.nextInt(length);
			
			iconTemp = symbols[randomNumber1];
			symbols[randomNumber1] = symbols[randomNumber2];
			symbols[randomNumber2] = iconTemp;
		}
	}

}
