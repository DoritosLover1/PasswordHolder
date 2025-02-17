import java.io.*;  
import java.util.HashMap;  
import java.util.Map; 

public class TextFileHandler {  
    private File file;  

    public TextFileHandler() {  
        try {  
            file = new File("password.txt");  
            if (!file.exists()) {  
                file.createNewFile();  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
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
                writer.close();
            }
            writer.newLine();
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        
    }
}
