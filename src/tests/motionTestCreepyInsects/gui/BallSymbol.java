/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: --2014																							*
* Comment: 	*									
* 		*
* 		*
*************************************************************************************************************/

package tests.motionTestCreepyInsects.gui;


import javax.swing.*;

import tests.motionTestCreepyInsects.operation.RandomMovementGenerator;

@SuppressWarnings("serial")
public class BallSymbol extends JLabel  implements Runnable{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private Icon icon;
	
	private Thread thread;
	
	//operational variables
	private int boundaryX;
	private int boundaryY;
	private int movingLimit;
	private int movingDirectionX;
	private int movingDirectionY;
	private int increaseInX;
	private int increaseInY;
	private int delayTime;
	private int numberOfFrames;
	
	private int[][] change;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public BallSymbol() {
		boundaryX = 605;
		boundaryY = 400;
		movingLimit = 300;
		movingDirectionX = 1;
		movingDirectionY = 1;
		increaseInX = 2;
		increaseInY = 2;
		delayTime = 20;
		numberOfFrames = 100;
		icon = new ImageIcon(getClass().getResource("/tests/motionTestCreepyInsects/pictures/Ball1.png"));
		
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
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**

		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**

		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setSize(50, 50);
		setHorizontalAlignment(0);
		setVerticalAlignment(0);		
		setIcon(icon);	
		//setBorder((new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
	}

	//**
	// Action Events 																						#********AE*******#
	//**

	// End of Action Events 																				#________AE_______#

	//**
	// Run Method																							#*******R*******#
	//**
	public void run() {
		int horizontalPosition=getX(),
			verticalPosition=getY();
		
		change = new RandomMovementGenerator().generateRandomMovement(numberOfFrames, RandomMovementGenerator.SOFT_MOVEMENT);
		
		int [] changeDirection_changeSpeed = {movingDirectionX*increaseInX, movingDirectionY*increaseInY};
		
		for(int i=0; i<movingLimit; i++){
			setLocation(horizontalPosition, verticalPosition);
			
			horizontalPosition = horizontalPosition + change[0] [(i/4)%numberOfFrames] * changeDirection_changeSpeed[0];
			verticalPosition = verticalPosition + change[1] [(i/4)%numberOfFrames] * changeDirection_changeSpeed[1];
			
			if(horizontalPosition<0 || horizontalPosition>boundaryX){
				changeDirection_changeSpeed[0] = changeDirection_changeSpeed[0] * -1;
			}
			if(verticalPosition<0 || verticalPosition>boundaryY){
				changeDirection_changeSpeed[1] = changeDirection_changeSpeed[1] * -1;
			}
			
			try {
				Thread.sleep(delayTime);
			} catch (Exception e) {
				
			}
		}
	}
	//End of Run Method																						#_______R_______#

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	/**
	 * Sets all types of criterion of the object's movement.
	 * @param boundaryX	horizontal boundary, how far it is allowed to go(in pixel) [default: 605]
	 * @param boundaryY vertical boundary, how far it is allowed to go(in pixel) [default: 400]
	 * @param movingLimit how much time this object is allowed to move(in frame number) [default: 1000]
	 * @param movingDirectionX there can be two directions 1 for right -1 for left [default: 1]
	 * @param movingDirectionY there can be two directions 1 for up -1 for down [default: 1]
	 * @param increaseInX increase in the moving speed from horizontal point of view [default: 1]
	 * @param increaseInY increase in the moving speed from vertical point of view [default: 1]
	 * @param delayTime how much it should delay between two steps(in milli second) [default: 20]
	 */
	public void setCriterion(int boundaryX, int boundaryY, int movingLimit, int movingDirectionX, int movingDirectionY,
						int increaseInX, int increaseInY, int delayTime) {
		
		this.boundaryX = boundaryX;
		this.boundaryY = boundaryY;
		this.movingLimit = movingLimit;
		this.movingDirectionX = movingDirectionX;
		this.movingDirectionY = movingDirectionY;
		this.increaseInX = increaseInX;
		this.increaseInY = increaseInY;
		this.delayTime = delayTime;
	}
	
	
	/**
	 * Sets the motion speed, direction & type of the object.
	 * @param movingDirectionX there can be two directions 1 for right -1 for left [default: 1]
	 * @param movingDirectionY there can be two directions 1 for up -1 for down [default: 1]
	 * @param increaseInX increase in the moving speed from horizontal point of view [default: 1]
	 * @param increaseInY increase in the moving speed from vertical point of view [default: 1]
	 */
	public void setMotion(int movingDirectionX, int movingDirectionY, int increaseInX, int increaseInY){
		this.movingDirectionX = movingDirectionX;
		this.movingDirectionY = movingDirectionY;
		this.increaseInX = increaseInX;
		this.increaseInY = increaseInY;
	}

	
	/**
	 * 
	 * @param movingLimit
	 */
	public void setMovingLimit(int movingLimit){
		this.movingLimit = movingLimit;
	}
	
	
	/**
	 * Sets the icon;
	 * @param iconNumber from 1 to 4 [default: 1]
	 */
	public void setIcon(int iconNumber){
		setIcon(new ImageIcon(getClass().getResource("/tests/motionTestCreepyInsects/pictures/Ball" + iconNumber + ".png")));
	}
	
	public void start(){
		thread = new Thread(this);
		thread.start();
	}
	// End of Auxiliary Methods 																			#________AM_______#
}