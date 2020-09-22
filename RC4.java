import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class RC4 {

    private static String key;
    private static String plaintext;
    private static Cipher encipher;
    private static Cipher decipher;

    public static void main(String[] args) throws Exception{
        System.out.print("Key: ");
        Scanner scanner = new Scanner(System.in);
        key = scanner.nextLine();

        byte[] keys = null;
        if (key.length() < 5) {
            keys = Arrays.copyOf(key.getBytes(), 5);
        } else {
            keys = key.getBytes();
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(keys, "RC4");

        System.out.print("Plaintext: ");
        plaintext = scanner.nextLine();

        encipher = Cipher.getInstance("RC4");
        encipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        decipher = Cipher.getInstance("RC4");
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

        System.out.println("Encrypted Data: " + encrypted);
        System.out.println("Decrypted Data: " + decrypted);
        System.out.println("encryption duration: "+encryptDuration+" ns");
        System.out.println("Decryption duration: "+decryptDuration+" ns");
    }

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
}
