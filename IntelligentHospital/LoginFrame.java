
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;
 
//Implementation of our Login Frame
public class LoginFrame extends JFrame implements ActionListener {
 
//	Contianer
    Container container=getContentPane();
//    Creating Our Labels
    JLabel titleLabel = new JLabel("BAVIS HOSPITAL");
    JLabel userLabel=new JLabel("USERNAME");
    JLabel passwordLabel=new JLabel("PASSWORD");
//    Creating Our Text Fields
    JTextField userTextField;
    JPasswordField passwordField;

//    Creating Our Check Box To Show Our Password While Being Typed
    JCheckBox showPassword;
    
//    Buttons
    JButton loginButton;
    JButton resetButton;
    JButton createButton;
 
   public LoginFrame(JButton loginButton, JButton resetButton, JButton createButton, JTextField userTextField, JPasswordField passwordField, JCheckBox showPassword)
    {
	   this.loginButton = loginButton;
	   this.resetButton = resetButton;
	   this.createButton = createButton;
	   this.userTextField = userTextField;
	   this.passwordField = passwordField;
	   this.showPassword = showPassword;
    	
       //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        editFont();
        addComponentsToContainer();
        addActionListeners();
 
    }
   public void setLayoutManager()
   {
       container.setLayout(null);
   }
 //Setting location and Size of each components using setBounds() method.
   public void setLocationAndSize()
   {

	   titleLabel.setBounds(100,50,200,30);
       userLabel.setBounds(50,150,100,30);
       passwordLabel.setBounds(50,220,100,30);
       userTextField.setBounds(150,150,150,30);
       passwordField.setBounds(150,220,150,30);
       showPassword.setBounds(150,250,150,30);
       loginButton.setBounds(50,300,100,30);
       resetButton.setBounds(200,300,100,30);
       createButton.setBounds(80,400,200,30);
 
 
   }
//   Edits Fonts
   public void editFont() {
	   titleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
	   Font font = titleLabel.getFont();
	   Map attributes = font.getAttributes();
	   attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	   titleLabel.setFont(font.deriveFont(attributes));
	   titleLabel.setForeground(Color.BLUE);
   }
//   Adds components to contianer
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
	   container.add(titleLabel);
       container.add(userLabel);
       container.add(passwordLabel);
       container.add(userTextField);
       container.add(passwordField);
       container.add(showPassword);
       container.add(loginButton);
       container.add(resetButton);
       container.add(createButton);
   }


   public void addActionListeners() {
	  
//	   Implemetnation of reset button
   resetButton.addActionListener(new ActionListener() {
       @Override
       	public void actionPerformed(ActionEvent e) {
    	   	userTextField.setText("");
    	   	passwordField.setText("");
       	}
   	});
   }
   @Override
   public void actionPerformed(ActionEvent e) {
	   // TODO Auto-generated method stub
	   
   }
}

    
 


