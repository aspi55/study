package Study.LuzinA;

public class Main {

    public static void main(String[] args) {
	// write your code here
        final String str = "VIGENENER, TEST message"; //Сообщение для зашифровки
        final String key = "KeyV"; //ключ шифровки
        final Vigenener v = new Vigenener();
        System.out.println(str); // вывод строки с сообщением для шифрования
        v.charSetNumbers(str); //символьное представление строки
        String enc = v.encrypt(str, key); //шифрование сообщения
        System.out.println("");
        System.out.println(enc); //вывод зашифрованного сообщения
        v.charSetNumbers(enc); //символьное представление зашифрованного сообщения
        String dec = v.decrypt(enc, key); //дешифровка сообщения
        System.out.println("");
        System.out.println(dec); //вывод расшифрованного сообщения
        v.charSetNumbers(dec);
        System.out.println("");
        IndexOfCoincidence index = new IndexOfCoincidence();
        index.indexOfCoincidence(enc, enc.length(), key.length());
    }
}
