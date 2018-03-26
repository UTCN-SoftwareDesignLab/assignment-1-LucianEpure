package controller.regEmployeeControllers.TransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.regEmployeeControllers.AddClientController;
import controller.regEmployeeControllers.ShowClientController;
import controller.regEmployeeControllers.UpdateClientController;
import testMain.RegEmployeeComponentFactory;
import view.AddClientView;
import view.RegEmployeeMenu;
import view.ShowClient;
import view.UpdateClientView;

public class TransitionController {

	private final RegEmployeeComponentFactory regEmployeeComponentFactory;
	private final RegEmployeeMenu regEmployeeMenu;
	public TransitionController(RegEmployeeMenu regEmployeeMenu,
			RegEmployeeComponentFactory regEmployeeComponentFactory) {
			this.regEmployeeMenu = regEmployeeMenu;
			this.regEmployeeComponentFactory = regEmployeeComponentFactory;
			regEmployeeMenu.setAddClientListener(new AddClientTransitionButtonListener());
			regEmployeeMenu.setShowClientListener(new ShowClientTransitionButtonListener());
			regEmployeeMenu.setUpdateClientListener(new UpdateClientTransitionButtonListener());
	}
	
	class AddClientTransitionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		AddClientView addClientView = new AddClientView();
		addClientView.setVisible(true);
		new AddClientController(addClientView,regEmployeeComponentFactory.getClientService());
		}
	}
	
	class ShowClientTransitionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		ShowClient showClientView = new ShowClient();
		showClientView.setVisible(true);
		new ShowClientController(showClientView,regEmployeeMenu,regEmployeeComponentFactory.getClientService());
		}
	}
	class UpdateClientTransitionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		UpdateClientView updateClientView = new UpdateClientView();
		updateClientView.setVisible(true);
		new UpdateClientController(updateClientView, regEmployeeMenu,regEmployeeComponentFactory.getClientService());
		}
		
	}


	

}
