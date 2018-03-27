package controller.regEmployeeControllers.afterTransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import controller.regEmployeeControllers.RegEmployeeController;
import services.account.AccountService;
import view.AddAccount;
import view.RegEmployeeMenu;

public class AddAccountController {
	
			private final AddAccount addAccountView;
			private final AccountService accountService;
			private final RegEmployeeMenu regEmployeeMenu;
			
			
			public AddAccountController(AddAccount addAccountView, AccountService accountService,RegEmployeeMenu regEmployeeMenu, RegEmployeeController regEmployeeController){
				this.addAccountView = addAccountView;
				this.accountService = accountService;
				this.regEmployeeMenu = regEmployeeMenu;
				addAccountView.setAddListener(new AddAccountButtonListener());	
				addAccountView.getClientTf().setText((String) regEmployeeMenu.getClients().getValueAt(regEmployeeController.getSelectedRow(), 2));

			}
			
			class AddAccountButtonListener implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e) {
					String type = addAccountView.getTypeCb().getSelectedItem().toString();
					String sum = addAccountView.getSumTf().getText();
					String date = addAccountView.getDateTf().getText();
					String client = addAccountView.getClientTf().getText();
					
					//String string = "January 2, 2010";
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MM yyyy");
					LocalDate accDate = LocalDate.parse(date, formatter);
					accountService.addAccount(type,Double.parseDouble(sum), accDate, client);
				}
			}

		
}

