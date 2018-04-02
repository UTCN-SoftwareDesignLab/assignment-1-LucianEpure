package controller.adminControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import controller.IController;
import model.Employee;
import model.Record;
import services.employee.AuthenticationService;
import services.employee.EmployeeService;
import services.record.RecordService;
import validators.Notification;
import view.AdministratorMenu;

public class AdminController implements IController{
	private final AdministratorMenu administratorMenu;
	private final EmployeeService employeeService;
	private final AuthenticationService authenticationService;
	private final RecordService recordService;
	private int selectedRow = 0;
	private int selectedCol = 3;

	public AdminController(AdministratorMenu administratorMenu, EmployeeService employeeService,AuthenticationService authenticationService, RecordService recordService){
		this.administratorMenu = administratorMenu;
		//this.administratorMenu.setVisible(true);
		this.employeeService = employeeService;
		this.authenticationService = authenticationService;
		this.recordService = recordService;
		this.administratorMenu.setStartDateTf("2018-05-02");
		this.administratorMenu.setEndDateTf("2018-10-05");
		administratorMenu.setUpdateEmployeeButtonListener(new UpdateRegEmployeeButtonListener());
		administratorMenu.setShowEmployeeButtonListener(new ShowRegEmployeeButtonListener());
		administratorMenu.setShowAllButtonListener(new ShowAllButtonListener());
		administratorMenu.setFireEmployeeButtonListener(new FireEmployeeButtonListener());
		administratorMenu.setRegisterRegEmployeeButtonListener(new RegisterRegEmployeeButtonListener());
		administratorMenu.setGenerateRecordsButtonListener(new GenerateRecordsButtonListener());
		administratorMenu.setTableListener(new TableListenerEmployees());
	}
	
	class UpdateRegEmployeeButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String oldUsername = administratorMenu.getUpdateUsernameTf().getText();
			String newUsername = administratorMenu.getUpdatedUsernameTf().getText();
			String newPassword = administratorMenu.getUpdatedPasswordTf().getText();
			Employee employee = employeeService.findByUsername(oldUsername);
			
			
			Notification<Boolean> updateNotification = employeeService.updateEmployee(employee,newUsername,newPassword);
            if (updateNotification.hasErrors()) {
                JOptionPane.showMessageDialog(administratorMenu.getContentPane(), updateNotification.getFormattedErrors());
            } else {
                JOptionPane.showMessageDialog(administratorMenu.getContentPane(), "Update successful!");
            }
			 administratorMenu.setUpdateUsernameTf("");
			 administratorMenu.setUpdatedUsernameTf("");
			 administratorMenu.setUpdatedPasswordTf("");
			 administratorMenu.getEmployeesModel().setRowCount(0);
		}
	}
	
	class ShowRegEmployeeButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorMenu.getEmployeesModel().setRowCount(0);
			String username = administratorMenu.getShowEmployeeTf().getText();
			Employee employee = employeeService.findByUsername(username);
			administratorMenu.getEmployeesModel().addRow(new Object[] {employee.getId(), employee.getRoles().get(0).getRoleTitle(), employee.getUsername()});
			administratorMenu.setShowEmployeeTf("");
		}
	}
	
	class ShowAllButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorMenu.getEmployeesModel().setRowCount(0);
			List<Employee> employees = employeeService.showAll();
			for(Employee employee:employees)
				administratorMenu.getEmployeesModel().addRow(new Object[] {employee.getId(),employee.getRoles().get(0).getRoleTitle(), employee.getUsername()});
			
		}
	}
	
	class FireEmployeeButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
	
			String id = administratorMenu.getIdTf().getText();
			employeeService.fireEmployeeById(Long.parseLong(id));
			administratorMenu.setIdTf("");
			administratorMenu.setFireEmployee("");
			administratorMenu.getEmployeesModel().setRowCount(0);
		}
		
	}
	
	
	class RegisterRegEmployeeButtonListener implements ActionListener{

		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			 String username = administratorMenu.getUsername();
	         String password = administratorMenu.getPassword();
			
	         Notification<Boolean> registerNotification = authenticationService.registerEmployee(username, password);
	            if (registerNotification.hasErrors()) {
	                JOptionPane.showMessageDialog(administratorMenu.getContentPane(), registerNotification.getFormattedErrors());
	            } else {
	                JOptionPane.showMessageDialog(administratorMenu.getContentPane(), "Register successful!");
	                administratorMenu.setUsernameTf("");
	                administratorMenu.setPasswordTf("");
	            }
		}
		
	}
	
	class GenerateRecordsButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			String date1 = administratorMenu.getStartDateTf().getText();
			String date2 = administratorMenu.getEndDateTf().getText();
			String employeeId = administratorMenu.getEmployees().getValueAt(selectedRow, 0).toString();
			if(employeeId == null){
                JOptionPane.showMessageDialog(administratorMenu.getContentPane(), "Select employee!");
			}
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			LocalDate accDate1 = LocalDate.parse(date1, formatter);
			LocalDate accDate2 = LocalDate.parse(date2, formatter);
			
			List<Record> records = recordService.generateRecords(Long.parseLong(employeeId), accDate1, accDate2);
			for(Record record: records){
				administratorMenu.getRecordsModel().addRow(new Object[] {record.getEmployeeId(),record.getClientId(),record.getOperationName(),record.getDate()});
			}

			
		}
		
	}
	class TableListenerEmployees implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
				selectedRow = administratorMenu.getEmployees().rowAtPoint(e.getPoint());
				selectedCol = administratorMenu.getEmployees().columnAtPoint(e.getPoint());
				administratorMenu.setFireEmployee(administratorMenu.getEmployees().getValueAt(selectedRow, 2).toString());
				administratorMenu.setIdTf(administratorMenu.getEmployees().getValueAt(selectedRow, 0).toString());
				administratorMenu.setUpdateUsernameTf(administratorMenu.getEmployees().getValueAt(selectedRow, 2).toString());
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
	class TableListenerRecords implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
				selectedRow = administratorMenu.getRecords().rowAtPoint(e.getPoint());
				selectedCol = administratorMenu.getRecords().columnAtPoint(e.getPoint());
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
		
	public void activateView(){
		this.administratorMenu.setVisible(true);
	}

}
