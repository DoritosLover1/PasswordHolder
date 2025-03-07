import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WarningDialog {
	public WarningDialog(JFrame frame, ArrayList<String> listOfNames, String message_param){
	JDialog dialog = new JDialog();
	JButton closeButton = new JButton("❌ Close");
	closeButton.setFocusPainted(false);
	
	closeButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dialog.dispose();
		}
	});
	
	Object[] option = {closeButton};
	dialog.setContentPane(new JOptionPane(message_param + String.join(", ", listOfNames), JOptionPane.WARNING_MESSAGE,
			JOptionPane.DEFAULT_OPTION, null, option, option[0]));
	dialog.setModal(true);
	dialog.pack();
	dialog.setLocationRelativeTo(frame);
	dialog.setVisible(true);
	}
}
