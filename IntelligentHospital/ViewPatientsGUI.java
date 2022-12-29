import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Implemetnation of our view patients GUI for our docotr
public class ViewPatientsGUI extends JFrame implements ActionListener{
//	Frame
	ViewPatientsFrame frame;
//	Buttons
	JButton backButton = new JButton("BACK");
//	Doctors password and username
	String username;
	String password;
	public ViewPatientsGUI(String username, String password){
		frame=new ViewPatientsFrame(username, password, backButton);
		frame.setTitle("Bavis Hospital View Patients");
        frame.setVisible(true);
        frame.setBounds(1000,400,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	      
	}
	
//	Adds action listners to button
	 public void addActionListeners() {
		  backButton.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	 frame.dispose();
		    	 new DoctorWelcomeGUI(username,password);
		   	  }
		  	});

	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
