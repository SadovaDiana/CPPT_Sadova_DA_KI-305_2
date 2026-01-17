package ki305.Sadova.lab6;

/**
 * Клас-драйвер для тестування сміттєвого бака.
 */
public class TrashBinApp {
    public static void main(String[] args) {
        // Створюємо бак, який може містити будь-які IItem
        TrashBin<? super IItem> bin = new TrashBin<>();

        // Додаємо об'єкти різних класів (GarbageBag та OldDevice)
        bin.addItem(new GarbageBag("Чорний", 5));
        bin.addItem(new OldDevice("Монітор", 12));
        bin.addItem(new GarbageBag("Зелений", 2));
        bin.addItem(new OldDevice("Чайник", 3));

        bin.printAll();

        // Знаходимо найважчий предмет
        IItem maxWeight = (IItem) bin.findMax();
        System.out.println("\nНайважчий предмет: " + maxWeight);

        // Виймаємо елемент
        System.out.println("\nВиймаємо останній предмет: " + bin.removeItem());
        
        System.out.println("\nНовий вміст:");
        bin.printAll();
    }
}