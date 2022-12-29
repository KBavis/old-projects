import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


//Patient welcome GUI
public class PatientWelcomeGUI extends JFrame implements ActionListener{
//	Frame
	PatientWelcomeFrame frame;
//	Buttons
	JButton register_button = new JButton("REGISTRATION");
	JButton logout_button = new JButton("LOGOUT");
	JButton view_results_button = new JButton("VIEW RESULTS");
//	Patient username and password
	String username;
	String password;
	public PatientWelcomeGUI(String username, String password) {
		this.username = username;
		this.password = password;
		frame=new PatientWelcomeFrame(username,password, register_button, logout_button, view_results_button);
		frame.setTitle("Bavis Hospital: Welcome Page");
        frame.setVisible(true);
        frame.setBounds(1000,400,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	      
	}
	
//	Adds action listeners
	public void addActionListeners() {
//		Brings up new GUI for corresponding buttons
		  register_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  frame.dispose();
		    	  new PatientRegistrationGUI(username, password);
		   	  }
		  	});
		  logout_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  frame.dispose();
		    	  new LoginGUI();
		   	  }
		  	});
		  view_results_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  frame.dispose();
		    	  
		    	  
//		    	  NEED TO IMPLEMENT AN IF STATEMENT TO DETERMINE IF THE PATIENT WAS DIAGNOSED OR NOT
		    	  new DiagnosisGUI(username, password);
		   	  }
		  	});

	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
