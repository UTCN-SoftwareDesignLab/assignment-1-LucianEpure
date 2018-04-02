package controller.regEmployeeControllers.afterTransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

import controller.IControllerParameter;
import repository.EntityNotFoundException;
import services.account.AccountOperations;
import services.client.ClientService;
import services.record.RecordService;
import validators.Notification;
import view.ProcessBillsView;
import view.RegEmployeeMenu;

public class ProcessBillsController implements IControllerParameter{
	
	private final AccountOperations accountOperations;
	private final ClientService clientService;
	private final ProcessBillsView processBillsView;
	private final RegEmployeeMenu regEmployeeMenu;
	private final RecordService recordService;
	
	public ProcessBillsController(AccountOperations accountOperations, ClientService clientService, ProcessBillsView processBillsView, RegEmployeeMenu regEmployeeMenu,  RecordService recordService){
		this.accountOperations = accountOperations;
		this.clientService = clientService;
		this.processBillsView = processBillsView;
		this.regEmployeeMenu = regEmployeeMenu;
		this.recordService = recordService;
		processBillsView.setPayBillListener(new PayButtonListener());
	}

	 class PayButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
				String clientCnp = processBillsView.getClientTf().getText();
				String accountId = processBillsView.getAccountIdTf().getText();
				String sum = processBillsView.getSumTf().getText();
				String billNumber = processBillsView.getBillNumberTf().getText();
				
				String currentDate = regEmployeeMenu.getDateTf().getText();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
				LocalDate accDate = LocalDate.parse(currentDate, formatter);
				Long clientId;
				try {
					clientId = clientService.findClientByCnp(clientCnp).getId();
				
				
				Notification<Boolean> payNotification = accountOperations.processBill(billNumber, clientId, Long.parseLong(accountId), Double.parseDouble(sum));
				recordService.addRecord(clientId, "Bill payed!", accDate);
				if (payNotification.hasErrors()) {
		                JOptionPane.showMessageDialog(processBillsView.getContentPane(), payNotification.getFormattedErrors());
		            } else {
		                JOptionPane.showMessageDialog(processBillsView.getContentPane(), "Bill payed!");
		                processBillsView.dispose();
		            }
				} catch (EntityNotFoundException e1) {
	                JOptionPane.showMessageDialog(processBillsView.getContentPane(), "Customer not right!");
				}
		}
		 
	 }

	public void activateView(int selectedRow){
		processBillsView.setClientTf(regEmployeeMenu.getClients().getValueAt(selectedRow, 2).toString());
		processBillsView.setVisible(true);
	}
}
