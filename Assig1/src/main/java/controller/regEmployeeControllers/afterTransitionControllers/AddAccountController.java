package controller.regEmployeeControllers.afterTransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import controller.IController;
import controller.IControllerParameter;
import repository.EntityNotFoundException;
import services.account.AccountService;
import services.client.ClientService;
import services.record.RecordService;
import validators.Notification;
import view.AddAccount;
import view.RegEmployeeMenu;

public class AddAccountController implements IControllerParameter{
	
			private final AddAccount addAccountView;
			public AddAccount getAddAccountView() {
				return addAccountView;
			}

			private final AccountService accountService;
			private final RecordService recordService;
			private final ClientService clientService;
			private final RegEmployeeMenu regEmployeeMenu;
			
			
			public AddAccountController(AddAccount addAccountView, AccountService accountService,ClientService clientService,RecordService recordService, RegEmployeeMenu regEmployeeMenu){
				this.addAccountView = addAccountView;
				this.accountService = accountService;
				this.clientService = clientService;
				this.recordService = recordService;
				this.regEmployeeMenu = regEmployeeMenu;
			  addAccountView.setAddListener(new AddAccountButtonListener());	
			}
			
			class AddAccountButtonListener implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e) {
					String type = addAccountView.getTypeCb().getSelectedItem().toString();
					String sum = addAccountView.getSumTf().getText();
					String date = addAccountView.getDateTf().getText();
					String cnp = addAccountView.getClientTf().getText();
					
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
					
					
						LocalDate accDate = LocalDate.parse(date, formatter);
				
					
					try {
						Notification<Boolean> addAccountNotification = accountService.addAccount(type,Double.parseDouble(sum), accDate, clientService.findClientByCnp(cnp).getId());
						recordService.addRecord(clientService.findClientByCnp(cnp).getId(), "Account added", accDate);
					
					if (addAccountNotification.hasErrors()) {
		                JOptionPane.showMessageDialog(addAccountView.getContentPane(), addAccountNotification.getFormattedErrors());
		            } else {
		                JOptionPane.showMessageDialog(addAccountView.getContentPane(), "Account added!");   
		               addAccountView.dispose();
		            }
					} catch (EntityNotFoundException e1) {
						JOptionPane.showMessageDialog(addAccountView.getContentPane(), "Client not found!");   
					}
				}
			}
			@Override
			public void activateView(int selectedRow){
				addAccountView.setClientTf(regEmployeeMenu.getClients().getValueAt(selectedRow, 2).toString());	
				addAccountView.setVisible(true);
			}

			
}

