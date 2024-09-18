import BurgerFrenzyModel.Customer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

import view.BurgerFrenzyView;
import BurgerFrenzyModel.*;

/**
 * Main application class for the Burger Frenzy game.
 * Initializes and starts the game by setting up the model and view, and linking them together.
 */
public class BurgerFrenzyApp extends Application {
    BurgerFrenzyGame model;
    BurgerFrenzyView view;

    /**
    * Starts the application by setting up the game model and view. 
    * Initializes a list of customers and creates instances of BurgerFrenzyGame and BurgerFrenzyView.
    *
    * @param primaryStage The primary stage for this application, onto which the application scene can be set.
    */

    @Override
    public void start(Stage primaryStage) {
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());
        this.model = new BurgerFrenzyGame(new int[]{100, 200, 300}, 100,  customers);
        this.view = new BurgerFrenzyView(model, primaryStage);
        this.model.getPlayer().view = this.view;
    }

    /**
    * Main entry point for launching the Burger Frenzy game application.
    *
    * @param args Command line arguments passed to the application.
    *             Not used in this implementation.
    */


    public static void main(String[] args) {
        launch(args);
    }
}
