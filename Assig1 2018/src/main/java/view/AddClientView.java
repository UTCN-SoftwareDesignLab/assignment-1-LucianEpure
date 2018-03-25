package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddClientView extends JFrame {

	private JPanel contentPane;
	private JTextField clientNameTf;
	
	private JTextField clientAddressTf;
	private JTextField cnpTf;
	private JTextField cardIdTf;
	private JButton addClientBtn;
	private JButton generateCardIdBtn;


	public AddClientView() {
		setTitle("Add Client");
		setBounds(100, 100, 263, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 24, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 49, 46, 14);
		contentPane.add(lblAddress);
		
		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setBounds(10, 74, 46, 14);
		contentPane.add(lblCnp);
		
		JLabel lblNewLabel = new JLabel("Card ID");
		lblNewLabel.setBounds(10, 99, 46, 14);
		contentPane.add(lblNewLabel);
		
		clientNameTf = new JTextField();
		clientNameTf.setBounds(66, 21, 156, 20);
		contentPane.add(clientNameTf);
		clientNameTf.setColumns(10);
		
		clientAddressTf = new JTextField();
		clientAddressTf.setBounds(66, 46, 156, 20);
		contentPane.add(clientAddressTf);
		clientAddressTf.setColumns(10);
		
		cnpTf = new JTextField();
		cnpTf.setBounds(66, 71, 156, 20);
		contentPane.add(cnpTf);
		cnpTf.setColumns(10);
		
		cardIdTf = new JTextField();
		cardIdTf.setBounds(66, 96, 156, 20);
		contentPane.add(cardIdTf);
		cardIdTf.setColumns(10);
		
		generateCardIdBtn = new JButton("Generate Card Id");
		generateCardIdBtn.setBounds(48, 124, 151, 23);
		contentPane.add(generateCardIdBtn);
		
		addClientBtn = new JButton("Add Client");
		addClientBtn.setBounds(48, 156, 151, 23);
		contentPane.add(addClientBtn);
	}
	

	 public void setAddClientListener(ActionListener addClientListener) {
	        addClientBtn.addActionListener(addClientListener);
	    }
	 

	 public void setGenerateCardId(ActionListener generatedCardIdListener) {
	        generateCardIdBtn.addActionListener(generatedCardIdListener);
	    }
	 
	 public JTextField getClientNameTf() {
			return clientNameTf;
		}


		public JTextField getClientAddressTf() {
			return clientAddressTf;
		}


		public JTextField getCnpTf() {
			return cnpTf;
		}


		public JTextField getCardIdTf() {
			return cardIdTf;
		}




}
