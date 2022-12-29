import java.sql.*;
import java.util.*;
//Implementation of Doctor Functionality
public class Doctor {
    String first_name, last_name, userName, password;
    public Doctor() {
    	this.first_name = "";
    	this.last_name = "";
    	this.userName = "";
    	this.password = "";
    }
    public Doctor(String first_name, String last_name, String userName, String password){
        this.first_name = first_name;
        this.last_name = last_name;
        this.userName = userName;
        this.password = password;
    }
    public String get_first_name(){
        return this.first_name;
    }
    public String get_last_name(){
    	return this.last_name;
    }
    public String getUser(){
        return this.userName;
    }
    public String getPassword(){
        return this.password;
    }
    public void set_first_name(String n){
        this.first_name = n;
    }
    public void set_last_name(String n){
        this.last_name = n;
    }
    public void setUser(String user){
        this.userName = user;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
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
    //Add patient to database
    public void addPatient(Patient patient) {
		try {
			Connection connection = connect();
			String sql = "INSERT INTO patients (name, symptoms, age, gender, date, time, blood_pressure, weight, city, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, patient.getName());
			statement.setString(2, patient.getSymptoms());
			statement.setInt(3, patient.getAge());
			statement.setString(4, patient.getGender());
			statement.setString(5, patient.getDate());
			statement.setString(6, patient.getTime());
			statement.setDouble(7, patient.getBloodPressure());
			statement.setDouble(8, patient.getWeight());
			statement.setString(9, patient.getCity());
			statement.setString(10, patient.getState());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			System.out.println("Error[-]");
			e.printStackTrace();
		}
    }
    
    //Remove patient from database
    public void removePatient(Patient patient) {
		try {
			Connection connection = connect();			
			String sql = "DELETE FROM patients WHERE username = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,patient.getUsername());
			statement.executeUpdate();
			statement.close();
		}catch(SQLException e) {
			System.out.println("Error[-]");
			e.printStackTrace();
		}
    }
    
    
    //Method to set the diagnosis of our patient in our database
    public void diagnose_patient(String diagnosis,Patient p) {
    	Connection connection = connect();
		String sql = "UPDATE patients SET diagnosis = ? WHERE name = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, diagnosis);
			statement.setString(2, p.getName());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    //Method used to obtian the treatment associated with a known disease in our database
    public void treatment_patient(String diagnosis, Patient patient) {
    	Connection connection = connect();
    	String treatment;
		String sql = "UPDATE patients SET treatment = ? WHERE name = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			treatment = getTreatment(diagnosis);
			statement.setString(1, treatment);
			statement.setString(2, patient.getName());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //Method used to set patients treatmenet in database when its prescribed directly by doctor
    public void set_unique_treatment(String treatment, Patient patient) {
    	Connection connection = connect();
		String sql = "UPDATE patients SET treatment = ? WHERE name = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, treatment);
			statement.setString(2, patient.getName());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    //Use patients symptoms to provide a diagnosis
    public String determine_illness(Patient patient){
    	String diagnosis = "";
    	//HashMap for storing the number of times a disease corresponds to the patients symptoms
    	HashMap<String,Integer> diseases = new HashMap<String,Integer>();
    	try {
    		Connection connection = connect();
    		String[] entered_symptoms = patient.getSymptoms().split(",");
    		int maxCount = 1;
    		for(int i = 0; i < entered_symptoms.length; i++) {
    			String sql = "SELECT * FROM diseases WHERE symptoms LIKE '%" + entered_symptoms[i] + "%'";
    			Statement statement = connection.createStatement();	
    			ResultSet result = statement.executeQuery(sql);
    			while(result.next()) {
    				String disease = result.getString("disease_name");
    				if(diseases.containsKey(disease)) {
    					int currentVal = diseases.get(disease);
    					diseases.remove(disease);
    					diseases.put(disease,++currentVal);
    					if(maxCount < currentVal) {
    						maxCount = currentVal;
    						diagnosis = disease;
    					}
    				}
    				else {
    					diseases.put(disease, 1);
    					if(diagnosis == "") {
    						diagnosis = disease;
    					}
    				}
    				System.out.println();
    			}
    		}

    		
    	}catch(SQLException e) {
			System.out.println("Error[-]");
			e.printStackTrace();
		}
    	diseases.clear();
    	return diagnosis;
    }
    
    
    //Method used to obtain the diagnosis we have on record for a patient based on their usernname and password 
    public String get_diagnosis(String username, String password) {
    	Connection connection = connect();
    	String sql = "SELECT * FROM patients WHERE username= '" + username + "'";
    	
		try {
			Statement statement = connection.createStatement();	
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String user = result.getString("username");
				String pass = result.getString("password");
				if(username.equals(user) && pass.equals(password)) {
//					String selected_diagnosis = select_diagnosis(username, password);
					return result.getString("diagnosis");
				}
				else {
					return null;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error[-]");
			e.printStackTrace();
		}
		return null;
    }
    
    
//    Returns Recommended Treatment Based On Diagnosis
    public String getTreatment(String diagnosis) {
    	String treatment = "";
    	try {
    		Connection connection = connect();
    		String sql = "SELECT * FROM diseases WHERE disease_name LIKE '%" + diagnosis + "%'";
    		Statement statement = connection.createStatement();	
    		ResultSet result = statement.executeQuery(sql);
    		result.next();
    		treatment = result.getString("treatments");
    	}catch(SQLException e) {
    		System.out.println("Error[-]");
    		e.printStackTrace();
    	}
    	return treatment;
    }
    
    
    //Returns Treatment Assigned to speciifc user 
    public String get_treatment_by_username(String username) {
    	String treatment = "";
    	try {
    		Connection connection = connect();
    		String sql = "SELECT * FROM patients WHERE username = '" + username + "'";
    		Statement statement = connection.createStatement();	
    		ResultSet result = statement.executeQuery(sql);
    		result.next();
    		treatment = result.getString("treatment");
    	}catch(SQLException e) {
    		System.out.println("Error[-]");
    		e.printStackTrace();
    	}
    	return treatment;
    }
    
    
    //Method to return a given patient based on the patients username on our application
    public Patient getPatientByUser(String username) {
    	String sql = "SELECT * FROM patients WHERE username = '" + username + "'";
    	Patient p = null;
    	try {
    	Connection connection = connect();
		Statement statement = connection.createStatement();	
		ResultSet result = statement.executeQuery(sql);
		while(result.next()) {
			String name = result.getString("name");
			String symptoms = result.getString("symptoms");
			int age = result.getInt("age");
			String state= result.getString("state");
			String city= result.getString("city");
			String gender = result.getString("gender");
			String date = result.getString("date");
			String time = result.getString("time");
			double blood_pressure = result.getDouble("blood_pressure");
			double weight = result.getDouble("weight");
			String password = result.getString("password");
			p = new Patient(name,symptoms,age,state,city,gender,date,time,blood_pressure,weight,username,password);
			return p;
		}
    	}
    	catch(SQLException e) {
    		System.out.println("Error[-]");
    		e.printStackTrace();
    	}
    	return p;
    }
    
    
    //Method to return a given patient based on the patients name 
    public Patient getPatientByName(String name) {
    	String sql = "SELECT * FROM patients WHERE name = '" + name + "'";
    	Patient p = null;
    	try {
    	Connection connection = connect();
		Statement statement = connection.createStatement();	
		ResultSet result = statement.executeQuery(sql);
		while(result.next()) {
			String symptoms = result.getString("symptoms");
			int age = result.getInt("age");
			String state= result.getString("state");
			String city= result.getString("city");
			String gender = result.getString("gender");
			String date = result.getString("date");
			String time = result.getString("time");
			double blood_pressure = result.getDouble("blood_pressure");
			double weight = result.getDouble("weight");
			String username = result.getString("username");
			String password = result.getString("password");
			p = new Patient(name,symptoms,age,state,city,gender,date,time,blood_pressure,weight,username,password);
			return p;
		}
    	}
    	catch(SQLException e) {
    		System.out.println("Error[-]");
    		e.printStackTrace();
    	}
    	return p;
    }
    
    
    //Method to get all of the known patients on record details 
    public List<Patient> getAllPatients() {
    	String sql = "SELECT * FROM patients";
    	Patient p = null;
    	List<Patient> patients = new ArrayList<Patient>();
    	try {
        	Connection connection = connect();
    		Statement statement = connection.createStatement();	
    		ResultSet result = statement.executeQuery(sql);
    		while(result.next()) {
    			String name = result.getString("name");
    			String symptoms = result.getString("symptoms");
    			int age = result.getInt("age");
    			String state= result.getString("state");
    			String city= result.getString("city");
    			String gender = result.getString("gender");
    			String date = result.getString("date");
    			String time = result.getString("time");
    			double blood_pressure = result.getDouble("blood_pressure");
    			double weight = result.getDouble("weight");
    			String username = result.getString("username");
    			String password = result.getString("password");
    			p = new Patient(name,symptoms,age,state,city,gender,date,time,blood_pressure,weight,username,password);
    			patients.add(p);
    		}
    	}
    	catch(SQLException e) {
    		System.out.println("Error[-]");
    		e.printStackTrace();
    	}
    	return patients;
    }
    
//    Methdo to change password
    public void changePassword(String user, String password) {
    	Connection connection = connect();
		String sql = "UPDATE doctors SET password = ? WHERE user_name = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, password);
			statement.setString(2, user);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public String toString(){
        return this.first_name + ", " + this.last_name + ", " + this.userName + ", " + this.password;
    }
}
