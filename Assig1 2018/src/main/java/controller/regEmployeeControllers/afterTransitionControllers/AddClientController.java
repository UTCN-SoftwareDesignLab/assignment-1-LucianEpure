package controller.regEmployeeControllers.afterTransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import services.client.ClientService;
import services.employee.AuthenticationService;
import validators.Notification;
import view.AddClientView;
import view.AdministratorMenu;
import view.RegEmployeeMenu;

    public class AddClientController {
	private final AddClientView addClientView;
	private final ClientService clientService;
	
	public AddClientController(AddClientView addClientView, ClientService clientService){
		this.addClientView = addClientView;
		this.clientService = clientService;
		addClientView.setAddClientListener(new AddClientButtonListener());
		addClientView.setGenerateCardId(new GenerateIdListener());
	}
	
	class AddClientButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String name = addClientView.getClientNameTf().getText();
			String address = addClientView.getClientAddressTf().getText();
			String cnp = addClientView.getCnpTf().getText();
			String cardIdNumber = addClientView.getCardIdTf().getText();
			
			Notification<Boolean> clientNotification = clientService.addClient(name, address, cnp, cardIdNumber);
	            if (clientNotification.hasErrors()) {
	                JOptionPane.showMessageDialog(addClientView.getContentPane(), clientNotification.getFormattedErrors());
	            } else {
	                JOptionPane.showMessageDialog(addClientView.getContentPane(), "Register successful!");
	                
	            }
		}
	}
	class GenerateIdListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String cardNumberId = String.valueOf(clientService.generateCardIdNumber());
			addClientView.getCardIdTf().setText(cardNumberId);
		}
	}
}
