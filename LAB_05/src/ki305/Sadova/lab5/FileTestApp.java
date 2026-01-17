package ki305.Sadova.lab5;

import java.io.IOException;
import java.util.Scanner;
/**
 * Клас <code>FileTestApp</code> тестує коректність роботи читання/запису.
 */
public class FileTestApp {
    public static void main(String[] args) {
        Equation eq = new Equation();
        FileManager fileMgr = new FileManager();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter x: ");
        double x = in.nextDouble();

        try {
            // 1. Обчислюємо вираз
            double result = eq.calculate(x);
            System.out.println("Обчислений результат: " + result);

            // 2. Тестуємо ТЕКСТОВИЙ формат
            fileMgr.writeText("res.txt", result);
            double resText = fileMgr.readText("res.txt");
            System.out.println("Результат з текстового файлу: " + resText);

            // 3. Тестуємо ДВІЙКОВИЙ формат
            fileMgr.writeBin("res.bin", result);
            double resBin = fileMgr.readBin("res.bin");
            System.out.println("Результат з двійкового файлу: " + resBin);

            // 4. Порівняння
            if (Math.abs(resText - resBin) < 1E-10) {
                System.out.println("\nТест успішний: дані збігаються!");
            }

        } catch (ArithmeticException e) {
            System.out.println("Математична помилка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка при роботі з файлами: " + e.getMessage());
        }
    }
}