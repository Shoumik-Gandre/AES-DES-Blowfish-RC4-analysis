import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;


class DESmanualKey {
    private static Cipher encipher;
    private static Cipher decipher;
    private static String key;
    private static String plaintext = "I Giorno Giovana have a dream";

    public static void main(String[] args) {

        try {
            System.out.print("Key: ");
            // read DES key
            Scanner scanner = new Scanner(System.in);
            key = scanner.nextLine();

            // convert key to a form we like
            byte[] keyData = Arrays.copyOf(key.getBytes(), 8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "DES");

            System.out.print("Plaintext: ");
            plaintext = scanner.nextLine();

            encipher = Cipher.getInstance("DES");
            decipher = Cipher.getInstance("DES");

            encipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            decipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            long startTime, endTime;
            String encrypted, decrypted;

            startTime = System.nanoTime();
            encrypted = encrypt(plaintext);
            endTime = System.nanoTime();

            long encryptDuration = (endTime - startTime);

            startTime = System.nanoTime();
            decrypted = decrypt(encrypted);
            endTime = System.nanoTime();

            long decryptDuration = (endTime - startTime);

            System.out.println("Encrypted: " + encrypted);
            System.out.println("Decrypted: " + decrypted);

            System.out.println("encryption duration: " + encryptDuration + " ns");
            System.out.println("Decryption duration: " + decryptDuration + " ns");

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such Algorithm: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.out.println("No such Padding " + e.getMessage());
        } catch (InvalidKeyException e) {
            System.out.println("Invalid Key: " + e.getMessage());
        }
    }

    public static String encrypt(String str) {
        try {
            // encode the string into a sequence of bytes using the named charset
            // storing the result into a new byte array.
            byte[] utf8 = str.getBytes(StandardCharsets.UTF_8);
            byte[] enc = encipher.doFinal(utf8);
            // encode to base64
            return Base64.getEncoder().encodeToString(enc);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String str) {
        try {
            // decode with base64 to get bytes
            byte[] dec = Base64.getDecoder().decode(str.getBytes());
            byte[] utf8 = decipher.doFinal(dec);
            // create new string based on the specified charset
            return new String(utf8, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}