/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 14-Jun-2014																							*
* Comment: This is a test for concentration and detecting objects quickly.									*
*************************************************************************************************************/

package tests.flexibilityTestColouredShapes.gui;


import java.awt.event.ActionEvent;
import javax.swing.*;
import run.mainFrame.gui.BrainTestMainFrame;

@SuppressWarnings("serial")
public class ColouredShapes extends BrainTestMainFrame{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel[] jLabelPrompt;
	private JLabel[][] jLabelImageView;
	private JLabel[] jLabelFeedback;
	private JButton[] jButtonAnswer;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public ColouredShapes() {

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
		jLabelPrompt = new JLabel[2];
		jLabelImageView = new JLabel[2][2];
		jLabelFeedback = new JLabel[2];
		jButtonAnswer = new JButton[2];
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		for(int i=0; i<2; i++){
			jLabelPrompt[i] = new JLabel();
			jLabelPrompt[i].setBounds(WEIGTH/2-180, i*190+80, 365, 165);
			jLabelPrompt[i].setHorizontalAlignment(0);
			jLabelPrompt[i].setVerticalAlignment(0);		
			jLabelPrompt[i].setFont(new java.awt.Font("Consolas", 0, 160));
			
			for(int j=0; j<2; j++){
				jLabelImageView[i][j] = new JLabel();
				jLabelImageView[i][j].setBounds(j*170+35, 30, 120, 120);
				jLabelImageView[i][j].setFont(new java.awt.Font("Consolas", 0, 50));
				jLabelImageView[i][j].setHorizontalAlignment(0);
				jLabelImageView[i][j].setVerticalAlignment(0);		
//				jLabelImageView[i][j].setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 1)));
			}
			
			jLabelFeedback[i] = new JLabel();
			jLabelFeedback[i].setBounds(130, 45, 95, 95);
			jLabelFeedback[i].setHorizontalAlignment(0);
			jLabelFeedback[i].setVerticalAlignment(0);		
//			jLabelFeedback[i].setBorder((new javax.swing.border.LineBorder(new java.awt.Color(105, 30, 30), 1)));
			
			jButtonAnswer[i] = new JButton();
			jButtonAnswer[i].setBounds(i*190+170, HEIGHT-120, 172, 50);
			jButtonAnswer[i].setBackground(new java.awt.Color(230, 255, 242));
			jButtonAnswer[i].setHorizontalAlignment(0);
			jButtonAnswer[i].setVerticalAlignment(0);		
			jButtonAnswer[i].setFont(new java.awt.Font("Arial", 0, 26));
			jButtonAnswer[i].getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(javax.swing.KeyStroke.getKeyStroke(37+i*2, 0), "AerrowKey"+i+"_pressed");
			jButtonAnswer[i].getActionMap().put("AerrowKey"+i+"_pressed", new AbstractAction() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					jButtonAnswerActionPerformed(evt);
				}
			});
			jButtonAnswer[i].addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					jButtonAnswerActionPerformed(evt);
				}
			});
		}
		
		jLabelPrompt[0].setBorder((new javax.swing.border.TitledBorder(null, "Shape", 0, 0, 
				new java.awt.Font("Arial", 0, 16), new java.awt.Color(130, 155, 142))));
		jLabelPrompt[1].setBorder((new javax.swing.border.TitledBorder(null, "Colour", 0, 0, 
				new java.awt.Font("Arial", 0, 16), new java.awt.Color(130, 155, 142))));
		
		jButtonAnswer[0].setText("<   No");
		jButtonAnswer[1].setText("Yes  >");
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		jLabelMain.add(jButtonControl);
		
		for(int i=0; i<2; i++){
			jLabelMain.add(jLabelPrompt[i]);
		
			for(int j=0; j<2; j++){
				jLabelPrompt[i].add(jLabelImageView[i][j]);
				jLabelPrompt[i].add(jLabelFeedback[i]);
			}
			
			jLabelMain.add(jButtonAnswer[i]);
		}
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setTitle("Coloured Shapes");
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	private void jButtonAnswerActionPerformed(ActionEvent evt){
		System.out.println(evt.getSource());
	}
	// End of Action Events 																				#________AE_______#
	
	//**
	// Run Method																							#*******R*******#
	//**
	@Override
	public void run() {
		
		jLabelImageView[0][0].setIcon(new ImageIcon(getClass().
				getResource("/tests/flexibilityTestColouredShapes/pictures/Symbol1b.png")));
		jLabelImageView[0][1].setIcon(new ImageIcon(getClass().
				getResource("/tests/flexibilityTestColouredShapes/pictures/Symbol1b.png")));
		jLabelImageView[1][0].setIcon(new ImageIcon(getClass().
				getResource("/tests/flexibilityTestColouredShapes/pictures/Symbol1b.png")));
		jLabelImageView[1][1].setIcon(new ImageIcon(getClass().
				getResource("/tests/flexibilityTestColouredShapes/pictures/Symbol1b.png")));
		
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
		ColouredShapes gui = new ColouredShapes();
		gui.setVisible(true);
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	// End of Auxiliary Methods 																			#________AM_______#
}


