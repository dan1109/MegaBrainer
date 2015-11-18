/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 14-Jun-2014																							*
* Comment: 	*									
*************************************************************************************************************/

package run.mainFrame.gui;


import help.about.gui.About;
import help.developer.gui.Profile;
import help.instruction.gui.Instruction;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import tests.perceptionTestAlphanumericDisorder.gui.AlphanumericDisorder;
import tests.perfectionTestLostTwin.gui.LostTwin;
import tests.reactionTestSymbolCollection.gui.SymbolCollection;
import tests.logicTestSymbolSudoku.gui.SymbolSudoku;
import tests.memoryTestLostSymbols.gui.LostAnimals;
import tests.motionTestCreepyInsects.gui.CreepyInsects;
import tests.concentrationTestMixedNumbers.gui.MixedNumbers;


@SuppressWarnings("serial")
public class BrainTestMainFrame extends JFrame implements Runnable{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	protected JLabel jLabelMain;
	
	protected JButton jButtonControl;
	protected JPanel[] jPanelProgress;
	
	//menu bar
	private JMenuBar jMenuBarMain;
	private JMenu jMenuTest, jMenuHelp;
	private JMenuItem[] jMITests;
	private JMenuItem jMIInstruction, jMIDeveloper, jMIAbout;
	
	//operational variables
	protected String instruction;
	private int numberOfTests;
	
	//main thread
	protected Thread thread;
	
	//operational variables
	protected int gameState;

