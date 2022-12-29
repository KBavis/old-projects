

import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//Implementation of our GUI for a Doctor Diagnosing a POatient
public class DiagnosePatientGUI {
//	Frame
	DiagnosePatientFrame frame;
//	Buttons
	JButton submit_button = new JButton("DIAGNOSE");
	JButton back_button = new JButton("BACK");
//	Textfields
	JTextField patient = new JTextField();
//	Doctor username and password
	String username;
	String password;
	public DiagnosePatientGUI(String username, String password) {
		this.username = username;
		this.password = password;
		frame = new DiagnosePatientFrame(patient, submit_button, back_button);
		frame.setTitle("Bavis Hospital: Remove Patients");
        frame.setVisible(true);
        frame.setBounds(1000,400,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	}
	
//	Adds action listeners to our buttons
	public void addActionListeners() {
		  submit_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
//		    	  Ensures that the patient exists
		    	  if(new Doctor().getPatientByName(patient.getText()) == null) {
		    		  JOptionPane.showMessageDialog(frame, "Please Enter A Valid Patient Name.", "Error", JOptionPane.ERROR_MESSAGE);
		    		  frame.dispose();
		    		  new DiagnosePatientGUI(username, password);
		    	  }
		    	  else {
		    		  new PatientInformationGUI(username, password, patient.getText());
		    		  frame.dispose();
		    	  }
		   	  }
		  	});
//		  Brings Back to Doctor Welcome Screen
		  back_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  new DoctorWelcomeGUI(username, password);
		    	  frame.dispose();
		   	  }
		  	});

	 }
	//@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
}