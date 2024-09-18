package BurgerFrenzyModel.Food;
import java.util.ArrayList;
import java.util.HashSet;


/**
 * Class Burger, extends the Item class to represent a burger in the BurgerFrenzy game.
 * This class manages the ingredients of the burger and tracks its cooking status.
 */
public class Burger extends Item {
    /**
     * Enum Status, represents the cooking status of the burger.
     * Values include Raw, Fried, and Burnt.
     */
    public enum Status {
        Raw, Fried, Burnt
    }
    protected ArrayList<Ingredient> ingredients;
    protected Status status;
    /**
     * Constructor for the Burger class.
     * Initializes a burger with a given list of ingredients.
     * @param ingredients ArrayList of Ingredient objects representing the ingredients of the burger.
     */
    public Burger(ArrayList<Ingredient> i)
    {
        if (i.isEmpty()) this.name = "burger";
        else{
            StringBuilder s = new StringBuilder();
            for(Ingredient ingredient : i)
                s.append(",").append(ingredient);
            this.name = "burger with" + s;
        }
        this.ingredients= i;
        this.ingredients.add(new Patty());
        this.ingredients.add(new Bun());
        this.status = Status.Raw;
    }

    /**
    * Provides a string representation of the burger, including its name and ingredients.
    *
    * @return A string describing the burger.
    */
    @Override
    public String toString() {return this.name;}

    /**
    * Changes the status of the burger to 'Fried'.
    */
    public void fry(){
        this.status = Status.Fried;
    }

    /**
    * Retrieves the list of ingredients used in the burger.
    *
    * @return ArrayList of Ingredient objects in the burger.
    */
    public ArrayList<Ingredient> getIngredients(){
        return ingredients;
    }
}

