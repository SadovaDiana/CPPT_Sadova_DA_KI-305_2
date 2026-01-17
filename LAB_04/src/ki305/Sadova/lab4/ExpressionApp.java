package ki305.Sadova.lab4;

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Клас <code>ExpressionApp</code> є драйвером для демонстрації обчислень
 */
public class ExpressionApp {
    public static void main(String[] args) {
        Equation eq = new Equation();
        Scanner in = new Scanner(System.in);
        
        try (PrintWriter out = new PrintWriter("Result.txt")) {
            System.out.print("Введіть x (у градусах): ");
            if (!in.hasNextDouble()) {
                System.out.println("Помилка: введено не число.");
                return;
            }
            
            double x = in.nextDouble();
            double res = eq.calculate(x);
            
            System.out.println("Результат: " + res);
            out.println("Для x = " + x + ", y = " + res);
            
        } catch (ArithmeticException e) {
            System.out.println("Математична помилка: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Помилка роботи з файлом: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Виникла непередбачена помилка: " + e.getMessage());
        } finally {
            in.close();
        }
    }
}