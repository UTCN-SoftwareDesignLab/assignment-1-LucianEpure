package controller.regEmployeeControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Client;
import model.Employee;
import repository.EntityNotFoundException;
import services.client.ClientService;
import validators.Notification;
import view.RegEmployeeMenu;
import view.UpdateClientView;

public class UpdateClientController {

	private final UpdateClientView updateClientView;
	private final RegEmployeeMenu regEmployeeMenu;
	private final ClientService clientService;
	
	public UpdateClientController( UpdateClientView updateClientView, RegEmployeeMenu regEmployeeMenu,ClientService clientService){
		this.updateClientView = updateClientView;
		this.regEmployeeMenu = regEmployeeMenu;
		this.clientService = clientService;
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
				try {
					Client client = clientService.findClientByCnp(cnp);
					System.out.println("out"+client.getId());
					Notification<Boolean> updateNotification = clientService.updateClient(client,name,address,cardId);
		            if (updateNotification.hasErrors()) {
		                JOptionPane.showMessageDialog(regEmployeeMenu.getContentPane(), updateNotification.getFormattedErrors());
		            } else {
		                JOptionPane.showMessageDialog(regEmployeeMenu.getContentPane(), "Update successful!");
		            }
				} catch (NumberFormatException | EntityNotFoundException e1) {
					// TODO Auto-generated catch block
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
