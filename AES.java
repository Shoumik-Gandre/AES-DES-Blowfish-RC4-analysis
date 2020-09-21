import javax.crypto.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AES {

    private static SecretKey key;
    private static Cipher encipher;
    private static Cipher decipher;
    public static void main(String[] args) {
        try {
            key = KeyGenerator.getInstance("AES").generateKey();
            String b64Key = Base64.getEncoder().encodeToString(key.getEncoded());
            System.out.println("KeyLength: " + b64Key.length() + "\nKey: " + b64Key);
            encipher = Cipher.getInstance("AES");
            decipher = Cipher.getInstance("AES");
            encipher.init(Cipher.ENCRYPT_MODE, key);
            decipher.init(Cipher.DECRYPT_MODE, key);
            String plaintext = "I Giorno Giovana have a dream.";
            long startTime = System.nanoTime();
            String encrypted = encrypt(plaintext);
            long endTime = System.nanoTime();

            long encryptDuration = (endTime - startTime);

            startTime = System.nanoTime();
            String decrypted = decrypt(encrypted);
            endTime = System.nanoTime();

            long decryptDuration = (endTime - startTime);

            System.out.println("Encrypted: " + encrypted);
            System.out.println("Decrypted: " + decrypted);

            System.out.println("encryption duration: "+encryptDuration+" ns \nDecryption duration: "+decryptDuration+" ns");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String str) {
        byte[] utf8 = str.getBytes(StandardCharsets.UTF_8);
        byte[] enc = new byte[0];
        try {
            enc = encipher.doFinal(utf8);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(enc);

    }

    public static String decrypt(String str) {
        byte[] dec = Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8));
        byte[] utf8 = new byte[0];
        try {
            utf8 = decipher.doFinal(dec);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(utf8, StandardCharsets.UTF_8);

    }
}
