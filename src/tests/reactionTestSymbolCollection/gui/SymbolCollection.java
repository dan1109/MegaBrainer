/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 14-Jun-2014																							*
* Comment: 	*
*************************************************************************************************************/

package tests.reactionTestSymbolCollection.gui;


import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.*;

import commonOperations.randomValueMaker.operation.RandomIntegerGenerator;

import tests.reactionTestSymbolCollection.operation.RandomSymbolGenerator;

import message.gui.Message;
import run.mainFrame.gui.BrainTestMainFrame;


@SuppressWarnings("serial")
public class SymbolCollection extends BrainTestMainFrame{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel jLabelSymbolView;
	private JButton[] jButtonAnswer;
	private JLabel jLabelResult;
	
	//operational variables
	private String aerrowKey;
	
	private int noOfCorrectAnswer, noOfWrongAnswer, noOfNoAnswer;
	private double totalReactionTimeOfCorrectAnswer, totalReactionTimeOfWrongAnswer;
	
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public SymbolCollection() {

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
		jLabelSymbolView = new JLabel();
		jButtonAnswer = new JButton[2];
		jLabelResult = new JLabel();
		
		//operational variables
		aerrowKey="";
		
		noOfCorrectAnswer=0;
		noOfWrongAnswer=0;
		noOfNoAnswer=0;
		totalReactionTimeOfCorrectAnswer=0;
		totalReactionTimeOfWrongAnswer=0;
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jLabelSymbolView.setBounds(WEIGTH/2-125, 100, 250, 250);
		jLabelSymbolView.setBorder((new LineBorder(new Color(100, 100, 250, 150), 3)));
		jLabelSymbolView.setFont(new Font("Consolas", 1, 140));
		jLabelSymbolView.setHorizontalAlignment(0);
		jLabelSymbolView.setVerticalAlignment(0);		
		
		for(int i=0; i<2; i++){
			jButtonAnswer[i] = new JButton();
			jButtonAnswer[i].setBounds(i*180 + (WEIGTH/2-180), 385, 180, 50);
			jButtonAnswer[i].setBackground(new Color(230, 255, 242));
			jButtonAnswer[i].setFont(new Font("Tahoma", 0, 25));
			jButtonAnswer[i].setFocusable(false);
			jButtonAnswer[i].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(37+i*2, 0), "AerrowKey"+i+"_pressed");
			jButtonAnswer[i].getActionMap().put("AerrowKey"+i+"_pressed", new AbstractAction() {
				public void actionPerformed(ActionEvent evt) {
					//jButtonAnswerActionPerformed(evt);
					JButton jButton = (JButton) evt.getSource();
					jButton.doClick();
				}
			});
			jButtonAnswer[i].addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonAnswerActionPerformed(evt);
				}
			});
		}
		jButtonAnswer[0].setText(" Numeral");
		jButtonAnswer[0].setFocusable(false);
		jButtonAnswer[0].setIcon(new ImageIcon(getClass()
				.getResource("/tests/reactionTestSymbolCollection/pictures/Left.png")));
		jButtonAnswer[1].setIconTextGap(-155);
		jButtonAnswer[1].setText(" Alphabet");
		jButtonAnswer[1].setFocusable(false);
		jButtonAnswer[1].setIcon(new ImageIcon(getClass()
				.getResource("/tests/reactionTestSymbolCollection/pictures/Right.png")));
		
		
		jLabelResult.setBounds(WEIGTH/2-125, 450, 250, 50);
		jLabelResult.setBorder((new LineBorder(new java.awt.Color(100, 100, 250, 150), 3)));
		jLabelResult.setHorizontalAlignment(0);
		jLabelResult.setVerticalAlignment(0);		
		jLabelResult.setFont(new java.awt.Font("Consolas", 0, 30));
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		jLabelMain.add(jLabelSymbolView);
		
		for(int i=0; i<2; i++){
			jLabelMain.add(jButtonAnswer[i]);
		}
		jLabelMain.add(jLabelResult);
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setTitle("Symbol Collection");
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	private void jButtonAnswerActionPerformed(ActionEvent evt){
		if(jButtonAnswer[0].isFocusable()){
			gameState = IS_STARTED;
			if(evt.getSource()==jButtonAnswer[0]){
				aerrowKey="L";
				//jButtonAnswer[0].doClick();
			}else{
				aerrowKey="R";
				//jButtonAnswer[1].doClick();
			}
//			System.out.println("OK");
		}
	}
	// End of Action Events 																				#________AE_______#
	
	//**
	// Run Method																							#*******R*******#
	//**
	public void run() {

		try{
			//****Field Setup****////////////////////////////////////////////////////////////////////////////////////////
			gameState=IS_STARTED;
			
			int numberOfSymbols=30;
			int symbolShowTime=200;
			int totalShowTime=symbolShowTime*10;
			
			Boolean[] mainAnswerIsAlphabet = new Boolean[numberOfSymbols];
			String[] symbols = new RandomSymbolGenerator().makeRandomSymbol(mainAnswerIsAlphabet);
			
			Thread.sleep(1000);	//wait some time
			
			int chance=0;
			for(chance=0; chance<numberOfSymbols; chance++){
				//****Field Show****/////////////////////////////////////////////////////////////////////////////////////
				gameState = IS_SHOWING;
				
				progressPanelAdvance(195*(chance+1)/numberOfSymbols);
				
				//**distraction**//
				for(int i=new RandomIntegerGenerator().nextInt(8); i>0; i--){
					jLabelSymbolView.setText(".");
					Thread.sleep(symbolShowTime);
					jLabelSymbolView.setText("");
					
					Thread.sleep(symbolShowTime*5);
					
					if(gameState==IS_FINISHED){	//if stop button is pressed
						return ;
					}
				}
				
				//**show symbol**//
				jLabelSymbolView.setText(symbols[chance]);
				Thread.sleep(symbolShowTime);
				jLabelSymbolView.setText("");
				
				//****Take Input****/////////////////////////////////////////////////////////////////////////////////////
				gameState = IS_WAITING;
				
				jButtonAnswer[0].setFocusable(true);
				jButtonAnswer[1].setFocusable(true);
				
				int passedTime=0;
				while(passedTime<totalShowTime && gameState == IS_WAITING){
					if(gameState == IS_FINISHED){
						stopInput();
						return ;
					}
					
					Thread.sleep(5);
					passedTime += 5;
				}
				
				//****Result Processing****//////////////////////////////////////////////////////////////////////////////
				gameState = IS_PROCESSING;
				stopInput();
				
				if((mainAnswerIsAlphabet[chance]&&aerrowKey.equals("R")) ||
						(!mainAnswerIsAlphabet[chance] && aerrowKey.equals("L"))){
					
					noOfCorrectAnswer++;
					totalReactionTimeOfCorrectAnswer += passedTime;
					
					if(symbolShowTime>100){
						symbolShowTime -= 20;
					}else if(symbolShowTime>50){
						symbolShowTime -= 10;
					}else if(symbolShowTime>40){
						symbolShowTime -= 5;
					}
					
	//				totalShowTime = symbolShowTime*10;	//disabled
				}else if(aerrowKey.equals("")){
					noOfNoAnswer++;
				}else{
					noOfWrongAnswer++;
					totalReactionTimeOfWrongAnswer += passedTime;
				}
				
				//**Update**//
				jLabelResult.setText((chance+1) + " : " + noOfCorrectAnswer);
				
				if(gameState==IS_FINISHED){	//stop the game if the user wants
					return;
				}
				
				//**Reinitialize**//
				aerrowKey="";
				Thread.sleep(300);	//give a rest
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
		SymbolCollection gui = new SymbolCollection();
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
		aerrowKey="";
		
		noOfCorrectAnswer=0;
		noOfWrongAnswer=0;
		noOfNoAnswer=0;
		totalReactionTimeOfCorrectAnswer=0;
		totalReactionTimeOfWrongAnswer=0;
		
		jLabelResult.setText("");
	}
	
	@Override
	protected void stopInput(){
		jButtonAnswer[0].setFocusable(false);
		jButtonAnswer[1].setFocusable(false);
	}
	
	@Override
	protected void showResult(){
		new Message("Correct Answer- " + noOfCorrectAnswer + ", Avg. Time- " +
				(float)(totalReactionTimeOfCorrectAnswer/noOfCorrectAnswer) + "ms," +
				"\nWrong Answer- " + noOfWrongAnswer + ", Avg. Time- " +
				(float)(totalReactionTimeOfWrongAnswer/noOfWrongAnswer) + "ms," +
				"\nNo Answer- " + noOfNoAnswer + ".", 0);
	}
	// End of Overridden Methods 																			#________OM_______#

}
