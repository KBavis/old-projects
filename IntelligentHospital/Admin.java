import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Functionality of our applications ADMIN
public class Admin {
	private String user_name;
	private String password;
	public Admin() {
		this.user_name = "";
		this.password= "";
	}
	public Admin(String user_name, String password) {
		this.user_name = user_name;
		this.password = password;
	}
//	Accessors and mutators 
	public String getUser() {
		return this.user_name;
	}
	public String getPassword() {
		return this.password;
	}
	public void setUser(String user_name) {
		this.user_name = user_name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//Establishes connection to our database
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
//	Method to add a dcotor to our database 
	public void addDoctor(Doctor doctor) {
		try {
			Connection connection = connect();
			String sql = "INSERT INTO doctors (first_name, last_name, user_name, password) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, doctor.get_first_name());
			statement.setString(2, doctor.get_last_name());
			statement.setString(3, doctor.getUser());
			statement.setString(4, doctor.getPassword());

			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			System.out.println("Error[-]");
			e.printStackTrace();
		}
	}
//Method to remvoe a doctor from our database
	public void removeDoctor(Doctor doctor) {
			try {
				Connection connection = connect();			
				String sql = "DELETE FROM doctors WHERE user_name = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1,doctor.getUser());
				statement.executeUpdate();
				statement.close();
			}catch(SQLException e) {
				System.out.println("Error[-]");
				e.printStackTrace();
			}
	}
//	Method to get the list of dcotors that are currently in our database (used by View All Doctors button on Admin GUI)
	public List<Doctor> getAllDoctors() {
    	String sql = "SELECT * FROM doctors";
    	Doctor doc = null;
    	List<Doctor> doctors = new ArrayList<Doctor>();
    	try {
        	Connection connection = connect();
    		Statement statement = connection.createStatement();	
    		ResultSet result = statement.executeQuery(sql);
    		while(result.next()) {
    			String first_name = result.getString("first_name");
    			String last_name = result.getString("last_name");
    			String user_name = result.getString("user_name");
    			String password = result.getString("password");
    			doc = new Doctor(first_name,last_name,user_name,password);
    			doctors.add(doc);
    		}
    	}
    	catch(SQLException e) {
    		System.out.println("Error[-]");
    		e.printStackTrace();
    	}
    	return doctors;
    }
	
	 
    //Method to return a given patient based on the patients name 
    public Doctor getDoctorByName(String first_name, String last_name) {
    	String sql = "SELECT * FROM doctors WHERE first_name = '" + first_name + "'" + " AND last_name = '" + last_name + "'";
    	Doctor temp = null;
    	try {
    	Connection connection = connect();
		Statement statement = connection.createStatement();	
		ResultSet result = statement.executeQuery(sql);
		while(result.next()) {
			String user_name = result.getString("user_name");
			String password = result.getString("password");
			temp = new Doctor(first_name,last_name,user_name,password);
			return temp;
		}
    	}
    	catch(SQLException e) {
    		System.out.println("Error[-]");
    		e.printStackTrace();
    	}
    	return temp;
    }
	@Override
	public String toString()
	{
		return "User: " + this.user_name + ", Password: " + this.password;
	}
}
