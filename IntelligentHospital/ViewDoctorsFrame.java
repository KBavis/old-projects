import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ViewDoctorsFrame extends JFrame implements ActionListener {
    Container container=getContentPane();
//  Creating Our Labels
  JLabel titleLabel = new JLabel("VIEW DOCTORS");
//  Creating Our Buttons
  JButton backButton;
  String username;
  String password;

  ViewDoctorsFrame(String username, String password, JButton backButton)
  {
	  this.password = password;
	  this.username = username;
  	  this.backButton = backButton;
      setLayoutManager();
      setLocationAndSize();
      editFont();
      getOurDoctors();
      addComponentsToContainer();
      

  }
 public void setLayoutManager()
 {
     container.setLayout(null);
 }
// Gets all our doctors
 public void getOurDoctors() {
	 List<Doctor> doctors = new Admin().getAllDoctors();
	 String first_name;
	 String last_name;
	 String full_name;
	 JLabel nameLabel;
	 Doctor doc = null;
	 int x = 30;
	 int y = 150;
	 int width = 200;
	 int height = 30;
	 //TODO: Implement a Next Page So We Can View More Than 14 Doctors At A Time		
	 for(int i = 0 ; i < doctors.size(); i++) {
		 doc = doctors.get(i);
		 first_name = doc.get_first_name();
		 last_name = doc.get_last_name();
		 full_name = first_name + " " + last_name;
		 nameLabel = new JLabel(full_name.toUpperCase());
		 nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		 nameLabel.setBounds(x,y,width,height);
		 container.add(nameLabel);
		 if(y == 450) {
			 y = 150;
			 x = 200;
		 }
		 else {
			 y+=50;
		 }
	 }
 }

 //Setting location and Size of each components using setBounds() method.
 public void setLocationAndSize()
 {
	   titleLabel.setBounds(100,50,200,30);
       backButton.setBounds(80,500,200,30);


 }
// edits fonts
 public void editFont() {
	   titleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
	   Font font = titleLabel.getFont();
	   Map attributes = font.getAttributes();
	   attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	   titleLabel.setFont(font.deriveFont(attributes));
	   titleLabel.setForeground(Color.BLUE);
	   
	   
 }
 public void addComponentsToContainer()
 {
    //Adding each components to the Container
	   container.add(titleLabel);
     container.add(backButton);
 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
