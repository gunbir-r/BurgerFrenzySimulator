package view;

import BurgerFrenzyModel.*;
import BurgerFrenzyModel.Food.*;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.input.KeyEvent; //you will need these!
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.event.EventHandler; //you will need this too!
import javafx.scene.AccessibleRole;

import java.io.File;
import java.util.*;

public class BurgerFrenzyView {

    BurgerFrenzyGame model; //model of the game
    Stage stage; //stage on which all is rendered
    //Button saveButton, loadButton;
    Button helpButton; //buttons
    VBox helpPane = new VBox();
    Boolean helpToggle = false; //is help on display?
    GridPane gridPane = new GridPane(); //to hold images and buttons
    HBox scores = new HBox();
    HBox counterView = new HBox();
    VBox fryingPan = new VBox();
    Boolean fullPan = false;
    VBox ingredientsList = new VBox(5);
    TextField inputTextField; //for user input
    Label gameStatusLabel = new Label();
    Label scoreLabel;
    Label orderLabel;
    Label plateLabel;
    private MediaPlayer mediaPlayer; //to play audio
    private boolean mediaPlaying; //to know if the audio is playing
    private int customersServed = 0;

    /**
     * Adventure Game View Constructor
     * __________________________
     * Initializes attributes
     */
    public BurgerFrenzyView(BurgerFrenzyGame model, Stage stage) {
        this.model = model;
        this.stage = stage;
        intiUI();
    }

