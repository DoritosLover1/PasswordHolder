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
import javax.swing.JTextArea;

public class Application extends JFrame implements UtilityFunctions{

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
					Application frame = new Application();
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
	public Application() {
		file = new TextFileHandler(UtilityFunctions.openFileExplorer());
		setTitle("Password Holder with Encryption ~ by DoritosLover1");
		
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		setResizable(false); 
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton change_saveButton = new JButton("Save 📑");
		change_saveButton.setFocusPainted(false);
		change_saveButton.setBounds(240, 10, 100, 23);
		contentPane.add(change_saveButton);
		
	    JButton openFileButton = new JButton("Open File");
	    openFileButton.setFocusPainted(false);
	    openFileButton.setBounds(380, 10, 100, 23);
	    contentPane.add(openFileButton);
	    
	    openFileButton.addActionListener(e->{
	    	file.setFile(UtilityFunctions.openFileExplorer());
	    });
		
		mail_phoneArea = new JTextField();
		mail_phoneArea.setName("Mail/Phone");
		mail_phoneArea.setBounds(270, 134, 180, 20);
		contentPane.add(mail_phoneArea);
		mail_phoneArea.setColumns(10);
		
		passwordArea = new JTextField();
		passwordArea.setName("Password");
		passwordArea.setBounds(270, 189, 180, 20);
		contentPane.add(passwordArea);
		passwordArea.setColumns(10);
		
	    platformArea = new JTextField();
	    platformArea.setName("Platform");
	    platformArea.setColumns(10);
	    platformArea.setBounds(270, 72, 180, 20);
	    contentPane.add(platformArea);
	    
	    JTextArea secretKeyTextArea = new JTextArea();
	    secretKeyTextArea.setBackground(new Color(211, 211, 211));
	    secretKeyTextArea.setToolTipText("Enter Secret Key");
	    secretKeyTextArea.setFont(new Font("Arial Black", Font.ITALIC, 11));
	    secretKeyTextArea.setBounds(250, 451, 225, 32);
	    contentPane.add(secretKeyTextArea);
	    
	    
	    JLabel lblNewLabel_3 = new JLabel("Secret Key");
	    lblNewLabel_3.setBounds(335, 432, 80, 15);
	    contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("E-mail / Phone Number");
		lblNewLabel.setBounds(308, 118, 137, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(335, 174, 60, 14);
		contentPane.add(lblNewLabel_1);
		
	    JLabel lblNewLabel_2 = new JLabel("Platform");
	    lblNewLabel_2.setBounds(336, 57, 61, 14);
	    contentPane.add(lblNewLabel_2);
		
		JToggleButton dark_modeButton = new JToggleButton("Press for Dark Mode 🌚");
		dark_modeButton.setFocusPainted(false);
		dark_modeButton.setBounds(250, 525, 225, 25);
		contentPane.add(dark_modeButton);
		
		String[] columnNames = {"Platform", "E-Mail/Phone", "Password"};
		tableModel = new DefaultTableModel(columnNames, 0) {
            
			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 220, 540);
		contentPane.add(scrollPane);
		
		table = new JTable(tableModel);
		table.setUpdateSelectionOnSort(false);
		table.setSelectionBackground(new Color(255, 255, 224));
		scrollPane.setViewportView(table);
		
		JButton removeButton = new JButton("Remove");
		removeButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		removeButton.setFocusPainted(false);
		removeButton.setBounds(299, 260, 120, 23);
		removeButton.setEnabled(false);
		contentPane.add(removeButton);

	    JButton addNewData = new JButton("Add");
	    addNewData.setFocusPainted(false);
	    addNewData.setBounds(309, 226, 100, 23);
	    contentPane.add(addNewData);
		
	    JButton secretKeyButton = new JButton("Insert Key");
	    secretKeyButton.setFocusPainted(false);
	    secretKeyButton.setBounds(320, 490, 89, 23);
	    contentPane.add(secretKeyButton);
	    		
		table.getSelectionModel().addListSelectionListener(e -> {
		    int anySelected = table.getSelectedRow();
		    removeButton.setEnabled(anySelected != -1);
		});

/*****************After this comment, each of them addActionListeners.*****************/
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
			SecretKey secretKey;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					secretKey = FileEncryption.generateSecretKey();
					FileEncryption.encryptAndOverwriteFile(file.getFileName(),secretKey);
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					@SuppressWarnings("unused")
					InformationDialog informationDialog = new InformationDialog(Application.this, 
							FileEncryption.encodeSecretKey(secretKey),
							"KEEP IT THIS IS TOO CRUCIAL FOR ACCESING THIS TEXT FILE,\n"
							+ "then you interact with either close or button, application"
							+ "is gonna close!: \n");
				}
				
				System.exit(0);
			}
		});
		
	    secretKeyButton.addActionListener(e->{
	    	SecretKey secretKey = FileEncryption.decodeSecretKey(secretKeyTextArea.getText());
	    	try {
				FileEncryption.decryptAndOverwriteFile(file.getFileName(), secretKey);
				UtilityFunctions.importPasswordsToTable(file, tableModel);
				secretKeyTextArea.setText("");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    });	
	    
	    addNewData.addActionListener(new ActionListener() {
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
					WarningDialog warningDialog = new WarningDialog(Application.this, 
							UtilityFunctions.getEmptyFieldNames(platformArea, mail_phoneArea, passwordArea),
							"Please enter valid values for the following fields: \n");
				}
				else {
			         tableModel.addRow(new Object[]{platform, email_phone, password});
			         file.writeAtFile(platform, email_phone, password);
			         mail_phoneArea.setText("");
			         passwordArea.setText("");
			         platformArea.setText("");
				}
			}	
	    	
	    });
	    
		UtilityFunctions.importPasswordsToTable(file, tableModel);	
	}
}
