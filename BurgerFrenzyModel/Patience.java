package BurgerFrenzyModel;
import java.util.Timer;
import java.util.TimerTask;
import java.io.Serializable;

/**
 * Represents the patience level of an entity, such as a customer, in the Burger Frenzy game.
 *
 * This class encapsulates the concept of patience, with a numerical value representing the
 * current level of patience. It provides methods to get the current patience level and to
 * decrease it periodically, simulating the natural decrease of patience over time.
 *
 * The Patience class includes:
 * - patience: A double variable representing the numerical value of patience.
 * - A constructor that initializes the patience level.
 * - A method to retrieve the current patience level.
 * - A method to decrease the patience level at fixed intervals.
 *
 * The decreasePatience method uses a Timer and TimerTask to periodically reduce the patience level.
 * If the patience level drops to 0.0 or below, a message is displayed, and the timer is stopped.
 *
 * Note: This class can be used in scenarios where tracking and modifying the patience level of an
 * entity is required, particularly in game mechanics.
 */
public class Patience {
    public double patience;

    /**
     * Constructs a Patience object with an initial patience level.
     */
    public Patience() {
        this.patience = 300.0;
    }

    /**
     * Retrieves the current patience level.
     *
     * @return The current level of patience as a double.
     */
    public double getPatience() {
        return patience;
    }

    /**
     * Decreases the patience level of the entity at fixed intervals.
     *
     * This method initiates a timer that periodically decreases the entity's patience by 0.2 units
     * at every interval. If the patience level falls to 0.0 or below, a message is printed
     * indicating that the entity has run out of patience and is leaving, and the timer is stopped.
     *
     * The method uses Java's Timer and TimerTask classes to schedule and execute the patience
     * reduction task. The task begins immediately upon method invocation and repeats every 100 milliseconds.
     */
    public void decreasePatience() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (patience > 0.0) {
                    patience -= 0.2;
                } else {
                    System.out.println("Customer has run out of patience and is leaving.");
                    timer.cancel(); // Stop the timer when patience runs out
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 100);
    }
}

