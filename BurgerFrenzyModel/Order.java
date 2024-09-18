package BurgerFrenzyModel;
import BurgerFrenzyModel.Food.Burger;
import BurgerFrenzyModel.Food.Ingredient;
import BurgerFrenzyModel.Food.Item;
import BurgerFrenzyModel.Food.SingleFood;

import java.util.*;

/**
 a class that keeps track of customer's order, the given item to the customer and remaining item.
 usually is constructed from OrderFactory
 __________________________
 attributes

 items: All the items in the order
 givenItems : the items in the order that customer already received
 remainingItems : the items in the order that customer haven't received
 isDone : status of the order

 methods

 constructor: create an order, usually gets called by OrderFactory
 giveItem: giving an item of the order to the customer
 */
public class Order{
    protected ArrayList<Item> items;
    public boolean isDone = false;
    public Order(ArrayList<Item> i)
    {
        this.items = i;
    }

    /**
     * Checks if the customer's order matches the provided Plate object.
     * The order is considered done if all items in the order are found in the Plate.
     * Handles SingleFood and Burger items differently by checking their ingredients.
     * @param p The Plate object against which the order is checked.
     * @return boolean True if the order matches the Plate, false otherwise.
     */

    public boolean isCustomerDone(Plate p){
//        for(Item item:this.items){
//            if(item instanceof SingleFood) {
//                if (!p.items.contains(item.getName()))
//                    return false;
//            }
//            else{
//                Burger b = (Burger)item;
//                for(Ingredient ingredient:b.getIngredients())
//                    if(!p.items.contains(ingredient.getName()))
//                        return false;
//            }
//        }
        
// The new code  reflects the correct parsing of the randomization of the burger as encompass to the User Story the burger should be customized and correctly adhered when done. The previous code only reflected on the creation of the order and didnt correctly add the ingriendts to determine the order is complete for customer.  

        HashSet<String> itemList = new HashSet<>();
        for (Item item : this.items) {
            if (item instanceof Burger) {
                for (Item item1 : ((Burger) item).getIngredients()) {
                    itemList.add(item1.getName());
                }
            } else itemList.add(item.getName());
        }
//        if (itemList.equals(p.getItems())) {
//            this.isDone = true;
//            return true;
//        } else {
//            this.isDone = true;
//            return false;
//        }
        if (p.getItems().containsAll(itemList)) {
            this.isDone = true;
            return true;
        } else {
            this.isDone = false;
            return false;
        }


//        this.isDone = true;
//        return true;
    }

    /**
     * Determines whether the customer's order is complete based on the contents of the provided Plate.
     * An order is marked as done if all items, including the specific ingredients of any Burger items,
     * are present on the Plate. This method is crucial for verifying the completeness of an order
     * before serving it to the customer.
     *
     * @return boolean True if all items of the order are present on the Plate, indicating the order is complete.
     *                 False if any item is missing from the Plate, indicating the order is incomplete.
     */

    public ArrayList<Item> getItems() {
        return this.items;
    }
}
