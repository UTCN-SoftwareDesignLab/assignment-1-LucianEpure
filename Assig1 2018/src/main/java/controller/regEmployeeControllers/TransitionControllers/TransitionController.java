package controller.regEmployeeControllers.TransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.regEmployeeControllers.RegEmployeeController;
import controller.regEmployeeControllers.afterTransitionControllers.AddAccountController;
import controller.regEmployeeControllers.afterTransitionControllers.AddClientController;
import controller.regEmployeeControllers.afterTransitionControllers.ShowClientController;
import controller.regEmployeeControllers.afterTransitionControllers.UpdateClientController;
import services.account.AccountService;
import services.client.ClientService;
import testMain.RegEmployeeComponentFactory;
import view.AddAccount;
import view.AddClientView;
import view.RegEmployeeMenu;
import view.ShowClient;
import view.UpdateClientView;

public class TransitionController {


	private final RegEmployeeMenu regEmployeeMenu;
	private final ClientService clientService;
	private final AccountService accountService;
	private final RegEmployeeController regEmployeeController;

	public TransitionController(RegEmployeeMenu regEmployeeMenu,
			ClientService clientService, AccountService accountService,RegEmployeeController regEmployeeController) {
			this.regEmployeeMenu = regEmployeeMenu;
			this.clientService = clientService;
			this.accountService = accountService;
			this.regEmployeeController = regEmployeeController;
			regEmployeeMenu.setAddClientListener(new AddClientTransitionButtonListener());
			regEmployeeMenu.setShowClientListener(new ShowClientTransitionButtonListener());
			regEmployeeMenu.setUpdateClientListener(new UpdateClientTransitionButtonListener());
			regEmployeeMenu.setAddAccountListener(new AddAccountTransitionButtonListener());
	}
	
	class AddClientTransitionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		AddClientView addClientView = new AddClientView();
		addClientView.setVisible(true);
		new AddClientController(addClientView,clientService);
		}
	}
	
	class ShowClientTransitionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		ShowClient showClientView = new ShowClient();
		showClientView.setVisible(true);
		new ShowClientController(showClientView,regEmployeeMenu,clientService);
		}
	}
	class UpdateClientTransitionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		UpdateClientView updateClientView = new UpdateClientView();
		updateClientView.setVisible(true);
		new UpdateClientController(updateClientView, regEmployeeMenu,clientService,regEmployeeController);
		}
		
	}
	class AddAccountTransitionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		AddAccount addAccountView = new AddAccount();
		addAccountView.setVisible(true);
		new AddAccountController(addAccountView,accountService, regEmployeeMenu,regEmployeeController);
		}
	}


	

}
