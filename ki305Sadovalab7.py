"""
Модуль для виконання лабораторної роботи №7.
Автор: Садова
Варіант №21: Заповнення верхнього правого трикутника матриці.
"""
import sys

def generate_matrix():
    """
    Функція запитує розмір матриці та символ, будує зубчастий масив 
    і записує результат у текстовий файл.
    """
    # Введення розміру матриці
    try:
        size = int(input("Введіть розмір квадратної матриці: "))
        if size <= 0:
            print("Розмір має бути додатним числом.")
            return
        
        filler = input("Введіть символ заповнення: ")
        if len(filler) != 1:
            print("Будь ласка, введіть рівно один символ.")
            return
            
        # Створення зубчастого масиву (список списків)
        # Для зафарбовування верхнього правого трикутника:
        # Символ ставиться, якщо номер стовпця (j) >= номеру рядка (i)
        matrix = []
        for i in range(size):
            row = []
            for j in range(size):

                if j >= i:
                    row.append(filler)
                else:
                    row.append(" ") # Порожнє місце
            matrix.append(row)

        # Виведення на екран та запис у файл
        with open("matrix_result.txt", "w", encoding="utf-8") as f:
            for row in matrix:
                line = " ".join(row)
                print(line)
                f.write(line + "\n")
                
        print("\nРезультат також збережено у файл 'matrix_result.txt'")

    except ValueError:
        print("Помилка: введіть ціле число для розміру.")

if __name__ == "__main__":
    generate_matrix()