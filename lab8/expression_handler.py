"""
Модуль для обчислення виразу y = sin(3x-5) / ctg(2x)
та роботи з текстовими і двійковими файлами.
"""
import math
import struct

def calculate(x_deg):
    """
    Обчислює значення виразу для заданого x у градусах.
    """
    rad_x = math.radians(x_deg)
    # ctg(2x) = 1/tan(2x), тому ділення на ctg замінюємо множенням на tan
    try:
        tan_2x = math.tan(2 * rad_x)
        # Перевірка на критичні точки, де tan прямує до нескінченності
        if abs(math.cos(2 * rad_x)) < 1e-10:
             raise ArithmeticError("Точка розриву: ctg(2x) не визначений")
             
        y = math.sin(3 * rad_x - math.radians(5)) * tan_2x
        return y
    except ZeroDivisionError:
        raise ArithmeticError("Помилка обчислення: ділення на нуль")

def write_text(file_name, result):
    """Записує результат у текстовий файл."""
    with open(file_name, 'w', encoding='utf-8') as f:
        f.write(str(result))

def read_text(file_name):
    """Читає результат з текстового файлу."""
    with open(file_name, 'r', encoding='utf-8') as f:
        return float(f.read())

def write_bin(file_name, result):
    """Записує результат у двійковий файл."""
    with open(file_name, 'wb') as f:
        # 'd' означає double precision float (8 байт)
        f.write(struct.pack('d', result))

def read_bin(file_name):
    """Читає результат з двійкового файлу."""
    with open(file_name, 'rb') as f:
        data = f.read()
        return struct.unpack('d', data)[0]