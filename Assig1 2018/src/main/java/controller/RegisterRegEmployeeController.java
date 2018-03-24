package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import services.employee.AuthenticationService;
import validators.Notification;
import view.AdministratorMenu;

public class RegisterRegEmployeeController {
	private final AdministratorMenu administratorMenu;
	private final AuthenticationService authenticationService;
	
	public RegisterRegEmployeeController(AdministratorMenu administratorMenu, AuthenticationService authenticationService){
		this.administratorMenu = administratorMenu;
		this.authenticationService = authenticationService;
		administratorMenu.setRegisterRegEmployeeButtonListener(new RegisterRegEmployeeButtonListener());
	}
	
	class RegisterRegEmployeeButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			 String username = administratorMenu.getUsername();
	         String password = administratorMenu.getPassword();
			
	         Notification<Boolean> registerNotification = authenticationService.registerEmployee(username, password);
	            if (registerNotification.hasErrors()) {
	                JOptionPane.showMessageDialog(administratorMenu.getContentPane(), registerNotification.getFormattedErrors());
	            } else {
	                JOptionPane.showMessageDialog(administratorMenu.getContentPane(), "Register successful!");
	                administratorMenu.setUsernameTf("");
	                administratorMenu.setPasswordTf("");
	            }
		}
		
	}
}
