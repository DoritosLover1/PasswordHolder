import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class FileEncryption {	
	
	public static SecretKey generateSecretKey() throws Exception{
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		return keyGenerator.generateKey();
	}
	
	public static String encodeSecretKey(SecretKey key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

	public static SecretKey decodeSecretKey(String encodedKey) {
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
	}
	
    public static void encryptAndOverwriteFile(String filePath, SecretKey key) throws Exception {
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(fileBytes);

        Files.write(Paths.get(filePath), encryptedBytes);
    }

    public static void decryptAndOverwriteFile(String filePath, SecretKey key) throws Exception {

        byte[] encryptedBytes = Files.readAllBytes(Paths.get(filePath));

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        Files.write(Paths.get(filePath), decryptedBytes);
    }
	
	public static void main(String[] args) {
		try {
            SecretKey secretKey = generateSecretKey();
            String encodedKey = encodeSecretKey(secretKey);
            System.out.println("EncodedKey: " + encodedKey);
            
            String originalFile = "password.txt";

            encryptAndOverwriteFile(originalFile, secretKey);
/*
            SecretKey loadedKey = decodeSecretKey(encodedKey);
            decryptAndOverwriteFile(originalFile, loadedKey);  
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
