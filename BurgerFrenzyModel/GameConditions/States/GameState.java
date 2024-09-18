package BurgerFrenzyModel.GameConditions.States;

import BurgerFrenzyModel.GameConditions.Observers.Player;

import java.util.ArrayList;

/**
 * Abstract class GameState, represents a state in the BurgerFrenzy game.
 * This class manages a list of players and provides methods to
 * update them about changes in the game state.
 */

public abstract class GameState {
    private ArrayList<Player> players = new ArrayList<>();

    /**
     * Attaches a player to this game state.
     * Adds a player to the list of players who are observing changes in this state.
     * @param player The Player object to be attached.
     */

    public void attach(Player player) {
        this.players.add(player);
    }

    /**
     * Detaches a player from this game state.
     * Removes a player from the list of players who are observing changes in this state.
     * @param player The Player object to be detached.
     */

    public void detach(Player player) { this.players.remove(player); }

    /**
     * Notifies all attached players of a change in the game state.
     * This method is called to update each player, typically when the game state changes.
     */
    public void notifyPlayers() {
        for (Player player: this.players) {
            player.update();
        }

    }
}
