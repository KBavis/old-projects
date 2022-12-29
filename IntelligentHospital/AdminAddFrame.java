
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


//Implementation of our frame for our Admin to add more doctors 
public class AdminAddFrame extends JFrame implements ActionListener {
	 Container container= getContentPane();
//	 Buttons
	  JButton submit_button;
	  JButton back_button;
//	  Labels
	  JLabel titleLabel = new JLabel("ADD DOCTOR");
	  JLabel first_name_label = new JLabel("FIRST NAME");
	  JLabel last_name_label = new JLabel("LAST NAME");
	  JLabel user_label = new JLabel("USERNAME");
	  JLabel pass_label = new JLabel("PASSWORD");
//	  Text Fields
	  JTextField first_name_text;
	  JTextField last_name_text;
	  JTextField user_text;
	  JTextField pass_text;
	  AdminAddFrame(JButton submit_button, JButton back_button, JTextField first_name_text, JTextField last_name_text, JTextField user_text, JTextField pass_text)
	  {
	  	  this.submit_button = submit_button;
	  	  this.back_button = back_button;
	  	  this.first_name_text = first_name_text;
	  	  this.last_name_text = last_name_text;
	  	  this.user_text = user_text;
	  	  this.pass_text = pass_text;
	     //Calling methods inside constructor.
	      setLayoutManager();
	      setLocationAndSize();
	      editFont();
	      addComponentsToContainer();

	  }
	 public void setLayoutManager()
	 {
	     container.setLayout(null);
	 }
     //Setting location and Size of each components using setBounds() method.
	 public void setLocationAndSize()
	 {
		 
//		 Labels
		 titleLabel.setBounds(160,50,200,30);
		 first_name_label.setBounds(40,150,150,40);
		 last_name_label.setBounds(40,210,150,40);
		 user_label.setBounds(40,270,150,40);
		 pass_label.setBounds(40,330,150,40);

//		 Text Fields
		 first_name_text.setBounds(230,155,200,27);
		 last_name_text.setBounds(230, 215, 200, 27);
		 user_text.setBounds(230, 275, 200, 27);
		 pass_text.setBounds(230, 335, 200, 27);
		 
//		 Button
		 submit_button.setBounds(170,490,190,40);
		 back_button.setBounds(170,430,190,40);

	 }
//	 Method for editing fonts of our labels and text fields 
	 public void editFont() {
		   Font fontOne = new Font("Time New Roman", Font.PLAIN,16);
		   titleLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		   first_name_label.setFont(new Font("Verdana", Font.BOLD, 17));
		   last_name_label.setFont(new Font("Verdana", Font.BOLD, 17));
		   user_label.setFont(new Font("Verdana", Font.BOLD, 17));
		   pass_label.setFont(new Font("Verdana", Font.BOLD, 17));
		   
//		   Underlining Our Title
		   Font font = titleLabel.getFont();
		   Map attributes = font.getAttributes();
		   attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		   titleLabel.setFont(font.deriveFont(attributes));
		   titleLabel.setForeground(Color.BLUE);

//		   Setting Our Textbox Fonts For User Entering Information
		   first_name_text.setFont(fontOne);
		   last_name_text.setFont(fontOne);
		   user_text.setFont(fontOne);
		   pass_text.setFont(fontOne);
		   
	 }
//	 Method to add our components to our contianer
	 public void addComponentsToContainer()
	 {
//		 Adding Labels
	    container.add(titleLabel);
	    container.add(first_name_label);
	    container.add(last_name_label);
	    container.add(user_label);
	    container.add(pass_label);
	    
//	    Adding Text Boxes
	    container.add(first_name_text);
	    container.add(last_name_text);
	    container.add(user_text);
	    container.add(pass_text);

//		adding buttons
	    container.add(submit_button);
	    container.add(back_button);
	 }

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
}