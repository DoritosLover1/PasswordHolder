import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; 

public class TextFileHandler{  
    private File file;  
    
    public TextFileHandler(File newFile) {  
        try {  
            file = newFile;  
            if (!file.exists()) {  
                file.createNewFile();  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        
    }
    
    public void setFile(File newFile) {
    	file = newFile;
    }
    
    public String getFileName() {
    	return file.getName();
    }
    
    public Map<String, String> fileReader() {  
        Map<String, String> index = new HashMap<>();  

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {  
            String line;  
            while ((line = reader.readLine()) != null) {  
                String[] lineArray = line.split(" ");  
                if (lineArray.length >= 3) {  
                    index.put(lineArray[0], lineArray[1] + " " + lineArray[2]);  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  

        return index;  
    }  

    public void writeAtFile(String... strings) {  
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (String str : strings) {  
                writer.write(str+" ");
            }
            writer.newLine();
            writer.close();
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        
    }
    
    public void deleteAtFile(String platformName) {
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            List<String> updatedLines = lines.stream()
                    .filter(line -> !line.split(" ")[0].equals(platformName))
                    .collect(Collectors.toList());

            Files.write(file.toPath(), updatedLines);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
