package BurgerFrenzyModel.GameConditions.Observers;

/**
 * Abstract class Player, representing a player in the BurgerFrenzy game.
 * The update method is intended to be called when the game state changes.
 */
public abstract class Player {
/**
     * Abstract method update.
     * It's typically called by the GameState class when notifying players about state changes.
     */
    public abstract void update();
}
