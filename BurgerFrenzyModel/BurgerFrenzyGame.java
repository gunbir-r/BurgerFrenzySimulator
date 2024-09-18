package BurgerFrenzyModel;

import BurgerFrenzyModel.Food.Ingredient;
import BurgerFrenzyModel.Food.Item;
import BurgerFrenzyModel.GameConditions.Observers.PlayerTracker;
import BurgerFrenzyModel.GameConditions.States.XPState;
import view.BurgerFrenzyView;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;


/**
 * Class BurgerFrenzyGame, manages the core gameplay mechanics of the Burger Frenzy game.
 * This class is responsible for initializing and managing various game components such as
 * player tracking, state management, customer handling, and interaction with the game view.
 */
public class BurgerFrenzyGame implements Serializable {
    PlayerTracker player;
    XPState xpState;
    ArrayList<Customer> customers;
    Customer currentCustomer;
    BurgerFrenzyView view;
    Plate plate;
    private Scoreboard scoreboard;


    /**
    * Constructor for BurgerFrenzyGame.
     * Initializes the game with star thresholds, win threshold, and a list of customers.
     * Also sets up the XPState and attaches a PlayerTracker to it.
     * @param starThresholds Array of integers representing XP thresholds for each star level.
     * @param winThreshold Integer representing the XP threshold needed to win the game.
     * @param customers ArrayList of Customer objects representing the customers in the game.
     */

    public BurgerFrenzyGame(int[] starThresholds, int winThreshold, ArrayList<Customer> customers) {
        this.plate = new Plate();
        this.xpState = new XPState(starThresholds, winThreshold, customers, plate);
        this.player = new PlayerTracker(this.xpState, 5, view);
        this.xpState.attach(this.player);
        this.customers = customers;
    }

    /**
     * Saves the current state of the game to a file.
     * This method serializes the BurgerFrenzyGame object and writes it to the provided file.
     * @param file File object representing the file to which the game state is to be saved.
     */

    public void saveModel(File file) {
        try {
            FileOutputStream outfile = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(outfile);
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scoreboard getScoreboard() {
        // Return the Scoreboard instance
        return this.scoreboard;
    }

    /**
     * addIngredient
     * Adds an ingredient to the plate.
     * @param ingredient
     */
    public void addIngredient(Item ingredient) {
        this.plate.addIngredient(ingredient);
    }

    /**
     * sendOrder
     * Updates the XPState after an order is sent out to see if game conditions have been met.
     */
    public void sendOrder() {
        this.xpState.getCurrentCustomer().order.isDone = true;
//        this.xpState.getCurrentCustomer().order.isCustomerDone(this.plate);
        this.player.update();
        this.plate.items = new HashSet<>();
        if (this.xpState.getCustomers().isEmpty()) this.player.timerEnd();
    }

     /**
     * Retrieves the current player tracker instance.
     * @return PlayerTracker The current player tracker in the game.
     */

    public PlayerTracker getPlayer() {
        return this.player;
    }
    
    /**
     * Retrieves the current XP state instance.
     * @return XPState The current XP state of the game.
     */
    public XPState getXpState() {
        return this.xpState;
    }


}

