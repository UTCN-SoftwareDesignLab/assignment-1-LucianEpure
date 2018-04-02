package controller.regEmployeeControllers.afterTransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import controller.IControllerParameter;
import repository.EntityNotFoundException;
import services.account.AccountService;
import services.record.RecordService;
import validators.Notification;
import view.RegEmployeeMenu;
import view.UpdateAccountView;

public class UpdateAccountController implements IControllerParameter{

	private final AccountService accountService;
	private final UpdateAccountView updateAccount;
private final RecordService recordService;
	private final RegEmployeeMenu regEmployeeMenu;

	
	public UpdateAccountController(RegEmployeeMenu regEmployeeMenu, AccountService accountService, UpdateAccountView updateAccount,RecordService recordService){
		this.accountService = accountService;
		this.updateAccount = updateAccount;
		this.recordService = recordService;
		this.regEmployeeMenu = regEmployeeMenu;
		updateAccount.setUpdateButton(new UpdateAccountButtonListener());
	}
	
	class UpdateAccountButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		String accountId = updateAccount.getAccountIdTf().getText();
		String type = updateAccount.getTypeCb().getSelectedItem().toString();
		String sum = updateAccount.getSumTf().getText();
		String date = updateAccount.getDateTf().getText();
		String currentDate = regEmployeeMenu.getDateTf().getText();
			
			try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			LocalDate accDate = LocalDate.parse(date, formatter);
			LocalDate recordDate = LocalDate.parse(currentDate, formatter);
			Notification<Boolean> updateNotification = accountService.updateAccount(accountService.findById(Long.parseLong(accountId)),type,Double.parseDouble(sum),accDate);
			recordService.addRecord(accountService.findClientId(Long.parseLong(accountId)), "Updated account", recordDate);
			
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

	public void activateView(int selectedRow){
		updateAccount.setAccountIdTf(regEmployeeMenu.getAccounts().getValueAt(selectedRow,1).toString());
		updateAccount.setSumTf(regEmployeeMenu.getAccounts().getValueAt(selectedRow,3).toString());
		updateAccount.setVisible(true);
		
	}

	
	
}
