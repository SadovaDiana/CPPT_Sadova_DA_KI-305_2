package ki305.Sadova.lab2;

import java.io.FileNotFoundException;
import static java.lang.System.out; // Статичний імпорт (за прикладом)

/**
 * Клас <code>PistolApp</code> реалізує головний метод (main) 
 * для демонстрації можливостей класу Pistol.
 */
public class PistolApp {

    /**
     * Головний метод програми.
     * @param args Аргументи командного рядка (не використовуються).
     * @throws FileNotFoundException Якщо не вдалося створити файл Log.txt.
     */
    public static void main(String[] args) throws FileNotFoundException {
        // 1. Створення об'єкта пістолета
        Pistol gun = new Pistol(15); // Створюємо пістолет з ємністю 15

        out.println("--- Pistol Simulation Start ---");
        out.println("Max capacity: " + gun.getMaxCapacity());
        out.println("Current rounds: " + gun.getCurrentRounds());

        // 2. Демонстрація методів та логування
        
        // Спроба пострілу без патронів/зведеного курка
        out.println("\nTrying to shoot (Should Fail): " + gun.shoot()); // Fails: not cocked
        gun.cock();
        out.println("Trying to shoot (Should Fail): " + gun.shoot()); // Fails: empty magazine

        // Заряджання та перевірка стану
        gun.loadMagazine(10);
        out.println("Current rounds after loading: " + gun.getCurrentRounds());
        out.println("Has rounds: " + gun.hasRounds());
        
        // Постріли
        gun.cock();
        out.println("Shot 1: " + gun.shoot()); // Success
        gun.cock();
        out.println("Shot 2: " + gun.shoot()); // Success
        
        // Перевірка стану
        out.println("Rounds left: " + gun.getCurrentRounds());
        out.println("Total barrel shots: " + gun.getBarrelLifetimeShots());

        // Перевірка коректного завантаження/перевантаження
        gun.loadMagazine(20); // Намагаємось завантажити більше, ніж вміщає магазин
        out.println("Rounds after over-load attempt: " + gun.getCurrentRounds());
        
        // Розряджання
        gun.unloadAll();
        out.println("Rounds after unloading all: " + gun.getCurrentRounds());

        // 3. Коректне завершення роботи з ресурсами
        gun.dispose();
        
        out.println("--- Pistol Simulation End ---");
    }
}