package BurgerFrenzyModel;


import BurgerFrenzyModel.Food.Bun;
import BurgerFrenzyModel.Food.Ingredient;
import BurgerFrenzyModel.Food.Item;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class Plate, represents a plate in the BurgerFrenzy game.
 * This class holds a collection of item names that have been added to the plate
 * and provides methods to manage these items, including special handling for buns.
 */
public class Plate{
    public void addIngredient(String ingredient) {
    }
    //attributes
    protected HashSet<String> items;
    private boolean gotBun = false;

    /**
     * Constructor for the Plate class.
     * Initializes an empty set of item names and sets the flag for having a bun to false.
     */
    public Plate(){
        this.gotBun =false;
        this.items = new HashSet<>();
    }

    /**
     * Adds an item to the plate.
     * Special handling is included for buns, where only one bun can be added to the plate.
     * Other ingredients are added if the plate already has a bun.
     * @param i Item object to be added to the plate.
     */
    public void addIngredient(Item i){
        this.items.add(i.getName());
//        if(i instanceof Ingredient){
//            if(i instanceof Bun)
//                if(!gotBun){
//                this.gotBun = true;
//                this.items.add(i.getName());
//            }
//            if(gotBun)
//                this.items.add(i.getName());
//        }
//        else{
//            this.items.add(i.getName());
    }
//    }
    /**
    * Empties the plate and resets the gotBun flag.
     * This method is used to simulate trashing the current plate to start over.
     */
    public void trashPlate(){
        this.items = new HashSet<>();
        this.gotBun = false;
    }

    /**
     * Adds a Bun to the plate.
     * This is a convenience method that directly adds a Bun item.
     */
    public void addBun(){
        this.addIngredient(new Bun());
        this.gotBun = true;}

    /**
     * Returns the set of item names on the plate.
     * @return HashSet<String> representing the names of items on the plate.
     */
    public HashSet<String> getItems() {
        return this.items;
    }
}
