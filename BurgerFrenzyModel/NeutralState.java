package BurgerFrenzyModel;

import java.io.Serializable;

/**
 * Represents the 'Neutral' state of a customer in the Burger Frenzy game.
 *
 * This class extends CustomerState and is used to model a customer who is in a neutral state,
 * neither particularly happy nor unhappy. It is a part of the state pattern in the game that manages
 * different customer behaviors and reactions. In the NeutralState, a customer exhibits behaviors or
 * reactions that are neither positive nor negative, reflecting a baseline level of satisfaction.
 *
 * The NeutralState class includes:
 * - A constructor that initializes the state with the name "Neutral".
 * - An overridden displayText method that provides a text representation of the neutral state.
 *
 * This class plays a key role in representing the standard customer experience in the game,
 * where the customer is not exhibiting strong positive or negative emotions.
 */
public class NeutralState extends CustomerState{
    /**
     * Constructs a NeutralState object with the state name "Neutral".
     */
    public NeutralState() {
        super("Neutral");
    }

    /**
     * Provides a text representation of the 'Neutral' state.
     *
     * This method returns a string that signifies the customer's neutral emotional state,
     * overriding the parent class method to provide a customized message for the 'Neutral' state.
     * The returned message can be used within the game to display or log the customer's current
     * state of contentment.
     *
     * @return A string indicating that the customer is in a neutral state, e.g., "I'm feeling alright!".
     */
    @Override
    public String displayText() {
        // Implement logic for displaying text relevant to the neutral state
        return "I'm feeling alright!";
    }
}
