import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;


//Implementation of our Patients Diagnosis Frame
public class DiagnosisGUIFrame extends JFrame implements ActionListener{
//	Container
	Container container=getContentPane();
//	Button
	JButton back_button;
//	Lables
	JLabel titleLabel = new JLabel("Diagnosis");
	JLabel treatment_label;
	JLabel diagnosis;
	JLabel treatment;
//	Text Areas
	JTextArea diagnosis_label;
	JTextArea diagnosis_label_two;
//	Username and password for our patient
	String username;
	String password;
	public DiagnosisGUIFrame(String username, String password, JButton back_button)
    {
	   this.back_button = back_button;
    	this.username = username;
    	this.password = password;
       //Calling methods inside constructor.
        setLayoutManager();
        setDiagnosis();
        setLocationAndSize();
        editFont();
        addComponentsToContainer();
//        addActionListeners();
 
    }
//	Determines the view of the patient
	public void setDiagnosis() {
//		Checks if diagnosis on record for corresponding patiet
		String diag = new Doctor().get_diagnosis(username, password);
		if(diag==null) {
			diagnosis_label = new JTextArea("Please Return Later");
			diagnosis_label_two = new JTextArea("To View Diagnosis");
			diagnosis_label.setBounds(90,250,300,40);
			diagnosis_label_two.setBounds(90,270,300,40);
			diagnosis_label = textAreaProperties(diagnosis_label);
			diagnosis_label_two = textAreaProperties(diagnosis_label_two);
			diagnosis_label_two.setFont(new Font("Arial", Font.BOLD, 20));
			container.add(diagnosis_label_two);
		}
//		If there is, show the diagnosis and treatmenet
		else {
//			Diagnosis
			diagnosis_label = new JTextArea("You have been diagnosed with: ");
			diagnosis_label = textAreaProperties(diagnosis_label);
			diagnosis_label.setBounds(40,150,300,40);
			diagnosis = new JLabel(diag);
			diagnosis.setBounds(40,200,100,50);
			diagnosis.setFont(new Font("Arial", Font.BOLD, 19));
			diagnosis.setForeground(Color.RED);
			treatment_label = new JLabel("Treatments: ");
			
//			Treatment
			JTextArea treatment = new JTextArea(new Doctor().get_treatment_by_username(username));
			treatment = textAreaProperties(treatment);
			treatment_label.setBounds(40,310,300,40);
			treatment_label.setFont(new Font("Arial", Font.BOLD, 20));
			treatment.setBounds(40, 360, 300, 150);
			treatment.setFont(new Font("Arial", Font.BOLD, 19));
			treatment.setForeground(Color.RED);
			
			container.add(treatment_label);
			container.add(treatment);
			container.add(diagnosis);
		}
	}
	
//	Text Area Properties
	private JTextArea textAreaProperties(JTextArea textArea) {
	    textArea.setEditable(false);  
	    textArea.setCursor(null);  
	    textArea.setOpaque(false);  
	    textArea.setFocusable(false);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    return textArea;
	}
   public void setLayoutManager()
   {
       container.setLayout(null);
   }
//   Sets locaiton and size
   public void setLocationAndSize()
   {
       //Setting location and Size of each components using setBounds() method.
	   titleLabel.setBounds(110,50,300,30);
	   back_button.setBounds(80,500,200,30);
 
   
   }
//   Edits font 
   public void editFont() {
	   titleLabel.setFont(new Font("Verdana", Font.BOLD, 25));
	   titleLabel.setForeground(Color.BLUE);
	   Font font = titleLabel.getFont();
	   Map attributes = font.getAttributes();
	   attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	   titleLabel.setFont(font.deriveFont(attributes));
	   
	   diagnosis_label.setFont(new Font("Arial", Font.BOLD, 20));
	   
	   

   }
//   Adds component to contianer
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
	   container.add(titleLabel);
	   container.add(diagnosis_label);
	   container.add(back_button);
	   


   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
