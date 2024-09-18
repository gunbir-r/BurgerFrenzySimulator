package BurgerFrenzyModel.Food;

import BurgerFrenzyModel.Plate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class Item, serves as a base class for various items in the BurgerFrenzy game.
 * This class provides a common structure for items by defining a name attribute and a method to access it.
 */
public abstract class Item{
    protected String name;

    /**
     * Retrieves the name of the item.
     * This method is used to get the name of any item that extends this class.
     * @return String representing the name of the item.
     */
    //methods
    public String getName(){return name;}
}
