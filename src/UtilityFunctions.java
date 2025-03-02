import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
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
    // Bunun içine secretkey gerek bir eylem kısmı yapacğız ki. Kişi lazım olan key olmadan giremesin.
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
      
    public static File openFileExplorer() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File("."));
        
        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.exists()) {
                return selectedFile;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
