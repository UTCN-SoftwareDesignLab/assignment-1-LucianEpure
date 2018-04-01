package main;

import view.AdministratorMenu;

public class AdminViewFactory {
	private static AdminViewFactory instance;

	
	private AdministratorMenu administratorMenu;

	

	public static AdminViewFactory instance() {
			
        if (instance == null) {
            instance = new AdminViewFactory();
        }
        return instance;
    }
	
	
	public AdminViewFactory(){
		 this.administratorMenu = new AdministratorMenu();
	}
	
	public AdministratorMenu getAdministratorMenu() {
		return administratorMenu;
	}

}
