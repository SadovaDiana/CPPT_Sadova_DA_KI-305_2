package ki305.Sadova.lab2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Клас <code>Pistol</code> реалізує симуляцію пістолета.
 * Він містить механізми для заряджання, пострілів, перевірки
 * стану та веде протокол своїх дій у файл.
 */
public class Pistol {

    // Поля-об'єкти (мінімум 3 за вимогою)
    private Magazine magazine;
    private Trigger trigger;
    private Barrel barrel;
    private Logger logger; // Для ведення протоколу

    /**
     * Конструктор за замовчуванням.
     * Ініціалізує пістолет із магазином на 10 патронів та незведеним курком.
     * * @throws FileNotFoundException Якщо не вдається створити файл Log.txt.
     */
    public Pistol() throws FileNotFoundException {
        // Ініціалізація допоміжних об'єктів
        magazine = new Magazine(10); // Магазин на 10 патронів
        trigger = new Trigger();
        barrel = new Barrel();
        logger = new Logger("PistolLog.txt");

        logger.log("Pistol created with default magazine (10 max capacity).");
    }

    /**
     * Конструктор з параметром для встановлення максимальної ємності магазину.
     *
     * @param maxCapacity Максимальна кількість патронів, яку може вмістити магазин.
     * @throws FileNotFoundException Якщо не вдається створити файл Log.txt.
     */
    public Pistol(int maxCapacity) throws FileNotFoundException {
        // Виклик іншого конструктора (вимога this() має бути першою)
        this(); 
        
        // Зміна параметрів, якщо вони задані
        if (maxCapacity > 0) {
            magazine = new Magazine(maxCapacity);
            logger.log("Pistol created with custom max capacity: " + maxCapacity);
        } else {
            logger.log("Invalid maxCapacity provided. Using default (10).");
        }
    }

    /**
     * Конструктор для ініціалізації пістолета з заданою ємністю та початковими патронами.
     * * @param maxCapacity Максимальна ємність магазину.
     * @param initialRounds Початкова кількість патронів у магазині.
     * @throws FileNotFoundException Якщо не вдається створити файл Log.txt.
     */
    public Pistol(int maxCapacity, int initialRounds) throws FileNotFoundException {
        this(maxCapacity); // Виклик попереднього конструктора

        if (initialRounds > 0 && initialRounds <= magazine.getMaxCapacity()) {
            magazine.load(initialRounds);
            logger.log("Loaded " + initialRounds + " rounds initially.");
        } else {
            logger.log("No initial rounds loaded (invalid amount or capacity mismatch).");
        }
    }

    // --- Методи (мінімум 10 за вимогою) ---

    /**
     * Симулює постріл з пістолета.
     * Для пострілу необхідно, щоб курок був зведений, а магазин не був порожнім.
     * * @return true, якщо постріл відбувся успішно; false, якщо не відбувся.
     */
    public boolean shoot() {
        if (!trigger.isCocked()) {
            logger.log("SHOOT FAILED: Trigger is not cocked.");
            return false;
        }

        if (magazine.getRounds() <= 0) {
            logger.log("SHOOT FAILED: Magazine is empty.");
            return false;
        }

        // Постріл успішний
        magazine.unload(1);
        trigger.reset(); // Курок скидається після пострілу
        barrel.registerShot();
        logger.log("SUCCESSFUL SHOT. Rounds left: " + magazine.getRounds());
        return true;
    }

    /**
     * Зводить курок пістолета, готуючи його до пострілу.
     */
    public void cock() {
        if (trigger.isCocked()) {
            logger.log("COCK FAILED: Trigger is already cocked.");
        } else {
            trigger.cock();
            logger.log("Trigger cocked.");
        }
    }

    /**
     * Скидає курок без пострілу (аналог безпечного зняття з бойового зводу).
     */
    public void deCock() {
        if (trigger.isCocked()) {
            trigger.reset();
            logger.log("Trigger successfully decocked (safety mode).");
        } else {
            logger.log("DECOCK FAILED: Trigger is already in reset position.");
        }
    }

    /**
     * Заряджає в магазин повну кількість патронів, якщо це можливо.
     *
     * @param roundsToLoad Кількість патронів для додавання.
     * @return Фактична кількість доданих патронів.
     */
    public int loadMagazine(int roundsToLoad) {
        int added = magazine.load(roundsToLoad);
        if (added > 0) {
            logger.log("Loaded " + added + " rounds. Total rounds: " + magazine.getRounds());
        } else {
            logger.log("LOAD FAILED: Magazine is full or roundsToLoad is invalid.");
        }
        return added;
    }

