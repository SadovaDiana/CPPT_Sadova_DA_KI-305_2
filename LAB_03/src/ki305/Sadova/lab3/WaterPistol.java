package ki305.Sadova.lab3;

import java.io.FileNotFoundException;

/**
 * Клас <code>WaterPistol</code> розширює <code>Pistol</code> та реалізує <code>Refillable</code>.
 * Моделює роботу водяного пістолета.
 */
public class WaterPistol extends Pistol implements Refillable {

    private String waterType; // Наприклад, "Прісна" або "Солона"

    /**
     * Конструктор водяного пістолета.
     * @param capacity Ємність резервуара
     * @param waterType Тип води
     * @throws FileNotFoundException
     */
    public WaterPistol(int capacity, String waterType) throws FileNotFoundException {
        super();
        this.magazine = new Magazine(capacity); // Резервуар для води
        this.waterType = waterType;
        logger.log("WaterPistol created. Water type: " + waterType);
    }

    /**
     * Реалізація пострілу (випорскування води).
     */
    @Override
    public boolean shoot() {
        if (!trigger.isCocked()) {
            logger.log("WATER SHOOT FAILED: Pump it first (cock)!");
            return false;
        }
        if (magazine.getRounds() <= 0) {
            logger.log("WATER SHOOT FAILED: Out of water.");
            return false;
        }

        magazine.unload(1); // Витрачаємо "порцію" води
        trigger.reset();
        barrel.registerShot();
        logger.log("SQUIRT! Water shot successful. Remaining water: " + magazine.getRounds());
        return true;
    }

    @Override
    public void refill(int volume) {
        int added = magazine.load(volume);
        logger.log("Refilled " + added + " units of " + waterType + " water.");
    }

    @Override
    public void empty() {
        magazine.unload(magazine.getRounds());
        logger.log("Reservoir emptied.");
    }
    

    /**
     * Повертає тип води, що використовується.
     * @return waterType
     */
    public String getWaterType() {
        return waterType;
    }
}