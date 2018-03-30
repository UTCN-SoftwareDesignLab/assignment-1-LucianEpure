package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ProcessBillsView extends JFrame {

	private JPanel contentPane;
	private JTextField billNumberTf;
	private JTextField sumTf;
	private JTextField clientTf;
	private JButton payBtn ;
	private JTextField accountIdTf;

	public ProcessBillsView() {
		setTitle("Bills");
		setBounds(100, 100, 223, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBillId = new JLabel("Bill Number");
		lblBillId.setBounds(10, 27, 71, 14);
		contentPane.add(lblBillId);
		
		JLabel lblAmmount = new JLabel("Sum");
		lblAmmount.setBounds(10, 67, 71, 14);
		contentPane.add(lblAmmount);
		
		billNumberTf = new JTextField();
		billNumberTf.setBounds(83, 24, 86, 20);
		contentPane.add(billNumberTf);
		billNumberTf.setColumns(10);
		
		sumTf = new JTextField();
		sumTf.setBounds(83, 64, 86, 20);
		contentPane.add(sumTf);
		sumTf.setColumns(10);
		
		JLabel lblClient = new JLabel("Client");
		lblClient.setBounds(10, 111, 71, 14);
		contentPane.add(lblClient);
		
		clientTf = new JTextField();
		clientTf.setBounds(83, 108, 86, 20);
		contentPane.add(clientTf);
		clientTf.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Account");
		lblNewLabel.setBounds(10, 149, 71, 14);
		contentPane.add(lblNewLabel);
		
		payBtn = new JButton("Pay");
		payBtn.setBounds(46, 201, 89, 23);
		contentPane.add(payBtn);
		
		accountIdTf = new JTextField();
		accountIdTf.setBounds(83, 146, 86, 20);
		contentPane.add(accountIdTf);
		accountIdTf.setColumns(10);
	}
	public void setPayBillListener(ActionListener payBillListener){
	payBtn.addActionListener(payBillListener);
	}
	
	public JTextField getBillNumberTf() {
		return billNumberTf;
	}
	public void setBillNumberTf(String text) {
		this.billNumberTf.setText(text);;
	}
	public JTextField getSumTf() {
		return sumTf;
	}
	public void setSumTf(String text) {
		this.sumTf.setText(text);
	}
	public JTextField getClientTf() {
		return clientTf;
	}
	public void setClientTf(String text) {
		this.clientTf.setText(text);;
	}
	public JTextField getAccountIdTf() {
		return accountIdTf;
	}
	public void setAccountIdTf(String text) {
		this.accountIdTf.setText(text);
	}
	
}
