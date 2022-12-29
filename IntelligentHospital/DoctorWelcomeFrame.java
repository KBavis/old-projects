
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


// Implementation of our Doctor Welcoem Frame 
public class DoctorWelcomeFrame extends JFrame implements ActionListener{
//	Container
	Container container=getContentPane();
//	Buttons
	JButton logout_button;
	JButton diagnose_button;
	JButton remove_patient_button;
	JButton view_all_patients_button;
	JButton change_password_button;
//	Lables
	JLabel titleLabel = new JLabel("Welcome Doctor");
	public DoctorWelcomeFrame(String username, String password, JButton logout_button, JButton diagnose_button, JButton remove_patient_button, JButton view_all_patients_button, JButton change_password_button)
    {
	   this.logout_button = logout_button;
	   this.diagnose_button = diagnose_button;
	   this.remove_patient_button = remove_patient_button;
	   this.view_all_patients_button = view_all_patients_button;
	   this.change_password_button = change_password_button;
    	
       //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        editFont();
        addComponentsToContainer();
//        addActionListeners();
 
    }
   public void setLayoutManager()
   {
       container.setLayout(null);
   }
//Setting location and Size of each components using setBounds() method.
   public void setLocationAndSize()
   {
       //Setting location and Size of each components using setBounds() method.
	   titleLabel.setBounds(60,50,300,30);
	   diagnose_button.setBounds(40,125,275,50);
	   remove_patient_button.setBounds(40,200,275,50);
	   view_all_patients_button.setBounds(40,275,275,50);
	   change_password_button.setBounds(40,350,275,50);
	   logout_button.setBounds(40,425,275,50);
 
   
   }
//   Edits our fonts
   public void editFont() {
	   titleLabel.setFont(new Font("Verdana", Font.BOLD, 25));
	   Font font = titleLabel.getFont();
	   Map attributes = font.getAttributes();
	   attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	   titleLabel.setFont(font.deriveFont(attributes));
	   titleLabel.setForeground(new Color(51,153,255));
	   diagnose_button.setFont(new Font("Arial", Font.PLAIN, 18));
	   logout_button.setFont(new Font("Arial", Font.PLAIN, 18));
	   remove_patient_button.setFont(new Font("Arial", Font.PLAIN, 18));
	   view_all_patients_button.setFont(new Font("Arial", Font.PLAIN, 18));
	   change_password_button.setFont(new Font("Arial", Font.PLAIN, 18));
   }
//   Adds components to contianer
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
	   container.add(titleLabel);
	   container.add(diagnose_button);
	   container.add(logout_button);
	   container.add(remove_patient_button);
	   container.add(view_all_patients_button);
	   container.add(change_password_button);

	   
   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}