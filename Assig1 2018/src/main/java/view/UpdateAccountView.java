package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class UpdateAccountView extends JFrame {

	private JPanel contentPane;
	private JTextField sumTf;
	private JTextField dateTf;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JComboBox<String> typeCb;
	private JButton updateBtn;

	
	public UpdateAccountView() {
		setTitle("Update Account");
		setBounds(100, 100, 248, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		sumTf = new JTextField();
		sumTf.setBounds(92, 36, 86, 20);
		contentPane.add(sumTf);
		sumTf.setColumns(10);
		
		dateTf = new JTextField();
		dateTf.setBounds(92, 82, 86, 20);
		contentPane.add(dateTf);
		dateTf.setColumns(10);
		
		typeCb = new JComboBox<String>();
		typeCb.setBounds(92, 128, 86, 20);
		typeCb.addItem("Saving");
		typeCb.addItem("Spending");
		contentPane.add(typeCb);
		
		lblNewLabel = new JLabel("Sum");
		lblNewLabel.setBounds(36, 39, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setBounds(36, 85, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Type");
		lblNewLabel_2.setBounds(36, 131, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		updateBtn = new JButton("Upadte");
		updateBtn.setBounds(67, 192, 89, 23);
		contentPane.add(updateBtn);
	}
	
	public void setUpdateButton( ActionListener updateButtonListener){
		updateBtn.addActionListener(updateButtonListener);
	}
	
	public JTextField getSumTf() {
		return sumTf;
	}

	public void setSumTf(String sum) {
		this.sumTf.setText(sum);
	}

	public JTextField getDateTf() {
		return dateTf;
	}

	public void setDateTf(String date) {
		this.dateTf.setText(date);
	}

	public JComboBox<String> getTypeCb() {
		return typeCb;
	}

	public void setTypeCb(JComboBox<String> typeCb) {
		this.typeCb = typeCb;
	}

	
}
