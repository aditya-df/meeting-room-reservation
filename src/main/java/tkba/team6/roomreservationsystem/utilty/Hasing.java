package tkba.team6.roomreservationsystem.utilty;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

@FunctionalInterface
interface HashFunction {
    String hash(String input) throws NoSuchAlgorithmException;
}

public class Hasing {
    public static String converTextToSHA256(String text) {
        try {
            HashFunction sha256Hasher = input -> {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashedBytes = md.digest(input.getBytes());
    
                StringBuilder sb = new StringBuilder();
                for (byte b : hashedBytes) {
                    sb.append(String.format("%02x", input));
                }
    
                return sb.toString();
            };
            
            return sha256Hasher.hash(text);
        } catch (NoSuchAlgorithmException e) {
        }
        
        return "";
    }
}
