
import java.io.*;
import java.util.Scanner;

/**
 * Клас Lab1SadovaKI-305 створює трикутну матрицю із символів,
 * яку користувач вводить, і виводить її у консоль та файл MyFile.txt.
 * <p>
 * Програма запитує у користувача:
 * <ul>
 *     <li>розмір квадратної матриці (кількість рядків);</li>
 *     <li>символ-заповнювач для заштрихованих клітинок.</li>
 * </ul>
 * У результаті формується "трикутник" із символів у правому верхньому куті.
 * </p>
 * 
 */
public class Lab1SadovaKI305 {

    /**
     * Головний метод програми, який реалізує логіку створення матриці,
     * заповнення її символом та вивід у консоль і файл.
     * 
     * @param args аргументи командного рядка (не використовуються)
     * @throws FileNotFoundException якщо файл для запису не може бути створений
     */
    public static void main(String[] args) throws FileNotFoundException {
        int nRows; // Розмір квадратної матриці
        char[][] arr; // Зубчастий масив для зберігання заштрихованої області
        String filler; // Символ-заповнювач
        Scanner in = new Scanner(System.in);
        File dataFile = new File("MyFile.txt");
        PrintWriter fout = new PrintWriter(dataFile);
        
        // Введення розміру матриці
        System.out.print("Введіть розмір квадратної матриці: ");
        nRows = in.nextInt();
        in.nextLine(); // Очищення буфера після введення числа
        
        // Створення зубчастого масиву для зберігання лише заштрихованих елементів
        arr = new char[nRows][];
        for (int i = 0; i < nRows; i++) {
            arr[i] = new char[nRows - i]; // Кількість елементів у рядку
        }
        
        // Введення символу-заповнювача
        System.out.print("\nВведіть символ-заповнювач: ");
        filler = in.nextLine();
        
        // Перевірка коректності введення символу-заповнювача
        if (filler.length() == 0) {
            System.out.println("Не введено символ заповнювач");
            fout.close();
            in.close();
            return;
        } else if (filler.length() > 1) {
            System.out.println("Забагато символів заповнювачів");
            fout.close();
            in.close();
            return;
        }
        
        char fillChar = filler.charAt(0); // Отримуємо символ-заповнювач
        
        // Заповнення масиву та виведення результату
        for (int i = 0; i < nRows; i++) {
            // Виведення пробілів перед заштрихованою областю
            for (int k = 0; k < i; k++) {
                System.out.print("  ");
                fout.print("  ");
            }
            
            // Заповнення та виведення заштрихованих елементів
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = fillChar;
                System.out.print(arr[i][j] + " ");
                fout.print(arr[i][j] + " ");
            }
            
            System.out.println();
            fout.println();
        }
        
        // Завершення роботи з файлом
        fout.flush();
        fout.close();
        in.close();
        
    }
}
