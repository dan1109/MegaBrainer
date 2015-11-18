/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 14-Jun-2014																							*
* Comment: 	*									
* 		*
* 		*
*************************************************************************************************************/

package tests.perceptionTestAlphanumericDisorder.gui;


import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import tests.perceptionTestAlphanumericDisorder.operation.RandomStringGenerator;

import message.gui.Message;
import run.mainFrame.gui.BrainTestMainFrame;


@SuppressWarnings("serial")
public class AlphanumericDisorder extends BrainTestMainFrame implements Runnable{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel[] jLabelColumnName;
	private JLabel[] jLabelQuestionView;
	private JTextField[][] jTextFieldAnswers;
	
	//operational variables
	private int noOfBoxes = 12;
	private int rightAnswer=0;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public AlphanumericDisorder() {
		
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
		jLabelColumnName = new JLabel[2];
		jLabelQuestionView = new JLabel[noOfBoxes];
		jTextFieldAnswers = new JTextField[2][noOfBoxes];
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		for(int i=0; i<2; i++){
			jLabelColumnName[i] = new JLabel();
			jLabelColumnName[i].setBounds(i*65+560, 70, 45, 30);
			jLabelColumnName[i].setFont(new Font("Arial", 0, 20));
			jLabelColumnName[i].setHorizontalAlignment(0);
			jLabelColumnName[i].setVerticalAlignment(0);		
			jLabelColumnName[i].setBorder((new SoftBevelBorder(BevelBorder.LOWERED)));
		}
		jLabelColumnName[0].setText("Nu");
		jLabelColumnName[1].setText("Al");
		
		
		for(int i=0, y; i<noOfBoxes; i++){
			y = i*35+110;
			
			jLabelQuestionView[i] = new JLabel();
			jLabelQuestionView[i].setBounds(25, y, 450, 30);
			jLabelQuestionView[i].setFont(new Font("Consolas", 1, 16));
			jLabelQuestionView[i].setHorizontalAlignment(0);
			jLabelQuestionView[i].setVerticalAlignment(0);		
			jLabelQuestionView[i].setBorder((new SoftBevelBorder(BevelBorder.LOWERED)));
			
			jTextFieldAnswers[0][i] = new JTextField();
			jTextFieldAnswers[0][i].setSize(45, 30);
			jTextFieldAnswers[0][i].setLocation(560, y);
			jTextFieldAnswers[0][i].setFont(new Font("Consolas", 0, 20));
			jTextFieldAnswers[0][i].setHorizontalAlignment(0);
			jTextFieldAnswers[0][i].setEditable(false);
			jTextFieldAnswers[0][i].setBackground(new Color(230, 255, 242));
			jTextFieldAnswers[0][i].setBorder((new SoftBevelBorder(BevelBorder.LOWERED)));
			
			jTextFieldAnswers[1][i] = new JTextField();
			jTextFieldAnswers[1][i].setSize(45, 30);
			jTextFieldAnswers[1][i].setLocation(625, y);
			jTextFieldAnswers[1][i].setFont(new Font("Consolas", 0, 20));
			jTextFieldAnswers[1][i].setHorizontalAlignment(0);
			jTextFieldAnswers[1][i].setEditable(false);
			jTextFieldAnswers[1][i].setBackground(new Color(230, 255, 242));
			jTextFieldAnswers[1][i].setBorder((new SoftBevelBorder(BevelBorder.LOWERED)));
		}
		
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		jLabelMain.add(jLabelColumnName[0]);
		jLabelMain.add(jLabelColumnName[1]);
		
		for(int i=0; i<noOfBoxes; i++){
			jLabelMain.add(jLabelQuestionView[i]);
			jLabelMain.add(jTextFieldAnswers[0][i]);
			jLabelMain.add(jTextFieldAnswers[1][i]);
		}
		
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setTitle("Alphanumeric Disorder");
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
			
			char[][] answers = new char[2][noOfBoxes];
			
			String[] charSequence = new RandomStringGenerator().makeRandomString(answers[0], answers[1]);
			
			for(int i=0; i<noOfBoxes; i++){
				jLabelQuestionView[i].setText(charSequence[i]);
				Thread.sleep(30);
			}
			
			//****Field Show****/////////////////////////////////////////////////////////////////////////////////////////
			gameState = IS_SHOWING;
			
			//****Take Input****/////////////////////////////////////////////////////////////////////////////////////////
			gameState = IS_WAITING;
			
			for(int i=0; i<noOfBoxes; i++){
				jTextFieldAnswers[0][i].setEditable(true);
				jTextFieldAnswers[1][i].setEditable(true);
			}
			
			int totalShowTime=6000, passedTime=0;
			while(passedTime<totalShowTime){
				if(gameState == IS_FINISHED){
					stopInput();
					
					return ;
				}
				
				jButtonControl.setText((int)passedTime/100 + "." + passedTime%100);
				progressPanelAdvance(195*passedTime/totalShowTime);
				
				Thread.sleep(10);
				passedTime++;
			}
			
			//****Result Processing****///////////////////////////////////////////////////////////////////////////////////
			gameState = IS_PROCESSING;
			jButtonControl.setText("Time's up!");
			stopInput();
			
			for(int i=0; i<noOfBoxes; i++){
				if(jTextFieldAnswers[0][i].getText().equals(answers[0][i]+"")){		//right
					rightAnswer++;
					jTextFieldAnswers[0][i].setBackground(new Color(140, 255, 80));
				}else if(!jTextFieldAnswers[0][i].getText().equals("")){			//wrong
					jTextFieldAnswers[0][i].setBackground(new Color(255, 50, 50));
				}
				
				if(jTextFieldAnswers[1][i].getText().equalsIgnoreCase(answers[1][i]+"")){
					rightAnswer++;
					jTextFieldAnswers[1][i].setBackground(new Color(140, 255, 80));
				}else if(!jTextFieldAnswers[1][i].getText().equals("")){
					jTextFieldAnswers[1][i].setBackground(new Color(255, 50, 50));
				}
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
		AlphanumericDisorder gui = new AlphanumericDisorder();
		gui.setVisible(true);
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	// End of Auxiliary Methods 																			#________AM_______#

	//**
	// Overridden Methods 																					#********OM*******#
	//**
	@Override
	protected void gameInitialize(){
		for(int i=0; i<noOfBoxes; i++){
			rightAnswer=0;
			jLabelQuestionView[i].setText("");
			
			jTextFieldAnswers[0][i].setText("");
			jTextFieldAnswers[0][i].setBackground(new Color(230, 255, 242));
			jTextFieldAnswers[1][i].setText("");
			jTextFieldAnswers[1][i].setBackground(new Color(230, 255, 242));
		}
	}
	
	@Override
	protected void stopInput(){
		for(int i=0; i<noOfBoxes; i++){
			jTextFieldAnswers[0][i].setEditable(false);
			jTextFieldAnswers[1][i].setEditable(false);
		}
	}
	
	@Override
	protected void showResult(){
		if(rightAnswer<=2){
			new Message("You got " + rightAnswer + " right. \n" +
					"Did you really try?", 1);
		}else if(rightAnswer<=6){
			new Message("You got " + rightAnswer + " right. \n" +
					"Your perception is below average.", 1);
		}else if(rightAnswer<=10){
			new Message("You got " + rightAnswer + " right. \n" +
					"You have an average perception.", 1);
		}else if(rightAnswer<=14){
			new Message("You got " + rightAnswer + " right \n" +
					"Your perception is above average.", 1);
		}else if(rightAnswer<=18){
			new Message("You got " + rightAnswer + " right \n" +
					"You have a very high perception. \n" +
					"Almost double from average.", 1);
		}else if(rightAnswer<=20){
			new Message("You got " + rightAnswer + " right \n" +
					"You have an excelent working perception. \n" +
					"Way above from average.", 1);
		}else{
			new Message("You got " + rightAnswer + " right \n" +
					"How did you do that!?!? \n" +
					"You have a super perception! WOW!!!", 1);
		}
	}
	// End of Overridden Methods 																			#________OM_______#
}


