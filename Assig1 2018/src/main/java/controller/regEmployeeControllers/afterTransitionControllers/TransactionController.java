package controller.regEmployeeControllers.afterTransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.account.AccountService;
import view.TransactionView;

public class TransactionController {

	private final TransactionView transactionView;
	private final AccountService accountService;
	public TransactionController(TransactionView transactionView,AccountService accountService){
		
		this.transactionView = transactionView;
		this.accountService = accountService;
		transactionView.setProcess(new ProcessTransactionListener());
	}
	
	class ProcessTransactionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
