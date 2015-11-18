/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 14-Jun-2014																							*
* Comment: 	*									
*************************************************************************************************************/

package tests.memoryTestLostSymbols.gui;


import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import commonOperations.randomValueMaker.operation.RandomIntegerGenerator;

import message.gui.Message;

import run.mainFrame.gui.BrainTestMainFrame;
import tests.memoryTestLostSymbols.operation.RandomIconMaker;

@SuppressWarnings("serial")
public class LostAnimals extends BrainTestMainFrame{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel[] jLabelSymbolView;
	private JLabel[] jLabelNumberView;
	private JTextField[] jTextFieldAnswers;
	
	//operational variables
	private int numberOfSymbols;
	private int rightAnswer;
	//messages
	
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public LostAnimals() {
		numberOfSymbols = 24;
		/*initialMessage="Do not press the button 'start' before you finish this instruction. Below, you will find 22 \n" +
				"different symbols in the empty boxes with an assigned alphabet under each symbol. Try \n" +
				"to remember the assigned number that belongs with each symbol. You will get 1 minute \n" +
				"to memorize them. Do not bother if you can not remember them all! after 1 minute the \n" +
				"symbols will be shuffled and you should write correct alphabet with the specific symbol.\n";*/
		
		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables, placing them all to specific space on the frame and adding action
	 * listener to them. Also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		//**
		// Initialization 																					#*******I*******#
		//**
		jLabelSymbolView = new JLabel[numberOfSymbols];
		jLabelNumberView = new JLabel[numberOfSymbols];
		jTextFieldAnswers = new JTextField[numberOfSymbols];
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		for(int i=0, x, y; i<numberOfSymbols; i++){
			x=(i%8)*82 + 28;
			y=(i/8)*155+85;
			
			jLabelSymbolView[i] = new JLabel();
			jLabelSymbolView[i].setBounds(x, y, 65, 65);
			jLabelSymbolView[i].setHorizontalAlignment(0);
			jLabelSymbolView[i].setVerticalAlignment(0);		
			jLabelSymbolView[i].setBorder((new LineBorder(new Color(105, 30, 30), 3)));
			
			jLabelNumberView[i] = new JLabel();
			jLabelNumberView[i].setSize(65, 35);
			jLabelNumberView[i].setLocation(x, y+63);
			jLabelNumberView[i].setFont(new java.awt.Font("Consolas", 0, 25));
			jLabelNumberView[i].setHorizontalAlignment(0);
			jLabelNumberView[i].setVerticalAlignment(0);		
			jLabelNumberView[i].setBorder((new LineBorder(new Color(105, 30, 30), 3)));
			
			jTextFieldAnswers[i] = new JTextField();
			jTextFieldAnswers[i].setSize(65, 35);
			jTextFieldAnswers[i].setLocation(900, 0);
			jTextFieldAnswers[i].setFont(new java.awt.Font("Consolas", 0, 25));
			jTextFieldAnswers[i].setHorizontalAlignment(0);
			jTextFieldAnswers[i].setBackground(new Color(230, 255, 242));
			jTextFieldAnswers[i].setBorder((new LineBorder(new Color(105, 30, 30), 3)));
		}
		
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		
		for(int i=0; i<numberOfSymbols; i++){
			jLabelMain.add(jLabelSymbolView[i]);
			jLabelMain.add(jLabelNumberView[i]);
			jLabelMain.add(jTextFieldAnswers[i]);
		}
		
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setTitle("Lost Symbols");
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	// End of Action Events 																				#________AE_______#
	
	//**
	// Run Method																							#*******R*******#
	//**
	@Override
	public void run() {
		try{
			//****Field Setup****////////////////////////////////////////////////////////////////////////////////////////
			gameState=IS_STARTED;
			
			char[] answer = new char[numberOfSymbols];
			Icon[] symbols = new RandomIconMaker().generateRandomIcon(numberOfSymbols);
			for(int i=0; i<numberOfSymbols; i++){
				answer[i] = (char)(97+i);
				jLabelNumberView[i].setText(answer[i]+"");
				jLabelSymbolView[i].setIcon(symbols[i]);
				Thread.sleep(30);
			}
		
			//****Field Show****/////////////////////////////////////////////////////////////////////////////////////////
			gameState = IS_SHOWING;
			
			int totalShowTime=3600, passedTime=0;
			while(passedTime<totalShowTime){
				if(gameState==IS_FINISHED){
			
					return ;
				}
				
				jButtonControl.setText((int)passedTime/100 + "." + passedTime%100);
				progressPanelAdvance(195*passedTime/totalShowTime);
				
				Thread.sleep(10);
				passedTime++;
			}
			//****Take Input****/////////////////////////////////////////////////////////////////////////////////////////
			gameState = IS_WAITING;
			
			for(int i=0; i<numberOfSymbols; i++){
				jTextFieldAnswers[i].setLocation(jLabelNumberView[i].getLocation());
				
				jLabelNumberView[i].setLocation(900, 0);
				Thread.sleep(15);
			}
			
			Thread.sleep(500);
			
			shuffle(answer, symbols);
			
			for(int i=numberOfSymbols-1; i>=0; i--){
				jLabelSymbolView[i].setIcon(symbols[i]);
				Thread.sleep(15);
			}
			
			Thread.sleep(500);
			
			jButtonControl.setText("Submit");
			progressPanelReinitialize();
			
			while(gameState==IS_WAITING){
				Thread.sleep(500);
			}
			
			//****Result Processing****///////////////////////////////////////////////////////////////////////////////////
			gameState = IS_PROCESSING;
			jButtonControl.setText("Time's up!");
			stopInput();

			for(int i=0; i<numberOfSymbols; i++){
				jLabelNumberView[i].setLocation((i%8)*82 + 28, (i/8)*155 + 181);
				jLabelNumberView[i].setText(answer[i]+"");
				
				if(jTextFieldAnswers[i].getText().equalsIgnoreCase(answer[i]+"")){			//right
					rightAnswer++;
					jTextFieldAnswers[i].setBackground(new java.awt.Color(140, 255, 80));
				}else if(!jTextFieldAnswers[i].getText().equals("")){						//wrong
					jTextFieldAnswers[i].setBackground(new java.awt.Color(255, 50, 50));
				}
				
				Thread.sleep(15);
			}
			
			Thread.sleep(1000);
			
			//****Result Show****/////////////////////////////////////////////////////////////////////////////////////////
			showResult();
			
			
		}catch (Exception e) {
			//do noting
			
		}finally{
			//****Game Finish****/////////////////////////////////////////////////////////////////////////////////////////
			gameState = IS_FINISHED;
			
			jButtonControl.setText("Restart");
			progressPanelReinitialize();
		}
	}
	//End of Run Method																						#_______R_______#

	
	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		LostAnimals gui = new LostAnimals();
		gui.setVisible(true);
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	
	private void shuffle(char[] answer, Icon[] symbols){
		int length = answer.length;
		
		char charTemp;
		Icon iconTemp;
		
		RandomIntegerGenerator randomIntegerGenerator = new RandomIntegerGenerator();
		int randomNumber1=0, randomNumber2=0;
		for(int i=0; i<50; i++){
			randomNumber1 = randomIntegerGenerator.nextInt(length);
			randomNumber2 = randomIntegerGenerator.nextInt(length);
			
			charTemp = answer[randomNumber1];
			answer[randomNumber1] = answer[randomNumber2];
			answer[randomNumber2] = charTemp;
			
			iconTemp = symbols[randomNumber1];
			symbols[randomNumber1] = symbols[randomNumber2];
			symbols[randomNumber2] = iconTemp;
		}
	}
	// End of Auxiliary Methods 																			#________AM_______#
	
	
	//**
	// Overridden Methods 																					#********OM*******#
	//**
	@Override
	protected void gameInitialize(){
		rightAnswer=0;
		
		for(int i=0, x, y; i<numberOfSymbols; i++){
			x=(i%8)*82 + 28;
			y=(i/8)*155+85+63;
			
			jLabelNumberView[i].setLocation(x, y);
			jLabelNumberView[i].setText("");
			
			jTextFieldAnswers[i].setLocation(900, 0);
			jTextFieldAnswers[i].setText("");
			jTextFieldAnswers[i].setBackground(new java.awt.Color(230, 255, 242));
			jTextFieldAnswers[i].setEditable(true);
			
			jLabelSymbolView[i].setIcon(null);
		}
	}
	
	@Override
	protected void stopInput(){
		for (int i=0; i<numberOfSymbols; i++) {
			jTextFieldAnswers[i].setEditable(false);
		}
	}
	
	@Override
	protected void showResult(){
		if(rightAnswer<=5){
			new Message("You got " + rightAnswer + " right. \n" +
					"Your memory is below average.", 1);
		}else if(rightAnswer<=8){
			new Message("You got " + rightAnswer + " right. \n" +
					"You have an average memory.", 1);
		}else if(rightAnswer<=11){
			new Message("You got " + rightAnswer + " right \n" +
					"Your memory is above average.", 1);
		}else if(rightAnswer<=15){
			new Message("You got " + rightAnswer + " right \n" +
					"You have a very high working memory. \n" +
					"Almost double from average.", 1);
		}else if(rightAnswer<=19){
			new Message("You got " + rightAnswer + " right \n" +
					"You have an excelent working memory. \n" +
					"Way above from average.", 1);
		}else{
			new Message("You got " + rightAnswer + " right \n" +
					"How did you do that!?!? \n" +
					"You have a super memory! WOW!!!", 1);
		}
	}
	// End of Overridden Methods 																			#________OM_______#
}


