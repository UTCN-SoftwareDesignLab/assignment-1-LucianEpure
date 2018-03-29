package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class RegEmployeeMenu extends JFrame {

	private JPanel contentPane;
	private JTable clients;

	private final DefaultTableModel clientsModel;
	private JScrollPane clientsH;
	private JTable accounts;
	private final DefaultTableModel accountsModel;
	private JScrollPane accountsH;
	private JButton addClientBtn;
	private JButton showAllBtn ;
	private JButton viewClientBtn;
	private JButton updateClientBtn;
	private JButton addAccountBtn;
	private JButton updateAccountBtn;
	private JButton removeAccountBtn;
	private JButton showAccountsBtn;
	private JButton viewAccountBtn;
	private JButton transactionBtn;
	private JButton processBillBtn;
	private JLabel lblDate;
	private JTextField dateTf;

	public RegEmployeeMenu() {
		setTitle("Employee menu");
		setBounds(100, 100, 806, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addClientBtn = new JButton("Add Client");
		addClientBtn.setBounds(10, 11, 122, 23);
		contentPane.add(addClientBtn);
		
		addAccountBtn = new JButton("Add Account");
		addAccountBtn.setBounds(10, 158, 122, 23);
		contentPane.add(addAccountBtn);
		
		updateClientBtn = new JButton("Update Client");
		updateClientBtn.setBounds(10, 59, 122, 23);
		contentPane.add(updateClientBtn);
		
		viewClientBtn = new JButton("View Client");
		viewClientBtn.setBounds(10, 113, 122, 23);
		contentPane.add(viewClientBtn);
		
		updateAccountBtn = new JButton("Update Account");
		updateAccountBtn.setBounds(10, 192, 122, 23);
		contentPane.add(updateAccountBtn);

		removeAccountBtn = new JButton("Remove Account");
		removeAccountBtn.setBounds(10, 226, 122, 23);
		contentPane.add(removeAccountBtn);
		
		viewAccountBtn = new JButton("View Account");
		viewAccountBtn.setBounds(10, 260, 122, 23);
		contentPane.add(viewAccountBtn);
		Object[] clientsColumns = {  "Name","address","CNP","cardId"};
		
		clientsModel = new DefaultTableModel(clientsColumns, 0);
		clients = new JTable(clientsModel);
		clients.setBounds(231, 15, 388, 246);
		
		clientsH = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		clientsH.setViewportView(clients);
		clientsH.setBounds(142, 8, 638, 128);
		contentPane.add(clientsH);
		
		Object[] accountsColumn = {"Client", "Id","Type","Sum","Date"};
		
		accountsModel = new DefaultTableModel(accountsColumn, 0);
		accounts = new JTable(accountsModel);
		accounts.setBounds(231, 15, 388, 246);
		
		accountsH = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		accountsH.setViewportView(accounts);
		accountsH.setBounds(142, 155, 638, 136);
		contentPane.add(accountsH);
		
		showAllBtn = new JButton("Show clients");
		showAllBtn.setBounds(142, 308, 156, 23);
		contentPane.add(showAllBtn);
		
		showAccountsBtn = new JButton("Show accounts");
		showAccountsBtn.setBounds(308, 308, 156, 23);
		contentPane.add(showAccountsBtn);
		
		transactionBtn = new JButton("Transaction");
		transactionBtn.setBounds(474, 308, 156, 23);
		contentPane.add(transactionBtn);
		
		processBillBtn = new JButton("Bill Process");
		processBillBtn.setBounds(640, 308, 140, 23);
		contentPane.add(processBillBtn);
		
		lblDate = new JLabel("Date         y-m-d");
		lblDate.setBounds(10, 294, 122, 14);
		contentPane.add(lblDate);
		
		dateTf = new JTextField();
		dateTf.setBounds(10, 309, 122, 20);
		contentPane.add(dateTf);
		dateTf.setColumns(10);
	}
	public void setTableListenerClients(MouseListener tableListener){
		clients.addMouseListener(tableListener);
	}
	public void setTableListenerAccounts(MouseListener tableListener){
		accounts.addMouseListener(tableListener);
	}
	 public void setAddClientListener(ActionListener addClientListener) {
	        addClientBtn.addActionListener(addClientListener);
	    }
	 public void setShowAllListener(ActionListener showAllListener) {
	        showAllBtn.addActionListener(showAllListener);
	    }
	 public void setShowClientListener(ActionListener showClientListener) {
	        viewClientBtn.addActionListener(showClientListener);
	    }
	 public void setUpdateClientListener(ActionListener updateClientListener){
		 	updateClientBtn.addActionListener(updateClientListener);
	 }
	 public void setAddAccountListener(ActionListener addAccountListener) {
	        addAccountBtn.addActionListener(addAccountListener);
	    }
	 public void setShowAccountsListener(ActionListener showClientAccountsListener) {
		 viewAccountBtn.addActionListener(showClientAccountsListener);
	    }
	 public void setShowAllAccountsListener(ActionListener showAllAccountsListener) {
	        showAccountsBtn.addActionListener(showAllAccountsListener);
	    }
	 public void setRemoveAccountListener(ActionListener removeAccountsListener) {
	        removeAccountBtn.addActionListener(removeAccountsListener);
	    }
	 public void setUpdateAccountListener(ActionListener updateAccountsListener) {
	        updateAccountBtn.addActionListener(updateAccountsListener);
	    }
	 public void setTransactionListener(ActionListener transactionListener) {
	        transactionBtn.addActionListener(transactionListener);
	    }
	 public void setProcessBillListener(ActionListener processBillListener) {
	       processBillBtn.addActionListener(processBillListener);
	    }
	public DefaultTableModel getAccountsModel() {
			return accountsModel;
		}
	public DefaultTableModel getClientsModel() {
		return clientsModel;
	}
	public JTable getClients() {
		return clients;
	}
	public void setClients(JTable clients) {
		this.clients = clients;
	}
	public JTable getAccounts() {
		return accounts;
	}
	public void setAccounts(JTable accounts) {
		this.accounts = accounts;
	}

	public JTextField getDateTf() {
		return dateTf;
	}
	public void setDateTf(String text) {
		this.dateTf.setText(text);
	}
}
