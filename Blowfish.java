import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class Blowfish {

    private static String key = "!this+is/our*secret;key";
    private static Cipher encipher, decipher;
    private static String plaintext;

    public static String encrypt(String plaintext) {
        try {
            byte[] hasil = encipher.doFinal(plaintext.getBytes());
            return Base64.getEncoder().encodeToString(hasil);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String ciphertext) {
        try {
            byte[] hasil = decipher.doFinal(Base64.getDecoder().decode(ciphertext));
            return new String(hasil);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        System.out.print("Key: ");
        // read DES key
        Scanner scanner = new Scanner(System.in);
        key = scanner.nextLine();

        System.out.print("Plaintext: ");
        plaintext = scanner.nextLine();

        byte[] keyData = key.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");

        encipher = Cipher.getInstance("Blowfish");
        encipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        decipher = Cipher.getInstance("Blowfish");
        decipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        long startTime, endTime;
        String encrypted, decrypted;

        startTime = System.nanoTime();
        encrypted = encrypt(plaintext);
        endTime = System.nanoTime();

        long encryptDuration = (endTime - startTime);

        System.out.println("Encrypted Data: " + encrypted);

        startTime = System.nanoTime();
        decrypted = decrypt(encrypted);
        endTime = System.nanoTime();

        long decryptDuration = (endTime - startTime);

        System.out.println("Decrypted Data: " + decrypted);

        System.out.println("encryption duration: "+encryptDuration+" ns");
        System.out.println("Decryption duration: "+decryptDuration+" ns");
    }
}
