package testMain;


import view.AddAccount;
import view.AddClientView;
import view.ProcessBillsView;
import view.RegEmployeeMenu;
import view.ShowClient;
import view.TransactionView;
import view.UpdateAccountView;
import view.UpdateClientView;

public class RegEmpViewFactory {

private static RegEmpViewFactory instance;

	
	private RegEmployeeMenu regEmployeeMenu;
	private AddAccount addAccount;
	private AddClientView addClientView;
	private ShowClient showClient;
	private TransactionView transactionView;
	private ProcessBillsView processBills;
	private UpdateAccountView updateAccountView;
	private UpdateClientView updateClientView;

	public static RegEmpViewFactory instance() {
			
        if (instance == null) {
            instance = new RegEmpViewFactory();
        }
        return instance;
    }
	
	
	public RegEmpViewFactory(){
		 this.regEmployeeMenu = new RegEmployeeMenu();
		 this.addAccount = new AddAccount();
		 this.addClientView = new AddClientView();
		 this.showClient = new ShowClient();
		 this.transactionView = new TransactionView();
		 this.processBills = new ProcessBillsView();
		 this.updateAccountView = new UpdateAccountView();
		 this.updateClientView = new UpdateClientView();
	}
	
	public static RegEmpViewFactory getInstance() {
		return instance;
	}


	public RegEmployeeMenu getRegEmployeeMenu() {
		return regEmployeeMenu;
	}


	public AddAccount getAddAccount() {
		return addAccount;
	}


	public AddClientView getAddClientView() {
		return addClientView;
	}


	public ShowClient getShowClient() {
		return showClient;
	}


	public TransactionView getTransactionView() {
		return transactionView;
	}


	public ProcessBillsView getProcessBills() {
		return processBills;
	}


	public UpdateAccountView getUpdateAccountView() {
		return updateAccountView;
	}


	public UpdateClientView getUpdateClientView() {
		return updateClientView;
	}


	
}
