import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginGUI extends JFrame  implements ActionListener{
	public LoginFrame frame;
//  Creating Our Buttons
  JButton loginButton=new JButton("LOGIN");
  JButton resetButton=new JButton("RESET");
  JButton createButton = new JButton("CREATE ACCOUNT");
//  Textfields
  JTextField userTextField=new JTextField();
//  Passoword fields
  JPasswordField passwordField=new JPasswordField();
//  Checbox
  JCheckBox showPassword=new JCheckBox("Show Password");
	
	public LoginGUI(){
		frame= new LoginFrame(loginButton, resetButton, createButton,userTextField,passwordField,showPassword);
		frame.setTitle("Bavis Hospital Login");
		frame.setLocation(600,400);
        frame.setVisible(true);
        frame.setBounds(1000,400,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	      
	}
	
//	Adds action listenres
  public void addActionListeners() {
//	  Implementation of Create Button
  createButton.addActionListener(new ActionListener() {
      @Override
      	public void actionPerformed(ActionEvent e) {
    	  frame.dispose();
   	   	new CreateAccountGUI();
   	  }
  	});
// Login button funcitonality  
  loginButton.addActionListener(new ActionListener() {
      @Override
      	public void actionPerformed(ActionEvent e) {
//    	  Ensures validity of username and passowrd
    	  	boolean value = checkUserNameAndPassword();
    	  	if(value == false) {
    	  		if(checkDoctorUsernameAndPassword() == true) {
    	  			frame.dispose();
    	  			new DoctorWelcomeGUI(userTextField.getText(),String.valueOf(passwordField.getPassword()));
    	  		}
    	  		else if(checkAdminUsernameAndPassword() == true) {
    	  			frame.dispose();
    	  			new AdminWelcomeGUI(userTextField.getText(),String.valueOf(passwordField.getPassword()));
    	  		}
    	  		else {
    	  			JOptionPane.showMessageDialog(frame, "Username or Password Is Incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
    	  		}
    	  	}
    	  	else {
   	   			frame.dispose();
   	   			new PatientWelcomeGUI(userTextField.getText(),String.valueOf(passwordField.getPassword()));
    	  	}
      	}
  	});
//  Implements the showing of our password
  showPassword.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent e) {
	        if (e.getStateChange() == ItemEvent.SELECTED) {
	        	passwordField.setEchoChar((char) 0);
	        } else {
	             passwordField.setEchoChar((char)'*');
	        }
	    }
	});
  }
  
//  checks to see if given username corresponds to a doctor
  public boolean checkDoctorUsernameAndPassword() {
	  Connection connection = connect();
  	  String sql = "SELECT * FROM doctors WHERE user_name = '" + userTextField.getText() + "'";
  	
		try {
			Statement statement = connection.createStatement();	
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String user = result.getString("user_name");
				String pass = result.getString("password");
				if(userTextField.getText().equals(user) && String.valueOf(passwordField.getPassword()).equals(pass)) {
//					String selected_diagnosis = select_diagnosis(username, password);
					return true;
				}
				else {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error[-]");
			e.printStackTrace();
		}
		return false;
  }
  
//  Cehcks to see if given username corresponds to an admin
  public boolean checkAdminUsernameAndPassword() {
	  Connection connection = connect();
  	  String sql = "SELECT * FROM admin WHERE username = '" + userTextField.getText() + "'";
  	
		try {
			Statement statement = connection.createStatement();	
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String user = result.getString("username");
				String pass = result.getString("password");
				if(userTextField.getText().equals(user) && String.valueOf(passwordField.getPassword()).equals(pass)) {
//					String selected_diagnosis = select_diagnosis(username, password);
					return true;
				}
				else {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error[-]");
			e.printStackTrace();
		}
		return false;
  }
  
  //conects to our database
  private Connection connect() {
  	String url = "jdbc:mysql://localhost:3306/hospitaldb";
  	String username = "Kellen Bavis";
		String password = "99715Vcs!";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch(SQLException e) {
			System.out.println("Eroor[-]");
			e.printStackTrace();
		}
		return connection;
  }
  
  //checks if given username corresponds to an existing patient
  public boolean checkUserNameAndPassword() {
	    	Connection connection = connect();
	    	String sql = "SELECT * FROM patients WHERE username LIKE '%" + userTextField.getText() + "%'";
	    	
			try {
				Statement statement = connection.createStatement();	
				ResultSet result = statement.executeQuery(sql);
				while(result.next()) {
					String user = result.getString("username");
					String pass = result.getString("password");
					if(userTextField.getText().equals(user) && String.valueOf(passwordField.getPassword()).equals(pass)) {
//						String selected_diagnosis = select_diagnosis(username, password);
						return true;
					}
					else {
						return false;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error[-]");
				e.printStackTrace();
			}
			return false;

  }

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

}
