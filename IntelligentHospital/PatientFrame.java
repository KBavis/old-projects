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
import javax.swing.JPasswordField;
import javax.swing.JTextField;


//Registration Frame for Patinet
public class PatientFrame extends JFrame implements ActionListener {
	 Container container= getContentPane();
//	 Buttons
	  JButton submit_button;
//	  Labels
	  JLabel titleLabel = new JLabel("BAVIS HOSPITAL");
	  JLabel name_label = new JLabel("NAME");
	  JLabel symptoms_label = new JLabel("SYMPTOMS");
	  JLabel age_label = new JLabel("AGE");
	  JLabel gender_label = new JLabel("GENDER");
	  JLabel date_label = new JLabel("TODAY'S DATE");
	  JLabel time_label = new JLabel("CURRENT TIME");
	  JLabel blood_pressure_label = new JLabel("BLOOD PRESSURE");
	  JLabel weight_label = new JLabel("CURRENT WEIGHT");
	  JLabel city_label = new JLabel("CITY");
	  JLabel state_label = new JLabel("STATE");
//	  Text Fields
	  JTextField name_text;
	  JTextField symptoms_text;
	  JTextField age_text;
	  JTextField gender_text;
	  JTextField date_text;
	  JTextField time_text;
	  JTextField blood_pressure_text;
	  JTextField weight_text;
	  JTextField city_text;
	  JTextField state_text;
	  PatientFrame(JButton submit_button, JTextField name_text, JTextField symptoms_text, JTextField age_text, JTextField gender_text, JTextField date_text, JTextField time_text, 
			  JTextField blood_pressure_text, JTextField weight_text, JTextField city_text, JTextField state_text)
	  {
	  	  this.submit_button = submit_button;
	  	  this.name_text = name_text;
	  	  this.symptoms_text = symptoms_text;
	  	  this.age_text = age_text;
	  	  this.gender_text = gender_text;
	  	  this.date_text = date_text;
	  	  this.time_text = time_text;
	  	  this.blood_pressure_text = blood_pressure_text;
	  	  this.weight_text = weight_text;
	  	  this.city_text = city_text;
	  	  this.state_text = state_text;
	     //Calling methods inside constructor.
	      setLayoutManager();
	      setLocationAndSize();
	      editFont();
	      addComponentsToContainer();

	  }
	 public void setLayoutManager()
	 {
	     container.setLayout(null);
	 }
	 public void setLocationAndSize()
	 {
	     //Setting location and Size of each components using setBounds() method.
		 
//		 Labels
		 titleLabel.setBounds(150,50,200,30);
		 name_label.setBounds(40,150,150,40);
		 symptoms_label.setBounds(40,210,150,40);
		 age_label.setBounds(40,270,150,40);
		 gender_label.setBounds(40,330,150,40);
		 date_label.setBounds(40,390,180,40);
		 time_label.setBounds(40,450,180,40);
		 blood_pressure_label.setBounds(40,510,180,40);
		 weight_label.setBounds(40,570,180,40);
		 city_label.setBounds(40,630,150,40);
		 state_label.setBounds(40,690,150,40);
		 
//		 Text Fields
		 name_text.setBounds(230,155,200,27);
		 symptoms_text.setBounds(230, 215, 200, 27);
		 age_text.setBounds(230, 275, 200, 27);
		 gender_text.setBounds(230, 335, 200, 27);
		 date_text.setBounds(230, 395, 200, 27);
		 time_text.setBounds(230, 455, 200, 27);
		 blood_pressure_text.setBounds(230, 515, 200, 27);
		 weight_text.setBounds(230, 575, 200, 27);
		 city_text.setBounds(230, 635, 200, 27);
		 state_text.setBounds(230, 695, 200, 27);
		 
//		 Button
		 submit_button.setBounds(180,775,140,30);

	 }
//	 Edits font
	 public void editFont() {
		   Font fontOne = new Font("Time New Roman", Font.PLAIN,16);
		   titleLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		   name_label.setFont(new Font("Verdana", Font.BOLD, 17));
		   symptoms_label.setFont(new Font("Verdana", Font.BOLD, 17));
		   age_label.setFont(new Font("Verdana", Font.BOLD, 17));
		   gender_label.setFont(new Font("Verdana", Font.BOLD, 17));
		   date_label.setFont(new Font("Verdana", Font.BOLD, 17));
		   time_label.setFont(new Font("Verdana", Font.BOLD, 17));
		   blood_pressure_label.setFont(new Font("Verdana", Font.BOLD, 17));
		   weight_label.setFont(new Font("Verdana", Font.BOLD, 17));
		   city_label.setFont(new Font("Verdana", Font.BOLD, 17));
		   state_label.setFont(new Font("Verdana", Font.BOLD, 17));
		   
//		   Underlining Our Title
		   Font font = titleLabel.getFont();
		   Map attributes = font.getAttributes();
		   attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		   titleLabel.setFont(font.deriveFont(attributes));
		   titleLabel.setForeground(Color.BLUE);

//		   Setting Our Textbox Fonts For User Entering Information
		   name_text.setFont(fontOne);
		   symptoms_text.setFont(fontOne);
		   age_text.setFont(fontOne);
		   gender_text.setFont(fontOne);
		   date_text.setFont(fontOne);
		   time_text.setFont(fontOne);
		   blood_pressure_text.setFont(fontOne);
		   weight_text.setFont(fontOne);
		   city_text.setFont(fontOne);
		   state_text.setFont(fontOne);
		   
	 }
	 public void addComponentsToContainer()
	 {
//		 Adding Labels
	    container.add(titleLabel);
	    container.add(name_label);
	    container.add(symptoms_label);
	    container.add(age_label);
	    container.add(gender_label);
	    container.add(date_label);
	    container.add(time_label);
	    container.add(blood_pressure_label);
	    container.add(weight_label);
	    container.add(city_label);
	    container.add(state_label);
	    
//	    Adding Text Boxes
	    container.add(name_text);
	    container.add(symptoms_text);
	    container.add(age_text);
	    container.add(gender_text);
	    container.add(date_text);
	    container.add(time_text);
	    container.add(blood_pressure_text);
	    container.add(weight_text);
	    container.add(city_text);
	    container.add(state_text);

//		adding buttons
	    container.add(submit_button);
	 }

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
}
