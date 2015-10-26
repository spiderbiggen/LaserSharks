package lasersharksgui.panes;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import lasersharks.Direction;
import lasersharks.Highscores;
import lasersharks.Position;
import lasersharks.controllers.DirectionInputController;
import lasersharks.controllers.PauseController;
import lasersharks.controllers.ScreenController;
import lasersharks.controllers.ShootController;
import lasersharks.interfaces.DirectionCallback;
import lasersharks.interfaces.Displayable;
import lasersharks.seaobjects.LaserShark;
import lasersharksgui.MainGui;
import lasersharksgui.interfaces.Stoppable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the pane representing the gameplay.
 * 
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class GamePane extends StandardPane implements Stoppable {

  // private static final int ANIMATION_SLEEP_TIMER = 10;
  private AnimationTimer animation;
  private final double timeToMilis = 1_000_000;
  private ScreenController screenController;
  private static long time = 0;
  private static boolean paused = false;
  private DirectionCallback callback;
  private DirectionInputController directionInputController;
  private ShootController shootController;
  private PauseController pauseController;

  /**
   * The constructor creates a new keyboardcontroller and screencontroller. The GamePane connects
   * these to itself.
   */
  public GamePane() {
    screenController = new ScreenController(this);
    callback = this.screenController.getShark();
    startGame();
  }

  /**
   * Function for start of drawing fish on screen.
   */
  public void startGame() {
    addEventHandlers();
    Highscores.getInstance().setScore(0);
    clearPaneOfImageView();
    animation = new AnimationTimer() {
      @Override
      public void handle(long now) {
        if (paused) {
          time = now;
          paused = false;
        }
        double frametime = (now - time) / timeToMilis;
        final double milis = 1000;

        showFishList(screenController.getNextFrameInfo(milis / frametime));
        showShark(screenController.getShark());
        showScore();

        /*
         * try { Thread.sleep(ANIMATION_SLEEP_TIMER); } catch (InterruptedException e) {
         * Logger.getInstance().write("Animation timer Interrupted", e.getMessage()); }
         */
        time = now;
      }
    };
    animation.start();
  }

  /**
   * Adds all event handlers to the GamePane.
   */
  public void addEventHandlers() {
    addNonPauseEventHandlers();
    pauseController = new PauseController(this);
    MainGui.getInstance().getCurrentScene().addEventHandler(KeyEvent.ANY, pauseController);
  }

  /**
   * Stops the game. Can be resumed by calling resumeGame().
   */
  public void stopGame() {
    paused = true;
    removeNonPauseEventHandlers();
    animation.stop();
  }

  /**
   * resumes the game. Game has first to be started before it can be resumed.
   */
  public void resumeGame() {
    addNonPauseEventHandlers();
    animation.start();
  }

  /**
   * Displays the score in the upper right corner of the screen.
   */
  public void showScore() {
    addText("Score: " + Highscores.getInstance().getScore(), TEXT_SCALE_SIZE_SMALL,
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
  public void showFishList(List<Displayable> list) {
    clearPaneOfImageView();
    for (Displayable aList : list) {
      if (aList.isAlive()) {
        getChildren().add(fishImage(aList));
        Rectangle hitBox = aList.makeHitbox();
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
  public ImageView fishImage(Displayable swimmer) {
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
   * @param screenController
   *          the screenController to set
   */
  public void setScreenController(ScreenController screenController) {
    this.screenController = screenController;
  }

  @Override
  public void stop() {
    this.clearPaneOfImageView();
    this.stopGame();
    removeAllEventHandlers();
  }

  /**
   * This method removes all event handlers.
   */
  public void removeAllEventHandlers() {
    removeNonPauseEventHandlers();
    MainGui.getInstance().getCurrentScene().removeEventHandler(KeyEvent.ANY, pauseController);
  }

  /**
   * Removes all the event handlers, except for the pause handler. This is used for pausing the
   * game.
   */
  public void removeNonPauseEventHandlers() {
    MainGui.getInstance().getCurrentScene().removeEventHandler(KeyEvent.ANY,
        directionInputController);
    MainGui.getInstance().getCurrentScene().removeEventHandler(KeyEvent.ANY, shootController);
  }

  /**
   * Add all the event handlers, except for the pause handler. This is used for resuming the game.
   */
  public void addNonPauseEventHandlers() {
    directionInputController = new DirectionInputController(callback);
    shootController = new ShootController(screenController.getFishController());
    MainGui.getInstance().getCurrentScene().addEventHandler(KeyEvent.ANY, directionInputController);
    MainGui.getInstance().getCurrentScene().addEventHandler(KeyEvent.ANY, shootController);
  }
}
