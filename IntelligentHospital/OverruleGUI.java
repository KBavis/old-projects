import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverruleGUI extends JFrame implements ActionListener {
//	Patinet name, username, and passowrd
	String patient_name;
	String user_name;
	String password;
//	Button
	JButton submit_button = new JButton("SUBMIT");
	JButton back_button = new JButton("BACK");
//	Textfields
	JTextField diagnosis = new JTextField();
	JTextField treatment = new JTextField();
	OverruleFrame frame;
	public OverruleGUI(String user_name, String password, String patient_name) {
		this.user_name = user_name;
		this.password = password;
		this.patient_name = patient_name;
		frame = new OverruleFrame(diagnosis, treatment, submit_button,back_button,patient_name);
		frame.setTitle("Bavis Hospital: Patient Diagnosis");
        frame.setVisible(true);
        frame.setBounds(1000,400,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	}
//	Adds action listeners to button
	public void addActionListeners() {
//		Submit button actions 
		  submit_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  Patient ourPatient = new Doctor().getPatientByName(patient_name);
		    	  String diag = diagnosis.getText();
		    	  String treat = treatment.getText();
		    	  new Doctor().diagnose_patient(diag, ourPatient);
		    	  new Doctor().set_unique_treatment(treat, ourPatient);
		    	  JOptionPane.showMessageDialog(frame, "Patient Diagnosis And Treatment Updated.", "Succesful Update.", JOptionPane.INFORMATION_MESSAGE);
		    	  frame.dispose();
		    	  new DiagnosePatientGUI(user_name,password);
		   	  }
		  	});
//		  Backbutton actions
		  back_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  frame.dispose();
		    	  new PatientInformationGUI(user_name,password,patient_name);
		   	  }
		  	});

	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

