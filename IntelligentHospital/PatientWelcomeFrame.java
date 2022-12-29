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


//Implementation of our patient welcome frame
public class PatientWelcomeFrame extends JFrame implements ActionListener{
//	Container
	Container container=getContentPane();
//	Button
	JButton register_button;
	JButton logout_button;
	JButton view_results_button;
//	Labels
	JLabel titleLabel = new JLabel("Welcome");
	public PatientWelcomeFrame(String username, String password, JButton register_button, JButton logout_button, JButton view_results_button)
    {
	   this.register_button = register_button;
	   this.logout_button = logout_button;
	   this.view_results_button = view_results_button;
        setLayoutManager();
        setLocationAndSize();
        editFont();
        addComponentsToContainer();
 
    }
   public void setLayoutManager()
   {
       container.setLayout(null);
   }
//   Sets locations 
   public void setLocationAndSize()
   {
       //Setting location and Size of each components using setBounds() method.
	   titleLabel.setBounds(110,50,300,30);
	   register_button.setBounds(40,150,275,50);
	   view_results_button.setBounds(40,250,275,50);
	   logout_button.setBounds(40,350,275,50);
 
   
   }
//   Edits fonts
   public void editFont() {
	   titleLabel.setFont(new Font("Verdana", Font.BOLD, 25));
	   Font font = titleLabel.getFont();
	   Map attributes = font.getAttributes();
	   attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	   titleLabel.setFont(font.deriveFont(attributes));
	   titleLabel.setForeground(Color.RED);
	   register_button.setFont(new Font("Arial", Font.PLAIN, 18));
	   logout_button.setFont(new Font("Arial", Font.PLAIN, 18));
	   view_results_button.setFont(new Font("Arial", Font.PLAIN, 18));
   }
//   Adds compoentns to container
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
	   container.add(titleLabel);
	   container.add(register_button);
	   container.add(logout_button);
	   container.add(view_results_button);

   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
