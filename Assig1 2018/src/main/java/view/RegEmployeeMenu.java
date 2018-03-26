package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

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

	public RegEmployeeMenu() {
		setTitle("Employee menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addClientBtn = new JButton("Add Client");
		addClientBtn.setBounds(10, 11, 122, 23);
		contentPane.add(addClientBtn);
		
		JButton btnAddAccount = new JButton("Add Account");
		btnAddAccount.setBounds(10, 158, 122, 23);
		contentPane.add(btnAddAccount);
		
		updateClientBtn = new JButton("Update Client");
		updateClientBtn.setBounds(10, 59, 122, 23);
		contentPane.add(updateClientBtn);
		
		viewClientBtn = new JButton("View Client");
		viewClientBtn.setBounds(10, 113, 122, 23);
		contentPane.add(viewClientBtn);
		
		JButton updateAccountBtn = new JButton("Update Account");
		updateAccountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		updateAccountBtn.setBounds(10, 192, 122, 23);
		contentPane.add(updateAccountBtn);
		
		JButton btnRemoveAccount = new JButton("Remove Account");
		btnRemoveAccount.setBounds(10, 226, 122, 23);
		contentPane.add(btnRemoveAccount);
		
		JButton btnViewAccount = new JButton("View Account");
		btnViewAccount.setBounds(10, 260, 122, 23);
		contentPane.add(btnViewAccount);
		Object[] clientsColumns = {  "Name","address","CNP","cardId"};
		
		clientsModel = new DefaultTableModel(clientsColumns, 0);
		clients = new JTable(clientsModel);
		clients.setBounds(231, 15, 388, 246);
		
		clientsH = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		clientsH.setViewportView(clients);
		clientsH.setBounds(142, 8, 638, 128);
		contentPane.add(clientsH);
		
		Object[] accountsColumn = {  "Type","Sum","Date"};
		
		accountsModel = new DefaultTableModel(accountsColumn, 0);
		accounts = new JTable(accountsModel);
		accounts.setBounds(231, 15, 388, 246);
		
		accountsH = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		accountsH.setViewportView(accounts);
		accountsH.setBounds(142, 155, 638, 136);
		contentPane.add(accountsH);
		
		showAllBtn = new JButton("Show All");
		showAllBtn.setBounds(223, 302, 308, 23);
		contentPane.add(showAllBtn);
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
	public DefaultTableModel getAccountsModel() {
			return accountsModel;
		}
	public DefaultTableModel getClientsModel() {
		return clientsModel;
	}
}
