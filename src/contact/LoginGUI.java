package contact;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * @author Javier Bryan 620119423
 * @author Tashae Bowes 620116906
 * @version 1.0
 *
 */

public class LoginGUI extends JFrame {
	private  LogBook logBook;
	private static String username=null, password=null;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Sets Up the LoginGUI user interface
	 */
	public LoginGUI() {
		logBook = new LogBook();
		setTitle("Login");
		
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 14));
		setBounds(400, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(120, 67, 62, 14);
		getContentPane().add(usernameLabel);
		usernameLabel.setForeground(Color.green);
		
		JTextField usernameTextField = new JTextField();
		usernameTextField.setBounds(210, 64, 109, 20);
		getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(120, 92, 62, 14);
		getContentPane().add(passwordLabel);
		passwordLabel.setForeground(Color.GREEN);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(210, 89, 109, 20);
		getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(165, 140, 100, 25);	
		loginButton.setBackground(Color.CYAN);
		
		ActionListener buttonClick = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int state=0;
				username = usernameTextField.getText();
				password = String.valueOf(passwordField.getPassword());
				
				state = logBook.login(username, password);
				switch(state) {
					case -1:
						JOptionPane.showMessageDialog(null, "Restart and try again", "Locked Out",JOptionPane.ERROR_MESSAGE);
						state =-1;
						break;
					case 0:
						state =0;
						dispose();
						new GUI();
						break;
					case 1:
						if(logBook.getAttemptsRemaining()==0) {
							JOptionPane.showMessageDialog(null, "Restart and try again", "Locked Out",JOptionPane.ERROR_MESSAGE);
							break;
						}
						state =1;
						String errorMessage = "Username or password incorrect\n"+logBook.getAttemptsRemaining()+ " remaining";
						JOptionPane.showMessageDialog(null, errorMessage, "Login Failed",JOptionPane.ERROR_MESSAGE);
						break;
				}
			}
		};
		
		KeyListener enterToLogin = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					int state=0;
					username = usernameTextField.getText();
					password = String.valueOf(passwordField.getPassword());
					
					state = logBook.login(username, password);
					switch(state) {
						case -1:
							JOptionPane.showMessageDialog(null, "Restart and try again", "Locked Out",JOptionPane.ERROR_MESSAGE);
							state =-1;
							break;
						case 0:
							state =0;
							dispose();
							new GUI();
							break;
						case 1:
							if(logBook.getAttemptsRemaining()==0) {
								JOptionPane.showMessageDialog(null, "Restart and try again", "Locked Out",JOptionPane.ERROR_MESSAGE);
								break;
							}
							state =1;
							String errorMessage = "Username or password incorrect\n"+logBook.getAttemptsRemaining()+ " remaining";
							JOptionPane.showMessageDialog(null, errorMessage, "Login Failed",JOptionPane.ERROR_MESSAGE);
							break;
					}
			
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		loginButton.addActionListener(buttonClick);
		loginButton.addKeyListener(enterToLogin);
		getContentPane().add(loginButton);
		
		getContentPane().setBackground(Color.GRAY);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
