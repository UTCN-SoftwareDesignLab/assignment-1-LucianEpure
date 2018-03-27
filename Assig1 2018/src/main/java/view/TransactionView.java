package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransactionView extends JFrame {

	private JPanel contentPane;
	private JTextField fromTf;
	private JTextField toTf;
	private JTextField sumTf;
	private JButton processBtn;

	public TransactionView() {
		setTitle("Transaction");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 244, 184);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(10, 11, 46, 14);
		contentPane.add(lblFrom);
		
		fromTf = new JTextField();
		fromTf.setBounds(56, 8, 147, 20);
		contentPane.add(fromTf);
		fromTf.setColumns(10);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(10, 45, 46, 14);
		contentPane.add(lblTo);
		
		toTf = new JTextField();
		toTf.setBounds(56, 39, 147, 20);
		contentPane.add(toTf);
		toTf.setColumns(10);
		
		JLabel lblSum = new JLabel("Sum");
		lblSum.setBounds(10, 70, 46, 14);
		contentPane.add(lblSum);
		
		sumTf = new JTextField();
		sumTf.setBounds(56, 70, 147, 20);
		contentPane.add(sumTf);
		sumTf.setColumns(10);
		
		processBtn = new JButton("Process");
		processBtn.setBounds(66, 101, 89, 23);
		contentPane.add(processBtn);
	}
	
	public void setProcess(ActionListener processListener){
		processBtn.addActionListener(processListener);
	}
}
