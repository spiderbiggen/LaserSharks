package lasersharksgui.panes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lasersharks.Logger;
import lasersharks.Options;
import lasersharks.Position;
import lasersharks.controllers.AudioController;
import lasersharksgui.interfaces.Stoppable;

/**
 * The standardPane is the standard for creating new panes. It is an empty pane containing the
 * background and music playing.
 * 
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public abstract class StandardPane extends Pane implements Stoppable {

  // text variables
  protected static final int TEXT_SCALE_SIZE_BIG = 17;
  protected static final int TEXT_SCALE_SIZE_SMALL = 4;

  // audio variables
  protected Button muteButton;
  protected ImageView muteButtonImage = new ImageView("mutesound.png");
  protected ImageView unmuteButtonImage = new ImageView("unmutesound.png");

  // sprite and image variables
  protected ImageView sharkImage;
  protected static final int SCALING_FACTOR_TO_UNDERNEATH_MIDDLE = 3;
  protected static final int SCALING_FACTOR_TO_ABOVE_MIDDLE = -5;
  protected static final int SCALING_FACTOR_TO_LITTLE_BELOW_MIDDLE = 100;

  /**
   * Constructor of the StandardPane.
   */
  public StandardPane() {
    super();
    addBackGround();
    if (!Options.getInstance().isPlayingMusic()) {
      AudioController.getInstance().playMusic(Options.getInstance().getMusicFileName());
    }
  }

  /**
   * This function adds a background to the panel.
   */
  public void addBackGround() {
    BackgroundImage myBI = new BackgroundImage(
        new Image(Options.getInstance().getBackGroundImage(), Options.getGlobalWidth(),
            Options.getGlobalHeight(), true, false),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    setBackground(new Background(myBI));
  }

  /**
   * Displays the score in the upper right corner of the screen.
   * 
   * @param score
   *          a string containing the score of the player.
   */
  public void showScore(String score) {
    addText("Score: " + score, TEXT_SCALE_SIZE_SMALL, Position.upperCornerPosition());
  }

  /**
   * This function adds a textbox directly on the screen.
   * 
   * @param message
   *          The message that should be shown.
   * @param textSize
   *          The size of the message.
   * @param position
   *          the position of the message on the screen.
   */
  public void addText(String message, int textSize, Position position) {
    Text gameText = new Text(message);
    gameText.setScaleX(textSize);
    gameText.setScaleY(textSize);
    gameText.setX(position.getPosX());
    gameText.setY(position.getPosY());
    getChildren().add(gameText);
  }

  /**
   * This function adds a textbox directly on the screen. The X position here is automatically set
   * to the middle.
   * 
   * @param message
   *          The message that should be shown.
   * @param textSize
   *          The size of the message.
   * @param deltaY
   *          how far from the middle of the screen the message should be shown.
   */
  public void addMidText(String message, int textSize, double deltaY) {
    addText(message, textSize, new Position(Position.middlePosition().getPosX(),
        Position.middlePosition().getPosY() - deltaY));
  }
}
