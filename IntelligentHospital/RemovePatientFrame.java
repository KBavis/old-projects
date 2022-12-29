


////TO DO:  FINISH REMOVE PATIENT GUI AND REMOVE PATIENT FRAME





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


//Impleemtnatiopn of Remove Patient Frame for Doctor
public class RemovePatientFrame extends JFrame implements ActionListener{
//	Contianer
	Container container=getContentPane();
//	text fields
	JTextField patient;
//	Buttons
	JButton  remove_button;
	JButton  back_button;
//	Labels
	JLabel titleLabel = new JLabel("Remove Patients");
	JLabel prompt_label = new JLabel("Please Enter Patients Name: ");
	public RemovePatientFrame(JTextField patient, JButton remove_button, JButton back_button) {
		
	    this.patient = patient;
	    this.back_button = back_button;
	   this.remove_button = remove_button;

    	
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
//   Locations and size
   public void setLocationAndSize()
   {
       //Setting location and Size of each components using setBounds() method.
	   titleLabel.setBounds(60,50,300,30);
	   prompt_label.setBounds(60,150,275,50);
	   back_button.setBounds(40,450,275,50);
	   remove_button.setBounds(40,350,275,50);
	   patient.setBounds(40,200,275,50);

 
   
   }
//   Edits fonts
   public void editFont() {
	   titleLabel.setFont(new Font("Verdana", Font.BOLD, 25));
	   Font font = titleLabel.getFont();
	   Map attributes = font.getAttributes();
	   attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	   titleLabel.setFont(font.deriveFont(attributes));
	   titleLabel.setForeground(Color.RED);
	   prompt_label.setFont(new Font("Verdana", Font.PLAIN, 15));
	   patient.setFont(new Font("Times New Roman", Font.PLAIN, 20));

   }
 //Adding each components to the Container
   public void addComponentsToContainer()
   {
	   container.add(titleLabel);
	   container.add(remove_button);
	   container.add(back_button);
	   container.add(patient);
	   container.add(prompt_label);


   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
