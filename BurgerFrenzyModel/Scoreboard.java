package BurgerFrenzyModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class Scoreboard, manages and displays player scores in the BurgerFrenzy game.
 * This class keeps track of the scores of players and provides functionality to add and retrieve scores.
 */

public class Scoreboard {
    private List<PlayerScore> scores;

    /**
     * Constructor for Scoreboard.
     * Initializes an empty list to store player scores.
     */

    public Scoreboard() {
        this.scores = new ArrayList<>();
    }

    /**
     * Adds a score for a player and sorts the list of scores in descending order.
     * If the list exceeds 10 entries, it trims the list to keep the top 10 scores.
     * @param playerName String representing the name of the player.
     * @param xp Integer representing the experience points (XP) earned by the player.
     */

    public void addScore(String playerName, int xp) {
        scores.add(new PlayerScore(playerName, xp));

        Collections.sort(scores, Comparator.comparingInt(PlayerScore::getXp).reversed());

        if (scores.size() > 10) {
            scores = scores.subList(0, 10);
        }
    }

    /**
     * Retrieves the list of player scores.
     * @return List<PlayerScore> representing the scores of the players.
     */
    public List<PlayerScore> getScores() {
        return scores;
    }

    /**
     * Inner class PlayerScore, represents a player's score in the game.
     * Holds the player's name and the experience points (XP) they have earned.
     */
    public static class PlayerScore {
        private final String playerName;
        private final int xp;

        /**
         * Constructor for PlayerScore.
         * Initializes the player's name and their XP.
         * @param playerName playerName
         * @param xp xp
         */
        public PlayerScore(String playerName, int xp) {
            this.playerName = playerName;
            this.xp = xp;
        }

         /**
         * Retrieves the name of the player.
         * @return String representing the player's name.
         */

        public String getPlayerName() {
            return playerName;
        }
        
        /**
         * Retrieves the experience points of the player.
         * @return int representing the player's experience points.
         */

        public int getXp() {
            return xp;
        }
    }

}
