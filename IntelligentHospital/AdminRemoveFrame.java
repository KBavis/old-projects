


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


// Implementation of our Admin removal frame 
public class AdminRemoveFrame extends JFrame implements ActionListener{
//	Container
	Container container=getContentPane();
//	Text fields
	JTextField doctor;
//	Buttons
	JButton  remove_button;
	JButton  back_button;
//	Labels
	JLabel titleLabel = new JLabel("Remove Doctors");
	JLabel prompt_label = new JLabel("Please Enter Doctors Name: ");
	public AdminRemoveFrame(JTextField doctor, JButton remove_button, JButton back_button) {
		
	    this.doctor = doctor;
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
   //Setting location and Size of each components using setBounds() method.
   public void setLocationAndSize()
   {
   
	   titleLabel.setBounds(60,50,300,30);
	   prompt_label.setBounds(60,150,275,50);
	   back_button.setBounds(40,450,275,50);
	   remove_button.setBounds(40,350,275,50);
	   doctor.setBounds(40,200,275,50);

 
   
   }
//   Methods to edit oru fonts
   public void editFont() {
	   titleLabel.setFont(new Font("Verdana", Font.BOLD, 25));
	   Font font = titleLabel.getFont();
	   Map attributes = font.getAttributes();
	   attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	   titleLabel.setFont(font.deriveFont(attributes));
	   titleLabel.setForeground(Color.RED);
	   prompt_label.setFont(new Font("Verdana", Font.PLAIN, 15));
	   doctor.setFont(new Font("Times New Roman", Font.PLAIN, 20));

   }
//   Adding our components to our container
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
	   container.add(titleLabel);
	   container.add(remove_button);
	   container.add(back_button);
	   container.add(doctor);
	   container.add(prompt_label);


   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}