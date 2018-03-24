package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Employee;
import services.employee.AuthenticationService;
import validators.Notification;
import view.LoginForm;

public class RegisterController {

	private final LoginForm loginForm;
	private final AuthenticationService authenticationService;
	
	public RegisterController(LoginForm loginForm, AuthenticationService authenticationService){
		this.loginForm = loginForm;
		this.authenticationService = authenticationService;
		loginForm.setRegisterButtonListener(new RegisterButtonListener());
	}
	
	class RegisterButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			 String username = loginForm.getUsername();
	         String password = loginForm.getPassword();
			
	         Notification<Boolean> registerNotification = authenticationService.registerAdministrator(username, password);
	            if (registerNotification.hasErrors()) {
	                JOptionPane.showMessageDialog(loginForm.getContentPane(), registerNotification.getFormattedErrors());
	            } else {
	                JOptionPane.showMessageDialog(loginForm.getContentPane(), "Register successful!");
	            }
		}
		
	}
}
