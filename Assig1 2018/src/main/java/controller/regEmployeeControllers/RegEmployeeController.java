package controller.regEmployeeControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import controller.regEmployeeControllers.TransitionControllers.TransitionController;
import model.Client;
import services.client.ClientService;
import view.RegEmployeeMenu;

public class RegEmployeeController {

	private final RegEmployeeMenu regEmployeeMenu;
	private final ClientService clientService;
	private int selectedRow = 0;
	private int selectedCol = 0;
	
	public RegEmployeeController(RegEmployeeMenu regEmployeeMenu,ClientService clientService) {
			this.regEmployeeMenu = regEmployeeMenu;
			this.clientService = clientService;
			regEmployeeMenu.setShowAllListener(new ShowAllListener());
			regEmployeeMenu.setTableListener(new TableListener());
	        new TransitionController(regEmployeeMenu, clientService, this);
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
	class TableListener implements MouseListener{
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
