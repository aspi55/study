package study.LuzinA;


// Пример использования диалоговых окон работы с файлами и директориями

import org.w3c.dom.css.ViewCSS;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
// import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class WindowInterface extends JFrame {
    private static final long serialVersionUID = 1L;

    private JButton btnSaveFile = null;
    private JButton btnOpenFile = null;
    private JButton btnCrypt = null;
    private JTextArea textArea = null;
    private JFileChooser fileChooser = null;
    private JTextField textField = null;
    private JLabel label = null;
    private String path = "";

    public String getPath() {
        return path;
    }

    private final String[][] FILTERS = {{"txt", "Текстовые файлы (*.txt)"}};

    public WindowInterface() {
        super("Шифровка Гаммированием");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Кнопка создания диалогового окна для сохранения файла
        btnSaveFile = new JButton ("Сохранить файл");
        // Кнопка создания диалогового окна для сохранения файла
        btnOpenFile = new JButton("Открыть файл");
        btnCrypt = new JButton("Шифровать/Дешифровать");
        textArea = new JTextArea(15,40);
        textField = new JTextField(10);
        label = new JLabel("Ключ шифрования:");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);


        // Создание экземпляра JFileChooser
        fileChooser = new JFileChooser();
        // Подключение слушателей к кнопкам
        addFileChooserListeners();
        // Размещение кнопок в интерфейсе
        JPanel contents = new JPanel();
        contents.add(label);
        contents.add(textField);
        contents.add(textArea);
        contents.add(btnCrypt);
        contents.add(btnSaveFile);
        contents.add(btnOpenFile);

        getContentPane().add(contents);
        // Вывод окна на экран
        setSize(560, 350);
        setVisible(true);
    }

    private void addFileChooserListeners() {
        FileAdapter fileAdapter = new FileAdapter();
        GammaCipher crypto = new GammaCipher();
        btnCrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea.setText(crypto.cryptoGamma(textArea.getText(), textField.getText()));
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(WindowInterface.this, "Введите ключ шифрования!");
                    exception.printStackTrace();
                }
            }
        });
        btnSaveFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Сохранение файла");
                // Определение режима - только файл
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showSaveDialog(WindowInterface.this);
                // Если файл выбран, сохраним путь к нему
                if (result == JFileChooser.APPROVE_OPTION) {
                    path = fileChooser.getSelectedFile().toString();
                    try {
                        fileAdapter.fileWrite(path, textArea.getText());
                        JOptionPane.showMessageDialog(WindowInterface.this,
                                "Файл '" + fileChooser.getSelectedFile() +
                                        " сохранен");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });
        btnOpenFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выберите файл");
                // Определение режима - только файл
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(WindowInterface.this);
                // Если файл выбран, сохраним путь к нему
                if (result == JFileChooser.APPROVE_OPTION) {
                    path = fileChooser.getSelectedFile().toString();
                    try {
                        fileAdapter.fileOpen(path);
                        textArea.setText(fileAdapter.getTextFromFile());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    // Фильтр выбора файлов определенного типа
    class FileFilterExt extends javax.swing.filechooser.FileFilter {
        String extension;  // расширение файла
        String description;  // описание типа файлов

        FileFilterExt(String extension, String descr) {
            this.extension = extension;
            this.description = descr;
        }

        @Override
        public boolean accept(java.io.File file) {
            if (file != null) {
                if (file.isDirectory())
                    return true;
                if (extension == null)
                    return (extension.length() == 0);
                return file.getName().endsWith(extension);
            }
            return false;
        }

        // Функция описания типов файлов
        @Override
        public String getDescription() {
            return description;
        }
    }

}