	protected final int IS_NOT_STARTED=0;	//before the game starts
	protected final int IS_STARTED=1;		//after the game is started
	protected final int IS_SHOWING=2;		//field show
	protected final int IS_WAITING=3;		//take input from user 
	protected final int IS_PROCESSING=4;	//stop taking input from user & process result
	protected final int IS_FINISHED=5;		//game is finished
	
	
	//controlling values
	protected final int WEIGTH = 700;
	protected final int HEIGHT = 600;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public BrainTestMainFrame() {
		instruction = new String();
		numberOfTests=7;
		
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
		jLabelMain = new JLabel();
		
		jButtonControl = new JButton();
		jPanelProgress = new JPanel[2];

		jMenuBarMain = new JMenuBar();
		jMenuTest = new JMenu(); jMenuHelp = new JMenu();
		jMITests = new JMenuItem[numberOfTests];
		jMIInstruction = new JMenuItem(); jMIDeveloper = new JMenuItem(); jMIAbout = new JMenuItem();
		
		gameState = IS_NOT_STARTED;
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jLabelMain.setBounds(0, 0, WEIGTH, HEIGHT);
		jLabelMain.setLayout(null);
		jLabelMain.setIcon(new ImageIcon(getClass().getResource("/run/mainFrame/pictures/Background.png")));
		
		
		jButtonControl.setBounds(WEIGTH/2-100, 15, 200, 40);
		jButtonControl.setText("Start");
		jButtonControl.setLayout(null);
		jButtonControl.setBackground(new Color(230, 255, 242));
		jButtonControl.setFont(new java.awt.Font("Arial", 0, 20));
		jButtonControl.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter_pressed");
		jButtonControl.getActionMap().put("Enter_pressed", new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				//jButtonControlActionPerformed();
				JButton jButton = (JButton) evt.getSource();
				jButton.doClick();
			}
		});
		jButtonControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonControlActionPerformed();
			}
		});
		
		jPanelProgress[0] = new JPanel();
		jPanelProgress[0].setBounds(3, 3, 0, 5);
		jPanelProgress[0].setBackground(new Color(250, 0, 0, 120));
		
		jPanelProgress[1] = new JPanel();
		jPanelProgress[1].setBounds(3, 30, 0, 5);
		jPanelProgress[1].setBackground(new Color(250, 0, 0, 120));
		
		
		//menu
		jMenuTest.setText("Test      ");
		jMenuTest.setFont(new Font("Lucida Bright", 1, 13));
		jMenuTest.setIcon(new ImageIcon(getClass().getResource("/run/mainFrame/pictures/IconTest.png")));
		jMenuHelp.setText("Help      ");
		jMenuHelp.setFont(new Font("Lucida Bright", 1, 13));
		jMenuHelp.setIcon(new ImageIcon(getClass().getResource("/run/mainFrame/pictures/IconHelp.png")));
		
		
		for(int i=0; i<numberOfTests; i++){
			jMITests[i] = new JMenuItem();
			jMITests[i].setIcon(new ImageIcon(getClass().getResource("/run/mainFrame/pictures/IconTests.png")));
			jMITests[i].setFont(new Font("Lucida Bright", 2, 15));
			jMITests[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
        			jMITestsActionPerformed(evt);
				}
			});
		}
		jMITests[0].setText("Mixed Numbers");
		jMITests[1].setText("Lost Symbols");
		jMITests[2].setText("Symbol Sudoku");
		jMITests[3].setText("Alphanumeric Disorder");
		jMITests[4].setText("Lost Twin");
		jMITests[5].setText("Creepy Insects");
		jMITests[6].setText("Digit Collection");
		
		
		jMIInstruction.setText("Instruction"); 
		jMIInstruction.setIcon(new ImageIcon(getClass().getResource("/run/mainFrame/pictures/IconInstruction.png")));
		jMIInstruction.setFont(new Font("Lucida Bright", 2, 13));
    	jMIInstruction.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		jMIInstructionActionPerformed(evt);
        	}
        });
		jMIDeveloper.setText("Developer"); 
		jMIDeveloper.setIcon(new ImageIcon(getClass().getResource("/run/mainFrame/pictures/IconDeveloper.png")));
		jMIDeveloper.setFont(new Font("Lucida Bright", 2, 13));
    	jMIDeveloper.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		jMIDeveloperActionPerformed(evt);
        	}
        });
		jMIAbout.setText("About");
		jMIAbout.setIcon(new ImageIcon(getClass().getResource("/run/mainFrame/pictures/IconAbout.png")));
		jMIAbout.setFont(new Font("Lucida Bright", 2, 13));
    	jMIAbout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		jMIAboutActionPerformed(evt);
        	}
        });
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
    	jLabelMain.add(jButtonControl);
    	jButtonControl.add(jPanelProgress[0]);
    	jButtonControl.add(jPanelProgress[1]);
    	
    	jMenuBarMain.add(jMenuTest); jMenuBarMain.add(jMenuHelp);
		
		for(int i=0; i<numberOfTests; i++){
			jMenuTest.add(jMITests[i]);
		}
		jMenuHelp.add(jMIInstruction); jMenuHelp.add(jMIDeveloper); jMenuHelp.add(jMIAbout);
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("/run/mainFrame/pictures/Icon.png")).getImage());
		setBounds(150, 50, WEIGTH, HEIGHT);
		setTitle("Brain Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		add(jLabelMain);
		setJMenuBar(jMenuBarMain);
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	private void jButtonControlActionPerformed(){
		if(gameState==IS_NOT_STARTED){
			jButtonControl.setText("Stop");
			thread = new Thread(this);
			thread.start();
			
		}else if(gameState==IS_STARTED || gameState==IS_SHOWING || gameState==IS_WAITING){
			gameState=IS_FINISHED;
			
		}else if(gameState==IS_FINISHED){
			gameState=IS_NOT_STARTED;
			
			jButtonControl.setText("Start");
			gameInitialize();
		}
	}
	
	
	private void jMITestsActionPerformed(ActionEvent evt){
		
		if(evt.getActionCommand() == "Mixed Numbers"){
			new MixedNumbers().setVisible(true);
		}else if(evt.getActionCommand() == "Lost Symbols"){
			new LostAnimals().setVisible(true);
		}else if(evt.getActionCommand() == "Symbol Sudoku"){
			new SymbolSudoku().setVisible(true);
		}else if(evt.getActionCommand() == "Alphanumeric Disorder"){
			new AlphanumericDisorder().setVisible(true);
		}else if(evt.getActionCommand() == "Lost Twin"){
			new LostTwin().setVisible(true);
		}else if(evt.getActionCommand() == "Creepy Insects"){
			new CreepyInsects().setVisible(true);
		}else if(evt.getActionCommand() == "Symbol Collection"){
			new SymbolCollection().setVisible(true);
		}else{
			new MixedNumbers().setVisible(true);
		}
		
		gameState = IS_FINISHED;
      	dispose();
	}
	
	private void jMIInstructionActionPerformed(ActionEvent evt){
		new Instruction(instruction).setVisible(true);
	}
	
	private void jMIDeveloperActionPerformed(ActionEvent evt){
		new Profile("14-Jun-2014").setVisible(true);
	}
	
	private void jMIAboutActionPerformed(ActionEvent evt){
		new About().setVisible(true);
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
			
			//****Field Show****/////////////////////////////////////////////////////////////////////////////////////////
			gameState = IS_SHOWING;
			
			//****Take Input****/////////////////////////////////////////////////////////////////////////////////////////
			gameState = IS_WAITING;
			
			int totalShowTime=500, passedTime=0;
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
		BrainTestMainFrame gui = new BrainTestMainFrame();
		gui.setVisible(true);
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	protected void progressPanelReinitialize(){
		jPanelProgress[0].setSize(0, 7);
		jPanelProgress[1].setSize(0, 7);
	}
	
	protected void progressPanelAdvance(int i){
		jPanelProgress[0].setSize(i, 7);
		jPanelProgress[1].setSize(i, 7);
	}
	// End of Auxiliary Methods 																			#________AM_______#
	
	//**
	// Overridden Methods 																					#********OM*******#
	//**
	/**
	 * Returns game to its initial position so that the game can be restarted.
	 */
	protected void gameInitialize(){
		//should be overridden in properly
	}
	
	/**
	 * Stops taking input from the user.
	 */
	protected void stopInput(){
		//should be overridden in properly
	}
	
	/**
	 * Shows result depending on the performance of the player.
	 */
	protected void showResult(){
		//should be overridden in properly
	}
	// End of Overridden Methods 																			#________OM_______#

}
