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

public class AuthenticationController implements IController{
	    private final LoginForm loginForm;
	    private final AuthenticationService authenticationService;
	    private final RecordService recordService;
	    private final IController administrationController;
	    private final IController regEmployeeController;
	    private Long employeeId;
	  
	   
	    public AuthenticationController(LoginForm loginForm, AuthenticationService authenticationService, RecordService recordService, IController administrationController, IController regEmployeeController) {
	        this.loginForm = loginForm;
	        this.authenticationService = authenticationService;
	        this.recordService = recordService;
	        this.administrationController = administrationController;
	        this.regEmployeeController = regEmployeeController;
	        loginForm.setLoginButtonListener(new LoginButtonListener());
	        loginForm.setRegisterButtonListener(new RegisterButtonListener());
	        this.activateView();
	      
	    }

	    public void decideWhichView(Employee employee){
			if(employee.getRoles().get(0).getRoleTitle().equalsIgnoreCase(ADMINISTRATOR)){
				administrationController.activateView();
			}
			
			else if(employee.getRoles().get(0).getRoleTitle().equalsIgnoreCase(REGEMPLOYEE)){	
				regEmployeeController.activateView();
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

		@Override
		public void activateView() {
			loginForm.getFrmLogin().setVisible(true);
		}
}
