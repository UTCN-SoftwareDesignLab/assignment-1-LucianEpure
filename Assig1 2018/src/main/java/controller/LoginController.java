package controller;

import validators.Notification;
import services.employee.AuthenticationService;
import services.employee.EmployeeService;
import view.AdministratorMenu;
import view.LoginForm;

import javax.swing.*;

import model.Employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginController {
    private final LoginForm loginForm;
    private final AuthenticationService authenticationService;


    public LoginController(LoginForm loginForm, AuthenticationService authenticationService) {
        this.loginForm = loginForm;
        this.authenticationService = authenticationService;
        loginForm.setLoginButtonListener(new LoginButtonListener());
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
                System.out.println(loginNotification.getResult().getRoles().get(0).getRoleTitle());
                DecisionController.decideWhichView(loginNotification.getResult(), authenticationService);
                loginForm.dispose();
            }
        }      
    }
}
