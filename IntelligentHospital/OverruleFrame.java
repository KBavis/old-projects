
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
import javax.swing.JTextField;
//Implementation of our overrule diagnosis frame for docotr
public class OverruleFrame extends JFrame implements ActionListener{
//	Contianer
	Container container=getContentPane();
//	Text Fields
	JTextField diagnosis;
	JTextField treatment;
//	Buttons
	JButton  submit_button;
	JButton  back_button;
//	Labels
	JLabel titleLabel = new JLabel("Overrule Diagnosis");
	JLabel prompt_label = new JLabel("Please Enter The Patients Diagnosis: ");
	JLabel prompt_label_two = new JLabel("Please Enter The Patients Treatment: ");
	String patient_name;
	public OverruleFrame(JTextField diagnosis, JTextField treatment, JButton submit_button, JButton back_button, String patient) {
		
	    this.diagnosis = diagnosis;
	    this.treatment = treatment;
	    this.patient_name = patient;
	    this.back_button = back_button;
	   this.submit_button = submit_button;

    	
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
//   Sets locations on GUIO
   public void setLocationAndSize()
   {
       //Setting location and Size of each components using setBounds() method.
	   titleLabel.setBounds(40,50,300,30);
	   prompt_label.setBounds(40,100,300,50);
	   prompt_label_two.setBounds(40,200,300,50);
	   back_button.setBounds(40,475,275,50);
	   submit_button.setBounds(40,400,275,50);
	   diagnosis.setBounds(40,150,275,50);
	   treatment.setBounds(40,250,275,50);

 
   
   }
//   Edits our fonts
   public void editFont() {
	   titleLabel.setFont(new Font("Verdana", Font.BOLD, 25));
	   Font font = titleLabel.getFont();
	   Map attributes = font.getAttributes();
	   attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	   titleLabel.setFont(font.deriveFont(attributes));
	   titleLabel.setForeground(new Color(0,204,102));
	   prompt_label.setFont(new Font("Verdana", Font.PLAIN, 15));
	   prompt_label_two.setFont(new Font("Verdana", Font.PLAIN, 15));
	   diagnosis.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	   treatment.setFont(new Font("Times New Roman", Font.PLAIN, 20));

   }
//   Adds compoentns to contianers
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
	   container.add(titleLabel);
	   container.add(submit_button);
	   container.add(back_button);
	   container.add(diagnosis);
	   container.add(treatment);
	   container.add(prompt_label);
	   container.add(prompt_label_two);


   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
