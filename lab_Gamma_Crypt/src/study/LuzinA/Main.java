package study.LuzinA;

public class Main {

    public static void main(String[] args) {
        //********Лабораторная работа №2 "Шифрование методом гаммирования"***********
        String str = "Gamma Cipher text";
        String key = "56";
        GammaCipher gamma = new GammaCipher();
        String enc = gamma.cryptoGamma(str, key);
        System.out.println(enc);
        String dec = gamma.cryptoGamma(enc, key);
        System.out.println(dec);
        WindowInterface chooser = new WindowInterface();


    }
}
