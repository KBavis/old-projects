
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


//Frame for viewing infromation regarding patients recommended diagnosis and symptoms
public class PatientInformationFrame extends JFrame implements ActionListener {
	Container container = getContentPane();
	JButton agree_button;
	JButton disagree_button;
	JLabel titleLabel;
	String patient_name;
	JLabel symptoms_label;
	JLabel symptoms;
	JLabel age_label;
	JLabel age;
	JLabel gender_label;
	JLabel gender;
	JLabel blood_pressure_label;
	JLabel blood_pressure;
	JLabel weight_label;
	JLabel weight;
	JLabel diagnosis_label;
	JLabel diagnosis;


	public PatientInformationFrame(JButton agree_button, JButton disagree_button, String patient_name) {
		this.patient_name = patient_name;
		this.agree_button = agree_button;
		this.disagree_button = disagree_button;
		//titleLabel = new JLabel("Patient: " + patient_name);
		// Calling methods inside constructor.
		getPatientInfo();
		setLayoutManager();
		setLocationAndSize();
		editFont();
		addComponentsToContainer();
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}
	// Setting location and Size of each components using setBounds() method.
	public void setLocationAndSize() {
		titleLabel.setBounds(20, 20, 300, 30);
		agree_button.setBounds(20, 490, 100, 50);
		disagree_button.setBounds(235, 490, 100, 50);
		symptoms_label.setBounds(20,70,100,50);
		symptoms.setBounds(140, 70, 150, 50);
		age_label.setBounds(20,120,100,50);
		age.setBounds(100,120,100,50);
		gender_label.setBounds(20,170,100,50);
		gender.setBounds(100,170,100,50);
		blood_pressure_label.setBounds(20,230,200,50);
		blood_pressure.setBounds(160,230,100,50);
		weight_label.setBounds(20,280,100,50);
		weight.setBounds(100,280,100,50);
		diagnosis_label.setBounds(20,330,250,50);
		diagnosis.setBounds(260,330,120,50);
		
		

	}

	public void editFont() {
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		Font font = titleLabel.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		titleLabel.setFont(font.deriveFont(attributes));
		titleLabel.setForeground(Color.RED);
		symptoms_label.setFont(new Font("Verdana", Font.BOLD, 15));
		age_label.setFont(new Font("Verdana", Font.BOLD, 15));
		gender_label.setFont(new Font("Verdana", Font.BOLD, 15));
		blood_pressure_label.setFont(new Font("Verdana", Font.BOLD, 15));
		weight_label.setFont(new Font("Verdana", Font.BOLD, 15));
		diagnosis_label.setFont(new Font("Verdana", Font.BOLD, 15));

	}

	// Adding each components to the Container
	public void addComponentsToContainer() {
		container.add(titleLabel);
		container.add(symptoms_label);
		container.add(symptoms);
		container.add(age);
		container.add(age_label);
		container.add(gender);
		container.add(gender_label);
		container.add(blood_pressure);
		container.add(blood_pressure_label);
		container.add(weight_label);
		container.add(weight);
		container.add(diagnosis_label);
		container.add(diagnosis);		
		container.add(agree_button);
		container.add(disagree_button);

	}
//	Gets patient information
	public void getPatientInfo() {
		Patient current_patient = new Doctor().getPatientByName(patient_name);
		titleLabel = new JLabel("Patient: " + patient_name);
		symptoms_label = new JLabel("Symptoms: ");
		age_label = new JLabel("Age: ");
		gender_label = new JLabel("Gender: ");
		blood_pressure_label = new JLabel("Blood Pressure: ");
		weight_label = new JLabel("Weight: ");
		String diag = new Doctor().determine_illness(current_patient);
		diagnosis_label = new JLabel("Recommended Diagnosis: ");
		diagnosis = new JLabel("<html><p>" + diag + "</p></html>");
		symptoms = new JLabel("<html><p>" + current_patient.getSymptoms() + "</p></html>");
		age = new JLabel("<html><p>" + String.valueOf(current_patient.getAge()) + "</p></html>");
		gender = new JLabel("<html><p>" + current_patient.getGender() + "</p></html>");
		blood_pressure = new JLabel("<html><p>" + String.valueOf(current_patient.getBloodPressure()) + "</p></html>");
		weight = new JLabel(String.valueOf(current_patient.getWeight()));
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
