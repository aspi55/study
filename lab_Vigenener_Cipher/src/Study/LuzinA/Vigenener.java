package Study.LuzinA;

import java.util.Arrays;

public class Vigenener {
 //   private int offset;
    private char offset;
    private final static int numberOfChars = 26;


    public String encrypt(final String text, String key) {
        //c(i) = (p(i)+k(i))modN
        String encrypt = "";
        final int keyLen = key.length();
        for (int i = 0, len = text.length(); i < len; i++) {
            if(Character.isUpperCase(text.charAt(i))){
                offset = 'A';
                key = key.toUpperCase();
            }
            else {
                offset = 'a';
                key = key.toLowerCase();
            }

            if (Character.isLetter(text.charAt(i)))
                encrypt += (char) (((text.charAt(i) + key.charAt(i % keyLen) - 2 * offset) % numberOfChars) + offset);
            else encrypt += text.charAt(i);
        }
        return encrypt;
    }

    public String decrypt(final String cipher,  String key) {
        //p(i) = (c(i)+N-k(i))modN
        String decrypt = "";
        final int keyLen = key.length();
        for (int i = 0, len = cipher.length(); i < len; i++) {
                if(Character.isUpperCase(cipher.charAt(i))) {
                    offset = 'A';
                    key = key.toUpperCase();
                }
                else {
                    offset = 'a';
                    key = key.toLowerCase();
                }
                if (Character.isLetter(cipher.charAt(i)))
                    decrypt += (char)(((cipher.charAt(i) - key.charAt(i % keyLen) + numberOfChars) % numberOfChars) + offset);
                else decrypt += cipher.charAt(i);
          //      decrypt += temp;

        }
        return decrypt;
    }
    public void charSetNumbers (String text){
      //  System.out.println("");
        char chars [] = text.toCharArray();
        int charSet [] = new int[chars.length];
        for(int i = 0; i<text.length(); i++) {
            charSet[ i] =chars[i];
            System.out.print(charSet[i] + " ");
        }
    }
}


