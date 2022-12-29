import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountGUI extends JFrame implements ActionListener{
//	Frame
	CreateAccountFrame frame;
//	Buttons
	JButton createButton = new JButton("CREATE ACCOUNT");
//	Password Fields
	JPasswordField passwordFieldOne =new JPasswordField();
	JPasswordField passwordFieldTwo=new JPasswordField();
//	Text Fields
	JTextField userTextField=new JTextField();
	
	public CreateAccountGUI(){
		frame=new CreateAccountFrame(createButton, userTextField, passwordFieldOne, passwordFieldTwo);
		frame.setTitle("Bavis Hospital Create Account");
        frame.setVisible(true);
        frame.setBounds(1000,400,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	      
	}
	
//	Adds Action listeners to our buttons
	 public void addActionListeners() {
		 
		  createButton.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  //Ensures that passwords match
		   	   	if(String.valueOf(passwordFieldOne.getPassword()).equals(String.valueOf(passwordFieldTwo.getPassword()))) {
		   	   		//Ensures that the patient entered an actual password
		   	   		if(String.valueOf(passwordFieldOne.getPassword()).equals("")) {
		   	   			JOptionPane.showMessageDialog(frame, "Please Enter A Password.", "Error", JOptionPane.ERROR_MESSAGE);
		   	   		}
		   	   		//Esnures that the patient entered an actual username
		   	   		else if(userTextField.getText().equals("")) {
		   	   			JOptionPane.showMessageDialog(frame, "Please Enter A Username.", "Error", JOptionPane.ERROR_MESSAGE);
		   	   		}
		   	   		//Ensures that there already isn't a registered user with that name
		   	   		else if(new Doctor().getPatientByUser(userTextField.getText()) != null) {
		   	   			JOptionPane.showMessageDialog(frame, "That Username Already Exists.", "Error", JOptionPane.ERROR_MESSAGE);
		   	   		}
		   	   		else {
		   	   			frame.dispose();
		   	   			new PatientWelcomeGUI(userTextField.getText(), String.valueOf(passwordFieldOne.getPassword()));
		   	   		}
		   	   	}
		   	   	else {
		   	   		JOptionPane.showMessageDialog(frame, "Passowords don't match.", "Error", JOptionPane.ERROR_MESSAGE);
		   	   	}
		   	  }
		  	});

	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
