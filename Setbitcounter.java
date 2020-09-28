import java.math.BigInteger;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class Setbitcounter {

    public static int compareStrings(String a, String b) {
        int count = 0;
        for (int i=0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    static String toBString(String base64_str) {
        byte[] decode = Base64.getDecoder().decode(base64_str);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < decode.length; i++){
            String temp = Integer.toBinaryString(decode[i]);
            sb.append(String.format("%8s", temp).replace(" ", "0"));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("First Input: ");
        String s1 = scanner.nextLine();
        System.out.print("Second Input: ");
        String s2 = scanner.nextLine();
        byte[] a = Base64.getDecoder().decode(s1);
        byte[] b = Base64.getDecoder().decode(s2);
        String binaryStringa = new BigInteger(1, a).toString(2);
        String binaryStringb = new BigInteger(1, b).toString(2);

        // We need to Pad (When MSB is 0 it isn't counted by BigInteger)
        while (binaryStringa.length() < binaryStringb.length()) {
            binaryStringa = "0" + binaryStringa;
        }
        while (binaryStringa.length() > binaryStringb.length()) {
            binaryStringb = "0" + binaryStringb;
        }

        System.out.println(binaryStringa.length() + " " + binaryStringa);
        System.out.println(binaryStringb.length() + " " + binaryStringb);
        System.out.println(compareStrings(binaryStringa, binaryStringb));
    }
}
