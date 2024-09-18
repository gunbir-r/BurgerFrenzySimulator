package BurgerFrenzyModel;

import java.io.Serializable; 
/**
 * Represents the angry state of a customer in the Burger Frenzy game.
 * This state is used to indicate that a customer is currently angry.
 */
public class AngryState extends CustomerState {

    /**
     * Constructs an AngryState instance with a predefined state name.
     */
    public AngryState() {
        super("Angry");
    }

    /**
     * Provides a string representation of the customer's state when they are angry.
     *
     * @return A string describing the customer's current angry state.
     */
    @Override
    public String displayText() {
        // Implement logic for displaying text relevant to the angry state
        return "I'm feeling angry!";
    }
}
