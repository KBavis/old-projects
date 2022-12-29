



import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


//Remove Patients GUI Implementation for Doctor
public class RemovePatientGUI {
//	Frame
	RemovePatientFrame frame;
//	Buttons
	JButton remove_button = new JButton("REMOVE");
	JButton back_button = new JButton("BACK");
//	Text Field
	JTextField patient = new JTextField();
//	Doctor Username and Passowrd
	String username;
	String password;
	public RemovePatientGUI(String username, String password) {
		this.username = username;
		this.password = password;
		frame = new RemovePatientFrame(patient, remove_button, back_button);
		frame.setTitle("Bavis Hospital: Remove Patients");
        frame.setVisible(true);
        frame.setBounds(1000,400,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	}
//	Adds action listeners to buttons
	public void addActionListeners() {
//		Removes patient
		  remove_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
//		    	  Ensures patient exists
		    	  if(new Doctor().getPatientByName(patient.getText()) == null) {
		    		  JOptionPane.showMessageDialog(frame, "Please Enter A Valid Patient Name.", "Error", JOptionPane.ERROR_MESSAGE);
		    		  frame.dispose();
		    		  new RemovePatientGUI(username, password);
		    	  }
		    	  else {
		    		  new Doctor().removePatient(new Doctor().getPatientByName(patient.getText()));
		    		  JOptionPane.showMessageDialog(frame, "Patient Succesfully Remove From System.", "Success", JOptionPane.INFORMATION_MESSAGE);
		    		  frame.dispose();
		    		  new RemovePatientGUI(username, password);
		    	  }
		   	  }
		  	});
//		  Brings up doctor welcome gui
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