    /**
     * Initializes the user interface and sets up the game stage.
     */
    public void intiUI() {

        // setting up the stage
        this.stage.setTitle("Burger Frenzy");

        // GridPane
        gridPane.setPadding(new Insets(20));
        gridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#FFFFFF"),
                new CornerRadii(0),
                new Insets(0)
        )));
        gridPane.setBackground(new Background(new BackgroundImage(new Image("images/Background.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT))); // Replace with your customer image path)));

        // Define Columns
//        ColumnConstraints column1 = new ColumnConstraints(100, 100, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true);
//        ColumnConstraints column2 = new ColumnConstraints(700, 700, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true);
//        ColumnConstraints column3 = new ColumnConstraints(100, 100, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true);
        ColumnConstraints column1 = new ColumnConstraints(150, 150, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true);
        ColumnConstraints column2 = new ColumnConstraints(600, 600, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true);
        ColumnConstraints column3 = new ColumnConstraints(150, 150, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true);
        gridPane.getColumnConstraints().addAll(column1, column2, column3);
//        gridPane.getColumnConstraints().addAll(column1, column2, column3);

        // Define Rows
        RowConstraints row1 = new RowConstraints(100, 100, Double.MAX_VALUE, Priority.ALWAYS, VPos.CENTER, true); // Top row for order label and help button
        RowConstraints row2 = new RowConstraints(400, 500, Double.MAX_VALUE, Priority.ALWAYS, VPos.CENTER, true); // Middle content
//        RowConstraints row1 = new RowConstraints(50, 50, Double.MAX_VALUE, Priority.ALWAYS, VPos.CENTER, true); // Top row for order label and help button
//        RowConstraints row2 = new RowConstraints(400, 400, Double.MAX_VALUE, Priority.ALWAYS, VPos.CENTER, true); // Middle content
        RowConstraints row3 = new RowConstraints(200, 200, Double.MAX_VALUE, Priority.NEVER, VPos.CENTER, false); // Counter row
        RowConstraints row4 = new RowConstraints(50, 50, Double.MAX_VALUE, Priority.NEVER, VPos.CENTER, false); // Bottom row for text field

        gridPane.getColumnConstraints().addAll( column1 , column2 , column3 );
//        gridPane.getRowConstraints().addAll(row1, row2, row3, row4);
        gridPane.getRowConstraints().addAll(row1, row2, row3);


        // Current Order Label
        this.orderLabel = new Label();
        this.orderLabel.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#FFFFFF"),
                new CornerRadii(0),
                new Insets(0)
        )));
        this.orderLabel.setText("Current Order:\n");
        this.showOrder();
        gridPane.add(this.orderLabel, 0, 1); // Add the game status label to the left (column 0, row 1)

//        orderLabel = new Label("Current Order:");
//        orderLabel.setAlignment(Pos.CENTER);
//        orderLabel.setStyle("-fx-text-fill: black;");
//        orderLabel.setFont(new Font("Arial", 16));
//        gridPane.add(orderLabel, 0, 0); // Add the label to the top left corner
        ingredientsList.setAlignment(Pos.CENTER);
        gridPane.add(ingredientsList, 0, 1);

        Button showScoreboardButton = new Button("Scoreboard");


        // Use a style class or inline styles to increase the font size if desired
        showScoreboardButton.setStyle("-fx-font-size: 12pt;");

        showScoreboardButton.setOnAction(e -> displayScoreboard());
        GridPane.setHalignment(showScoreboardButton, HPos.RIGHT);


        gridPane.add(showScoreboardButton, 1, 0);

        // Help Button
        helpButton = new Button("Help");
        helpButton.setId("Help");
        //customizeButton(helpButton, 200, 50);
        //makeButtonAccessible(helpButton, "Help Button", "This button gives game instructions.", "This button gives instructions on the game controls. Click it to learn how to play.");
        addInstructionEvent();
        gridPane.add(helpButton, 1, 0); // Add the button to the top center

        // Score Label
        this.scoreLabel = new Label("Score: " + this.model.getXpState().getXP());
        this.scoreLabel.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#FFFFFF"),
                new CornerRadii(0),
                new Insets(0)
        )));
        scoreLabel.setAlignment(Pos.CENTER);
        scoreLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-background-color: #ff8c00; -fx-font-size: 16px;");
        scoreLabel.setFont(new Font("Arial", 16));
        gridPane.add(scoreLabel, 2, 0); // Add the label to the top right corner

        // Customer ImageView
        gridPane.add(createRandomCustomer(), 2, 1); // Add the customer image to the right (column 2, row 1)

        // Plate ImageView
        ImageView plateView = createPlateImage();
        gridPane.add(plateView, 1, 1); // Assuming column 1, row 1 for the plate image
        GridPane.setValignment(plateView, VPos.BOTTOM);

        this.plateLabel = new Label();
        this.plateLabel.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#FFFFFF"),
                new CornerRadii(0),
                new Insets(0)
        )));
        this.plateLabel.setText("Plate:\n");
        this.plateLabel.setPrefSize(300, 200);
        this.plateLabel.setFont(new Font(15));
        this.plateLabel.setAlignment(Pos.CENTER);
        gridPane.add(this.plateLabel, 1, 1); // Add the game status label to the left (column 0, row 1)


        // The Audio Playing
        Button playOrderButton = new Button("Play Order");
        playOrderButton.setOnAction(e -> playOrderAudio());
        gridPane.add(playOrderButton, 0, 0); // Adjust the position as needed

        //counter
        updateCounter(); //method displays an image and whatever text is supplied
        gridPane.add(counterView, 1, 2, 1, 1);


        //gridPane.add(createSendOrderButton(), 2, 1);
        //updateOrder(); //update items shows inventory and objects in rooms

        // Render everything
        var scene = new Scene( gridPane ,  1000, 800);
        scene.setFill(Color.BLACK);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.show();
    }

    /**
     * Plays the audio for the current customer's order.
     * The audio file is chosen based on the first ingredient in the order.
     */
    public void playOrderAudio() {
        String orderText = "";
        for (Item item : this.model.getXpState().getCurrentCustomer().order.getItems()) {
            if (item instanceof Burger) {
                for (Item item1 : ((Burger) item).getIngredients()) {
                    orderText += item1.getName() + " ";
                }
            } else orderText += item.getName() + " ";
        }



        List<Item> items = this.model.getXpState().getCurrentCustomer().order.getItems(); // Get the current order items
        if (items.isEmpty()) {
            // Handle the case where there are no items in the order
            return;
        }


        String audioFilePath = "BurgerFrenzySimulator/BurgerFrenzyModel/audio/";

        if (orderText.toLowerCase().contains("lettuce") && orderText.contains("Tomato")) {
            audioFilePath += "order_default.m4a";
        } else if (orderText.toLowerCase().contains("tomato")) {
            audioFilePath += "order_tomato.m4a";
        } else {
            // Default to the third audio file for all other cases
            audioFilePath += "order_lettuce.m4a";
        }

        try {
            Media sound = new Media(new File(audioFilePath).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            mediaPlaying = true;
        } catch (Exception e) {
            e.printStackTrace(); // Or handle the error as appropriate
        }
    }
    /**
     * Displays the current order in the UI.
     */
    private void showOrder() {
        String orderText = "";
        for (Item item : this.model.getXpState().getCurrentCustomer().order.getItems()) {
            if (item instanceof Burger) {
                for (Item item1 : ((Burger) item).getIngredients()) {
                    orderText += item1.getName() + "\n";
                }
            } else orderText += item.getName() + "\n";
        }
        this.orderLabel.setText("Current Order:\n" + orderText);
        this.orderLabel.setPrefSize(300, 200);
        this.orderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        this.orderLabel.setAlignment(Pos.CENTER);
    }
    /**
     * Updates the list of ingredients displayed in the UI.
     * @param ingredients List of ingredient names to display.
     */
    public void updateIngredientsList(List<String> ingredients) {
        //this.model.getCurrentCustomer().order;
        this.ingredientsList.getChildren().clear(); // Clear existing ingredients
        for (String ingredient : ingredients) {
            Label ingredientLabel = new Label(ingredient);
            ingredientLabel.setFont(new Font("Arial", 14));
            ingredientLabel.setStyle("-fx-text-fill: black;");
            ingredientsList.getChildren().add(ingredientLabel);
        }
    }
    /**
     * Creates a VBox with a random customer image and a button to send the order.
     * @return VBox containing the image and button.
     */
    private VBox createRandomCustomer() {
        // Customer ImageView
        Random random = new Random();
        // Generate a random number between 1 and 6
        int randomNumber = random.nextInt(6) + 1;
        //ImageView customerView = createImageView("Images/Customers/customer" + String.valueOf(randomNumber) + ".png");
        ImageView customerView = new ImageView(new Image("Images/Customers/customer" + String.valueOf(randomNumber) + ".png"));
        customerView.setFitWidth(150);
        customerView.setPreserveRatio(true);
        Button sendOrder = createSendOrderButton();
        VBox itemBox = new VBox(sendOrder, customerView);
        itemBox.setAlignment(Pos.CENTER);
        return itemBox;
//        return customerView;
    }
    /**
     * Creates an ImageView for the plate image.
     * @return ImageView for the plate.
     */
    private ImageView createPlateImage() {
        //must add other states of plate too

        //ImageView plateView = createImageView("Images/Ingredients/emptyBox.jpg");
        ImageView plateView = new ImageView(new Image("Images/Boxes/0.png"));
        plateView.setFitWidth(200);
        plateView.setFitHeight(200);
        plateView.setPreserveRatio(true);
        plateView.setSmooth(false);

        return plateView;
    }

    /**
     * Updates the game state to reflect a win and displays the win message.
     */
    public void winState() {
        this.gridPane.getChildren().remove(1,1);
        this.gameStatusLabel.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#FFFFFF"),
                new CornerRadii(0),
                new Insets(0)
        )));
        this.gameStatusLabel.setText("You have won the game!\nStar Level: " + this.model.getPlayer().getStarLevel() +
                "\nTotal XP: " + this.model.getXpState().getXP());
        this.gameStatusLabel.setPrefSize(300, 200);
        this.gameStatusLabel.setFont(new Font(20));
        this.gameStatusLabel.setAlignment(Pos.CENTER);
        gridPane.add(this.gameStatusLabel, 1, 1); // Add the game status label to the center

        playWinAudio(); // Play the win audio
    }

    /**
     * Plays the audio indicating a win condition.
     */
    private void playWinAudio() {
        String audioFilePath = "BurgerFrenzySimulator/BurgerFrenzyModel/audio/win.m4a"; // Path to your win audio file
        try {
            Media winSound = new Media(new File(audioFilePath).toURI().toString());
            MediaPlayer winMediaPlayer = new MediaPlayer(winSound);
            winMediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions
        }
    }

    /**
     * Updates the game state to reflect a loss and displays the loss message.
     */
    public void loseState() {
        this.gridPane.getChildren().remove(1,1);
        this.gameStatusLabel.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#FFFFFF"),
                new CornerRadii(0),
                new Insets(0)
        )));
        this.gameStatusLabel.setText("You have lost the game...\nStar Level: " + this.model.getPlayer().getStarLevel() +
                "\nTotal XP: " + this.model.getXpState().getXP());
        this.gameStatusLabel.setPrefSize(300, 200);
        this.gameStatusLabel.setFont(new Font(20));
        this.gameStatusLabel.setAlignment(Pos.CENTER);
        gridPane.add(this.gameStatusLabel, 1, 1); // Add the game status label to the center

        playLoseAudio(); // Play the lose audio
    }

    /**
     * Plays the audio indicating a loss condition.
     */
    private void playLoseAudio() {
        String audioFilePath = "BurgerFrenzySimulator/BurgerFrenzyModel/audio/lose.m4a"; // Path to your lose audio file
        try {
            Media loseSound = new Media(new File(audioFilePath).toURI().toString());
            MediaPlayer loseMediaPlayer = new MediaPlayer(loseSound);
            loseMediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions
        }
    }

    /**
     * Creates a button to send the current order.
     * When clicked, it processes the current order, updates the score label,
     * generates a new customer, shows the new order, and clears the plate label.
     *
     * @return A button configured with action to process and send the order.
     */
    private Button createSendOrderButton() {
        Button sendOrderButton = new Button();
        sendOrderButton.setText("Send Order");
        sendOrderButton.setOnAction(e -> {
            this.model.sendOrder();
            this.scoreLabel.setText("Score: " +  this.model.getXpState().getXP());
            this.createRandomCustomer();
            this.showOrder();
            this.plateLabel.setText("Plate:\n");
        });
        return sendOrderButton;
    }

    /**
     * Updates the counter display with buttons for all available ingredients.
     * Each button, when clicked, will add the corresponding ingredient to the plate
     * and play an audio clip representing the ingredient being added to the order.
     */
    public void updateCounter() {
        counterView = new HBox(10); // 10 is the spacing between elements
        // Create buttons for ingredients
        Button breadButton = createIngredientButton("bun");
        Button tomatoButton = createIngredientButton("tomato");
        Button lettuceButton = createIngredientButton("lettuce");
        Button cheeseButton = createIngredientButton("cheese");
        Button pattyButton = createIngredientButton("patty");
        VBox panButton = createPanImageView();

        // Add buttons and pan view to the counter
        counterView.getChildren().addAll(breadButton, tomatoButton, lettuceButton, cheeseButton, pattyButton, panButton);
    }
    /**
     * Creates a button for an ingredient with a label and image.
     * @param ingredientName Name of the ingredient for which to create the button.
     * @return Button for the ingredient.
     */
    private Button createIngredientButton(String ingredientName) {
        Button ingredientButton = new Button();
        ingredientButton.setStyle("-fx-background-color: white;");
        ingredientButton.setId(ingredientName);
        Label nameLabel = new Label("Add " + ingredientName);
        nameLabel.setStyle("-fx-text-fill: black;");
        nameLabel.setFont(new Font("Arial", 12));
        ImageView imageView = createImageView("Images/Ingredients/" + ingredientName + ".jpg");
        VBox itemBox = new VBox(imageView, nameLabel);
        itemBox.setAccessibleText(ingredientName);
        itemBox.setAlignment(Pos.CENTER);
        ingredientButton.setGraphic(itemBox);
        ingredientButton.setOnAction(e -> {
            playIngredientAudio(ingredientName); // Play audio for the ingredient
            addIngredientToPlate(ingredientName); // Add ingredient to plate and update UI
        });
        return ingredientButton;
    }

    /**
     * Plays the audio associated with adding a specific ingredient.
     * @param ingredientName Name of the ingredient for which to play audio.
     */
    private void playIngredientAudio(String ingredientName) {
        String audioFilePath = "BurgerFrenzySimulator/BurgerFrenzyModel/audio/" + ingredientName.toLowerCase() + ".m4a";
        try {
            Media sound = new Media(new File(audioFilePath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
        }
    }

    /**
     * Adds an ingredient to the plate and updates the UI accordingly.
     * @param ingredientName Name of the ingredient to add.
     */
    private void addIngredientToPlate(String ingredientName) {
        Item ingredient;
        switch (ingredientName) {
            case "patty":
                ingredient = new Patty();
                break;
            case "bun":
                ingredient = new Bun();
                break;
            case "cheese":
                ingredient = new Cheese();
                break;
            case "lettuce":
                ingredient = new Lettuce();
                break;
            case "tomato":
                ingredient = new Tomato();
                break;
            case "cookie":
                ingredient = new Cookie();
                break;
            case "drink":
                ingredient = new Drink();
                break;
            default:
                return; // If ingredient not recognized, exit method
        }
        if (ingredientName.toLowerCase()!= "patty"){
            model.addIngredient(ingredient);
            plateLabel.setText(plateLabel.getText() + ingredient.getName() + "\n");

        } else if (ingredientName.toLowerCase().equals("patty")) {
            fullPan = true;
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(event -> {
                fullPan = false;
                this.model.addIngredient(ingredient);
                this.plateLabel.setText(plateLabel.getText() + ingredient.getName() + "\n");
                // Optional: Additional code after patty is cooked
            });
            pause.play();
        }
    }

    /**
     * Creates a VBox containing the image of the pan, indicating if it's full or empty.
     * @return VBox containing the pan image.
     */
    private VBox createPanImageView() {
        String pan;
        if (fullPan) pan = "fullPan";
        else pan = "emptyPan";
        Label nameLabel = new Label("Cooking Pan");
        nameLabel.setStyle("-fx-text-fill: black;");
        nameLabel.setFont(new Font("Arial", 12));
        ImageView imageView = createImageView("Images/Ingredients/" + pan + ".jpg");
        VBox itemBox = new VBox(imageView, nameLabel);
        itemBox.setAlignment(Pos.CENTER);
        // Set the background to white
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
        Background whiteBackground = new Background(backgroundFill);
        itemBox.setBackground(whiteBackground);
        return itemBox;
    }

    /**
     * Creates an ImageView from a given image path.
     * @param imagePath Path to the image file.
     * @return ImageView created from the specified image.
     */
    private ImageView createImageView(String imagePath) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(80);
        imageView.setSmooth(false);
        //imageView.setPreserveRatio(true);
        return imageView;
    }
    /**
     * Creates a VBox containing the help text for the game.
     * @return VBox containing the instructions for the game.
     */
    private VBox instructionsPane() {

        Label helpText = new Label("You can click the ingredient buttons to add them to the box.\nYou have to add bread first.\nAfter being done send the order.");
        helpText.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#40E0D0"), new CornerRadii(0), new Insets(0)
        )));

        helpText.setFont(new Font("Arial", 16));
        helpText.setStyle("-fx-text-fill: black;");
        helpText.setTextOverrun(OverrunStyle.CLIP);
        helpText.setWrapText(true);

        VBox instructionsBox = new VBox();
        instructionsBox.getChildren().addAll( helpText);
        instructionsBox.setAlignment(Pos.TOP_CENTER);

        return  instructionsBox;
    }
    /**
     * Adds an event to the help button that toggles the display of game instructions.
     */
    public void addInstructionEvent() {
        helpButton.setOnAction(e -> {
            if (!helpToggle) {
                this.helpPane = instructionsPane();
                gridPane.add(helpPane, 1, 1);
                helpToggle = true;
            } else {
                gridPane.getChildren().remove(this.helpPane);
                //updateScene("");
                helpToggle = false;
            }
        });
    }

    /**
     * Displays a scoreboard in a separate stage.
     */
    public void displayScoreboard() {
        Stage scoreboardStage = new Stage();
        scoreboardStage.setWidth(100); // Set the width of the scoreboard window
        scoreboardStage.setHeight(100); // Set the height of the scoreboard window

        Scoreboard scoreboard = new Scoreboard();

        // Add test scores
        scoreboard.addScore("Player1", model.getXpState().getXP());

        // Create a new window to show the scores
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        // Add scores to the VBox
        for (Scoreboard.PlayerScore score : scoreboard.getScores()) {
            Label scoreLabel = new Label(score.getPlayerName() + " - " + score.getXp());
            vbox.getChildren().add(scoreLabel);
        }

        // Set the scene and show the stage
        Scene scene = new Scene(vbox);
        scoreboardStage.setScene(scene);
        scoreboardStage.setTitle("Scoreboard");
        scoreboardStage.show();
    }
}

