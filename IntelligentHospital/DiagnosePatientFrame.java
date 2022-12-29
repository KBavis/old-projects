
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

//Implementation of our frame for Doctors Diagnosing a Patient
public class DiagnosePatientFrame extends JFrame implements ActionListener{
//	Contianer
	Container container=getContentPane();
//	Text Fields
	JTextField patient;
//	Buttons
	JButton  submit_button;
	JButton  back_button;
//	Lables
	JLabel titleLabel = new JLabel("Diagnose Patients");
	JLabel prompt_label = new JLabel("Please Enter Patients Name: ");
	public DiagnosePatientFrame(JTextField patient, JButton submit_button, JButton back_button) {
		
	    this.patient = patient;
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
   //Setting location and Size of each components using setBounds() method.
   public void setLocationAndSize()
   {
	   titleLabel.setBounds(60,50,300,30);
	   prompt_label.setBounds(60,150,275,50);
	   back_button.setBounds(40,450,275,50);
	   submit_button.setBounds(40,350,275,50);
	   patient.setBounds(40,200,275,50);

 
   
   }
//   Editing the font
   public void editFont() {
	   titleLabel.setFont(new Font("Verdana", Font.BOLD, 25));
	   Font font = titleLabel.getFont();
	   Map attributes = font.getAttributes();
	   attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	   titleLabel.setFont(font.deriveFont(attributes));
	   titleLabel.setForeground(new Color(0,204,102));
	   prompt_label.setFont(new Font("Verdana", Font.PLAIN, 15));
	   patient.setFont(new Font("Times New Roman", Font.PLAIN, 20));

   }
//   Adding compoentns to container
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
	   container.add(titleLabel);
	   container.add(submit_button);
	   container.add(back_button);
	   container.add(patient);
	   container.add(prompt_label);


   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
