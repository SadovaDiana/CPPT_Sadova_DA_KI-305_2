"""
Модуль, що містить похідний клас WaterPistol.
"""
from pistol import Pistol

class WaterPistol(Pistol):
    """Клас, що описує водяний пістолет, який успадковує Pistol."""

    def __init__(self, model, capacity, color):
        """
        Конструктор похідного класу.
        :param color: Колір пластику
        """
        # Виклик конструктора базового класу
        super().__init__(model, capacity)
        self.color = color

    def shoot(self):
        """Перевизначений метод пострілу для води."""
        if self.ammo_count > 0:
            self.ammo_count -= 1
            print(f"{self.model} ({self.color}): Пс-с-ст! Виприснуто воду. Залишок: {self.ammo_count} мл")
        else:
            print(f"{self.model}: Вода закінчилася! Потрібна дозаправка.")

    def fill_full(self):
        """Специфічний метод для водяного пістолета."""
        self.ammo_count = self.capacity
        print(f"{self.model}: Повністю заправлений водою ({self.capacity} мл).")