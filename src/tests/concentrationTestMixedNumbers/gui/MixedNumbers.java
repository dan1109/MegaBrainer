/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 14-Jun-2014																							*
* Comment: This is a test for concentration and detecting objects quickly.									*
*************************************************************************************************************/

package tests.concentrationTestMixedNumbers.gui;


import java.awt.event.ActionEvent;
import javax.swing.*;

import tests.concentrationTestMixedNumbers.operation.RandomCharacterMaker;

import message.gui.Message;
import run.mainFrame.gui.BrainTestMainFrame;

@SuppressWarnings("serial")
public class MixedNumbers extends BrainTestMainFrame{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel jLabelCharView;
	private JLabel[] jLabelAnswer;
	private JLabel[] jLabelOutcome;
	private JLabel jLabelResult;
	private JButton[] jButtonNumbers;
	
	//operational variables
	private int result=0;
	private int noOfChances = 35;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public MixedNumbers() {

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
		jLabelCharView = new JLabel();
		jLabelAnswer = new JLabel[2];
		jLabelOutcome = new JLabel[2];
		jLabelResult = new JLabel();
		jButtonNumbers = new JButton[10];
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jLabelCharView.setBounds(WEIGTH/2-100, 170, 200, 200);
		jLabelCharView.setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 2)));
		jLabelCharView.setHorizontalAlignment(0);
		jLabelCharView.setVerticalAlignment(0);		
		jLabelCharView.setFont(new java.awt.Font("Consolas", 0, 160));
		
		for(int i=0; i<2; i++){
			jLabelAnswer[i] = new JLabel();
			jLabelAnswer[i].setBounds(i*99 + (WEIGTH/2-100), 390, 101, 60);
			jLabelAnswer[i].setFont(new java.awt.Font("Consolas", 0, 50));
			jLabelAnswer[i].setHorizontalAlignment(0);
			jLabelAnswer[i].setVerticalAlignment(0);		
			jLabelAnswer[i].setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 2)));
		}
		for(int i=0; i<2; i++){
			jLabelOutcome[i] = new JLabel();
			jLabelOutcome[i].setBounds(0, 0, 100, 60);
			jLabelOutcome[i].setHorizontalAlignment(0);
			jLabelOutcome[i].setVerticalAlignment(0);		
		}
		
		jLabelResult.setBounds(WEIGTH/2-100, 90, 200, 60);
		jLabelResult.setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 2)));
		jLabelResult.setHorizontalAlignment(0);
		jLabelResult.setVerticalAlignment(0);		
		jLabelResult.setFont(new java.awt.Font("Consolas", 0, 50));
		
		for(int i=0; i<10; i++){
			jButtonNumbers[i] = new JButton();
			jButtonNumbers[i].setBounds(i*50+100, HEIGHT-115, 50, 45);
			jButtonNumbers[i].setText(""+i);
			jButtonNumbers[i].setBackground(new java.awt.Color(230, 255, 242));
			jButtonNumbers[i].setHorizontalAlignment(0);
			jButtonNumbers[i].setVerticalAlignment(0);		
			jButtonNumbers[i].setFont(new java.awt.Font("Arial", 0, 30));
			jButtonNumbers[i].getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(javax.swing.KeyStroke.getKeyStroke(48+i, 0), "NumberButton"+i+"_pressed");
			jButtonNumbers[i].getActionMap().put("NumberButton"+i+"_pressed", new AbstractAction() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					//jButtonNumbersActionPerformed(evt);
					JButton jButton = (JButton) evt.getSource();
					jButton.doClick();
				}
			});
			jButtonNumbers[i].addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					jButtonNumbersActionPerformed(evt);
				}
			});
			jButtonNumbers[i].setFocusable(false);
		}
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		jLabelMain.add(jLabelCharView);
		
		for(int i=0; i<2; i++){
			jLabelMain.add(jLabelAnswer[i]);
			jLabelAnswer[i].add(jLabelOutcome[i]);
		}
		jLabelMain.add(jLabelResult);
		
		for(int i=0; i<10; i++){
			jLabelMain.add(jButtonNumbers[i]);
		}
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setTitle("Mixed Numbers");
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	private void jButtonNumbersActionPerformed(ActionEvent evt){
		if(jButtonNumbers[0].isFocusable()){
			if(jLabelAnswer[0].getText()==""){
				jLabelAnswer[0].setText(evt.getActionCommand());
			}else{
				jLabelAnswer[1].setText(evt.getActionCommand());
				gameState=IS_STARTED;
			}
			
//			System.out.println("OK");
		}
	}
	// End of Action Events 																				#________AE_______#
	
	//**
	// Run Method																							#*******R*******#
	//**
	@Override
	public void run() {

		try{
			//****Field Setup****////////////////////////////////////////////////////////////////////////////////////////
			gameState=IS_STARTED;
			
			RandomCharacterMaker randomCharacterMaker= new RandomCharacterMaker();
			int noOfChars = 14;
			char[] charSet = new char[noOfChars];
			char[] ch = new char[2];
			boolean[] outcome = new boolean[2];
			
			int timeGap = 300;
			int chance = 0;
			
			jLabelResult.setText(0+"/"+0);
			
			Thread.sleep(1000);
			while(chance<noOfChances){
				//****Field Show****////////////////////////////////////////////////////////////////////////////////////
				gameState = IS_SHOWING;
				
				progressPanelAdvance(195*(chance+1)/noOfChances);
				charSet=randomCharacterMaker.creatRandomNumeralInChar(noOfChars);
				Thread.sleep(500);
				
				for(int i=0; i<noOfChars; i++){		//show numerals
					jLabelCharView.setText(""+charSet[i]);
					Thread.sleep(timeGap);
					//System.out.print(charSet[i]);///test
					
					if(gameState==IS_FINISHED){	//stop the game if the user wants
						jLabelCharView.setText("");
						
						return;
					}
				}
				//System.out.println();///test
				jLabelCharView.setText("");
			
				//****Take Input****//////////////////////////////////////////////////////////////////////////////////////
				gameState = IS_WAITING;
				
				for(int i=0; i<10; i++){	//enabling the buttons for input
					jButtonNumbers[i].setFocusable(true);
				}
				gameState=IS_WAITING;	//wait for user input
				
				while(gameState==IS_WAITING){
					Thread.sleep(10);
				}
				
				for(int i=0; i<10; i++){	//unable the buttons
					jButtonNumbers[i].setFocusable(false);
				}
			
				if(gameState==IS_FINISHED){	//stop the game if the user wants
					return;
				}
			
				//****Result Processing****///////////////////////////////////////////////////////////////////////////////
				gameState = IS_PROCESSING;
				
				ch[0]=jLabelAnswer[0].getText().charAt(0);
				ch[1]=jLabelAnswer[1].getText().charAt(0);
				
				outcome = examine(charSet, ch);
				
				Thread.sleep(200);
				
				for(int i=0; i<2; i++){
					if(outcome[i]){
						jLabelOutcome[i].setIcon(new javax.swing.ImageIcon(getClass().
								getResource("/tests/concentrationTestMixedNumbers/pictures/Right.png")));
						result++;
					}else{
						jLabelOutcome[i].setIcon(new javax.swing.ImageIcon(getClass().
								getResource("/tests/concentrationTestMixedNumbers/pictures/Wrong.png")));
					}
				}
				
				Thread.sleep(500);
				
				jLabelResult.setText(result + "/" + (chance*2+2));
				
				//**Update**//
				if(outcome[0] && outcome[1]){
					if(timeGap>150){
						timeGap = timeGap-30;
					}else if(timeGap>100){
						timeGap = timeGap-10;
					}else if(timeGap>70){
						timeGap = timeGap-5;
					}else if(timeGap>50){
						timeGap = timeGap-2;
					}
				}
				
				Thread.sleep(1500);
				
				if(gameState==IS_FINISHED){	//stop the game if the user wants
					return;
				}
				
				//**initialization**//
				for(int i=0; i<2; i++){
					jLabelAnswer[i].setText("");
					jLabelOutcome[i].setIcon(null);
				}
				
				chance++;
			}
			
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
		MixedNumbers gui = new MixedNumbers();
		gui.setVisible(true);
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	private boolean[] examine(char[] charSet, char[] chars){
		int length = charSet.length;
		boolean[] outcome = {false, false};
		
		for(int i=0; i<length; i++){
			if(charSet[i]==chars[0]){
				charSet[i]='A';
				outcome[0]=true;
				break;
			}
		}
		for(int i=0; i<length; i++){
			if(charSet[i]==chars[1]){
				outcome[1]=true;
				break;
			}
		}
		
		return outcome;
	}
	// End of Auxiliary Methods 																			#________AM_______#


	//**
	// Overridden Methods 																					#********OM*******#
	//**
	@Override
	protected void gameInitialize(){
		result = 0;
		
		jLabelResult.setText("");
		jLabelCharView.setText("");
		
		for(int i=0; i<2; i++){
			jLabelAnswer[i].setText("");
			jLabelOutcome[i].setIcon(null);
		}
	}
	
	@Override
	protected void stopInput(){
		for(int i=0; i<10; i++){	//enabling the buttons for input
			jButtonNumbers[i].setFocusable(false);
		}
	}
	
	@Override
	protected void showResult(){

		float score = (float)((double)result/2.00/(double)noOfChances*100.00);
		
		String comment="";
		
		if(score>95){
			comment="Your score is excellent!!! \n How did you do that?";
		}else if(score>90){
			comment="Your score is excellent!!!";
		}else if(score>80){
			comment="Your score is very very high!!";
		}else if(score>70){
			comment="Your score is high!";
		}else if(score>60){
			comment="Your score is good.";
		}else if(score>50){
			comment="Your score is not bad.";
		}else if(score>40){
			comment="Your score is low.";
		}else if(score>30){
			comment="Your score is very low.";
		}else{
			comment="Are you joking? \nYou did not concentrate at all.";
		}
		
		new Message("You got " + result + " right in "+ (2*noOfChances) +".\nYou score is: "+ score +"%. \n" + comment, 0);
	
	}
	// End of Overridden Methods 																			#________OM_______#

}


