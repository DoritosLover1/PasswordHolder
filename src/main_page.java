import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import java.awt.*;
import javax.swing.JScrollPane;

public class main_page extends JFrame implements UtilityFunctions{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField mail_phoneArea;
	private JTextField passwordArea;
	private JLabel lblNewLabel_1;
	private JTable table;
	private DefaultTableModel tableModel;

	private boolean isDarkMode = true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_page frame = new main_page();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main_page() {
		
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 343);
		setResizable(false); 
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton change_saveButton = new JButton("Save");
		change_saveButton.setFocusPainted(false);
		change_saveButton.setBounds(274, 164, 75, 23);
		contentPane.add(change_saveButton);
		
		mail_phoneArea = new JTextField();
		mail_phoneArea.setBounds(230, 67, 164, 20);
		contentPane.add(mail_phoneArea);
		mail_phoneArea.setColumns(10);
		
		passwordArea = new JTextField();
		passwordArea.setBounds(230, 120, 164, 20);
		contentPane.add(passwordArea);
		passwordArea.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("E-mail / Phone Number");
		lblNewLabel.setBounds(257, 50, 125, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(285, 104, 60, 14);
		contentPane.add(lblNewLabel_1);
		
		JToggleButton dark_modeButton = new JToggleButton("Press for Dark Mode");
		dark_modeButton.setFocusPainted(false);
		dark_modeButton.setBounds(230, 270, 164, 23);
		contentPane.add(dark_modeButton);
		
		String[] columnNames = {"E-Mail/Phone", "Password"};
		tableModel = new DefaultTableModel(columnNames, 0);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 210, 282);
		contentPane.add(scrollPane);
		table = new JTable(tableModel);
		table.setSelectionBackground(new Color(255, 255, 224));
		scrollPane.setViewportView(table);
		
		JButton removeButton = new JButton("Remove");
		removeButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		removeButton.setFocusPainted(false);
		removeButton.setBounds(269, 198, 85, 23);
		removeButton.setEnabled(false);
		contentPane.add(removeButton);

		table.getSelectionModel().addListSelectionListener(e -> {
		    int anySelected = table.getSelectedRow();
		    removeButton.setEnabled(anySelected != -1);
		});

		removeButton.addActionListener(e -> {
		    int anySelected = table.getSelectedRow();
		    if (anySelected != -1) {
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        model.removeRow(anySelected);
		        table.clearSelection(); 
		    }
			contentPane.revalidate();
			contentPane.repaint();
		});
		
		dark_modeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isDarkMode = !isDarkMode;
				UtilityFunctions.darkModeFunction(table, contentPane, isDarkMode);
			}
			
		});
		
		change_saveButton.addActionListener(new ActionListener() {
			
			private String email;
			private String password;

			@Override
			public void actionPerformed(ActionEvent e) {
				email = mail_phoneArea.getText();
				password = passwordArea.getText();
				
				if(email.isEmpty() || password.isEmpty()) {
		
					@SuppressWarnings("unused")
					WarningDialog warningDialog = new WarningDialog(main_page.this);
				}
				else {
					
		            tableModel.addRow(new Object[]{email, password});
				}
				
			}
		});
		
	}
}
