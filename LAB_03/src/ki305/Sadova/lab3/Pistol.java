package ki305.Sadova.lab3;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Абстрактний клас <code>Pistol</code> є базою для різних типів пістолетів.
 */
public abstract class Pistol {
    protected Magazine magazine;
    protected Trigger trigger;
    protected Barrel barrel;
    protected Logger logger;

    public Pistol() throws FileNotFoundException {
        magazine = new Magazine(10);
        trigger = new Trigger();
        barrel = new Barrel();
        logger = new Logger("PistolLog.txt");
    }

    /**
     * Абстрактний метод для пострілу. Має бути реалізований у підкласах.
     * @return true якщо постріл успішний
     */
    public abstract boolean shoot();

    // Методи реалізовані в Лаб 2 залишаються тут як база
    public void cock() {
        if (trigger.isCocked()) {
            logger.log("COCK FAILED: Trigger is already cocked.");
        } else {
            trigger.cock();
            logger.log("Trigger cocked.");
        }
    }

    public void dispose() {
        logger.log("Closing log file...");
        logger.close();
    }
    
    public int getCurrentRounds() { return magazine.getRounds(); }
}

/**
 * Допоміжний клас для ведення логів.
 */
class Logger {
    private PrintWriter fout;
    public Logger(String filename) throws FileNotFoundException {
        fout = new PrintWriter(filename);
    }
    public void log(String message) {
        fout.println("[" + java.time.LocalTime.now() + "] " + message);
        fout.flush();
    }
    public void close() { if (fout != null) fout.close(); }
}

// Інші допоміжні класи (Magazine, Trigger, Barrel) аналогічні Лаб 2
class Magazine {
    private int maxCapacity;
    private int currentRounds;
    public Magazine(int capacity) { this.maxCapacity = capacity; this.currentRounds = 0; }
    public int load(int count) {
        int added = Math.min(count, maxCapacity - currentRounds);
        currentRounds += added;
        return added;
    }
    public int unload(int count) {
        int removed = Math.min(count, currentRounds);
        currentRounds -= removed;
        return removed;
    }
    public int getRounds() { return currentRounds; }
}

class Trigger {
    private boolean isCocked = false;
    public void cock() { isCocked = true; }
    public void reset() { isCocked = false; }
    public boolean isCocked() { return isCocked; }
}

class Barrel {
    private int totalShots = 0;
    public void registerShot() { totalShots++; }
}