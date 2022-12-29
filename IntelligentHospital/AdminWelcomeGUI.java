
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//Implemtation of our Admin Welcome GUI
public class AdminWelcomeGUI extends JFrame implements ActionListener{
//	Frmae
	AdminWelcomeFrame frame;
//	Buttons
	JButton logout_button = new JButton("LOGOUT");
	JButton add_button = new JButton("ADD DOCTOR");
	JButton remove_button = new JButton("REMOVE DOCTOR");
	JButton view_button = new JButton("VIEW ALL DOCTORS");
//	Admin username and password
	String username;
	String password;
	public AdminWelcomeGUI(String username, String password) {
		this.username = username;
		this.password = password;
		frame=new AdminWelcomeFrame(username,password,logout_button, add_button,remove_button,view_button);
		frame.setTitle("Bavis Hospital: Welcome Page");
        frame.setVisible(true);
        frame.setBounds(1000,400,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	      
	}
	
//	Adds action listenrs to our buttons
	public void addActionListeners() {
//		Allows admin to logout 
		  logout_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  frame.dispose();
		    	  new LoginGUI();
		   	  }
		  	});
//		  Brings up GUI to execute Admin Removal
		  remove_button.addActionListener(new ActionListener() {
			  @Override
			  public void actionPerformed(ActionEvent e) {
				  frame.dispose();
				  //TODO Implement RemoveDoctorGUI
				  new AdminRemoveGUI(username,password);
			  }
		  });
//		  Brings up GUI to execute Admin Addition
		  add_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  //TODO Implement AddDoctorGUI
		    	  new AdminAddGUI(username,password);
		    	  frame.dispose();
		   	  }
		  	});
//		  Brings Upo GUI To Execute Admin View
		  view_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  //TODO Implement AddDoctorGUI
		    	  new ViewDoctorsGUI(username,password);
		    	  frame.dispose();
		   	  }
		  	});

	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

