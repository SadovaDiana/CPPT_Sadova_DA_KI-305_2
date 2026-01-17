package ki305.Sadova.lab5;

import java.io.*;
import java.util.Scanner;

/**
 * Клас <code>FileManager</code> реалізує методи для читання та запису
 * результатів обчислень у текстовому та двійковому форматах.
 */
public class FileManager {

    /**
     * Записує результат обчислення у текстовий файл.
     * @param fileName ім'я файлу
     * @param result значення для запису
     * @throws FileNotFoundException
     */
    public void writeText(String fileName, double result) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.printf("%f", result);
        }
    }

    /**
     * Читає результат з текстового файлу.
     * @param fileName ім'я файлу
     * @return зчитане значення
     * @throws FileNotFoundException
     */
    public double readText(String fileName) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File(fileName))) {
            if (in.hasNextDouble()) {
                return in.nextDouble();
            } else {
                throw new RuntimeException("Некоректні дані у текстовому файлі.");
            }
        }
    }

    /**
     * Записує результат у двійковий файл.
     * @param fileName ім'я файлу
     * @param result значення для запису
     * @throws IOException
     */
    public void writeBin(String fileName, double result) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName))) {
            out.writeDouble(result);
        }
    }

    /**
     * Читає результат з двійкового файлу.
     * @param fileName ім'я файлу
     * @return зчитане значення
     * @throws IOException
     */
    public double readBin(String fileName) throws IOException {
        try (DataInputStream in = new DataInputStream(new FileInputStream(fileName))) {
            return in.readDouble();
        }
    }
}