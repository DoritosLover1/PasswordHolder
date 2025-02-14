import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;

public interface UtilityFunctions {
	public static void darkModeFunction(JTable table, JPanel panel, boolean isDark) {
		Color bgColor = !isDark ? Color.DARK_GRAY : Color.white;
		Color fgColor = !isDark ? Color.WHITE : Color.BLACK;
	
		table.setBackground(bgColor);
		table.setForeground(fgColor);
	
		panel.setBackground(bgColor);
	}
}
