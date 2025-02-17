import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public interface UtilityFunctions {
	public static void darkModeFunction(JTable table, JPanel panel, boolean isDark) {
		Color bgColor = !isDark ? Color.DARK_GRAY : Color.white;
		Color fgColor = !isDark ? Color.WHITE : Color.BLACK;
	
		table.setBackground(bgColor);
		table.setForeground(fgColor);
	
		panel.setBackground(bgColor);
	}
	
    public static ArrayList<String> getEmptyFieldNames(JTextField... fields) {  
        ArrayList<String> listOfString = new ArrayList<>();  
        for (JTextField field : fields) {
        	System.out.print(field.getName());
            if (field.getText().trim().isEmpty()) {  
                String fieldName = field.getName();  
                if (fieldName != null) { 
                    listOfString.add(fieldName);  
                } else {  
                    listOfString.add("Unnamed Field");
                }  
            }  
        }  
        return listOfString;  
    }  
}
