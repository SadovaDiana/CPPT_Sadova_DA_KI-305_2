"""
Модуль для виконання лабораторної роботи №8.
Автор: Садова
Варіант №21: y=sin(3x-5)/ctg(2x) 45
"""
import expression_handler as eh

def main():
    """Основна функція для демонстрації роботи програми."""
    try:
        x_val = float(input("Введіть x у градусах: "))
        res = eh.calculate(x_val)
        print(f"Обчислений результат: {res}")

        # Робота з текстовим файлом
        eh.write_text("result.txt", res)
        text_res = eh.read_text("result.txt")
        print(f"Прочитано з тексту: {text_res}")

        # Робота з двійковим файлом
        eh.write_bin("result.bin", res)
        bin_res = eh.read_bin("result.bin")
        print(f"Прочитано з бінарного файлу: {bin_res}")

    except ValueError:
        print("Помилка: введіть коректне число")
    except ArithmeticError as e:
        print(f"Математична помилка: {e}")
    except Exception as e:
        print(f"Виникла помилка: {e}")

if __name__ == "__main__":
    main()