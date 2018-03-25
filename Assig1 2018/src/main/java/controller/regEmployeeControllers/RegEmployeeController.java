package controller.regEmployeeControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.Client;
import services.client.ClientService;
import testMain.RegEmployeeComponentFactory;
import view.RegEmployeeMenu;

public class RegEmployeeController {

	private final RegEmployeeMenu regEmployeeMenu;
	private final ClientService clientService;
	
	public RegEmployeeController(RegEmployeeMenu regEmployeeMenu,ClientService clientService) {
			this.regEmployeeMenu = regEmployeeMenu;
			this.clientService = clientService;
			regEmployeeMenu.setShowAllListener(new ShowAllListener());
	}
	
	class ShowAllListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			regEmployeeMenu.getClientsModel().setRowCount(0);
			List<Client> clients = clientService.showAll();
			for(Client client:clients){
				regEmployeeMenu.getClientsModel().addRow(new Object[] {client.getName(), client.getAddress(), client.getCNP(), client.getCardIdNumber()});
			}
		}
		
	}

}
