package controller.regEmployeeControllers.TransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.regEmployeeControllers.AddClientController;
import testMain.RegEmployeeComponentFactory;
import view.AddClientView;
import view.RegEmployeeMenu;

public class TransitionController {

	private final RegEmployeeComponentFactory regEmployeeComponentFactory;
	public TransitionController(RegEmployeeMenu regEmployeeMenu,
			RegEmployeeComponentFactory regEmployeeComponentFactory) {
			this.regEmployeeComponentFactory = regEmployeeComponentFactory;
			regEmployeeMenu.setAddClientListener(new AddClientTransitionButtonListener());
	}
	
	class AddClientTransitionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		AddClientView addClientView = new AddClientView();
		addClientView.setVisible(true);
		new AddClientController(addClientView,regEmployeeComponentFactory.getClientService());
		}
	}
	


	

}
