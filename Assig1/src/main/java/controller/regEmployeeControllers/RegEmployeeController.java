package controller.regEmployeeControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
import view.AddAccount;
import view.AddClientView;
import view.ProcessBillsView;
import view.RegEmployeeMenu;
import view.ShowClient;
import view.TransactionView;
import view.UpdateAccountView;
import view.UpdateClientView;

public class RegEmployeeController {

	private final RegEmployeeMenu regEmployeeMenu;
private final ClientService clientService;
	private final AccountService accountService;
	private final RecordService recordService;
	private AddAccountController addAccountController;
	private AddClientController addClientController;
	private ProcessBillsController processBillsController;
	private ShowClientController showClientController;
	private TransactionController transactionController;
	private UpdateClientController updateClientController;
	private UpdateAccountController updateAccountController;
	private int selectedRow = 0;
	

	/*public RegEmployeeController(RegEmployeeMenu regEmployeeMenu,ClientService clientService,AccountService accountService,AccountOperations accountOperations, RecordService recordService
			,AddClientView addClientView, ShowClient showClient, UpdateClientView updateClientView, AddAccount addAccount
			,UpdateAccountView updateAccountView, TransactionView transactionView, ProcessBillsView processBillsView) {
			this.regEmployeeMenu = regEmployeeMenu;
			this.clientService = clientService;
			this.accountService = accountService;
			this.recordService = recordService;
			this.addClientView = addClientView;
			this.showClient = showClient;
			this.updateClientView = updateClientView;
			this.addAccount = addAccount;
			this.updateAccountView = updateAccountView;
			this.transactionView = transactionView;
			this.processBillsView = processBillsView;
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
	}*/
	public RegEmployeeController(RegEmployeeMenu regEmployeeMenu,ClientService clientService,AccountService accountService,AccountOperations accountOperations, RecordService recordService
	,AddClientController addClientController,AddAccountController addAccountController, ProcessBillsController processBillsController, ShowClientController showClientController
	,TransactionController transactionController, UpdateClientController updateClientController, UpdateAccountController updateAccountController)
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
			
			List<Account> accounts = accountService.showAllAccountsOfClient(clientCnp);
			try {
				recordService.addRecord(clientService.findClientByCnp(clientCnp).getId(), "View account", accDate);
			} catch (EntityNotFoundException e1) {
				e1.printStackTrace();
			}
			for(Account account:accounts){
				regEmployeeMenu.getAccountsModel().addRow(new Object[] {regEmployeeMenu.getClients().getValueAt(selectedRow, 0),account.getId(),account.getType(), account.getSum(), account.getDate()});
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
		addClientController.getAddClientView().setVisible(true);
		}
	}
	
	class ShowClientTransitionButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
		showClientController.getShowClientView().setVisible(true);
		}
	}
	class UpdateClientTransitionButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
		updateClientController.getUpdateClientView().setAddressTf(regEmployeeMenu.getClients().getValueAt(selectedRow, 1).toString());
		updateClientController.getUpdateClientView().setNameTf(regEmployeeMenu.getClients().getValueAt(selectedRow, 0).toString());
		updateClientController.getUpdateClientView().setCnpTf(regEmployeeMenu.getClients().getValueAt(selectedRow, 2).toString());
		updateClientController.getUpdateClientView().setCardIdTf(regEmployeeMenu.getClients().getValueAt(selectedRow, 3).toString());
		updateClientController.getUpdateClientView().setVisible(true);
		}
	}
	class AddAccountTransitionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		addAccountController.getAddAccountView().setClientTf(regEmployeeMenu.getClients().getValueAt(selectedRow, 2).toString());	
		addAccountController.getAddAccountView().setVisible(true);
		}
	}
	
	class UpdateAccountTransitionButtonListener implements ActionListener{

		
		@Override
		public void actionPerformed(ActionEvent e) {
			updateAccountController.getUpdateAccount().setAccountIdTf(regEmployeeMenu.getAccounts().getValueAt(selectedRow,1).toString());
			updateAccountController.getUpdateAccount().setSumTf(regEmployeeMenu.getAccounts().getValueAt(selectedRow,3).toString());
			updateAccountController.getUpdateAccount().setVisible(true);
		}		
	}
	class TransactionTransitionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {	
			transactionController.getTransactionView().setVisible(true);
		}
		
	}
	class ProcessBillTransitionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			processBillsController.getProcessBillsView().setVisible(true);
	}
	}
	
	class TableListenerClients implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
				setSelectedRow(regEmployeeMenu.getClients().rowAtPoint(e.getPoint()));
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
				setSelectedRow(regEmployeeMenu.getAccounts().rowAtPoint(e.getPoint()));
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
	
	
	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}
	public RegEmployeeMenu getRegEmployeeMenu() {
		return regEmployeeMenu;
	}


	



}
