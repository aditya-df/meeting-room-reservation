package tkba.team6.roomreservationsystem.utilty;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

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
                
                return Base64.getEncoder().encodeToString(hashedBytes);
            };

            return sha256Hasher.hash(text);
        } catch (NoSuchAlgorithmException e) {
        }

        return "";
    }
}
