import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WarningDialog {
	public WarningDialog(JFrame frame, ArrayList<String> listOfNames){
	JDialog dialog = new JDialog();
	JButton closeButton = new JButton("❌ Close");
	closeButton.setFocusPainted(false);
	
	closeButton.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			dialog.dispose();
		}
	});
	
	Object[] option = {closeButton};
	/*
	 * We need to change message with !!!listOfNames!!!
	 * 
	 * */
	String message = "Please enter valid values for the following fields: \n" + String.join(", ", listOfNames);
	JOptionPane contentPane = new JOptionPane(message, JOptionPane.WARNING_MESSAGE,
			JOptionPane.DEFAULT_OPTION, null, option, option[0]);
	dialog.setContentPane(contentPane);
	dialog.setModal(true);
	dialog.pack();
	dialog.setLocationRelativeTo(frame);
	dialog.setVisible(true);
	}
}
