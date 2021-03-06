package main;

import controller.IController;
import controller.IControllerParameter;
import controller.adminControllers.AdminController;
import controller.regEmployeeControllers.RegEmployeeController;
import controller.regEmployeeControllers.afterTransitionControllers.AddAccountController;
import controller.regEmployeeControllers.afterTransitionControllers.AddClientController;
import controller.regEmployeeControllers.afterTransitionControllers.ProcessBillsController;
import controller.regEmployeeControllers.afterTransitionControllers.ShowClientController;
import controller.regEmployeeControllers.afterTransitionControllers.TransactionController;
import controller.regEmployeeControllers.afterTransitionControllers.UpdateAccountController;
import controller.regEmployeeControllers.afterTransitionControllers.UpdateClientController;

public class ControllerFactory {
	private static ControllerFactory instance;
	
	private AdminViewFactory adminViewFactory;
	private ComponentFactory componentFactory;
	private RegEmpViewFactory regEmpViewFactory;
	private IController adminController;
	private IController regEmployeeController;
	
	
	private IControllerParameter addAccountController;
	private IController addClientController;
	private IControllerParameter processBillsController;
	private IController showClientController;
	private IController transactionController;
	private IControllerParameter updateClientController;
	private IControllerParameter updateAccountController;

	public static ControllerFactory instance() {
        if (instance == null) {
            instance = new ControllerFactory();
        }
        return instance;
    }
	

	
	public ControllerFactory(){
		this.adminViewFactory = AdminViewFactory.instance();
		this.componentFactory = ComponentFactory.instance();
		this.regEmpViewFactory = RegEmpViewFactory.instance();
		
		this.addClientController = new AddClientController(regEmpViewFactory.getRegEmployeeMenu(),regEmpViewFactory.getAddClientView(),componentFactory.getClientService(),componentFactory.getRecordService());
		
		this.addAccountController = new AddAccountController(regEmpViewFactory.getAddAccount(),componentFactory.getAccountService(),componentFactory.getClientService(),componentFactory.getRecordService(), regEmpViewFactory.getRegEmployeeMenu());
		
		this.processBillsController = new ProcessBillsController(componentFactory.getAccountOperations(),componentFactory.getClientService(),regEmpViewFactory.getProcessBills(), regEmpViewFactory.getRegEmployeeMenu(),componentFactory.getRecordService());
		
		this.showClientController = new ShowClientController(regEmpViewFactory.getShowClient(),regEmpViewFactory.getRegEmployeeMenu(),componentFactory.getClientService(),componentFactory.getRecordService());
		
		this.transactionController = new TransactionController(regEmpViewFactory.getTransactionView(),componentFactory.getAccountOperations(),componentFactory.getRecordService(),regEmpViewFactory.getRegEmployeeMenu());
		
		this.updateClientController = new UpdateClientController(regEmpViewFactory.getUpdateClientView(),regEmpViewFactory.getRegEmployeeMenu(),componentFactory.getClientService(),componentFactory.getRecordService());
		
		this.updateAccountController = new UpdateAccountController(regEmpViewFactory.getRegEmployeeMenu(), componentFactory.getAccountService(), regEmpViewFactory.getUpdateAccountView(),componentFactory.getRecordService());
		
		this.adminController = new AdminController(adminViewFactory.getAdministratorMenu(),componentFactory.getEmployeeService(),componentFactory.getAuthenticationService(), componentFactory.getRecordService());
		
		this.regEmployeeController = new RegEmployeeController(regEmpViewFactory.getRegEmployeeMenu(),componentFactory.getClientService(),componentFactory.getAccountService(),componentFactory.getAccountOperations(),componentFactory.getRecordService()
																,addClientController, addAccountController, processBillsController, showClientController,transactionController, updateClientController, updateAccountController);
		
	}
	public AdminViewFactory getAdminViewFactory() {
		return adminViewFactory;
	}

	
	public RegEmpViewFactory getRegEmpViewFactory() {
		return regEmpViewFactory;
	}


	public ComponentFactory getComponentFactory() {
		return componentFactory;
	}
	public IController getAdminController() {
		return adminController;
	}

	public IController getRegEmployeeController() {
		return regEmployeeController;
	}


	
	

}
