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

	private TextFileHandler file;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField mail_phoneArea;
	private JTextField passwordArea;
	private JTextField platformArea;
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
	@SuppressWarnings("serial")
	public main_page() {
		file = new TextFileHandler();

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
		change_saveButton.setBounds(274, 188, 75, 23);
		contentPane.add(change_saveButton);
		
		mail_phoneArea = new JTextField();
		mail_phoneArea.setName("Mail/Phone");
		mail_phoneArea.setBounds(230, 106, 164, 20);
		contentPane.add(mail_phoneArea);
		mail_phoneArea.setColumns(10);
		
		passwordArea = new JTextField();
		passwordArea.setName("Password");
		passwordArea.setBounds(230, 156, 164, 20);
		contentPane.add(passwordArea);
		passwordArea.setColumns(10);
		
	    platformArea = new JTextField();
	    platformArea.setName("Platform");
	    platformArea.setColumns(10);
	    platformArea.setBounds(230, 52, 164, 20);
	    contentPane.add(platformArea);
		
		JLabel lblNewLabel = new JLabel("E-mail / Phone Number");
		lblNewLabel.setBounds(257, 88, 137, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(285, 140, 60, 14);
		contentPane.add(lblNewLabel_1);
		
	    JLabel lblNewLabel_2 = new JLabel("Platform");
	    lblNewLabel_2.setBounds(288, 35, 61, 14);
	    contentPane.add(lblNewLabel_2);
		
		JToggleButton dark_modeButton = new JToggleButton("Press for Dark Mode");
		dark_modeButton.setFocusPainted(false);
		dark_modeButton.setBounds(230, 270, 164, 23);
		contentPane.add(dark_modeButton);
		
		String[] columnNames = {"Platform", "E-Mail/Phone", "Password"};
		tableModel = new DefaultTableModel(columnNames, 0) {
            
			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 210, 282);
		contentPane.add(scrollPane);
		
		table = new JTable(tableModel);
		table.setUpdateSelectionOnSort(false);
		table.setSelectionBackground(new Color(255, 255, 224));
		scrollPane.setViewportView(table);
		
		JButton removeButton = new JButton("Remove");
		removeButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		removeButton.setFocusPainted(false);
		removeButton.setBounds(269, 222, 85, 23);
		removeButton.setEnabled(false);
		contentPane.add(removeButton);

		table.getSelectionModel().addListSelectionListener(e -> {
		    int anySelected = table.getSelectedRow();
		    removeButton.setEnabled(anySelected != -1);
		 
		});

		removeButton.addActionListener(e -> {
		    int anySelected = table.getSelectedRow();
		    file.deleteAtFile(tableModel.getValueAt(anySelected, 0).toString());
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
			
			private String email_phone;
			private String password;
			private String platform;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				email_phone = mail_phoneArea.getText();
				password = passwordArea.getText();
				platform = platformArea.getText();
				
				if(email_phone.isEmpty() || password.isEmpty() || platform.isEmpty()) {
					
					@SuppressWarnings("unused")
					
					WarningDialog warningDialog = new WarningDialog(main_page.this, UtilityFunctions.getEmptyFieldNames(platformArea, mail_phoneArea, passwordArea));
				}
				else {
					
		            tableModel.addRow(new Object[]{platform, email_phone, password});
		            file.writeAtFile(platform, email_phone, password);
				}
				
			}
		});
		
		UtilityFunctions.importPasswordsToTable(file, tableModel);
	}
}
