import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

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
		file = new TextFileHandler(UtilityFunctions.openFileExplorer());
		
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		setResizable(false); 
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton change_saveButton = new JButton("Save 📑");
		change_saveButton.setFocusPainted(false);
		change_saveButton.setBounds(293, 211, 86, 23);
		contentPane.add(change_saveButton);
		
	    JButton openFileButton = new JButton("Open File");
	    openFileButton.setFocusPainted(false);
	    openFileButton.setBounds(324, 8, 100, 23);
	    contentPane.add(openFileButton);
	    
	    openFileButton.addActionListener(e->{
	    	file.setFile(UtilityFunctions.openFileExplorer());
	    });
		
		mail_phoneArea = new JTextField();
		mail_phoneArea.setName("Mail/Phone");
		mail_phoneArea.setBounds(249, 131, 175, 20);
		contentPane.add(mail_phoneArea);
		mail_phoneArea.setColumns(10);
		
		passwordArea = new JTextField();
		passwordArea.setName("Password");
		passwordArea.setBounds(249, 182, 175, 20);
		contentPane.add(passwordArea);
		passwordArea.setColumns(10);
		
	    platformArea = new JTextField();
	    platformArea.setName("Platform");
	    platformArea.setColumns(10);
	    platformArea.setBounds(249, 77, 175, 20);
	    contentPane.add(platformArea);
	    
	    JTextArea secretKeyTextArea = new JTextArea();
	    secretKeyTextArea.setBackground(Color.LIGHT_GRAY);
	    secretKeyTextArea.setToolTipText("Enter Secret Key");
	    secretKeyTextArea.setFont(new Font("Arial Black", Font.ITALIC, 11));
	    secretKeyTextArea.setBounds(249, 296, 175, 32);
	    contentPane.add(secretKeyTextArea);
	    
	    
	    JLabel lblNewLabel_3 = new JLabel("Secret Key");
	    lblNewLabel_3.setBounds(309, 279, 100, 14);
	    contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("E-mail / Phone Number");
		lblNewLabel.setBounds(287, 113, 137, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(313, 165, 60, 14);
		contentPane.add(lblNewLabel_1);
		
	    JLabel lblNewLabel_2 = new JLabel("Platform");
	    lblNewLabel_2.setBounds(318, 60, 61, 14);
	    contentPane.add(lblNewLabel_2);
		
		JToggleButton dark_modeButton = new JToggleButton("Press for Dark Mode 🌚");
		dark_modeButton.setFocusPainted(false);
		dark_modeButton.setBounds(246, 377, 178, 23);
		contentPane.add(dark_modeButton);
		
		String[] columnNames = {"Platform", "E-Mail/Phone", "Password"};
		tableModel = new DefaultTableModel(columnNames, 0) {
            
			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 216, 389);
		contentPane.add(scrollPane);
		
		table = new JTable(tableModel);
		table.setUpdateSelectionOnSort(false);
		table.setSelectionBackground(new Color(255, 255, 224));
		scrollPane.setViewportView(table);
		
		JButton removeButton = new JButton("Remove");
		removeButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		removeButton.setFocusPainted(false);
		removeButton.setBounds(275, 245, 120, 23);
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
		
		/*
		 * Şimdilik burayı halletim
		 * Sadece dosyayı oluştıurunca kaparken bize kod verecek
		 */
	    JButton secretKeyButton = new JButton("Insert Key");
	    secretKeyButton.setBounds(293, 339, 89, 23);
	    contentPane.add(secretKeyButton);
		
	    secretKeyButton.addActionListener(e->{
	    	SecretKey secretKey = FileEncryption.decodeSecretKey(secretKeyTextArea.getText());
	    	try {
				FileEncryption.decryptAndOverwriteFile(file.getFileName(), secretKey);
				UtilityFunctions.importPasswordsToTable(file, tableModel);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    });
	    
		UtilityFunctions.importPasswordsToTable(file, tableModel);
	}
}
