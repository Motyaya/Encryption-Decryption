package encryptdecrypt;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String keyString = "0";
        String text = "";
        String choose = "enc";
        String fileRead = "";
        String fileWrite = "";
        String algoritm = "shift";
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-data")) text = args[i + 1];
            if (args[i].equals("-key")) keyString = args[i + 1];
            if (args[i].equals("-mode")) choose = args[i + 1];
            if (args[i].equals("-in")) fileRead = args[i + 1];
            if (args[i].equals("-out")) fileWrite = args[i + 1];
            if (args[i].equals("-alg")) algoritm = args[i + 1];
        }
        if (!fileRead.equals("") && text.equals("")) {
            File file = new File(fileRead);
            Scanner readScanner = new Scanner(file);
            while (readScanner.hasNext()) {
                text = readScanner.nextLine();
            }
            readScanner.close();
        }
        int key = Integer.parseInt(keyString);
        AlgoritmFactory algoritmFactory = new AlgoritmFactory();
        algoritmFactory.getAlgoritm(text, key, algoritm, choose, fileWrite);
    }
}
class AlgoritmFactory{
    public void getAlgoritm(String text, int key, String algoritm, String choose, String fileWrite) throws IOException {
        if (choose.equals("dec") && !fileWrite.equals("") && algoritm.equals("unicode")) new Unicode().decryption(text, key, fileWrite);
        else if (choose.equals("dec") && fileWrite.equals("") && algoritm.equals("unicode")) new Unicode().decryption(text, key);
        else if (!fileWrite.equals("") && algoritm.equals("unicode")) new Unicode().encryption(text, key, fileWrite);
        else if (choose.equals("dec") && !fileWrite.equals("") && algoritm.equals("shift")) new Shift().decryption(text, key, fileWrite);
        else if (choose.equals("dec") && fileWrite.equals("") && algoritm.equals("shift")) new Shift().decryption(text, key);
        else if (!fileWrite.equals("") && algoritm.equals("shift")) new Shift().encryption(text, key, fileWrite);
        else if (algoritm.equals("unicode")) new Unicode().encryption(text, key);
        else new Shift().encryption(text,key);
    }
}

interface Algoritm{
    void encryption(String text, int key);
    void decryption(String text,int key);
    void encryption(String text, int key,String fileWrite) throws IOException;
    void decryption(String text,int key,String fileWrite) throws IOException;
}

class Shift implements Algoritm{

    @Override
    public void encryption(String text, int key) {
        char[] textMass = text.toCharArray();
        for (int i = 0; i < textMass.length; i++) {
            if (textMass[i] >= 65 && textMass[i] <= 90 || textMass[i] >= 97 && textMass[i] <= 122) {
                if ((textMass[i] + key) > 122) {
                    textMass[i] = (char) (96 + (key - (122 - textMass[i])));
                    System.out.print(textMass[i]);
                }else if ((textMass[i]+key)>90 && ((textMass[i]+key)<97)){
                    textMass[i] = (char) (64 + (key - (90 - textMass[i])));
                    System.out.print(textMass[i]);
                } else {
                    textMass[i] += key;
                    System.out.print(textMass[i]);
                }
            } else System.out.print(textMass[i]);
        }
    }

    @Override
    public void decryption(String text, int key) {
        char[] textMass = text.toCharArray();
        for (int i = 0; i < textMass.length; i++) {
            if (textMass[i] >= 65 && textMass[i] <= 90 || textMass[i] >= 97 && textMass[i] <= 122) {
                if ((textMass[i] - key) < 97 && textMass[i]>90) {
                    textMass[i] = (char) (122 - (key - (textMass[i] - 96)));
                    System.out.print(textMass[i]);
                } else if ((textMass[i] - key) < 65){
                    textMass[i] = (char) (90 - (key - (textMass[i] - 64)));
                    System.out.print(textMass[i]);
                } else {
                    textMass[i] -= key;
                    System.out.print(textMass[i]);
                }
            } else System.out.print(textMass[i]);
        }
    }

