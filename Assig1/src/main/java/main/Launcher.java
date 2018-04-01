package main;
import controller.AuthenticationController;

import view.LoginForm;


public class Launcher {

    public static void main(String[] args) {
    	
    	
		
        //ComponentFactory componentFactory = ComponentFactory.instance();
        //AdminComponentFactory adminComponentFactory = AdminComponentFactory.instance();
        //RegEmployeeComponentFactory regEmployeeComponentFactory = RegEmployeeComponentFactory.instance();
        ControllerFactory controllerFactory = ControllerFactory.instance();
    	LoginForm window = new LoginForm();
		window.getFrmLogin().setVisible(true);	
        new AuthenticationController(window, controllerFactory.getComponentFactory().getAuthenticationService(),
        		controllerFactory.getComponentFactory().getRecordService(),
        		controllerFactory.getAdminController(),
        		controllerFactory.getRegEmployeeController());
        

    }

}