    /**
     * Розряджає магазин, виймаючи всі патрони.
     *
     * @return Кількість вийнятих патронів.
     */
    public int unloadAll() {
        int unloaded = magazine.unload(magazine.getRounds());
        logger.log("Unloaded all rounds. Total rounds: " + magazine.getRounds());
        return unloaded;
    }

    /**
     * Перевіряє, чи є патрони в магазині.
     *
     * @return true, якщо магазин не порожній.
     */
    public boolean hasRounds() {
        boolean result = magazine.getRounds() > 0;
        logger.log("Check rounds: " + (result ? "YES" : "NO"));
        return result;
    }

    /**
     * Повертає поточну кількість патронів у магазині.
     *
     * @return Поточна кількість патронів.
     */
    public int getCurrentRounds() {
        return magazine.getRounds();
    }

    /**
     * Повертає максимальну ємність магазину.
     *
     * @return Максимальна ємність магазину.
     */
    public int getMaxCapacity() {
        return magazine.getMaxCapacity();
    }

    /**
     * Повертає, чи курок зведений.
     *
     * @return true, якщо курок зведений.
     */
    public boolean isCocked() {
        return trigger.isCocked();
    }
    
    /**
     * Повертає кількість пострілів, зроблених зі ствола.
     * * @return Загальна кількість пострілів.
     */
    public int getBarrelLifetimeShots() {
        return barrel.getTotalShots();
    }

    /**
     * Метод для коректного звільнення ресурсів, зокрема, закриття файлу протоколу.
     * Це задовольняє вимогу про механізм коректного завершення роботи з файлом.
     */
    public void dispose() {
        logger.log("Pistol object disposed. Closing log file...");
        logger.close();
        System.out.println("Log file closed successfully.");
    }
}

// --- Допоміжні класи (розміщуються в тому ж файлі, якщо не public) ---

/**
 * Клас <code>Magazine</code> симулює магазин пістолета.
 */
class Magazine {
    private int maxCapacity;
    private int currentRounds;

    /**
     * Конструктор магазину.
     * @param capacity Максимальна ємність.
     */
    public Magazine(int capacity) {
        this.maxCapacity = capacity > 0 ? capacity : 1;
        this.currentRounds = 0;
    }

    /**
     * Додає патрони в магазин.
     * @param count Кількість патронів для додавання.
     * @return Фактична кількість доданих патронів.
     */
    public int load(int count) {
        int availableSpace = maxCapacity - currentRounds;
        int added = Math.min(count, availableSpace);
        currentRounds += added;
        return added;
    }

    /**
     * Виймає патрони з магазину.
     * @param count Кількість патронів для виймання (пострілу).
     * @return Фактична кількість вийнятих патронів.
     */
    public int unload(int count) {
        int unloaded = Math.min(count, currentRounds);
        currentRounds -= unloaded;
        return unloaded;
    }

    public int getRounds() {
        return currentRounds;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}

/**
 * Клас <code>Trigger</code> симулює курок пістолета.
 */
class Trigger {
    private boolean isCocked; // True, якщо курок зведений

    public Trigger() {
        this.isCocked = false;
    }

    public void cock() {
        this.isCocked = true;
    }

    public void reset() {
        this.isCocked = false;
    }

    public boolean isCocked() {
        return isCocked;
    }
}

/**
 * Клас <code>Barrel</code> симулює ствол пістолета.
 */
class Barrel {
    private int totalShots;

    public Barrel() {
        this.totalShots = 0;
    }

    public void registerShot() {
        this.totalShots++;
    }

    public int getTotalShots() {
        return totalShots;
    }
}

/**
 * Клас <code>Logger</code> симулює механізм ведення протоколу у файл.
 * Є аналогом PrintWriter з демонстраційного прикладу.
 */
class Logger {
    private PrintWriter fout;

    /**
     * Конструктор, який відкриває файл для запису.
     * @param filename Назва файлу протоколу.
     * @throws FileNotFoundException Якщо не вдалося відкрити файл.
     */
    public Logger(String filename) throws FileNotFoundException {
        // Використовуємо true для додавання даних (append)
        fout = new PrintWriter(filename); 
    }

    /**
     * Записує повідомлення у файл протоколу.
     * @param message Повідомлення для запису.
     */
    public void log(String message) {
        // Додаємо мітку часу для кращого протоколу
        fout.println("[" + java.time.LocalTime.now() + "] " + message);
        fout.flush(); // Негайно записуємо дані у файл
    }

    /**
     * Закриває файл протоколу.
     */
    public void close() {
        if (fout != null) {
            fout.close();
        }
    }
}