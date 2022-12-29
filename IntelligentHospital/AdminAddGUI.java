import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


//Implemntation of our GUI for an Admin to Add another doctor 
public class AdminAddGUI extends JFrame implements ActionListener{
//	Admin username and password
	String username;
	String password;
//	Buttons
	JButton submit_button = new JButton("SUBMIT");
	JButton back_button = new JButton("BACK");
//	  Text Fields
	  JTextField first_name_text = new JTextField();
	  JTextField last_name_text = new JTextField();
	  JTextField user_text = new JTextField();
	  JTextField pass_text = new JTextField();
//	  Frame needed 
	  AdminAddFrame frame;
	public AdminAddGUI(String username, String password) {
		this.username = username;
		this.password = password;
		frame=new AdminAddFrame(submit_button,back_button,first_name_text,last_name_text,user_text,pass_text);
		frame.setTitle("Add Doctor");
        frame.setVisible(true);
        frame.setBounds(1000,300,500,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	}
//	Method that adds action listeners to our buttons 
	public void addActionListeners() {
		  submit_button.addActionListener(new ActionListener() {
		      @Override
//		      Ensures that all fields are filled out 
		      	public void actionPerformed(ActionEvent e) {
		    	  if(first_name_text.getText() == "" || last_name_text.getText() == "" || user_text.getText() == "" || pass_text.getText() == "") {
		    		  JOptionPane.showMessageDialog(frame, "Please Enter All Of The Fields.", "Error", JOptionPane.ERROR_MESSAGE);
		    	  }
//		    	  Ensures that the username is unique from other users
		    	  else if(checkUsername()) {
		    		  JOptionPane.showMessageDialog(frame, "Please Enter A Unique Username", "Error", JOptionPane.ERROR_MESSAGE);
		    	  }
//		    	  Adds our doctor to database
		    	  else {
		    		  Doctor newDoctor = new Doctor(first_name_text.getText(),last_name_text.getText(),user_text.getText(), pass_text.getText());
		    		  new Admin().addDoctor(newDoctor);
		    		  JOptionPane.showMessageDialog(frame, "Doctor Succesfully Added", "Success", JOptionPane.INFORMATION_MESSAGE);
		    		  first_name_text.setText("");
		    		  last_name_text.setText("");
		    		  user_text.setText("");
		    		  pass_text.setText("");
		    	  }
		    	
		   	  }
		  	});
//		  Back Button takes us back to Welcome Screen for Admin
		  back_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	frame.dispose();
		    	new AdminWelcomeGUI(username,password);
		   	  }
		  	});

	 }
//	Established ocnneciton to our database
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
	 
//	 Ensures doctor username doesn't already exist in our database
	 public boolean checkUsername() {
	    	Connection connection = connect();
	    	String sql = "SELECT * FROM doctors WHERE user_name = '" + user_text.getText() + "'";
	    	
			try {
				Statement statement = connection.createStatement();	
				ResultSet result = statement.executeQuery(sql);
				while(result.next()) {
					String user = result.getString("user_name");
					String pass = result.getString("password");
					if(user_text.getText().equals(user)) {
					//username already exists
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

