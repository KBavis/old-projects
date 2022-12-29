



import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//Implemtnation of our Admin Removal GUI
public class AdminRemoveGUI {
//	Frame
	AdminRemoveFrame frame;
//	Buttons
	JButton remove_button = new JButton("REMOVE");
	JButton back_button = new JButton("BACK");
//	Text fields
	JTextField doctor = new JTextField();
//	Admin username and password
	String username;
	String password;
	public AdminRemoveGUI(String username, String password) {
		this.username = username;
		this.password = password;
		frame = new AdminRemoveFrame(doctor, remove_button, back_button);
		frame.setTitle("Bavis Hospital: Remove Doctors");
        frame.setVisible(true);
        frame.setBounds(1000,400,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	}
	
//	Method that adds our action listenrs to our buttons
	public void addActionListeners() {
//		Ensures validity of entered information and executes removal
		  remove_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  String[] name = doctor.getText().split(" ");
		    	  if(new Admin().getDoctorByName(name[0],name[1]) == null) {
		    		  JOptionPane.showMessageDialog(frame, "Please Enter A Valid Doctor Name.", "Error", JOptionPane.ERROR_MESSAGE);
		    		  doctor.setText("");
		    	  }
		    	  else {
		    		  Doctor removed_doctor = new Admin().getDoctorByName(name[0], name[1]);
		    		  new Admin().removeDoctor(removed_doctor);
		    		  JOptionPane.showMessageDialog(frame, "Doctor Succesfully Removed From System.", "Success", JOptionPane.INFORMATION_MESSAGE);
		    		  doctor.setText("");
		    	  }
		   	  }
		  	});
//		  Back Button takes back to Admin Welcome Page
		  back_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	  new AdminWelcomeGUI(username, password);
		    	  frame.dispose();
		   	  }
		  	});

	 }
	//@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
}
