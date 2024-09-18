package BurgerFrenzyModel;

import java.io.Serializable; //you will need this to save the game!
/**
 * Represents the abstract base class for different customer states in the Burger Frenzy game.
 *
 * This abstract class provides a framework for defining various states of a customer, such as Happy,
 * Neutral, Angry, and Leaving. It encapsulates the common attributes and functionalities shared
 * across different customer states and is designed to be extended by specific state classes.
 *
 * Being Serializable, it allows state objects to be serialized, aiding in game-saving functionality.
 *
 * The CustomerState class includes:
 * - state: A protected String variable representing the name of the state.
 * - A constructor to initialize the state name.
 * - An abstract method 'displayText' that must be implemented by subclasses to provide a text
 *   representation of the state.
 * - A 'handleState' method that evaluates and updates the customer's state based on their patience level
 *   and order status.
 *
 * The handleState method is a key part of the state management, allowing dynamic state transitions based
 * on game logic.
 *
 * Note: Subclasses must implement the displayText method to provide specific messages or indications
 * suitable to each state.
 */
public abstract class CustomerState implements Serializable{
    protected String state;

    /**
     * Constructs a CustomerState with a specified state name.
     *
     * @param stateName The name of the state.
     */
    public CustomerState(String stateName) {
        this.state = stateName;
    }

    /**
     * Evaluates and updates the customer's state based on their patience level and order status.
     *
     * This method uses the customer's patience level and order completion status to determine
     * and set their current state. It transitions the customer to different states like Happy,
     * Neutral, Angry, or Leave, depending on these conditions.
     *
     * @param customer The customer whose state needs to be evaluated and updated.
     */
    public void handleState(Customer customer){
        double p = customer.patience.getPatience();
        if (p > 200.0) {
            customer.state = new HappyState();
        } else if (p > 100.0) {
            customer.state = new NeutralState();
        } else if (p > 0.0){
            customer.state = new AngryState();
        }
        else if (p <= 0.0 || customer.order.isDone){
            customer.state = new LeaveState();
        }
    }
    /**
     * Provides a text representation of the customer state.
     *
     * This abstract method must be implemented by subclasses to return a string that appropriately
     * represents the current state of the customer.
     *
     * @return A string representing the state of the customer.
     */
    public abstract String displayText();
}
