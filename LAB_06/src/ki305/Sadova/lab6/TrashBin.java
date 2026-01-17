package ki305.Sadova.lab6;

import java.util.ArrayList;

/**
 * Параметризований клас <code>TrashBin</code> моделює сміттєвий бак.
 * @param <T> тип елементів, що зберігаються (мають реалізувати IItem)
 */
public class TrashBin<T extends IItem> {
    private ArrayList<T> items;

    /**
     * Конструктор
     */
    public TrashBin() {
        items = new ArrayList<>();
    }

    /**
     * Додає елемент у бак
     * @param item елемент
     */
    public void addItem(T item) {
        items.add(item);
        System.out.println("Додано: " + item);
    }

    /**
     * Виймає останній доданий елемент
     * @return елемент або null
     */
    public T removeItem() {
        if (!items.isEmpty()) {
            return items.remove(items.size() - 1);
        }
        return null;
    }

    /**
     * Знаходить максимальний елемент за вагою (Варіант 21)
     * @return максимальний елемент
     */
    public T findMax() {
        if (items.isEmpty()) return null;
        T max = items.get(0);
        for (T item : items) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    /**
     * Виводить вміст бака
     */
    public void printAll() {
        System.out.println("--- Вміст бака ---");
        for (T item : items) {
            System.out.println(item);
        }
    }
}