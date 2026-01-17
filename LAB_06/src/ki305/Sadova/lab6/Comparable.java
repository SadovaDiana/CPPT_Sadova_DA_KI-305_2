package ki305.Sadova.lab6;

/**
 * Базовий інтерфейс для речей, що мають вагу
 */
interface IItem extends Comparable<IItem> {
    int getWeight();
}

/**
 * Клас, що описує Пакет зі сміттям
 */
class GarbageBag implements IItem {
    private String color;
    private int weight;

    public GarbageBag(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    @Override
    public int getWeight() { return weight; }

    @Override
    public int compareTo(IItem o) {
        return Integer.compare(this.weight, o.getWeight());
    }

    @Override
    public String toString() { return "Пакет [" + color + "], вага: " + weight + " кг"; }
}

/**
 * Клас, що описує Стару техніку
 */
class OldDevice implements IItem {
    private String name;
    private int weight;

    public OldDevice(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public int getWeight() { return weight; }

    @Override
    public int compareTo(IItem o) {
        return Integer.compare(this.weight, o.getWeight());
    }

    @Override
    public String toString() { return "Техніка (" + name + "), вага: " + weight + " кг"; }
}