/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 03-Jun-2014																							*
* Comment: 	*									
* 		*
* 		*
*************************************************************************************************************/

package message.gui;


import javax.swing.*;

@SuppressWarnings("serial")
public class Message extends JDialog {
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel jLabelMain;
	private JLabel jLabelIcon;
	private JLabel jLabelText1, jLabelText2, jLabelText3;	
	private JButton jButtonOK;
	
	//other variables
	private String[] message;
	private int messageType;
	// End of Variable Declaration 																			#_______D_______#

	
	/**
	 * Constructor.
	 * Shows message to the user to guide or give information of the operation status.
	 * @param message main part of message to be shown
	 * @param messageType type of message, like- plain message(0), confirmation message(1), warning message(210) and
	 * 	error message(420)
	 */
	public Message(String message, int messageType) {
		this.message = getMessage(message);
		this.messageType=messageType;

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
		jLabelIcon = new JLabel();
		jLabelText1 = new JLabel();	
		jLabelText2 = new JLabel();	
		jLabelText3 = new JLabel();	
		jButtonOK = new JButton();
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jLabelMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/message/pictures/BackGround.png")));
        jLabelMain.setBounds(0, 0, 470, 300);
        jLabelMain.setLayout(null);
        
        
        jLabelIcon.setBounds(10, 33, 70, 70);
        if(messageType==1){
        	jLabelIcon.setIcon(new ImageIcon(getClass().getResource("/message/pictures/Confirm.png")));
        }else if(messageType==210){
        	jLabelIcon.setIcon(new ImageIcon(getClass().getResource("/message/pictures/Warning.png")));
        }else if(messageType==420){
        	jLabelIcon.setIcon(new ImageIcon(getClass().getResource("/message/pictures/Error.png")));
        }else {
        	jLabelIcon.setIcon(new ImageIcon(getClass().getResource("/message/pictures/Message.png")));
        }
        
        jLabelText1.setBounds(100, 40, 350, 25);
        jLabelText1.setFont(new java.awt.Font("Lucida", 0, 16));
        jLabelText1.setText(message[0]);
        
        jLabelText2.setBounds(100, 65, 350, 25);
        jLabelText2.setFont(new java.awt.Font("Lucida", 0, 16));
        jLabelText2.setText(message[1]);        
        
        jLabelText3.setBounds(100, 90, 350, 25);
        jLabelText3.setFont(new java.awt.Font("Lucida", 0, 16));
        jLabelText3.setText(message[2]);        
        
        jButtonOK.setText("OK");
        jButtonOK.setBounds(310, 130, 60, 30);
        jButtonOK.setBackground(new java.awt.Color(212, 227, 250));
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });
        jButtonOK.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
	    	put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER,0), "ENTER_pressed");
	    jButtonOK.getActionMap().put("ENTER_pressed", new AbstractAction() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	jButtonOKActionPerformed(evt);
	        }
	    });
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		jLabelMain.add(jLabelIcon);
		jLabelMain.add(jLabelText1);
		jLabelMain.add(jLabelText2);
		jLabelMain.add(jLabelText3);
		jLabelMain.add(jButtonOK);
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("")).getImage());
		setBounds(240, 200, 470, 200);
		setTitle("Message");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setModal(true);
		add(jLabelMain);
		setVisible(true);
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt){
		dispose();
	}
	// End of Action Events 																				#________AE_______#

	/**///Main Method
	public static void main(String args[]) {
		/*// Set the Web look and feel //*/
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		new Message("Hi You are grounded for your insignificant and \nnonsence actions.", 1);
	}
	/**/

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	private String[] getMessage(String message){
		String[] messages = new String[3];
		
		if(message.contains("\n")){
			messages[0] = message.substring(0, message.indexOf("\n"));
			message = message.substring(message.indexOf("\n")+1);
			if(message.contains("\n")){
				messages[1] = message.substring(0, message.indexOf("\n"));
				messages[2] = message.substring(message.indexOf("\n")+1);
				
			}else{
				messages[1] = message;
			}
		}else{
			messages[0] = message;
		}
		
		
		return messages;
	}
	// End of Auxiliary Methods 																			#________AM_______#
}
