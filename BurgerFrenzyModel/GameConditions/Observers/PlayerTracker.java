package BurgerFrenzyModel.GameConditions.Observers;

import BurgerFrenzyModel.GameConditions.Observers.Player;
import BurgerFrenzyModel.GameConditions.States.XPState;
import view.BurgerFrenzyView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class PlayerTracker, Keeps track of the current game.
 */
public class PlayerTracker extends Player {
    private XPState xpState;
    private int starLevel;
    private Timer totalTimer;
    private int gameEndTime;
    private boolean winThresholdAchieved;
    public BurgerFrenzyView view;

    /**
     * PlayerTracker Constructor,
     * Initializes attributes
     * @param xpState
     * @param gameEndTime
     */
    public PlayerTracker(XPState xpState, int gameEndTime, BurgerFrenzyView view) {
        this.xpState = xpState;
        this.starLevel = 0;
        this.totalTimer = new Timer();
        this.gameEndTime = gameEndTime;
        this.winThresholdAchieved = false;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timerEnd();
            }
        };
        this.totalTimer.schedule(task, (long) gameEndTime * 60 * 1000);
        this.view = view;
    }

    /**
     * Updates the star level and checks if the win threshold has been achieved.
     */
    public void update() {
        Object[] currState = this.xpState.getState();
        this.winThresholdAchieved = (boolean) currState[0];
        this.starLevel = (int) currState[1];
        if ((boolean) currState[2]) {
            if (winThresholdAchieved) wonGame();
            else lostGame();
        }

    }

    /**
     * Runs the win state of the BurgerFrenzyGame, with game stats displayed
     */
    private void wonGame() { this.view.winState(); }

    /**
     * Runs the losing state of the game, with game stats displayed
     */
    private void lostGame() { this.view.loseState(); }

    /**
     * Will activate winning or losing state depending on player's performance in game
     * once the timer ends
     */
    public void timerEnd() {
        if (this.winThresholdAchieved) wonGame();
        else lostGame();
    }
    /**
    * Retrieves the current star level of the player.
    *
    * @return the star level as an integer.
    */


    public int getStarLevel() {
        return this.starLevel;
    }
}
