package ki305.Sadova.lab3;

import java.io.FileNotFoundException;

/**
 * Клас <code>WaterPistolApp</code> демонструє роботу WaterPistol.
 */
public class WaterPistolApp {
    public static void main(String[] args) throws FileNotFoundException {
        // Створюємо об'єкт
        WaterPistol myGun = new WaterPistol(50, "Ocean Water");
        System.out.println("--- Water Pistol Simulation Start ---");
        System.out.println("Water Type: " + myGun.getWaterType());
        
        // 1. Спроба вистрілити порожнім
        System.out.println("\nAction: Trying to shoot without water...");
        myGun.cock();
        if (myGun.shoot()) {
            System.out.println("Result: Success!");
        } else {
            System.out.println("Result: Failed (as expected).");
        }

        // 2. Заправка
        System.out.println("\nAction: Refilling 20 units of water...");
        myGun.refill(20);
        System.out.println("Current water level: " + myGun.getCurrentRounds());

        // 3. Постріл після заправки
        System.out.println("\nAction: Cocking and shooting...");
        myGun.cock();
        if (myGun.shoot()) {
            System.out.println("Result: SQUIRT! Shot successful.");
        }

        // 4. Перевірка залишку та очищення
        System.out.println("\nFinal water level: " + myGun.getCurrentRounds());
        
        System.out.println("Action: Emptying the reservoir...");
        myGun.empty();
        System.out.println("Water level after emptying: " + myGun.getCurrentRounds());
        
        // Закриття ресурсів
        myGun.dispose();
        System.out.println("\n--- Simulation End ---");
    }
}