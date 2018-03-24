package testMain;
import controller.LoginController;
import controller.RegisterController;
import view.LoginForm;


public class Launcher {

    public static void main(String[] args) {
    	
    	
		
        ComponentFactory componentFactory = ComponentFactory.instance();
      
    	LoginForm window = new LoginForm();
		window.getFrmLogin().setVisible(true);	
        new LoginController(window, componentFactory.getAuthenticationService());
        new RegisterController(window, componentFactory.getAuthenticationService());

    }

}
