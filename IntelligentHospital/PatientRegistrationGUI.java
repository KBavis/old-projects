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


//Patient Regristration GUI
public class PatientRegistrationGUI extends JFrame implements ActionListener{
//	Patient username and password
	String username;
	String password;
//	Button
	JButton submit_button = new JButton("SUBMIT");
//	  Text Fields
	  JTextField name_text = new JTextField();
	  JTextField symptoms_text = new JTextField();
	  JTextField age_text = new JTextField();
	  JTextField gender_text = new JTextField();
	  JTextField date_text = new JTextField();
	  JTextField time_text = new JTextField();
	  JTextField blood_pressure_text = new JTextField();
	  JTextField weight_text = new JTextField();
	  JTextField city_text = new JTextField();
	  JTextField state_text = new JTextField();
	PatientFrame frame;
	public PatientRegistrationGUI(String username, String password) {
		this.username = username;
		this.password = password;
		frame=new PatientFrame(submit_button, name_text, symptoms_text, age_text, gender_text, date_text, time_text, blood_pressure_text, weight_text, city_text, state_text);
		frame.setTitle("Bavis Hospital: Entering Your Information");
        frame.setVisible(true);
        frame.setBounds(1000,300,500,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	}
//	Adds action listeners to buttons
	public void addActionListeners() {
//		Submit button actions
		  submit_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
//		    	  Ensures that fields are filled out 
		    	if(name_text.getText().equals("") || symptoms_text.getText().equals("") || age_text.getText().equals("") || gender_text.getText().equals("") || date_text.getText().equals("")
		    			|| time_text.getText().equals("") || blood_pressure_text.getText().equals("") || weight_text.getText().equals("") || city_text.getText().equals("") || state_text.equals("")) {
		    		JOptionPane.showMessageDialog(frame, "Please Ensure All Fields Are Filled Out.", "Error", JOptionPane.ERROR_MESSAGE);
		    	}
//		    	Updates registered symptoms if patient already exists
		    	else if(checkUserNameAndPassword()) {
		    		int age = Integer.parseInt(age_text.getText());
		    		double blood_pressure = Double.parseDouble(blood_pressure_text.getText());
		    		double weight = Double.parseDouble(weight_text.getText());
		    		Patient p = new Patient(name_text.getText(), symptoms_text.getText(), age, state_text.getText(), city_text.getText(), gender_text.getText(), date_text.getText(), time_text.getText(), 
		    				blood_pressure, weight , username, password);
		    		new Doctor().removePatient(new Doctor().getPatientByUser(username));
		    		p.addPatient();
		    		new PatientWelcomeGUI(username,password);
		    		frame.dispose();
		    	}
//		    	Adds new patinet
		    	else {
		    		int age = Integer.parseInt(age_text.getText());
		    		double blood_pressure = Double.parseDouble(blood_pressure_text.getText());
		    		double weight = Double.parseDouble(weight_text.getText());
		    		Patient p = new Patient(name_text.getText(), symptoms_text.getText(), age, state_text.getText(), city_text.getText(), gender_text.getText(), date_text.getText(), time_text.getText(), 
		    				blood_pressure, weight , username, password);
		    		p.addPatient();
		    		new PatientWelcomeGUI(username,password);
		    		frame.dispose();
		    	}
		   	  }
		  	});

	 }
//	Establishes connection to database
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
	
//	Checks if username and password are in database
	 public boolean checkUserNameAndPassword() {
	    	Connection connection = connect();
	    	String sql = "SELECT * FROM patients WHERE username = '" + this.username + "'";
	    	
			try {
				Statement statement = connection.createStatement();	
				ResultSet result = statement.executeQuery(sql);
				while(result.next()) {
					String user = result.getString("username");
					String pass = result.getString("password");
					if(this.username.equals(user) && this.password.equals(pass)) {
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
