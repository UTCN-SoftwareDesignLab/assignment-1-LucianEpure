package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddAccount extends JFrame {

	private JPanel contentPane;
	private JTextField sumTf;
	private JTextField dateTf;
	private JTextField clientTf;

	private JComboBox<String> typeCb;
	private JButton addAccountBtn;

	public AddAccount() {
		setTitle("Add Account");
		setBounds(100, 100, 249, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(10, 71, 46, 14);
		contentPane.add(lblType);
		
		typeCb = new JComboBox<String>();
		typeCb.setBounds(85, 68, 138, 20);
		contentPane.add(typeCb);
		typeCb.addItem("Saving");
		typeCb.addItem("Spending");
	
		
		sumTf = new JTextField();
		sumTf.setBounds(85, 108, 138, 20);
		contentPane.add(sumTf);
		sumTf.setColumns(10);
		
		JLabel lblSum = new JLabel("Sum");
		lblSum.setBounds(10, 111, 46, 14);
		contentPane.add(lblSum);
		
		JLabel lblNewLabel = new JLabel("Date");
		lblNewLabel.setBounds(10, 149, 46, 14);
		contentPane.add(lblNewLabel);
		
		dateTf = new JTextField();
		dateTf.setBounds(85, 146, 138, 20);
		contentPane.add(dateTf);
		dateTf.setColumns(10);
		
		JLabel lblClient = new JLabel("Client");
		lblClient.setBounds(10, 30, 46, 14);
		contentPane.add(lblClient);
		
		clientTf = new JTextField();
		clientTf.setBounds(85, 27, 138, 20);
		contentPane.add(clientTf);
		clientTf.setColumns(10);
		
		addAccountBtn = new JButton("Add Account");
		addAccountBtn.setBounds(35, 177, 153, 23);
		contentPane.add(addAccountBtn);
	}
	
	public void setAddListener(ActionListener accountListener){
		addAccountBtn.addActionListener(accountListener);
	}
	
	public JTextField getSumTf() {
		return sumTf;
	}

	public JTextField getDateTf() {
		return dateTf;
	}

	public JComboBox<String> getTypeCb() {
		return typeCb;
	}
	public JTextField getClientTf() {
		return clientTf;
	}

	public void setSumTf(String text) {
		this.sumTf.setText(text);
	}

	public void setDateTf(String text) {
		this.dateTf.setText(text);
	}

	public void setClientTf(String text) {
		this.clientTf.setText(text);
	}

}
