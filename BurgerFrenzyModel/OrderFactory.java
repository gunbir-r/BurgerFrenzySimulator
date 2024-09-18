package BurgerFrenzyModel;
import BurgerFrenzyModel.Food.*;

import java.util.ArrayList;

/**
 * Class OrderFactory, responsible for generating orders in the BurgerFrenzy game.
 * This class contains methods to create orders of varying difficulty levels,
 * including the generation of burgers with different ingredients.
 */
public class OrderFactory {

    /**
     * Generates an Order based on the specified difficulty level.
     * Difficulty levels determine the complexity and variety of items in the order.
     * @param difficulty The difficulty level of the order (1, 2, or 3).
     * @return Order An Order object containing a list of items according to the difficulty level.
     */

    public static Order generateOrder(int difficulty)
    {
        ArrayList<Item> items =  new ArrayList<>();
        int randomNumber = (int)(Math.random() * 3) + 1;
        switch (difficulty) {
            case 1->{
                items.add(new Burger(new ArrayList<>()));
//                switch(randomNumber) {
//                    case 1-> items.add(new Burger(new ArrayList<>()));
//                    case 2-> items.add(new Cookie());
//                    case 3-> items.add(new Drink());
                }
//            }
            case 2->{
                items.add(generateBurger());
//                int r = (int)(Math.random() * 3) + 1;
////                if(r==1)
////                    items.add(new Cookie());
////                else
////                    items.add(new Drink());
            }
            case 3->{
                items.add(generateBurger());
//                items.add(new Drink());
//                items.add(new Cookie());
            }
        }
        return new Order(items);
    }

    /**
     * Generates a Burger with a random selection of ingredients.
     * The number and type of ingredients vary based on a random selection.
     * @return Burger A Burger object with a specific set of ingredients.
     */
    public static Burger generateBurger() {
        int randomNumber = (int)(Math.random() * 3) + 1;
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        switch (randomNumber)
        {
            case 1-> ingredients.add(new Lettuce());
            case 2-> ingredients.add(new Tomato());
            case 3->{
                ingredients.add(new Lettuce());
                ingredients.add(new Tomato());
            }
        }
        return new Burger(ingredients);
    }
}
