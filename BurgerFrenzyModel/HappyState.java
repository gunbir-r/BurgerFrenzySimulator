package BurgerFrenzyModel;

/**
 * Represents the 'Happy' state of a customer in the Burger Frenzy game.
 *
 * This class extends CustomerState and models a customer who is in a happy and satisfied condition.
 * It is part of the state pattern used in the game to handle different customer behaviors and interactions.
 * When a customer is in the HappyState, specific actions or reactions that correspond to a happy customer
 * can be implemented, enhancing the game's interactivity and realism.
 *
 * The HappyState class includes:
 * - A constructor that sets the state name to "Happy".
 * - An overridden displayText method that provides a text representation of the happy state.
 *
 * This class is crucial for representing positive customer interactions in the game, especially
 * when a customer is satisfied with the service.
 */
public class HappyState extends CustomerState{
    /**
     * Constructs a HappyState object, initializing it with the state name "Happy".
     */
    public HappyState() {
        super("Happy");
    }

    /**
     * Provides a text representation of the 'Happy' state.
     *
     * This method returns a string that signifies the customer's satisfaction and happiness,
     * overriding the parent class method to provide a customized message for the 'Happy' state.
     * The message can be used to display or log the customer's state within the game.
     *
     * @return A string indicating that the customer is happy, e.g., "I'm feeling great!".
     */
    @Override
    public String displayText() {
        // Implement logic for displaying text relevant to the happy state
        return "I'm feeling great!";
    }
}

