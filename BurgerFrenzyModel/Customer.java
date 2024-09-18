package BurgerFrenzyModel;

import BurgerFrenzyModel.*;
/**
 * Represents a customer in the Burger Frenzy game.
 *
 * This class models a customer with attributes for an order, state, and patience level.
 * It is part of the game logic where customer interactions and state transitions are significant.
 * Being Serializable, instances of this class can be serialized for game-saving purposes.
 *
 * The Customer class includes:
 * - order: An Order object representing the customer's order.
 * - state: A CustomerState object indicating the current state of the customer (e.g., Happy, Neutral, Angry).
 * - patience: A Patience object representing the customer's patience level.
 *
 * The default constructor initializes the customer with a new order, sets their state to 'Happy',
 * and assigns them a new patience level.
 *
 * The class also includes a method 'CustomerEnter' which is used to trigger the decrease in patience
 * when a customer enters.
 *
 * Note: This class is part of the Burger Frenzy game model and interacts with other classes
 * such as Order, Patience, and various CustomerState classes.
 */

public class Customer{
    public Order order;
    public CustomerState state;
    public Patience patience;

    /**
     * Constructs a Customer object with a new order, a happy state, and a default patience level.
     */
    public Customer() {
        this.order = OrderFactory.generateOrder(3);
        this.state = new HappyState();
        this.patience = new Patience();
    }

    /**
     * Handles the event of a customer entering by decreasing their patience.
     *
     * This method is called when a customer enters, triggering a decrease in the customer's patience level.
     * It operates on the 'patience' attribute, calling its 'decreasePatience' method to signify
     * a decrease in patience.
     */
    public void CustomerEnter() {
        this.patience.decreasePatience();
    }
}
