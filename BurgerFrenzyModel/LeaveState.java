package BurgerFrenzyModel;

/**
 * Represents the 'Leave' state of a customer in the Burger Frenzy game.
 *
 * This class extends CustomerState and is used to model the state of a customer who is
 * leaving. It is part of the state pattern used to handle different customer behaviors
 * in the game. When a customer is in the LeaveState, specific actions such as triggering
 * leave animations, updating the game state, and removing the customer from the game are performed.
 *
 * The LeaveState class includes:
 * - A constructor that sets the state name to "Leave".
 * - An overridden handleState method that defines the actions to be taken when a customer is in this state.
 * - An overridden displayText method that provides a text representation of the state.
 *
 * This class is crucial for managing customer interactions and transitions in the game,
 * especially when customers leave the game environment.
 */
public class LeaveState extends CustomerState{

    /**
     * Constructs a LeaveState object, initializing it with the state name "Leave".
     */
    public LeaveState() {
        super("Leave");
    }

    /**
     * Handles the actions to be taken when a customer is in the 'Leave' state.
     *
     * This method implements the logic for removing a customer from the game, which may include
     * triggering a leave animation, updating the game state, and decrementing the number of customers.
     * The specific implementation details are to be provided in the game's GUI and management systems.
     *
     * @param customer The customer who is in the 'Leave' state.
     */
    @Override
    public void handleState(Customer customer) {
        // Trigger leave animation, update game state, etc.
        // Remove the customer here
        // GameGUI.instance().removeCustomer(customer);

        // Update game mechanics, like decrementing the number of customers
        // GameManager.instance().customerLeft(customer);
        // to be implemented
    }

    /**
     * Provides a text representation of the 'Leave' state.
     *
     * This method returns a string that signifies the customer's action of leaving,
     * overriding the parent class method to provide a customized message for the 'Leave' state.
     *
     * @return A string indicating that the customer is leaving, e.g., "I'm leaving!".
     */
    @Override
    public String displayText() {
        // Implement logic for displaying text relevant to the leaving state
        return "I'm leaving!";
    }
}
