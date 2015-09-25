package lasersharksgui;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import lasersharks.Direction;
import lasersharks.Highscores;
import lasersharks.DirectionInputController;
import lasersharks.LaserShark;
import lasersharks.Logger;
import lasersharks.Position;
import lasersharks.RestartGameController;
import lasersharks.ScreenController;
import lasersharks.Swimmer;

/**
 * This is the pane representing the gameplay.
 * @author Sytze
 *
 */
public class GamePane extends StandardPane {

  private AnimationTimer animation;
  private final double timeToMilis = 1_000_000;
  private ScreenController screenController;
  private static long time = 0;
  
  /**
   * The constructor creates a new keyboardcontroller and screencontroller. 
   * The GamePane connects these to itself.
   */
  public GamePane() {
    screenController = new ScreenController(this);
    startGame();
    new DirectionInputController(this.screenController, this.screenController.getShark());
  }
  
  
  /**
   * Function for start of drawing fish on screen.
   */
  public void startGame() {
    Highscores.setScore(0);
    animation = new AnimationTimer() {
      @Override
      public void handle(long now) {
        double frametime = (now - time) / timeToMilis;
        final double milis = 1000;
        try {
          showFishList(screenController.getNextFrameInfo(milis / frametime));
        } catch (IOException e) {
          Logger.getInstance().write(
              "IOException getting fishlist in Gamepane::startGame", 
              e.getMessage());
        }
        showShark(screenController.getShark());
        showScore();
        time = now;
      }
    };
    animation.start();
  }

  /**
   * Stops the game. Can be resumed by calling resumeGame().
   */
  public void stopGame() {
    animation.stop();
  }
  
  /**
   * resumes the game. Game has first to be started before it can be resumed.
   */
  public void resumeGame() {
    animation.start();
  }
  
  public void restartGame() {
    System.out.println("restart");
  }
  
  
  /**
   * Displays the score in the upper right corner of the screen.
   */
  public void showScore() {
    addText("Score: " + Highscores.getScore(), 
        TEXT_SCALE_SIZE_SMALL , 
        Position.upperCornerPosition());  
  }
  
  /**
   * This function removes all the ImageView objects. This is used to remove all the fish images on
   * the screen.
   */
  public void clearPaneOfImageView() {
    ObservableList<Node> list = getChildren();
    list.removeAll(list.stream()
        .filter(v -> v instanceof ImageView || v instanceof Rectangle || v instanceof Text)
        .collect(Collectors.toList()));
  }
  
  /**
   * This method will display the shark on the screen.
   * 
   * @param shark
   *          the shark to display
   */
  public void showShark(LaserShark shark) {
    if (sharkImage == null) {
      sharkImage = new ImageView(shark.getImageResource());
    }
    Position position = shark.getPosition();
    double size = shark.getSize();
    Direction dir = shark.getDirection();

    // flip the image according to the direction.
    if (dir.getDeltaX() != 0) {
      sharkImage.setScaleX(dir.getDeltaX());
    }
    sharkImage.setFitHeight(size);
    sharkImage.setFitWidth(size * shark.getWidthScale());

    sharkImage.setX(position.getPosX());
    sharkImage.setY(position.getPosY());
    getChildren().add(sharkImage);
  }
  
  /**
   * This method displays a list<Fish> on the scene of the gui.
   * 
   * @param list
   *          the list of fish that needs to be displayed.
   */
  public void showFishList(List<Swimmer> list) {
    clearPaneOfImageView();
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).isAlive()) {
        getChildren().add(fishImage(list.get(i)));
        Rectangle hitBox = list.get(i).makeHitbox();
        hitBox.setOpacity(0); // comment this line if you want to see the hitboxes as black boxes
        getChildren().add(hitBox);
      }
    }
  }
  

  /**
   * an image object of a fish.
   * 
   * @param swimmer
   *          the fish to display.
   * @return an imageview of the fish.
   */
  public ImageView fishImage(Swimmer swimmer) {
    Position position = swimmer.getPosition();
    double size = swimmer.getSize();
    Direction dir = swimmer.getDirection();

    ImageView image;
    image = new ImageView(swimmer.getImageResource());
    // flip the image according to the direction.
    if (dir.getDeltaX() != 0) {
      image.setScaleX(dir.getDeltaX());
    }
    image.setFitHeight(size);
    image.setFitWidth(size * swimmer.getWidthScale());

    image.setX(position.getPosX());
    image.setY(position.getPosY());

    return image;
  }


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
  
}
