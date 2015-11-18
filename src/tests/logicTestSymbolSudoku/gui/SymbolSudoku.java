/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 27-Jun-2014																							*
* Comment: 	*									
*************************************************************************************************************/

package tests.logicTestSymbolSudoku.gui;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.*;

import tests.logicTestSymbolSudoku.operation.RandomIconMaker;

import message.gui.Message;
import run.mainFrame.gui.BrainTestMainFrame;

@SuppressWarnings("serial")
public class SymbolSudoku extends BrainTestMainFrame{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel jLabelSymbolContainer;
	private JPanel[] jPanelQuestionSymbol;
	private JLabel[] jLabelQuestionSymbol;
	private JLabel[] jLabelOptionSymbol;
	
	//operational variables
	private int rightAnswer;
	private int wrongAnswer;
	private int numberOfSymbols;
	private int numberOfBoxs;
	
	private int selectedQuestionSymbolNo;
	private int selectedOptionSymbolNo;
	// End of Variable Declaration 																			#_______D_______#

	
	
	/***##Constructor##***/
	public SymbolSudoku() {
		numberOfSymbols=6;
		numberOfBoxs=numberOfSymbols*numberOfSymbols;
		selectedQuestionSymbolNo=-1;
		selectedOptionSymbolNo=-1;
		
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
		jLabelSymbolContainer = new JLabel();
		jPanelQuestionSymbol = new JPanel[numberOfBoxs];
		jLabelQuestionSymbol = new JLabel[numberOfBoxs];
		jLabelOptionSymbol = new JLabel[numberOfSymbols];
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jLabelSymbolContainer.setBounds(180, 100, 334, 334);
		jLabelSymbolContainer.setBorder((new LineBorder(new Color(105, 30, 30), 3)));
		
		for(int i=0; i<numberOfBoxs; i++){
			jPanelQuestionSymbol[i] = new JPanel();
			jPanelQuestionSymbol[i].setBounds((i/6)*55+2, (i%6)*55+2, 55, 55);
			jPanelQuestionSymbol[i].setLayout(null);
			jPanelQuestionSymbol[i].setBackground(new Color(230, 255, 242));
		}
		
		for(int i=0; i<numberOfBoxs; i++){
			jLabelQuestionSymbol[i] = new JLabel();
			jLabelQuestionSymbol[i].setBounds(0, 0, 55, 55);
			jLabelQuestionSymbol[i].setHorizontalAlignment(0);
			jLabelQuestionSymbol[i].setVerticalAlignment(0);		
			jLabelQuestionSymbol[i].setBorder((new LineBorder(new Color(105, 30, 30), 2)));
			jLabelQuestionSymbol[i].setFocusable(false);
			jLabelQuestionSymbol[i].setName(i+"");
			jLabelQuestionSymbol[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					jLabelQuestionSymbolActionPerformed(arg0);
	            }
	        });
		}
		
		
		for(int i=0; i<numberOfSymbols; i++){
			jLabelOptionSymbol[i] = new JLabel();	
			jLabelOptionSymbol[i].setSize(55, 55);
			jLabelOptionSymbol[i].setHorizontalAlignment(0);
			jLabelOptionSymbol[i].setVerticalAlignment(0);		
			jLabelOptionSymbol[i].setBorder((new LineBorder(new Color(105, 30, 30), 1)));
			jLabelOptionSymbol[i].setFocusable(false);
			jLabelOptionSymbol[i].setName(i+"");
			jLabelOptionSymbol[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					jLabelOptionSymbolActionPerformed(arg0);
	            }
	        });
		}
		jLabelOptionSymbol[0].setLocation(65, 300);
		jLabelOptionSymbol[1].setLocation(575, 300);
		jLabelOptionSymbol[2].setLocation(40, 370);
		jLabelOptionSymbol[3].setLocation(600, 370);
		jLabelOptionSymbol[4].setLocation(85, 440);
		jLabelOptionSymbol[5].setLocation(555, 440);
		
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		jLabelMain.add(jLabelSymbolContainer);
		
		for(int i=0; i<numberOfBoxs; i++){
			jLabelSymbolContainer.add(jPanelQuestionSymbol[i]);
			jPanelQuestionSymbol[i].add(jLabelQuestionSymbol[i]);
			
		}
		
		for(int i=0; i<numberOfSymbols; i++){
			jLabelMain.add(jLabelOptionSymbol[i]);
		}
		
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setTitle("Symbol Sudoku");
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	private void jLabelQuestionSymbolActionPerformed(MouseEvent arg){
		JLabel jLabelTemp = (JLabel) arg.getSource();
		
		if(jLabelTemp.isFocusable()){
			if(selectedOptionSymbolNo>=0){
				jLabelOptionSymbol[selectedOptionSymbolNo].setBorder((new LineBorder(new Color(105, 30, 30), 1)));
				
				jLabelTemp.setIcon(jLabelOptionSymbol[selectedOptionSymbolNo].getIcon());
				selectedOptionSymbolNo=-1;
				
			}else{
				for(int i=0; i<numberOfBoxs; i++){
					jLabelQuestionSymbol[i].setBorder((new LineBorder(new Color(105, 30, 30), 2)));
				}
				
				jLabelTemp.setBorder((new LineBorder(new Color(255, 100, 100), 3)));
				selectedQuestionSymbolNo = Integer.parseInt(jLabelTemp.getName());
			}
		}
	}
	
	private void jLabelOptionSymbolActionPerformed(MouseEvent arg){
		JLabel jLabelTemp = (JLabel) arg.getSource();
		
		if(jLabelTemp.isFocusable()){
			if(selectedQuestionSymbolNo>=0){
				jLabelQuestionSymbol[selectedQuestionSymbolNo].setBorder((new LineBorder(new Color(105, 30, 30), 2)));
				
				jLabelQuestionSymbol[selectedQuestionSymbolNo].setIcon(jLabelTemp.getIcon());
				selectedQuestionSymbolNo=-1;
				
			}else{
				for(int i=0; i<numberOfSymbols; i++){
					jLabelOptionSymbol[i].setBorder((new LineBorder(new Color(105, 30, 30), 1)));
				}
				
				jLabelTemp.setBorder((new LineBorder(new Color(255, 100, 100), 2)));
				selectedOptionSymbolNo = Integer.parseInt(jLabelTemp.getName());
			}
		}
	}
	// End of Action Events 																				#________AE_______#
	
	//**
	// Run Method																							#********R********#
	//**
	@Override
	public void run() {
		try{
			//****Field Setup****////////////////////////////////////////////////////////////////////////////////////////
			gameState=IS_STARTED;
			
			Icon[] icons = new RandomIconMaker().generateRandomIcon(numberOfSymbols);
			Icon[] answerIcons = new Icon[4*(numberOfSymbols-3)];
		
			for(int i=0, j=0; i<numberOfBoxs; i++){
				if(i==7 || i==8 || i==9 || i==10 || i==13 || i==16 || i==19 || i==22 || i==25 || i==26 || i==27 || i==28){
					jLabelQuestionSymbol[i].setFocusable(true);
					answerIcons[j] = icons[i+numberOfSymbols];
					j++;
				}else{
					jLabelQuestionSymbol[i].setIcon(icons[i+numberOfSymbols]);
					Thread.sleep(30);
				}
			}
			for(int i=0; i<numberOfSymbols; i++){
				jLabelOptionSymbol[i].setIcon(icons[i]);
				jLabelOptionSymbol[i].setFocusable(true);
				Thread.sleep(70);
			}
		
//			System.out.println(icons[0][0].toString().substring(icons[0][0].toString().lastIndexOf('/')+1));///test
		
			//****Field Show****/////////////////////////////////////////////////////////////////////////////////////////
			gameState = IS_SHOWING;
			
			//****Take Input****/////////////////////////////////////////////////////////////////////////////////////////
			gameState = IS_WAITING;
			
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
			
			for(int i=0, j=0; i<numberOfBoxs; i++){
				if(i==7 || i==8 || i==9 || i==10 || i==13 || i==16 || i==19 || i==22 || i==25 || i==26 || i==27 || i==28){
					if(answerIcons[j] == jLabelQuestionSymbol[i].getIcon()){	//if right
						rightAnswer++;
						jPanelQuestionSymbol[i].setBackground(new Color(190, 250, 165));
					}else if(jLabelQuestionSymbol[i].getIcon() != null){		//if wrong
						wrongAnswer++;
						jPanelQuestionSymbol[i].setBackground(new Color(250, 190, 165));
					}
					
					j++;
				}
			}
			
			Thread.sleep(1000);
			
			//****Result Show****/////////////////////////////////////////////////////////////////////////////////////////
			showResult();
			
		}catch(Exception e){
//			e.printStackTrace();
			new Message("Error", 420);
			
		}finally{
			//****Game Finish****/////////////////////////////////////////////////////////////////////////////////////////
			gameState = IS_FINISHED;
			
			jButtonControl.setText("Restart");
			progressPanelReinitialize();
		}
		
	}
	//End of Run Method																						#________R________#

	
	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		SymbolSudoku gui = new SymbolSudoku();
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
		rightAnswer=0;
		wrongAnswer=0;
		try{
			for(int i=numberOfSymbols-1; i>=0; i--){
				jLabelOptionSymbol[i].setIcon(null);
			}
			for(int i=numberOfBoxs-1; i>=0; i--){
				jLabelQuestionSymbol[i].setIcon(null);
				jPanelQuestionSymbol[i].setBackground(new Color(230, 255, 242));
			}
		}catch(Exception e){
			//handle exception
		}
	}
	
	@Override
	protected void stopInput(){
		for(int i=0; i<numberOfBoxs; i++){
			jLabelQuestionSymbol[i].setFocusable(false);
			jLabelQuestionSymbol[i].setBorder((new LineBorder(new Color(105, 30, 30), 2)));
		}
		for(int i=0; i<numberOfSymbols; i++){
			jLabelOptionSymbol[i].setFocusable(false);
			jLabelOptionSymbol[i].setBorder((new LineBorder(new Color(105, 30, 30), 1)));
		}
	}
	
	@Override
	protected void showResult(){
		new Message("You got right: " + rightAnswer + "\nwrong: " + wrongAnswer, 1);
	}
	// End of Overridden Methods 																			#________OM_______#
}


