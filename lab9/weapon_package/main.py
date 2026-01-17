"""
Головний модуль програми (Точка входу), розміщений у пакеті.
"""
from pistol import Pistol
from water_pistol import WaterPistol

def main():
    """Демонстрація роботи класів."""
    # Створення об'єкта базового класу
    my_pistol = Pistol("Glock-17", 17)
    my_pistol.load(10)
    my_pistol.shoot()
    
    print("-" * 20)
    
    # Створення об'єкта похідного класу
    my_toy = WaterPistol("SuperSoaker", 300, "Червоний")
    my_toy.fill_full()
    my_toy.shoot()

if __name__ == "__main__":
    main()