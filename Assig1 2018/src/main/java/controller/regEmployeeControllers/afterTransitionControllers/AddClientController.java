package controller.regEmployeeControllers.afterTransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import repository.EntityNotFoundException;
import services.client.ClientService;
import services.record.RecordService;
import validators.Notification;
import view.AddClientView;
import view.RegEmployeeMenu;

    public class AddClientController {
	private final AddClientView addClientView;
	private final ClientService clientService;
	private final RegEmployeeMenu regEmployeeMenu;
	private final RecordService recordService;
	
	public AddClientController(RegEmployeeMenu regEmployeeMenu, AddClientView addClientView, ClientService clientService,RecordService recordService){
		this.addClientView = addClientView;
		this.clientService = clientService;
		this.regEmployeeMenu = regEmployeeMenu;
		this.recordService = recordService;
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
			String date = regEmployeeMenu.getDateTf().getText();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			LocalDate accDate = LocalDate.parse(date, formatter);
			
			Notification<Boolean> clientNotification = clientService.addClient(name, address, cnp, cardIdNumber);
			
			try {
				recordService.addRecord(clientService.findClientByCnp(cnp).getId(), "Client added", accDate);
			} catch (EntityNotFoundException e1) {
			}
			
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
