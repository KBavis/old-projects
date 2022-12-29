import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


//Funcitonality of our patient 
public class Patient {
    String name;
    String symptoms;
    int age;
    String state;
    String city;
    String gender;
    String date;
    String time;
    double bloodPressure;
    double weight;
    String username;
    String password;
    public Patient( String name, String symptoms,int age,String state, String city,String gender,String date, String time, double bloodPressure, double weight, String username, String password){
        this.name = name;
        this.symptoms = symptoms;
        this.age = age;
        this.state = state;
        this.city = city;
        this.gender = gender;
        this.date = date;
        this.time = time;
        this.bloodPressure = bloodPressure;
        this.weight = weight;
        this.username = username;
        this.password = password;
    }
    //Accessors & Mutators
    public String getName(){
        return this.name;
    }
    public String getSymptoms(){
    	return this.symptoms;
    }
    public int getAge(){
        return this.age;
    }
    public String getState(){
        return this.state;
    }
    public String getCity() {
    	return this.city;
    }
    public String getGender(){
        return this.gender;
    }
    public String getDate() {
    	return this.date;
    }
    public String getTime(){
        return this.time;
    }
    public double getBloodPressure(){
        return this.bloodPressure;
    }
    public double getWeight(){
        return this.weight;
    }
    public String getUsername() {
    	return this.username;
    }
    public String getPassword() {
    	return this.password;
    }
    public void setName(String n){
        this.name = n;
    }
    public void setSymptoms(String symps){
        this.symptoms = symps;
    }
    public void setAge(int a){
        this.age = a;
    }
    public void setState(String state) {
    	this.state = state;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setGender(String gen){
        this.gender = gen;
    }
    public void setDate(String date) {
    	this.date = date;
    }
    public void setTime(String t){
        this.time = t;
    }
    public void setBloodPressure(double bp){
        this.bloodPressure = bp;
    }
    public void setWeight(double w){
        this.weight = w;
    }
    public void setUsername(String user) {
    	this.username = user;
    }
    public void setPassword(String pass) {
    	this.password = pass;
    }
//    Establishes conneciton to database
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
//    Adds patient to database
    public void addPatient() {
		try {
			Connection connection = connect();
			String sql = "INSERT INTO patients (name, symptoms, age, gender, date, time, blood_pressure, weight, city, state, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			
			statement.setString(1, this.getName());
			statement.setString(2, this.getSymptoms());
			statement.setInt(3, this.getAge());
			statement.setString(4, this.getGender());
			statement.setString(5, this.getDate());
			statement.setString(6, this.getTime());
			statement.setDouble(7, this.getBloodPressure());
			statement.setDouble(8, this.getWeight());
			statement.setString(9, this.getCity());
			statement.setString(10, this.getState());
			statement.setString(11, this.getUsername());
			statement.setString(12, this.getPassword());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			System.out.println("Error[-]");
			e.printStackTrace();
		}
    }
//    Updates patinet in database
    public void update() {
    	Connection connection = connect();
    	String sql = "UPDATE patients SET name = ?, symptoms = ?, age = ?, gender = ?, date = ?, time = ?, blood_pressure = ?, weight = ?, city = ?, state = ? WHERE username = " + this.username;
    	try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, this.name);
			statement.setString(2, this.symptoms);
			statement.setInt(3, this.age);
			statement.setString(4, this.gender);
			statement.setString(5, this.date);
			statement.setString(6, this.time);
			statement.setDouble(7, this.bloodPressure);
			statement.setDouble(8, this.weight);
			statement.setString(9, this.city);
			statement.setString(10, this.state);
			statement.setString(11, this.username);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public String toString(){
        return this.name + " " + this.age + " " + this.city + " " + this.state + " " + this.gender +  " " + this.symptoms + " " + this.date + " " + this.time + " " + this.bloodPressure + " " + this.weight
        		+ " " + this.username + " " + this.password;
    }

}
