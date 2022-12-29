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


//Frame for changing our docotrs password
public class DoctorChangePasswordFrame extends JFrame implements ActionListener {
    Container container=getContentPane();
//  Creating Our Labels
  JLabel titleLabel = new JLabel("CHANGE PASSWORD");
  JLabel user_label=new JLabel("USERNAME");
  JLabel old_password_label =new JLabel("OLD PASSWORD");
  JLabel new_password_label_one=new JLabel("NEW PASSWORD");
  JLabel new_password_label_two=new JLabel("RE-TYPE PASSWORD");
//  Creating Our Text Fields
  JTextField username;
  JPasswordField old_password;
  JPasswordField new_password_one;
  JPasswordField new_password_two;
//  Creating Our Buttons
  JButton change_password;
  JButton back_button;
//  Creating Our Check Box To Show Our Password While Being Typed
//  JCheckBox showPassword=new JCheckBox("Show Password");

  DoctorChangePasswordFrame(JButton change_password, JButton back_button, JTextField username, JPasswordField old_password, JPasswordField new_password_one, JPasswordField new_password_two)
  {
  	  this.change_password= change_password;
  	  this.back_button = back_button;
  	  this.username = username;
  	  this.old_password = old_password;
  	  this.new_password_one = new_password_one;
  	  this.new_password_two = new_password_two;
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
	   titleLabel.setBounds(80,50,250,30);
     user_label.setBounds(50,150,150,30);
     old_password_label.setBounds(50,220,150,30);
     new_password_label_one.setBounds(50,290,150,30);
     new_password_label_two.setBounds(50,360,150,30);
     username.setBounds(180,150,150,30);
     old_password.setBounds(180,220,150,30);
     new_password_one.setBounds(180,290,150,30);
     new_password_two.setBounds(180,360,150,30);
     back_button.setBounds(55,450,250,30);
     change_password.setBounds(55,500,250,30);


 }
// Edits font
 public void editFont() {
	   titleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
	   Font font = titleLabel.getFont();
	   Map attributes = font.getAttributes();
	   attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	   titleLabel.setFont(font.deriveFont(attributes));
	   titleLabel.setForeground(Color.BLUE);
	   
	   
 }
// Adds component to our container
 public void addComponentsToContainer()
 {
    //Adding each components to the Container
	   container.add(titleLabel);
     container.add(user_label);
     container.add(old_password_label);
     container.add(new_password_label_one);
     container.add(new_password_label_two);
     container.add(username);
     container.add(old_password);
     container.add(new_password_one);
     container.add(new_password_two);
     container.add(change_password);
     container.add(back_button);
 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
