/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 26-Jul-2014																							*
* Comment: 	*									
*************************************************************************************************************/

package tests.mathTestWaterDropes.gui;


import javax.swing.*;
import run.mainFrame.gui.BrainTestMainFrame;

@SuppressWarnings("serial")
public class WaterDropes extends BrainTestMainFrame{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel jLabelMainBackground;
	private JLabel jLabelCloud;
	private JLabel jLabelWater;
	private JLabel jLabelWaterDropes;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public WaterDropes() {

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
		jLabelMainBackground = new JLabel();
		jLabelCloud = new JLabel();
		jLabelWater = new JLabel();
		jLabelWaterDropes = new JLabel();
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jLabelMainBackground.setBounds(20, 80, 655, 450);
		jLabelMainBackground.setBorder((new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
		jLabelMainBackground.setHorizontalAlignment(0);
		jLabelMainBackground.setVerticalAlignment(0);
		jLabelMainBackground.setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/tests/mathTestWaterDropes/pictures/Grass.png")));
		
		
		jLabelCloud.setSize(655, 100);
		jLabelCloud.setLocation(2, 2);
		jLabelCloud.setHorizontalAlignment(0);
		jLabelCloud.setVerticalAlignment(0);
		jLabelCloud.setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/tests/mathTestWaterDropes/pictures/Cloud.png")));		
		
		jLabelWater.setSize(655, 130);
		jLabelWater.setLocation(0, 380);
		jLabelWater.setHorizontalAlignment(0);
		jLabelWater.setVerticalAlignment(0);
		jLabelWater.setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/tests/mathTestWaterDropes/pictures/Water.png")));		
		
		jLabelWaterDropes.setSize(90, 110);
		jLabelWaterDropes.setHorizontalAlignment(0);
		jLabelWaterDropes.setVerticalAlignment(0);
		jLabelWaterDropes.setLocation(80, 200);
		jLabelWaterDropes.setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/tests/mathTestWaterDropes/pictures/WaterDrop.png")));
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		jLabelMain.add(jLabelMainBackground);
		
		jLabelMainBackground.add(jLabelCloud);
		jLabelMainBackground.add(jLabelWater);
		jLabelMainBackground.add(jLabelWaterDropes);
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
		WaterDropes gui = new WaterDropes();
		gui.setVisible(true);
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	// End of Auxiliary Methods 																			#________AM_______#
}


