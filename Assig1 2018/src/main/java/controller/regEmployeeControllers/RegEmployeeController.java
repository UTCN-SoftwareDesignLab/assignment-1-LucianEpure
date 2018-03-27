package controller.regEmployeeControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import controller.regEmployeeControllers.TransitionControllers.TransitionController;
import model.Account;
import model.Client;
import services.account.AccountService;
import services.client.ClientService;
import view.RegEmployeeMenu;

public class RegEmployeeController {

	private final RegEmployeeMenu regEmployeeMenu;
	private final ClientService clientService;
	private final AccountService accountService;
	private int selectedRow = 0;
	private int selectedCol = 3;
	
	public RegEmployeeController(RegEmployeeMenu regEmployeeMenu,ClientService clientService,AccountService accountService) {
			this.regEmployeeMenu = regEmployeeMenu;
			this.clientService = clientService;
			this.accountService = accountService;
			regEmployeeMenu.setShowAllListener(new ShowAllListener());
			regEmployeeMenu.setTableListenerClients(new TableListenerClients());
			regEmployeeMenu.setTableListenerAccounts(new TableListenerAccounts());
			regEmployeeMenu.setShowAccountsListener(new ShowAccountsListener());
			regEmployeeMenu.setRemoveAccountsListener(new RemoveAccountListener());
			new TransitionController(regEmployeeMenu, clientService, accountService, this);

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
			String clientCnp = regEmployeeMenu.getClients().getValueAt(selectedRow, selectedCol).toString();
			List<Account> accounts = accountService.showAll(clientCnp);
			for(Account account:accounts){
				regEmployeeMenu.getAccountsModel().addRow(new Object[] {regEmployeeMenu.getClients().getValueAt(selectedRow, 1),account.getId(),account.getType(), account.getSum(), account.getDate()});
			}
		}
	}
	class RemoveAccountListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String accountId = regEmployeeMenu.getAccounts().getValueAt(selectedRow, selectedCol).toString();
			System.out.println(accountId);
			accountService.removeAccount(Long.parseLong(accountId));
		}
		
	}
	
	
	class TableListenerClients implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
				setSelectedRow(regEmployeeMenu.getClients().rowAtPoint(e.getPoint()));
				setSelectedCol(regEmployeeMenu.getClients().columnAtPoint(e.getPoint()));
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
				setSelectedCol(regEmployeeMenu.getAccounts().columnAtPoint(e.getPoint()));
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
	
	public int getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}

	public int getSelectedCol() {
		return selectedCol;
	}

	public void setSelectedCol(int selectedCol) {
		this.selectedCol = selectedCol;
	}

}
