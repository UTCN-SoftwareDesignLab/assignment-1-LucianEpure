package controller;

import static database.Constants.Roles.ADMINISTRATOR;
import static database.Constants.Roles.REGEMPLOYEE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.adminControllers.AdminController;
import controller.regEmployeeControllers.RegEmployeeController;
import model.Employee;
import services.employee.AuthenticationService;
import services.record.RecordService;
import validators.Notification;
import view.LoginForm;

public class AuthenticationController {
	    private final LoginForm loginForm;
	    private final AuthenticationService authenticationService;
	    private final RecordService recordService;
	    //private final RegEmployeeMenu regEmployeeMenu;
	    //private final AdministratorMenu administratorMenu;
	    private final AdminController administrationController;
	    private final RegEmployeeController regEmployeeController;
	    private Long employeeId;
	  
	   
	    public AuthenticationController(LoginForm loginForm, AuthenticationService authenticationService, RecordService recordService, AdminController administrationController, RegEmployeeController regEmployeeController) {
	        this.loginForm = loginForm;
	        this.authenticationService = authenticationService;
	        this.recordService = recordService;
	        this.administrationController = administrationController;
	        this.regEmployeeController = regEmployeeController;
	        loginForm.setLoginButtonListener(new LoginButtonListener());
	        loginForm.setRegisterButtonListener(new RegisterButtonListener());
	    }

	    public void decideWhichView(Employee employee){
			if(employee.getRoles().get(0).getRoleTitle().equalsIgnoreCase(ADMINISTRATOR)){
				administrationController.activate();
			}
			
			else if(employee.getRoles().get(0).getRoleTitle().equalsIgnoreCase(REGEMPLOYEE)){	
				regEmployeeController.activate();
				recordService.setEmployeeId(employeeId);
			}
		}
	    private class LoginButtonListener implements ActionListener {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String username = loginForm.getUsername();
	            String password = loginForm.getPassword();
	           
	            Notification<Employee> loginNotification = authenticationService.login(username, password);
	            if (loginNotification.hasErrors()) {
	                JOptionPane.showMessageDialog(loginForm.getContentPane(), loginNotification.getFormattedErrors());
	            } else {
	                JOptionPane.showMessageDialog(loginForm.getContentPane(), "Login successful!");
	                employeeId = authenticationService.obtainId(username);  //id of the employee, I send it to throught Dec controller to be set in the coressponding factory
	                decideWhichView(loginNotification.getResult());
	                loginForm.dispose();
	            }
	        }      
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
