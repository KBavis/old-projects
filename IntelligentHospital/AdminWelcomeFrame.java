
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


// Admin Welcome Frame Implemtnation
public class AdminWelcomeFrame extends JFrame implements ActionListener{
//	Container
	Container container=getContentPane();
//	Buttons
	JButton logout_button;
	JButton add_button;
	JButton remove_button;
	JButton view_button;
//	Label
	JLabel titleLabel = new JLabel("Welcome Admin");
	public AdminWelcomeFrame(String username, String password, JButton logout_button, JButton add_button, JButton remove_button, JButton view_button)
    {
	   this.logout_button = logout_button;
	   this.view_button = view_button;
	   this.add_button = add_button;
	   this.remove_button = remove_button;
    	
       //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        editFont();
        addComponentsToContainer();
//        addActionListeners();
 
    }
   public void setLayoutManager()
   {
       container.setLayout(null);
   }
 //Setting location and Size of each components using setBounds() method.
   public void setLocationAndSize()
   {
       //Setting location and Size of each components using setBounds() method.
	   titleLabel.setBounds(60,50,300,30);
	   add_button.setBounds(40,145,275,50);
	   remove_button.setBounds(40,240,275,50);
	   view_button.setBounds(40,345,275,50);
	   logout_button.setBounds(40,445,275,50);
 
   
   }
//   Method to edit our fonts
   public void editFont() {
	   titleLabel.setFont(new Font("Verdana", Font.BOLD, 25));
	   Font font = titleLabel.getFont();
	   Map attributes = font.getAttributes();
	   attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	   titleLabel.setFont(font.deriveFont(attributes));
	   titleLabel.setForeground(new Color(51,153,255));
	   add_button.setFont(new Font("Arial", Font.PLAIN, 18));
	   logout_button.setFont(new Font("Arial", Font.PLAIN, 18));
	   remove_button.setFont(new Font("Arial", Font.PLAIN, 18));
	   view_button.setFont(new Font("Arial", Font.PLAIN, 18));
   }
//   Adds eleemnts to our contianer
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
	   container.add(titleLabel);
	   container.add(add_button);
	   container.add(logout_button);
	   container.add(remove_button);
	   container.add(view_button);

   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
