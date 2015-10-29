package lasersharksgui.panes;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import lasersharks.Direction;
import lasersharks.HighScores;
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
 * This is the pane representing the game play.
 *
 * @author SEMGroup27
 */
@SuppressWarnings("restriction")
public class GamePane extends AbstractStandardPane implements Stoppable {

  private static final double TIME_TO_MILLIS = 1_000_000;
  private static long time;
  private static boolean paused;
  private final DirectionCallback callback;

  private AnimationTimer animation;
  private DirectionInputController directionInputController;
  private ScreenController screenController;
  private ShootController shootController;
  private PauseController pauseController;

  /**
   * The constructor creates a new keyboardController and screenController. The GamePane connects
   * these to itself.
   */
  public GamePane() {
    super();
    screenController = new ScreenController(this);
    callback = this.screenController.getShark();
    startGame();
  }

  private static void setPaused(final boolean paused) {
    GamePane.paused = paused;
  }

  /**
   * Function for start of drawing fish on screen.
   */
  public void startGame() {
    addEventHandlers();
    HighScores.getInstance().setScore(0);
    clearPaneOfImageView();
    animation = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        if (paused) {
          time = now;
          setPaused(false);
        }

        final double frameTime = (now - time) / TIME_TO_MILLIS;
        final double millis = 1000;

        showFishList(screenController.getNextFrameInfo(millis / frameTime));
        showShark(screenController.getShark());
        showScoreAndAmmo();

        time = now;
      }
    };
    animation.start();
  }

  /**
   * Adds all event handlers to the GamePane.
   */
  private void addEventHandlers() {
    addNonPauseEventHandlers();
    pauseController = new PauseController(this);
    MainGui.getInstance().getCurrentScene().addEventHandler(KeyEvent.ANY, pauseController);
  }

  /**
   * Stops the game. Can be resumed by calling resumeGame().
   */
  public void stopGame() {
    GamePane.setPaused(true);
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

  private void showScoreAndAmmo() {
    addText(String.format("Ammo: %d Score: %d", screenController.getShark().getAmmo(),
            HighScores.getInstance().getScore()), TEXT_SCALE_SIZE_SMALL,
        Position.upperCornerPosition());
  }

  /**
   * This function removes all the ImageView objects. This is used to remove all the fish images on
   * the screen.
   */
  private void clearPaneOfImageView() {
    final ObservableList<Node> list = getChildren();
    list.removeAll(list.stream()
        .filter(v -> v instanceof ImageView || v instanceof Rectangle || v instanceof Text)
        .collect(Collectors.toList()));
  }

  /**
   * This method will display the shark on the screen.
   *
   * @param shark the shark to display
   */
  private void showShark(final LaserShark shark) {
    if (sharkImage == null) {
      sharkImage = new ImageView(shark.getImageResource());
    }
    final Position position = shark.getPosition();
    final double size = shark.getSize();
    final Direction dir = shark.getDirection();

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
   * @param list the list of fish that needs to be displayed.
   */
  private void showFishList(final List<Displayable> list) {
    clearPaneOfImageView();
    // comment this line if you want to see the hit boxes as black boxes
    list.stream().filter(Displayable::isAlive).forEach(aList -> {
      getChildren().add(fishImage(aList));
      final Rectangle hitBox = aList.makeHitBox();
      hitBox.setOpacity(0); // comment this line if you want to see the hit boxes as black boxes
      getChildren().add(hitBox);
    });
  }

  /**
   * an image object of a fish.
   *
   * @param swimmer the fish to display.
   * @return an imageView of the fish.
   */
  private ImageView fishImage(final Displayable swimmer) {
    final Position position = swimmer.getPosition();
    final double size = swimmer.getSize();
    final Direction dir = swimmer.getDirection();

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
  @Deprecated
  public ScreenController getScreenController() {
    return screenController;
  }

  /**
   * @param screenController the screenController to set
   */
  public void setScreenController(final ScreenController screenController) {
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
  private void removeAllEventHandlers() {
    removeNonPauseEventHandlers();
    MainGui.getInstance().getCurrentScene().removeEventHandler(KeyEvent.ANY, pauseController);
  }

  /**
   * Removes all the event handlers, except for the pause handler. This is used for pausing the
   * game.
   */
  private void removeNonPauseEventHandlers() {
    MainGui.getInstance().getCurrentScene().removeEventHandler(KeyEvent.ANY,
        directionInputController);
    MainGui.getInstance().getCurrentScene().removeEventHandler(KeyEvent.ANY, shootController);
  }

  /**
   * Add all the event handlers, except for the pause handler. This is used for resuming the game.
   */
  private void addNonPauseEventHandlers() {
    directionInputController = new DirectionInputController(callback);
    shootController = new ShootController(screenController.getFishController());
    MainGui.getInstance().getCurrentScene().addEventHandler(KeyEvent.ANY, directionInputController);
    MainGui.getInstance().getCurrentScene().addEventHandler(KeyEvent.ANY, shootController);
  }
}
