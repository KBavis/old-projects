import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Implemeneation of viewing all our doctors for admin
public class ViewDoctorsGUI extends JFrame implements ActionListener{
	ViewDoctorsFrame frame;
	JButton backButton = new JButton("BACK");
	String username;
	String password;
	public ViewDoctorsGUI(String username, String password){
		frame=new ViewDoctorsFrame(username, password, backButton);
		frame.setTitle("Bavis Hospital View Doctors");
        frame.setVisible(true);
        frame.setBounds(1000,400,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	      
	}
	
//	adds action listeners
	 public void addActionListeners() {
		  backButton.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	 frame.dispose();
		    	 new AdminWelcomeGUI(username,password);
		   	  }
		  	});

	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}