package controller.regEmployeeControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import controller.IController;
import controller.IControllerParameter;
import controller.regEmployeeControllers.afterTransitionControllers.AddAccountController;
import controller.regEmployeeControllers.afterTransitionControllers.AddClientController;
import controller.regEmployeeControllers.afterTransitionControllers.ProcessBillsController;
import controller.regEmployeeControllers.afterTransitionControllers.ShowClientController;
import controller.regEmployeeControllers.afterTransitionControllers.TransactionController;
import controller.regEmployeeControllers.afterTransitionControllers.UpdateAccountController;
import controller.regEmployeeControllers.afterTransitionControllers.UpdateClientController;
import model.Account;
import model.Client;
import repository.EntityNotFoundException;
import services.account.AccountOperations;
import services.account.AccountService;
import services.client.ClientService;
import services.record.RecordService;
import view.RegEmployeeMenu;

public class RegEmployeeController implements IController {

	private final RegEmployeeMenu regEmployeeMenu;
private final ClientService clientService;
	private final AccountService accountService;
	private final RecordService recordService;
	private IControllerParameter addAccountController;
	private IController addClientController;
	private IControllerParameter processBillsController;
	private IController showClientController;
	private IController transactionController;
	private IControllerParameter updateClientController;
	private IControllerParameter updateAccountController;
	private int selectedRow = 0;
	

	public RegEmployeeController(RegEmployeeMenu regEmployeeMenu,ClientService clientService,AccountService accountService,AccountOperations accountOperations, RecordService recordService
	,IController addClientController,IControllerParameter addAccountController, IControllerParameter processBillsController, IController showClientController
	,IController transactionController, IControllerParameter updateClientController, IControllerParameter updateAccountController)
  {
	this.regEmployeeMenu = regEmployeeMenu;
	this.clientService = clientService;
	this.accountService = accountService;
	this.recordService = recordService;
	this.addAccountController = addAccountController;
	this.addClientController = addClientController;
	this.processBillsController = processBillsController;
	this.transactionController = transactionController;
	this.updateAccountController = updateAccountController;
	this.showClientController = showClientController;
	this.updateClientController = updateClientController;
	this.regEmployeeMenu.setDateTf("2018-05-2");
	regEmployeeMenu.setShowAllListener(new ShowAllListener());
	regEmployeeMenu.setShowAccountsListener(new ShowAccountsListener());
	regEmployeeMenu.setShowAllAccountsListener(new ViewAllAccountsListener());
	regEmployeeMenu.setRemoveAccountListener(new RemoveAccountListener());
	regEmployeeMenu.setAddClientListener(new AddClientTransitionButtonListener());
	regEmployeeMenu.setShowClientListener(new ShowClientTransitionButtonListener());
	regEmployeeMenu.setUpdateClientListener(new UpdateClientTransitionButtonListener());
	regEmployeeMenu.setAddAccountListener(new AddAccountTransitionButtonListener());
	regEmployeeMenu.setUpdateAccountListener(new UpdateAccountTransitionButtonListener());
	regEmployeeMenu.setTransactionListener(new TransactionTransitionButtonListener());
	regEmployeeMenu.setProcessBillListener(new ProcessBillTransitionButtonListener());
	regEmployeeMenu.setTableListenerClients(new TableListenerClients());
	regEmployeeMenu.setTableListenerAccounts(new TableListenerAccounts());
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
	
	class ShowAccountsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			regEmployeeMenu.getAccountsModel().setRowCount(0);
			String clientCnp = regEmployeeMenu.getClients().getValueAt(selectedRow, 2).toString();	
			String currentDate = regEmployeeMenu.getDateTf().getText();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			LocalDate accDate = LocalDate.parse(currentDate, formatter);
			try {
				Long clientId = clientService.findClientByCnp(clientCnp).getId();
				List<Account> accounts = accountService.showAllAccountsOfClient(clientId);
				recordService.addRecord(clientId, "View account", accDate);
				for(Account account:accounts){
					regEmployeeMenu.getAccountsModel().addRow(new Object[] {regEmployeeMenu.getClients().getValueAt(selectedRow, 0),account.getId(),account.getType(), account.getSum(), account.getDate()});
				}
			} catch (EntityNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}
	
	class ViewAllAccountsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			regEmployeeMenu.getAccountsModel().setRowCount(0);
			List<Account> accounts = accountService.showAllAccounts();
			for(Account account:accounts){
				regEmployeeMenu.getAccountsModel().addRow(new Object[] {"Mr/Mrs",account.getId(),account.getType(), account.getSum(), account.getDate()});
			}
		}
	}
	class RemoveAccountListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String accountId = regEmployeeMenu.getAccounts().getValueAt(selectedRow,1).toString();
			String currentDate = regEmployeeMenu.getDateTf().getText();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			LocalDate accDate = LocalDate.parse(currentDate, formatter);
			recordService.addRecord(accountService.findClientId(Long.parseLong(accountId)), "Remove account", accDate);		
			accountService.removeAccount(Long.parseLong(accountId));
		}
		
	}
	class AddClientTransitionButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
		addClientController.activateView();
		}
	}
	
	class ShowClientTransitionButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
		showClientController.activateView();
		}
	}
	class UpdateClientTransitionButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
		updateClientController.activateView(selectedRow);
		}
	}
	class AddAccountTransitionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		addAccountController.activateView(selectedRow);
		}
	}
	
	class UpdateAccountTransitionButtonListener implements ActionListener{

		
		@Override
		public void actionPerformed(ActionEvent e) {
			updateAccountController.activateView(selectedRow);
		}		
	}
	class TransactionTransitionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {	
			transactionController.activateView();
		}
		
	}
	class ProcessBillTransitionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			processBillsController.activateView(selectedRow);
		}
	}
	
	class TableListenerClients implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
				selectedRow = regEmployeeMenu.getClients().rowAtPoint(e.getPoint());
		}
		
		@Override
		public void mousePressed(MouseEvent e) {	
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	class TableListenerAccounts implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
				selectedRow = regEmployeeMenu.getAccounts().rowAtPoint(e.getPoint());
		}
		
		@Override
		public void mousePressed(MouseEvent e) {	
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	



	@Override
	public void activateView() {
		this.regEmployeeMenu.setVisible(true);
		
	}

	



}
