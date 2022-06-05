package study.LuzinA;

public class GammaCipher {
    private final static int a = 3;
    private final static int b = 2;
    private final static int m = 40692;

    String cryptoGamma (final String text, final String key){
        String result = "";
        char currentKey [] = key.toCharArray();

        for(int i=0; i<text.length(); i++){
            result += (char)(text.charAt(i)^currentKey[i%key.length()]);
            currentKey [i%key.length()] = (char) ((currentKey [i%currentKey.length]*a+b)%m);
        }
        return result;
    }
}
