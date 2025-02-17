import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


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
    
    public static void importPasswordsToTable(TextFileHandler file, DefaultTableModel table) {
    	Map<String, String> pair = file.fileReader();
    	if(!pair.isEmpty()) {
        	for(Map.Entry<String, String> entry: pair.entrySet()) {
        		String platform = entry.getKey();
        		String[] strings = entry.getValue().split(" ");
        		
        		table.addRow(new Object[] {platform, strings[0], strings[1]});
        	}
    	}
    }
}
