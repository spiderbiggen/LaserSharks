package lasersharksgui.panes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lasersharks.Options;
import lasersharks.Position;
import lasersharks.controllers.AudioController;
import lasersharksgui.interfaces.Stoppable;

/**
 * The standardPane is the standard for creating new panes. It is an empty pane containing the
 * background and music playing.
 *
 * @author SEMGroup27
 */
@SuppressWarnings("restriction")
public abstract class AbstractStandardPane extends Pane
    implements Stoppable {

  // text variables
  protected static final int TEXT_SCALE_SIZE_BIG = 17;
  protected static final int TEXT_SCALE_SIZE_SMALL = 4;
  protected static final int SCALING_FACTOR_TO_UNDERNEATH_MIDDLE = 3;
  protected static final int SCALING_FACTOR_TO_ABOVE_MIDDLE = -5;
  protected static final int SCALING_FACTOR_TO_LITTLE_BELOW_MIDDLE = 100;
  protected static final double DEFAULT_SCREEN_HEIGHT = 1080;
  protected final double screenScale;
  // sprite and image variables
  protected ImageView sharkImage;

  /**
   * Constructor of the AbstractStandardPane.
   */
  public AbstractStandardPane() {
    super();
    screenScale = Options.getGlobalHeight() / DEFAULT_SCREEN_HEIGHT;
    addBackGround();
    if (!Options.getInstance().isPlayingMusic()) {
      AudioController.getInstance().playMusic(Options.getInstance().getMusicFileName());
    }
  }

  /**
   * This function adds a background to the panel.
   */
  protected void addBackGround() {
    final BackgroundImage myBI = new BackgroundImage(
        new Image(Options.getInstance().getBackground(), Options.getGlobalWidth(),
            Options.getGlobalHeight(), true, false),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    setBackground(new Background(myBI));
  }

  /**
   * Displays the score in the upper right corner of the screen.
   *
   * @param score a string containing the score of the player.
   */
  public void showScore(String score) {
    addText("Score: " + score, TEXT_SCALE_SIZE_SMALL, Position.upperCornerPosition());
  }

  /**
   * This function adds a text box directly on the screen.
   *
   * @param message
   *          The message that should be shown.
   * @param textSize
   *          The size of the message.
   * @param position
   *          the position of the message on the screen.
   */
  public void addText(final String message, final int textSize, final Position position) {
    final Text gameText = new Text(message);
    gameText.setScaleX(textSize * screenScale);
    gameText.setScaleY(textSize * screenScale);
    gameText.setX(position.getPosX());
    gameText.setY(position.getPosY());
    getChildren().add(gameText);
  }

  /**
   * This function adds a text box directly on the screen. The X position here is automatically set
   * to the middle.
   *
   * @param message  The message that should be shown.
   * @param textSize The size of the message.
   * @param deltaY   how far from the middle of the screen the message should be shown.
   */
  public void addMidText(final String message, final int textSize, final double deltaY) {
    addText(message, textSize, new Position(Position.middlePosition().getPosX(),
        Position.middlePosition().getPosY() - deltaY));
  }
}
