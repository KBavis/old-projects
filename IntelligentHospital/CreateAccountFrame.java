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

public class CreateAccountFrame extends JFrame implements ActionListener {
    Container container=getContentPane();
//  Creating Our Labels
  JLabel titleLabel = new JLabel("CREATE ACCOUNT");
  JLabel userLabel=new JLabel("USERNAME");
  JLabel passwordLabelOne=new JLabel("PASSWORD");
  JLabel passwordLabelTwo=new JLabel("RE-TYPE PASSWORD");
//  Creating Our Text Fields
  JTextField userTextField;
  JPasswordField passwordFieldOne;
  JPasswordField passwordFieldTwo;
//  Creating Our Buttons
  JButton createButton;
//  Creating Our Check Box To Show Our Password While Being Typed
//  JCheckBox showPassword=new JCheckBox("Show Password");

  CreateAccountFrame(JButton createButton, JTextField userTextField, JPasswordField passwordFieldOne, JPasswordField passwordFieldTwo)
  {
  	  this.createButton = createButton;
  	  this.passwordFieldOne = passwordFieldOne;
  	  this.passwordFieldTwo = passwordFieldTwo;
  	  this.userTextField = userTextField;
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
	   titleLabel.setBounds(100,50,200,30);
     userLabel.setBounds(50,150,150,30);
     passwordLabelOne.setBounds(50,220,150,30);
     passwordLabelTwo.setBounds(50,290,150,30);
     userTextField.setBounds(180,150,150,30);
     passwordFieldOne.setBounds(180,220,150,30);
     passwordFieldTwo.setBounds(180,290,150,30);
     createButton.setBounds(80,400,200,30);
 }
// Method to edit our fonts 
 public void editFont() {
	   titleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
	   Font font = titleLabel.getFont();
	   Map attributes = font.getAttributes();
	   attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	   titleLabel.setFont(font.deriveFont(attributes));
	   titleLabel.setForeground(Color.BLUE);
	   
	   
 }
// Method that adds compoentns to our contianer
 public void addComponentsToContainer()
 {
    //Adding each components to the Container
	   container.add(titleLabel);
     container.add(userLabel);
     container.add(passwordLabelOne);
     container.add(passwordLabelTwo);
     container.add(userTextField);
     container.add(passwordFieldOne);
     container.add(passwordFieldTwo);
//     container.add(showPassword);
     container.add(createButton);
 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
