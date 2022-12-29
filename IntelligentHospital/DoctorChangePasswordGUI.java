
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DoctorChangePasswordGUI extends JFrame implements ActionListener{
//	Frame
	DoctorChangePasswordFrame frame;
//	Buttons
	JButton change_password = new JButton("CHANGE PASSWORD");
	JButton back_button = new JButton("BACK");
//	Text Fields
	JTextField username = new JTextField();
//	Password Fields
	JPasswordField old_password  =new JPasswordField();
	JPasswordField new_password_one =new JPasswordField();
	JPasswordField new_password_two =new JPasswordField();
//	Doctor username and password
	String user;
	String password;
	
	public DoctorChangePasswordGUI(String user, String password){
		this.user = user;
		this.password = password;
		frame=new DoctorChangePasswordFrame(change_password, back_button, username, old_password, new_password_one, new_password_two);
		frame.setTitle("Bavis Hospital Change Password");
        frame.setVisible(true);
        frame.setBounds(1000,400,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	      
	}
	
	
	 public void addActionListeners() {
		  change_password.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
//		    	  Ensures valid username and passowrd
		    	  if(checkUserNameAndPassword()) {
//		    		  Confirms new passwords match
		    		  if(String.valueOf(new_password_one.getPassword()).equals(String.valueOf(new_password_two.getPassword()))) {
		    			  new Doctor().changePassword(username.getText(), String.valueOf(new_password_one.getPassword()));
		    			  JOptionPane.showMessageDialog(frame, "Passoword Updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
		    			  frame.dispose();
		    			  new DoctorWelcomeGUI(user,password);
		    		  }
		    		  else {
		    			  JOptionPane.showMessageDialog(frame, "Passowords don't match.", "Error", JOptionPane.ERROR_MESSAGE);
		    			  frame.dispose();
		    			  new DoctorChangePasswordGUI(user,password);
		    		  }
		    	  }
		    	  else {
		    		  JOptionPane.showMessageDialog(frame, "Please Enter Correct Username And Password To Change Password.", "Error", JOptionPane.ERROR_MESSAGE);
		    		  frame.dispose();
		    		  new DoctorChangePasswordGUI(user,password);
		    	  }
		   	  }
		  	});
//		  Bringss bac to Doctor Welcome Page
		  back_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  new DoctorWelcomeGUI(user,password);
		   	  }
		  	});

	 }
//	 Provides conneciton to our database
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
	 
//	 Checks valdiity of username and password 
	 public boolean checkUserNameAndPassword() {
	    	Connection connection = connect();
	    	String sql = "SELECT * FROM doctors WHERE user_name = '" + username.getText() + "'";
	    	
			try {
				Statement statement = connection.createStatement();	
				ResultSet result = statement.executeQuery(sql);
				while(result.next()) {
					String user = result.getString("user_name");
					String pass = result.getString("password");
					if(username.getText().equals(user) && String.valueOf(old_password.getPassword()).equals(pass)) {
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
