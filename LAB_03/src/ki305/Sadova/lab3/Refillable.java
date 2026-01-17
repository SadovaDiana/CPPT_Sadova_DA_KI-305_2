package ki305.Sadova.lab3;

/**
 * Інтерфейс <code>Refillable</code> визначає методи для предметів, 
 * які можна наповнювати рідиною.
 */
public interface Refillable {
    /**
     * Наповнює резервуар до певного рівня.
     * @param volume об'єм рідини
     */
    void refill(int volume);
    
    /**
     * Повністю спустошує резервуар.
     */
    void empty();
}