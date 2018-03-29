package controller.regEmployeeControllers.afterTransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import model.Client;
import repository.EntityNotFoundException;
import services.client.ClientService;
import services.record.RecordService;
import validators.Notification;
import view.RegEmployeeMenu;
import view.UpdateClientView;

public class UpdateClientController {

	private final UpdateClientView updateClientView;
	private final RegEmployeeMenu regEmployeeMenu;
	private final ClientService clientService;
	private final RecordService recordService;
	
	public UpdateClientController( UpdateClientView updateClientView, RegEmployeeMenu regEmployeeMenu,ClientService clientService, RecordService recordService){
		this.updateClientView = updateClientView;
		this.regEmployeeMenu = regEmployeeMenu;
		this.clientService = clientService;
		this.recordService = recordService;
		updateClientView.setUpdateActionListener(new UpdateClientButtonListener());
		updateClientView.setGenerateCardIdActionListener(new GenerateCardIdButtonListener());
	}
	
	class UpdateClientButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = updateClientView.getNameTf().getText();
			String address = updateClientView.getAddressTf().getText();
			String cnp = updateClientView.getCnpTf().getText();
			String cardId = updateClientView.getCardIdTf().getText();
			String date = regEmployeeMenu.getDateTf().getText();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			LocalDate accDate = LocalDate.parse(date, formatter);
				
					Client client;
					try {
						client = clientService.findClientByCnp(cnp);
					    Notification<Boolean> updateNotification = clientService.updateClient(client,name,address,cardId);
					    recordService.addRecord(client.getId(), "Client updated", accDate);
		            if (updateNotification.hasErrors()) {
		                JOptionPane.showMessageDialog(updateClientView.getContentPane(), updateNotification.getFormattedErrors());
		            } else {
		                JOptionPane.showMessageDialog(updateClientView.getContentPane(), "Update successful!");
		            }
					} catch (EntityNotFoundException e1) {
						JOptionPane.showMessageDialog(updateClientView.getContentPane(), "Client not found!");
						e1.printStackTrace();
					}
				
		
		}
		
	}
	
	class GenerateCardIdButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Long cardId = clientService.generateCardIdNumber();
			updateClientView.setCardIdTf(String.valueOf(cardId));
		}
		
	}
}
