/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 22-Jul-2014																							*
* Comment: 	*									
*************************************************************************************************************/

package tests.reactionTestTheLostBird.gui;


import java.awt.Color;
import java.awt.Point;
import javax.swing.*;

import commonOperations.randomValueMaker.operation.RandomIntegerGenerator;

import run.mainFrame.gui.BrainTestMainFrame;

@SuppressWarnings("serial")
public class TheLostBird extends BrainTestMainFrame{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel jLabelSky;
	private JLabel jLabelBirdView;
	private JLabel[] jLabelBirds;
	private JButton[] jButtonsAerrowKey;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public TheLostBird() {

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
		jLabelSky = new JLabel();
		jLabelBirdView = new JLabel();
		jLabelBirds = new JLabel[5];
		jButtonsAerrowKey = new JButton[4];
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jLabelSky.setBounds(20, 80, 655, 450);
		jLabelSky.setBorder((new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
		jLabelSky.setHorizontalAlignment(0);
		jLabelSky.setVerticalAlignment(0);
		jLabelSky.setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/tests/reactionTestTheLostBird/pictures/Sky.png")));
		
		
		jLabelBirdView.setSize(260, 260);
		jLabelBirdView.setBackground(new Color(250, 250, 250, 20));
		jLabelBirdView.setHorizontalAlignment(0);
		jLabelBirdView.setVerticalAlignment(0);
//		jLabelBirdView.setBorder((new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
		
		Point[] point = getRandomFormation();
		for(int i=0; i<5; i++){
			jLabelBirds[i] = new JLabel();
			jLabelBirds[i].setSize(50, 50);
			jLabelBirds[i].setHorizontalAlignment(0);
			jLabelBirds[i].setVerticalAlignment(0);
			jLabelBirds[i].setLocation(point[i]);
			jLabelBirds[i].setIcon(new javax.swing.
					ImageIcon(getClass().getResource("/tests/reactionTestTheLostBird/pictures/BirdR.png")));
		}
		
		for(int i=0; i<4; i++){
			jButtonsAerrowKey[i] = new JButton();
			jButtonsAerrowKey[i].setSize(50, 40);
			jButtonsAerrowKey[i].setHorizontalAlignment(0);
			jButtonsAerrowKey[i].setVerticalAlignment(0);
			jButtonsAerrowKey[i].setBackground(new java.awt.Color(230, 255, 242));
		}
		jButtonsAerrowKey[0].setLocation(300, 370);
		jButtonsAerrowKey[0].setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/tests/reactionTestTheLostBird/pictures/U.png")));
		jButtonsAerrowKey[1].setLocation(250, 410);
		jButtonsAerrowKey[1].setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/tests/reactionTestTheLostBird/pictures/L.png")));
		jButtonsAerrowKey[2].setLocation(300, 410);
		jButtonsAerrowKey[2].setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/tests/reactionTestTheLostBird/pictures/D.png")));
		jButtonsAerrowKey[3].setLocation(350, 410);
		jButtonsAerrowKey[3].setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/tests/reactionTestTheLostBird/pictures/R.png")));
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		jLabelMain.add(jLabelSky);
		
		jLabelSky.add(jLabelBirdView);
		for(int i=0; i<4; i++){
			jLabelSky.add(jButtonsAerrowKey[i]);
		}
		
		for(int i=0; i<5; i++){
			jLabelBirdView.add(jLabelBirds[i]);
		}
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setTitle("The Lost Bird");
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
		TheLostBird gui = new TheLostBird();
		gui.setVisible(true);
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	private Point[] getRandomFormation(){
		Point[][] point = new Point[8][5];
		
		point[0][0] = new Point(0, 100); point[0][1] = new Point(50, 100); point[0][2] = new Point(100, 100);
				point[0][3] = new Point(150, 100); point[0][4] = new Point(200, 100);
		
		point[1][0] = new Point(100, 0); point[1][1] = new Point(100, 50); point[1][2] = new Point(100, 100);
				point[1][3] = new Point(100, 150); point[1][4] = new Point(100, 200);
		
		point[2][0] = new Point(0, 0); point[2][1] = new Point(50, 50); point[2][2] = new Point(100, 100);
				point[2][3] = new Point(150, 150); point[2][4] = new Point(200, 200);
		
		point[3][0] = new Point(200, 0); point[3][1] = new Point(150, 50); point[3][2] = new Point(100, 100);
				point[3][3] = new Point(50, 150); point[3][4] = new Point(0, 200);
		
		point[4][0] = new Point(0, 0); point[4][1] = new Point(50, 50); point[4][2] = new Point(100, 100);
				point[4][3] = new Point(50, 150); point[4][4] = new Point(0, 200);
		
		point[5][0] = new Point(200, 0); point[5][1] = new Point(150, 50); point[5][2] = new Point(100, 100);
				point[5][3] = new Point(150, 150); point[5][4] = new Point(200, 200);
		
		point[6][0] = new Point(0, 0); point[6][1] = new Point(50, 50); point[6][2] = new Point(100, 100);
				point[6][3] = new Point(150, 50); point[6][4] = new Point(200, 0);
		
		point[7][0] = new Point(0, 200); point[7][1] = new Point(50, 150); point[7][2] = new Point(100, 100);
				point[7][3] = new Point(150, 150); point[7][4] = new Point(200, 200);
		

		return point[new RandomIntegerGenerator().nextInt(8)];
	}
	// End of Auxiliary Methods 																			#________AM_______#
}


