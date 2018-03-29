package controller.regEmployeeControllers.afterTransitionControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.Client;
import repository.EntityNotFoundException;
import services.client.ClientService;
import services.record.RecordService;
import view.RegEmployeeMenu;
import view.ShowClient;


public class ShowClientController {
	
	private final ShowClient showClientView;
	private final RegEmployeeMenu regEmployeeMenu;
	private final ClientService clientService;
	private final RecordService recordService;
	
	public ShowClientController(ShowClient showClientView, RegEmployeeMenu regEmployeeMenu, ClientService clientService, RecordService recordService){
		this.showClientView = showClientView;
		this.regEmployeeMenu = regEmployeeMenu;
		this.clientService = clientService;
		this.recordService = recordService;
		showClientView.setShowClientListener(new ShowClientButtonListener());
	}
	
	class ShowClientButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			regEmployeeMenu.getClientsModel().setRowCount(0);
			String date = regEmployeeMenu.getDateTf().getText();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			LocalDate accDate = LocalDate.parse(date, formatter);
				
			String CNP = showClientView.getCnpTf().getText();
			try {
				Client client = clientService.findClientByCnp(CNP);
				recordService.addRecord(client.getId(), "View client", accDate);
				regEmployeeMenu.getClientsModel().addRow(new Object[]{client.getName(), client.getAddress(), client.getCNP(), client.getCardIdNumber()});
			} catch (EntityNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