    @Override
    public void encryption(String text, int key, String fileWrite) throws IOException {
        FileWriter fileWriter = new FileWriter(fileWrite);
        char[] textMass = text.toCharArray();
        for (int i = 0; i < textMass.length; i++) {
            if (textMass[i] >= 65 && textMass[i] <= 90 || textMass[i] >= 97 && textMass[i] <= 122) {
                if ((textMass[i] + key) > 122) {
                    textMass[i] = (char) (96 + (key - (122 - textMass[i])));
                    fileWriter.write(textMass[i]);
                } else if ((textMass[i]+key)>90 && ((textMass[i]+key)<97)){
                    textMass[i] = (char) (64 + (key - (90 - textMass[i])));
                    fileWriter.write(textMass[i]);
                }
                else {
                    textMass[i] += key;
                    fileWriter.write(textMass[i]);
                }
            } else fileWriter.write(textMass[i]);
        }
        fileWriter.close();
    }

    @Override
    public void decryption(String text, int key, String fileWrite) throws IOException {
        FileWriter fileWriter = new FileWriter(fileWrite);
        char[] textMass = text.toCharArray();
        for (int i = 0; i < textMass.length; i++) {
            if (textMass[i] >= 65 && textMass[i] <= 90 || textMass[i] >= 97 && textMass[i] <= 122) {
                if ((textMass[i] - key) < 97 && textMass[i]>90) {
                    textMass[i] = (char) (122 - (key - (textMass[i] - 96)));
                    fileWriter.write(textMass[i]);
                } else if ((textMass[i] - key) < 65){
                    textMass[i] = (char) (90 - (key - (textMass[i] - 64)));
                    fileWriter.write(textMass[i]);
                }
                else {
                    textMass[i] -= key;
                    fileWriter.write(textMass[i]);
                }
            } else fileWriter.write(textMass[i]);
        }
        fileWriter.close();
    }
}
class Unicode implements Algoritm{

    @Override
    public void encryption(String text, int key) {
        char[] textMass = text.toCharArray();
        for (int i = 0; i < textMass.length; i++) {
            if (textMass[i] >= 32 && textMass[i] <= 126) {
                if ((textMass[i] + key) > 126) {
                    textMass[i] = (char) (32 + (key - (127 - textMass[i])));
                    System.out.print(textMass[i]);
                } else {
                    textMass[i] += key;
                    System.out.print(textMass[i]);
                }
            } else System.out.print(textMass[i]);
        }
    }

    @Override
    public void decryption(String text, int key) {
        char[] textMass = text.toCharArray();
        for (int i = 0; i < textMass.length; i++) {
            if (textMass[i] >= 32 && textMass[i] <= 126) {
                if ((textMass[i] - key) < 32) {
                    textMass[i] = (char) (126 - (key - (textMass[i] - 31)));
                    System.out.print(textMass[i]);
                } else {
                    textMass[i] -= key;
                    System.out.print(textMass[i]);
                }
            } else System.out.print(textMass[i]);
        }
    }

    @Override
    public void encryption(String text, int key, String fileWrite) throws IOException {
        FileWriter fileWriter = new FileWriter(fileWrite);
        char[] textMass = text.toCharArray();
        for (int i = 0; i < textMass.length; i++) {
            if (textMass[i] >= 32 && textMass[i] <= 126) {
                if ((textMass[i] + key) > 126) {
                    textMass[i] = (char) (32 + (key - (127 - textMass[i])));
                    fileWriter.write(textMass[i]);
                } else {
                    textMass[i] += key;
                    fileWriter.write(textMass[i]);
                }
            } else fileWriter.write(textMass[i]);
        }
        fileWriter.close();
    }

    @Override
    public void decryption(String text, int key, String fileWrite) throws IOException {
        FileWriter fileWriter = new FileWriter(fileWrite);
        char[] textMass = text.toCharArray();
        for (int i = 0; i < textMass.length; i++) {
            if (textMass[i] >= 32 && textMass[i] <= 126) {
                if ((textMass[i] - key) < 32) {
                    textMass[i] = (char) (126 - (key - (textMass[i] - 31)));
                    fileWriter.write(textMass[i]);
                } else {
                    textMass[i] -= key;
                    fileWriter.write(textMass[i]);
                }
            } else fileWriter.write(textMass[i]);
        }
        fileWriter.close();
    }
}