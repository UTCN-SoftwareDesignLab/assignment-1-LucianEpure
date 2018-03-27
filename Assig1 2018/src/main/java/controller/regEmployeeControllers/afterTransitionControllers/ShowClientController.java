package controller.regEmployeeControllers.afterTransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Client;
import repository.EntityNotFoundException;
import services.client.ClientService;
import view.RegEmployeeMenu;
import view.ShowClient;


public class ShowClientController {
	
	private final ShowClient showClientView;
	private final RegEmployeeMenu regEmployeeMenu;
	private final ClientService clientService;
	
	public ShowClientController(ShowClient showClientView, RegEmployeeMenu regEmployeeMenu, ClientService clientService){
		this.showClientView = showClientView;
		this.regEmployeeMenu = regEmployeeMenu;
		this.clientService = clientService;
		showClientView.setShowClientListener(new ShowClientButtonListener());
	
	}
	
	class ShowClientButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String CNP = showClientView.getCnpTf().getText();
			try {
				Client client = clientService.findClientByCnp(CNP);
				regEmployeeMenu.getClientsModel().addRow(new Object[]{client.getName(), client.getAddress(), client.getCNP(), client.getCardIdNumber()});
			} catch (EntityNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
