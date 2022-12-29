import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


//GUI For Patinet to view diagnosis 
public class DiagnosisGUI extends JFrame implements ActionListener{
//	Frmae
	DiagnosisGUIFrame frame;
//	Button
	JButton back_button = new JButton("BACK");
//	Username and Password for patient
	String username;
	String password;
	
	public DiagnosisGUI(String username, String password){
		this.username = username;
		this.password = password;
		frame=new DiagnosisGUIFrame(username, password, back_button);
		frame.setTitle("Bavis Hospital: View Results");
        frame.setVisible(true);
        frame.setBounds(1000,400,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionListeners();
	      
	}
	
//	Adds Action Listeners to our 
	 public void addActionListeners() {
//		 Brings back to patient welcome page
		  back_button.addActionListener(new ActionListener() {
		      @Override
		      	public void actionPerformed(ActionEvent e) {
		    	 frame.dispose();
		    	 new PatientWelcomeGUI(username,password);
		   	  }
		  	});

	 }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
