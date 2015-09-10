package lasersharks.gui;

import java.util.List;
import java.util.stream.Collectors;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import lasersharks.Direction;
import lasersharks.Fish;
import lasersharks.Game;
import lasersharks.Position;
import lasersharks.ScreenController;

/**
 * This class represents the gui of our application.
 * 
 * @author michiel, daan
 *
 */

public class LevelGUI extends Application {

  private static final double FRAME_DELAY = 0.06;
  /**
   * the x resolution of the screen.
   */
  private static final double XRES = 1920;
  /**
   * the y resolution of the screen.
   */
  private static final double YRES = 1080;
  /**
   * The background color of the gui.
   */
  private static final Color BACKCOLOUR = Color.BLUE;
  private ScreenController screenController;
  private Pane pane;
  private Scene scene;
  private Stage stage;

  /**
   * @return the screenController.
   */
  public ScreenController getScreenController() {
    return screenController;
  }

  /**
   * @param screenController
   *          the screenController to set.
   */
  public void setScreenController(ScreenController screenController) {
    this.screenController = screenController;
  }

  /**
   * gets the scene of the gui.
   * 
   * @return the scene
   */
  public Scene getScene() {
    return scene;
  }

  /**
   * sets the scene.
   * 
   * @param scene
   *          the scene
   */
  public void setScene(Scene scene) {
    this.scene = scene;
  }

  /**
   * @param args
   *          parameters to influence the startup of this game.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * This function is called by the application. This sets up a scene and pane.
   * 
   * @param stage
   *          the stage the scene is set to.
   */
  public void start(Stage stage) {
    pane = new Pane();
    this.stage = stage;
    stage.setFullScreen(true);
    scene = new Scene(pane, stage.getHeight(), stage.getWidth(), BACKCOLOUR);
    addElements();
    stage.setScene(scene);
    stage.show();
    Game game = new Game();
    game.launch(this);
  }

  /**
   * Function for start of drawing fish on screen.
   */
  public void startGame() {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(FRAME_DELAY), ev -> {
      this.showFishList(this.screenController.getNextFrameInfo());
    }));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }

  /**
   * Add some key elements to the pane. This includes: Background
   * 
   * @return the pane with elements
   */
  public Pane addElements() {
    BackgroundImage myBackground = new BackgroundImage(new Image("background.jpg", XRES, YRES,
        true, false), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    pane.setBackground(new Background(myBackground));
    return pane;
  }

  /**
   * This function removes all the ImageView objects. This is used to remove all the fish images on
   * the screen.
   */
  public void clearPaneOfImageView() {
    ObservableList<Node> list = pane.getChildren();

    list.removeAll(list.stream().filter(
        v -> v instanceof ImageView || v instanceof Rectangle).collect(Collectors.toList()));
  }

  /**
   * This method displays a list<Fish> on the scene of the gui.
   * 
   * @param list
   *          the list of fish that needs to be displayed.
   */
  public void showFishList(List<Fish> list) {
    clearPaneOfImageView();
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).isAlive()) {
        this.pane.getChildren().add(fishImage(list.get(i)));
        Rectangle hitBox = list.get(i).makeHitbox();
        hitBox.setOpacity(0); // comment this line if you want to see the hitboxes as black boxes
        this.pane.getChildren().add(hitBox);
      }
    }
  }

  /**
   * an image object of a fish.
   * 
   * @param fish
   *          the fish to display.
   * @return an imageview of the fish.
   */
  public ImageView fishImage(Fish fish) {
    Position position = fish.getPosition();
    double size = fish.getSize();
    Direction dir = fish.getDirection();

    ImageView image;
    image = new ImageView(fish.getImageResource());
    // flip the image according to the direction.
    if (dir.getDeltaX() != 0) {
      image.setScaleX(dir.getDeltaX());
    }
    image.setFitHeight(size);
    image.setFitWidth(fish.getWidthScale() * size);

    image.setX(position.getPosX());
    image.setY(position.getPosY());

    return image;
  }
  
  /**
   * calling this method clears the board of fish and show a winning message.
   */
  public void showWinningScreen() {
    clearPaneOfImageView();
    pane.getChildren().add(textInMiddle("You are now the biggest fish in the sea!"));
  }
  
  /**
   * calling this method clears the board of fish and show a game over message.
   */
  public void showGameOverScreen() {
    System.out.println("gui says game lost.");
    stage.close();
    clearPaneOfImageView();
    pane.getChildren().add(textInMiddle("Game Over!"));
  }
  
  /**
   * make a message block that is in the middle of our screen.
   * @param message the message to display.
   * @return a text object displaying the message in the middle of the screen.
   */
  private Text textInMiddle(String message) {
    Text text = new Text(message);
    text.setX(Position.middlePosition().getPosX());
    text.setX(Position.middlePosition().getPosY());
    return text;
  }
}