package view;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class LoginForm extends JFrame{

	private JFrame frmLogin;

	private JTextField usernameTf;

	private JButton loginBtn;
	private JButton registerBtn;
	private JTextField passwordTf;

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 314, 157);
		frmLogin.getContentPane().setLayout(null);
		
		usernameTf = new JTextField();
		usernameTf.setBounds(96, 11, 163, 20);
		frmLogin.getContentPane().add(usernameTf);
		usernameTf.setColumns(10);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(25, 14, 61, 14);
		frmLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(25, 44, 61, 17);
		frmLogin.getContentPane().add(lblPassword);
		
		loginBtn = new JButton("Login");
		loginBtn.setBounds(35, 73, 89, 23);
		frmLogin.getContentPane().add(loginBtn);
		
		registerBtn = new JButton("New Admin");
		registerBtn.setBounds(149, 73, 110, 23);
		frmLogin.getContentPane().add(registerBtn);
		
		passwordTf = new JTextField();
		passwordTf.setBounds(96, 42, 163, 20);
		frmLogin.getContentPane().add(passwordTf);
		passwordTf.setColumns(10);
	}
	public String getUsername(){
		return usernameTf.getText();
	}
	public String getPassword(){
		return passwordTf.getText();
	}
	
	public JFrame getFrmLogin() {
		return frmLogin;
	}

	public void setFrmLogin(JFrame frmLogin) {
		this.frmLogin = frmLogin;
	}
	
	  public void setLoginButtonListener(ActionListener loginButtonListener) {
	        loginBtn.addActionListener(loginButtonListener);
	    }
	  public void setRegisterButtonListener(ActionListener registerButtonListener){
		  registerBtn.addActionListener(registerButtonListener);
	  }
}
