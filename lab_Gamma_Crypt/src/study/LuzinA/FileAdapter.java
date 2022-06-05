package study.LuzinA;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileAdapter {

    private String textFromFile;

    public void fileWrite(String path, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        {
            try {
                fileWriter.write(text.toCharArray());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            fileWriter.close();
        }
    }

    public void fileOpen(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        try {

            int i;
            textFromFile = "";
            while((i=fileReader.read())!= -1){
                textFromFile += (char)i;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fileReader.close();
    }

    public String getTextFromFile() {
        return textFromFile;
    }

}
