package controller.regEmployeeControllers.afterTransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import controller.regEmployeeControllers.RegEmployeeController;
import model.Account;
import model.Client;
import repository.EntityNotFoundException;
import services.account.AccountService;
import validators.Notification;
import view.RegEmployeeMenu;
import view.UpdateAccountView;

public class UpdateAccountController {

	private final AccountService accountService;
	private final UpdateAccountView updateAccount;
	private final RegEmployeeMenu regEmployeeMenu;
	private final RegEmployeeController regEmployeeController;
	private String accountId;
	
	public UpdateAccountController(RegEmployeeMenu regEmployeeMenu, AccountService accountService, UpdateAccountView updateAccount, RegEmployeeController regEmployeeController){
		this.accountService = accountService;
		this.regEmployeeMenu = regEmployeeMenu;
		this.updateAccount = updateAccount;
		this.regEmployeeController = regEmployeeController;
		accountId = regEmployeeMenu.getAccounts().getValueAt(regEmployeeController.getSelectedRow(), 1).toString();
		updateAccount.setUpdateButton(new UpdateAccountButtonListener());
		updateAccount.setSumTf(regEmployeeMenu.getAccounts().getValueAt(regEmployeeController.getSelectedRow(), 3).toString());
		updateAccount.setDateTf( regEmployeeMenu.getAccounts().getValueAt(regEmployeeController.getSelectedRow(),4).toString());

		
		
	}
	
	class UpdateAccountButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		String type = updateAccount.getTypeCb().getSelectedItem().toString();
		String sum = updateAccount.getSumTf().getText();
		String date = updateAccount.getDateTf().getText();
		
			Account account;
			try {
				account = accountService.findById(Long.parseLong(accountId));
			
			

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			LocalDate accDate = LocalDate.parse(date, formatter);
			Notification<Boolean> updateNotification = accountService.updateAccount(account,type,Double.parseDouble(sum),accDate);
            if (updateNotification.hasErrors()) {
                JOptionPane.showMessageDialog(updateAccount.getContentPane(), updateNotification.getFormattedErrors());
            } else {
                JOptionPane.showMessageDialog(updateAccount.getContentPane(), "Update successful!");
            }
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(updateAccount.getContentPane(),"Sum is a number");

			} catch (EntityNotFoundException e1) {
				JOptionPane.showMessageDialog(updateAccount.getContentPane(),"Account not found");

			}
			updateAccount.dispose();
		
		}
		
	}
	
}
