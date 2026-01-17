"""
Модуль, що містить базовий клас Pistol.
"""

class Pistol:
    """Клас, що описує базову функціональність пістолета."""

    def __init__(self, model, capacity):
        """
        Конструктор базового класу.
        :param model: Назва моделі
        :param capacity: Ємність магазину
        """
        self.model = model
        self.capacity = capacity
        self.ammo_count = 0

    def load(self, count):
        """Заряджання пістолета."""
        self.ammo_count = min(self.capacity, self.ammo_count + count)
        print(f"{self.model}: Заряджено. Набоїв: {self.ammo_count}")

    def shoot(self):
        """Постріл з пістолета."""
        if self.ammo_count > 0:
            self.ammo_count -= 1
            print(f"{self.model}: Бабах! Залишилось набоїв: {self.ammo_count}")
        else:
            print(f"{self.model}: Клац! Магазин порожній.")

    def get_info(self):
        """Повертає інформацію про об'єкт."""
        return f"Пістолет {self.model}, ємність: {self.capacity}"