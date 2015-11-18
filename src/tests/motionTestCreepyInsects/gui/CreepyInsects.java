/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 01-Jul-2014																							*
* Comment: 	*									
*************************************************************************************************************/

package tests.motionTestCreepyInsects.gui;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import commonOperations.randomValueMaker.operation.RandomIntegerGenerator;

import run.mainFrame.gui.BrainTestMainFrame;
import message.gui.Message;


@SuppressWarnings("serial")
public class CreepyInsects extends BrainTestMainFrame{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel jLabelField;
	private BallSymbol[] jLabelSymbolView;
	
	//operational variables
	private int numberOfObjects;
	private int numberOfTrackingObjects;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public CreepyInsects() {
		numberOfObjects=10;
		numberOfTrackingObjects=2;	//initially two objects are marked to keep track
		
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
		jLabelField = new JLabel();
		jLabelSymbolView = new BallSymbol[numberOfObjects];
		
		numberOfTrackingObjects=2;
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jLabelField.setBounds(20, 70, 655, 450);
		jLabelField.setLayout(null);
		jLabelField.setBorder((new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
		
		int randomIconNumber = new RandomIntegerGenerator().nextInt(5);	//a variable randomly decides an icon
		for(int i=0; i<numberOfObjects; i++){
			jLabelSymbolView[i] = new BallSymbol();
			jLabelSymbolView[i].setLocation(i*50+80, (i%3)*100+100);
			jLabelSymbolView[i].setName(i+"");
			jLabelSymbolView[i].setBorder(null);
			jLabelSymbolView[i].setFocusable(true);
			jLabelSymbolView[i].setIcon(randomIconNumber);
			jLabelSymbolView[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					jLabelSymbolViewActionPerformed(arg0);
	            }
	        });
		}
		jLabelSymbolView[0].setBorder((new javax.swing.border.LineBorder(new java.awt.Color(115, 0, 0), 2)));
		jLabelSymbolView[numberOfObjects-1].setBorder((new javax.swing.border.LineBorder(new java.awt.Color(115, 0, 0), 2)));
		
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		jLabelMain.add(jLabelField);
		
		for(int i=0; i<numberOfObjects; i++){
			jLabelField.add(jLabelSymbolView[i]);
		}
		
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setTitle("Creepy Insects");
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	private void jLabelSymbolViewActionPerformed(MouseEvent arg0){
		JLabel jLabelTemp = (JLabel) arg0.getSource();
		
		if(jLabelTemp.isFocusable() && numberOfTrackingObjects<7){
			if(jLabelTemp.getBorder() == null){
				jLabelTemp.setBorder((new javax.swing.border.LineBorder(new java.awt.Color(115, 0, 0), 2)));
				if(jButtonControl.getText().equals("Start")){
					numberOfTrackingObjects++;
				}
			}else{
				jLabelTemp.setBorder(null);
				if(jButtonControl.getText().equals("Start")){
					numberOfTrackingObjects--;
				}
			}
		}
	}
	// End of Action Events 																				#________AE_______#
	
	//**
	// Run Method																							#*******R*******#
	//**
	@Override
	public void run() {
		
		int answer[] = new int[numberOfTrackingObjects];
		
		for(int i=0, j=0; i<numberOfObjects; i++){			//get track of the objects & initialize them
			jLabelSymbolView[i].setFocusable(false);
			
			if(!(jLabelSymbolView[i].getBorder() == null)){
				jLabelSymbolView[i].setBorder(null);
				answer[j] = i;
				j++;
			}
		}
		
		try {
			Thread.sleep(1000);
		
			for(int i=0; i<numberOfObjects; i++){			//starting the motion
				jLabelSymbolView[i].start();
				Thread.sleep(50);
			}
			
			Thread.sleep(7000);								//wait till all stops
			
			for(int i=0; i<numberOfObjects; i++){			//making them able to take answer
				jLabelSymbolView[i].setFocusable(true);
			}
			jButtonControl.setText("Submit");
			
			while(!jButtonControl.getText().equals("Restart")){
				Thread.sleep(500);
			}
			
			for(int i=0; i<numberOfObjects; i++){			//stop takeing answer
				jLabelSymbolView[i].setFocusable(false);
			}
			
			int rightAnswer=0;
			int wrongAnswer=0;
			for(int i=0; i<numberOfObjects; i++){		//process result
				if(!(jLabelSymbolView[i].getBorder() == null)){
					jLabelSymbolView[i].setBorder(null);
					
					for(int j=0; j<numberOfTrackingObjects; j++){
						if(answer[j]==i){
							rightAnswer++;
							break;
						}
					}
					
					wrongAnswer++;
				}
			}
			wrongAnswer = wrongAnswer - rightAnswer;
			
			
			String message="";										//feedback message
			int messageType=0;
			int score = rightAnswer-wrongAnswer;
			
			if(score<0){
				message = "What was that? Take as much as you can handel.";
				messageType = 420;
			}else if(score<2){
				message = "Try harder. Your ability is bellow average.";
				messageType = 210;
			}else if(score<3){
				message = "Not bad, take more this time.";
				messageType = 0;
			}else if(score<4){
				message = "OK, your motion detection ability is average.";
				messageType = 0;
			}else if(score<5){
				message = "Good, your ability is above average.";
				messageType = 1;
			}else if(score<6){
				message = "Excellent! Your ability is very high.";
				messageType = 1;
			}else{
				message = "Wow!!! You ability is at super level";
				messageType = 1;
			}
			
			new Message("Right: " + rightAnswer + ",   Wrong: " + wrongAnswer + ",   Undetect: " + 
					(numberOfTrackingObjects-rightAnswer) + ".\nYour score is: " + (score) +
					".\n" + message, messageType);
			
		} catch (Exception e) {
			new Message("Error", 420);
			//e.printStackTrace();
		}finally{
			for(int i=0; i<numberOfObjects; i++){			//making them able to take input
				jLabelSymbolView[i].setFocusable(true);
				jLabelSymbolView[i].setBorder(null);
			}
			
			
			numberOfTrackingObjects=2;
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
		CreepyInsects gui = new CreepyInsects();
		gui.setVisible(true);
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	
	// End of Auxiliary Methods 																			#________AM_______#
}

