package controller.regEmployeeControllers.afterTransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import controller.IController;
import repository.EntityNotFoundException;
import services.account.AccountOperations;
import services.record.RecordService;
import validators.Notification;
import view.RegEmployeeMenu;
import view.TransactionView;

public class TransactionController implements IController{

	private final TransactionView transactionView;
private final AccountOperations accountOperations;
	private final RecordService recordService;
	private final RegEmployeeMenu regEmployeeMenu;
	
	public TransactionController(TransactionView transactionView,AccountOperations accountOperations, RecordService recordService, RegEmployeeMenu regEmployeeMenu){
		
		this.transactionView = transactionView;
		this.accountOperations = accountOperations;
		this.recordService = recordService;
		this.regEmployeeMenu = regEmployeeMenu;
		transactionView.setProcess(new ProcessTransactionListener());
	}
	
	class ProcessTransactionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String idFrom = transactionView.getFromTf().getText();
			String idTo = transactionView.getToTf().getText();
			String sum = transactionView.getSumTf().getText();
			String currentDate = regEmployeeMenu.getDateTf().getText();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			LocalDate accDate = LocalDate.parse(currentDate, formatter);
			try {
				Notification<Boolean> transferNotification = accountOperations.performTransaction(Long.parseLong(idFrom), Long.parseLong(idTo), Double.parseDouble(sum));
				 if (transferNotification.hasErrors()) {
		                JOptionPane.showMessageDialog(transactionView.getContentPane(), transferNotification.getFormattedErrors());
		            } else {
		                JOptionPane.showMessageDialog(transactionView.getContentPane(), "Transfer successful!");
		                recordService.addRecord((accountOperations.getAccountsClientId(Long.parseLong(idFrom))), "Transaction done!", accDate);
		                transactionView.dispose();
		            }
			
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(transactionView.getContentPane(),"Sum is a number");

			} catch (EntityNotFoundException e1) {
				JOptionPane.showMessageDialog(transactionView.getContentPane(),"Account not found");

			}
		}

	}	

	public void activateView(){
		transactionView.setVisible(true);
	}
	
}
