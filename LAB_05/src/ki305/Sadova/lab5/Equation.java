package ki305.Sadova.lab5;

/**
 * Клас <code>Equation</code> реалізує метод для обчислення математичного виразу.
 * Варіант 21: y = sin(3x-5) / ctg(2x)
 */
public class Equation {
    /**
     * Обчислює значення виразу y = sin(3x-5) * tan(2x)
     * @param x параметр у градусах
     * @return результат обчислення
     * @throws ArithmeticException при виникненні математичних помилок (наприклад, tan(90°))
     */
    public double calculate(double x) throws ArithmeticException {
        double radX = Math.toRadians(x);
        double result;

        // Перевірка на критичні значення для тангенса (коли cos(2x) = 0)
        // Наприклад, для x = 45, 135 градусів і т.д.
        if (Math.abs(Math.cos(2 * radX)) < 1E-10) {
            throw new ArithmeticException("Математична помилка: ctg(2x) не визначений (ділення на 0)");
        }

        result = Math.sin(3 * radX - Math.toRadians(5)) * Math.tan(2 * radX);

        if (Double.isNaN(result) || Double.isInfinite(result)) {
            throw new ArithmeticException("Результат не є числом (NaN або нескінченність)");
        }

        return result;
    }
}