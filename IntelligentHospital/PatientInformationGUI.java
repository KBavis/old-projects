import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Implemetation of GUI for Doctor deciding if symptoms match recommended diagnosis
public class PatientInformationGUI extends JFrame implements ActionListener {
	String patient_name;
//	Doctors username and password
	String user_name;
	String password;
//	Buttons
	JButton agree_button = new JButton("AGREE");
	JButton disagree_button = new JButton("DISAGREE");
//	Frame
	PatientInformationFrame frame;
	public PatientInformationGUI(String user_name, String password, String patient_name) {
		this.user_name = user_name;
		this.password = password;
		this.patient_name = patient_name;
		frame = new PatientInformationFrame(agree_button,disagree_button,patient_name);
		frame.setTitle("Bavis Hospital: Patient Diagnosis");
        frame.setVisible(true);
        frame.setBounds(1000,400,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	}
//	Doctor has the ability to either disagree with the diagnosis given by computer or agree
// If he/she disagrees, they are able to view the diagnosis and state what they beleive the diagnosis should be 
	public void addActionListeners() {
//		Updates Database
		  agree_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  //TODO: Update Database To Have Patients Diagnosis And Treatments
		    	  Patient ourPatient = new Doctor().getPatientByName(patient_name);
		    	  String diagnosis = new Doctor().determine_illness(ourPatient);
		    	  new Doctor().diagnose_patient(diagnosis, ourPatient);
		    	  new Doctor().treatment_patient(diagnosis, ourPatient);
		    	  JOptionPane.showMessageDialog(frame, "Patient Diagnosis And Treatment Updated.", "Succesful Update.", JOptionPane.INFORMATION_MESSAGE);
		    	  frame.dispose();
		    	  new DiagnosePatientGUI(user_name,password);
		   	  }
		  	});
		  disagree_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  //TODO: Implement Doctor Overrule GUI
		    	  new OverruleGUI(user_name,password,patient_name);
		    	  frame.dispose();
		   	  }
		  	});

	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
