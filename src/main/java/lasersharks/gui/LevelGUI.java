package lasersharks.gui;

import java.util.Iterator;
import java.util.List;

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
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;
import lasersharks.Direction;
import lasersharks.Fish;
import lasersharks.Game;
import lasersharks.LaserShark;
import lasersharks.Position;
import lasersharks.ScreenController;

/**
 * This class represents the gui of our application.
 * 
 * @author michiel, daan
 *
 */
@SuppressWarnings("restriction")
public class LevelGUI extends Application {
  private static final double FRAME_DELAY = 0.1;
  /**
   * the x resolution of the screen.
   */
  private static final double XRES = 1920;
  /**
   * the y resolution of the screen.
   */
  private static final double YRES = 1080;
  /**
   * The background colour of the gui.
   */
  private static final Color BACKCOLOUR = Color.BLUE;
  private ScreenController screenController;
  private Pane pane;
  private Scene scene;

  /**
   * @return the screenController
   */
  public ScreenController getScreenController() {
    return screenController;
  }

  /**
   * @param screenController the screenController to set
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
   *          parameters to influence the startup of this game
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
    stage.setFullScreen(true);
    scene = new Scene(pane, stage.getHeight(), stage.getWidth(), BACKCOLOUR);
    addElements();
    stage.setScene(scene);
    stage.show();

    Game g = new Game();
    g.launch(this);
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
    BackgroundImage myBI = new BackgroundImage(new Image("background.jpg", XRES, YRES, true, false),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    pane.setBackground(new Background(myBI));
    return pane;
  }

  /**
   * This function removes all the ImageView objects. This is used to remove all the fish images on
   * the screen.
   */
  public void clearPaneOfImageView() {
    ObservableList<Node> list = pane.getChildren();
    Iterator<Node> it = list.iterator();
    while (it.hasNext()) {
      Node node = it.next();
      if (node instanceof ImageView) {
        list.remove(node);
      }
    }
  }

  /**
   * This method displays a list<Fish> on the scene of the gui.
   * 
   * @param list
   *          the list of fish that needs to be displayed.
   */
  public void showFishList(List<Fish> list) {
    clearPaneOfImageView();
    System.out.println("ShowFishCalled");
    for (int i = 0; i < list.size(); i++) {
      this.pane.getChildren().add(fishImage(list.get(i)));
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
    if (fish instanceof LaserShark) {
      image = new ImageView("LaserShark.gif");
    } else {
      image = new ImageView("FishBot.jpg");
    }
    // flip the image according to the direction.
    if (dir != Direction.None) {
      image.setScaleX(dir.getDeltaX());
    }
    image.setFitHeight(size);
    image.setFitWidth(size);

    image.setX(position.getPosX());
    image.setY(position.getPosY());

    return image;
  }
}