package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class ShowClient extends JFrame {

	private JPanel contentPane;
	private JTextField cnpTf;

	private JButton showClientBtn;
	
	
	public ShowClient() {
		setTitle("Show Client");
		setBounds(100, 100, 237, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cnpTf = new JTextField();
		cnpTf.setBounds(47, 42, 118, 20);
		contentPane.add(cnpTf);
		cnpTf.setColumns(10);
		
		JLabel lblWhomDoYou = new JLabel("Whom do you wish to see ?");
		lblWhomDoYou.setBounds(22, 11, 159, 20);
		contentPane.add(lblWhomDoYou);
		
		JLabel lblCnp = new JLabel("CNP:");
		lblCnp.setBounds(22, 45, 46, 14);
		contentPane.add(lblCnp);
		
		showClientBtn = new JButton("Show Client");
		showClientBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		showClientBtn.setBounds(22, 78, 143, 23);
		contentPane.add(showClientBtn);
	}
	
	public void setShowClientListener(ActionListener showClientListener){
		showClientBtn.addActionListener(showClientListener);
	}
	public JTextField getCnpTf() {
		return cnpTf;
	}

}
