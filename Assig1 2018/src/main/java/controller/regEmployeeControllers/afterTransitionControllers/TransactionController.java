package controller.regEmployeeControllers.afterTransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.DecisionController;
import model.Employee;
import repository.EntityNotFoundException;
import services.account.AccountService;
import validators.Notification;
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
			String idFrom = transactionView.getFromTf().getText();
			String idTo = transactionView.getToTf().getText();
			String sum = transactionView.getSumTf().getText();
		
			try {
				Notification<Boolean> transferNotification = accountService.performTransaction(Long.parseLong(idFrom), Long.parseLong(idTo), Double.parseDouble(sum));
				 if (transferNotification.hasErrors()) {
		                JOptionPane.showMessageDialog(transactionView.getContentPane(), transferNotification.getFormattedErrors());
		            } else {
		                JOptionPane.showMessageDialog(transactionView.getContentPane(), "Transfer successful!");
		                transactionView.dispose();
		            }
			
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(transactionView.getContentPane(),"Sum is a number");

			} catch (EntityNotFoundException e1) {
				JOptionPane.showMessageDialog(transactionView.getContentPane(),"Account not found");

			}
		}

	}	
	
}
