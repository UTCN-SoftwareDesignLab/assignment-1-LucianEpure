package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class UpdateClientView extends JFrame {

	private JPanel contentPane;
	private JTextField nameTf;
	private JTextField addressTf;
	private JTextField cnpTf;
	

	private JTextField cardIdTf;
	
	private JButton updateBtn;
	private JButton generateCardIdBtn;
	
	public UpdateClientView() {
		setTitle("Update Client");
		setBounds(100, 100, 304, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 23, 68, 14);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 60, 68, 14);
		contentPane.add(lblAddress);
		
		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setBounds(10, 98, 68, 14);
		contentPane.add(lblCnp);
		
		JLabel lblCardId = new JLabel("Card Id ");
		lblCardId.setBounds(10, 140, 68, 14);
		contentPane.add(lblCardId);
		
		nameTf = new JTextField();
		nameTf.setBounds(88, 20, 190, 20);
		contentPane.add(nameTf);
		nameTf.setColumns(10);
		
		addressTf = new JTextField();
		addressTf.setBounds(88, 57, 190, 20);
		contentPane.add(addressTf);
		addressTf.setColumns(10);
		
		cnpTf = new JTextField();
		cnpTf.setBounds(88, 95, 190, 20);
		contentPane.add(cnpTf);
		cnpTf.setColumns(10);
		
		cardIdTf = new JTextField();
		cardIdTf.setBounds(88, 137, 190, 20);
		contentPane.add(cardIdTf);
		cardIdTf.setColumns(10);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(32, 208, 190, 23);
		contentPane.add(updateBtn);
		
	    generateCardIdBtn = new JButton("Generate CardId");
		generateCardIdBtn.setBounds(32, 174, 190, 23);
		contentPane.add(generateCardIdBtn);
	}
	
	public void setUpdateActionListener(ActionListener updateActionListener){
		updateBtn.addActionListener(updateActionListener);
	}
	public void setGenerateCardIdActionListener(ActionListener generateCardIdActionListener){
		generateCardIdBtn.addActionListener(generateCardIdActionListener);
	}
	public JTextField getCardIdTf() {
		return cardIdTf;
	}

	public void setCardIdTf(String string) {
		this.cardIdTf.setText(string);
	}

	public JTextField getNameTf() {
		return nameTf;
	}

	public JTextField getAddressTf() {
		return addressTf;
	}

	public JTextField getCnpTf() {
		return cnpTf;
	}
	public void setNameTf(String text) {
		this.nameTf.setText(text);
	}

	public void setAddressTf(String text) {
		this.addressTf.setText(text);
	}

	public void setCnpTf(String text) {
		this.cnpTf.setText(text);
	}

}
