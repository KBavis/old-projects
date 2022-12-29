
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


//Implementation of our Doctor GUI
public class DoctorWelcomeGUI extends JFrame implements ActionListener{
//	Frame
	DoctorWelcomeFrame frame;
//	Buttons
	JButton logout_button = new JButton("LOGOUT");
	JButton diagnose_button = new JButton("DIAGNOSE PATIENT");
	JButton remove_button = new JButton("REMOVE PATIENT");
	JButton view_button = new JButton("VIEW ALL PATIENTS");
	JButton change_password_button = new JButton("CHANGE PASSWORD");
//	Doctors username nad password
	String username;
	String password;
	public DoctorWelcomeGUI(String username, String password) {
		this.username = username;
		this.password = password;
		frame=new DoctorWelcomeFrame(username,password,logout_button, diagnose_button,remove_button,view_button,change_password_button);
		frame.setTitle("Bavis Hospital: Welcome Page");
        frame.setVisible(true);
        frame.setBounds(1000,400,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	      
	}
	
//	Adds action listeners to our buttons
	public void addActionListeners() {
//		New GUI for each button pressed
		  logout_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  frame.dispose();
		    	  new LoginGUI();
		   	  }
		  	});
		  remove_button.addActionListener(new ActionListener() {
			  @Override
			  public void actionPerformed(ActionEvent e) {
				  frame.dispose();
				  new RemovePatientGUI(username,password);
			  }
		  });
		  diagnose_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  frame.dispose();
		    	  new DiagnosePatientGUI(username,password);
		   	  }
		  	});
		  view_button.addActionListener(new ActionListener() {
			  @Override
			  public void actionPerformed(ActionEvent e) {
				  frame.dispose();
				  new ViewPatientsGUI(username,password);
			  }
		  });
		  change_password_button.addActionListener(new ActionListener() {
			  @Override
			  public void actionPerformed(ActionEvent e) {
				  frame.dispose();
				  new DoctorChangePasswordGUI(username,password);
			  }
		  });

	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
