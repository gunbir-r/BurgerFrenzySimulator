package BurgerFrenzyModel.GameConditions.States;

// import org.apache.commons.lang.time.StopWatch;

import BurgerFrenzyModel.*;
import BurgerFrenzyModel.GameConditions.States.GameState;

import java.util.ArrayList;

/**
 * Manages the XP (experience points) state of the game, tracking the player's current XP,
 * star levels, and win condition.
 */

public class XPState extends GameState {
    private int xp;
    private int[] starThresholds;
    private int winThreshold;
    // private StopWatch orderTime
    private ArrayList<Customer> customers;
    private Customer currentCustomer;
    private Object[] state;
    private Plate plate;

    /**
     * Constructor for XPState.
     * Initializes the game state with the provided star thresholds, win threshold, and customer list.
     * @param starThresholds the XP thresholds for each star level.
     * @param winThreshold the XP threshold needed to win the game.
     * @param customers ArrayList of Customer
     */

    public XPState(int[] starThresholds, int winThreshold, ArrayList<Customer> customers, Plate plate) {
        this.xp = 0;
        this.starThresholds = starThresholds;
        this.winThreshold = winThreshold;
        this.customers = customers;
        this.currentCustomer = customers.remove(0);
        this.state = new Object[3];
        this.state[0] = false;
        this.state[1] = 0;
        this.state[2] = false;
        this.plate = plate;

        // this.orderTime = new StopWatch();
    }

    /**
     * Retrieves the current game state, including win status and star level.
     * Updates the XP based on the current customer's order fulfillment and
     * recalculates the game state.
     * @return Object array containing the win status (Boolean) and current star level (Integer).
     */

    public Object[] getState() {
        this.xp += orderFulfilled(this.currentCustomer);

        if (this.xp >= this.winThreshold) {
            this.state[0] = true;
        }
        if (this.xp >= this.starThresholds[2]) {
            this.state[1] = 3;
        } else if (this.xp >= this.starThresholds[1]) {
            this.state[1] = 2;
        } else if (this.xp >= this.starThresholds[0]) {
            this.state[1] = 1;
        } else {
            this.state[1] = 0;
        }
        return this.state;
    }

   /**
    * Retrieves the current XP, reflecting the player's progress in the game.
    *
    * @return Current XP as an integer.
    */
    public int getXP() { return this.xp; }

    /**
    * Provides the current customer being served in the game.
    *
    * @return The current Customer object.
    */

    public Customer getCurrentCustomer() { return this.currentCustomer; }

    /**
    * Returns the list of all customers in the game.
    *
    * @return ArrayList of Customer objects.
    */

    public ArrayList<Customer> getCustomers() { return this.customers; }

    /**
     * Private method to calculate XP earned from a fulfilled customer order.
     * XP calculation is based on the state and patience level of the customer.
     * @param customer The Customer object whose order is being fulfilled.
     * @return The earned XP for the fulfilled order.
     */

    private int orderFulfilled(Customer customer) {
        if (!customer.order.isDone) {
            return 0;
        } else {
            if (!this.customers.isEmpty()) {
                this.currentCustomer = this.customers.remove(0);
            }
            if (customer.order.isCustomerDone(plate)) return 100;
            else return -10;
//            if (customer.state.getClass().equals(AngryState.class)) {
//                return -10;
//            } else if (customer.state.getClass().equals(NeutralState.class)) {
//                return 50;
//            } else {
//                return 100;
//            }
        }
    }


}
