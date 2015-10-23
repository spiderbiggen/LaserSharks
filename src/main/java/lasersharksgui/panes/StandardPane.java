package lasersharksgui.panes;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import lasersharks.Logger;
import lasersharks.Options;
import lasersharks.Position;
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
  protected static final int TEXT_SCALE_SIZE_MED = 10;
  protected static final int TEXT_SCALE_SIZE_SMALL = 4;

  // audio variables
  protected static MediaPlayer mediaPlayer;
  protected static MediaPlayer soundPlayer;
  private static boolean musicIsPlaying = false;
  private static boolean shouldEffectPlay = true;
  protected Button muteButton;

  // sprite and image variables
  protected ImageView sharkImage;
  protected static final int SCALING_FACTOR_TO_UNDERNEATH_MIDDLE = 3;
  protected static final int SCALING_FACTOR_TO_ABOVE_MIDDLE = -5;
  protected static final int SCALING_FACTOR_TO_LITTLE_BELOW_MIDDLE = 100;
  protected ImageView muteButtonImage = new ImageView("mutesound.png");
  protected ImageView unmuteButtonImage = new ImageView("unmutesound.png");

  // variables for the mute button
  protected static final int BUTTON_HEIGHT = 24;
  protected static final int BUTTON_WIDTH = 36;

  /**
   * Constructor of the StandardPane.
   */
  public StandardPane() {
    super();
    addBackGround();
    if (!musicIsPlaying) {
      playMusic(Options.getInstance().getMusicFileName());
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
   * Method for adding the mute button to the scene.
   */
  public Button muteButton(double xPos, double yPos, double size) {
    muteButtonImage.setFitHeight(BUTTON_HEIGHT * size);
    muteButtonImage.setFitWidth(BUTTON_WIDTH * size);
    unmuteButtonImage.setFitHeight(BUTTON_HEIGHT * size);
    unmuteButtonImage.setFitWidth(BUTTON_WIDTH * size);
    muteButton = new Button();
    muteButton.setGraphic(muteButtonImage);
    muteButton.setTranslateX(xPos);
    muteButton.setTranslateY(yPos);

    muteButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        muteSound();
      }
    });
    return muteButton;
  }

  /**
   * This function plays a music track on repeat.
   * 
   * @param path
   *          the path of the musicfile that should be played.
   */
  public static void playMusic(String path) {
    try {
      mediaPlayer = new MediaPlayer(new Media(new File(path).toURI().toString()));
      mediaPlayer.setAutoPlay(true);
      mediaPlayer.play();
      musicIsPlaying = true;
    } catch (Exception e) {
      Logger.getInstance().write("MusicPlay failed", e.getMessage());
    }
  }

  /**
   * This function plays a sound effect.
   * 
   * @param path
   *          the path of the sound file that should be played.
   */
  public static void playSoundEffect(String path) {
    try {
      soundPlayer = new MediaPlayer(new Media(new File(path).toURI().toString()));
      if (shouldEffectPlay) {
        soundPlayer.play();
      }
    } catch (Exception e) {
      Logger.getInstance().write("AudioPlay failed", e.getMessage());
    }
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

  /**
   * method for muting and unmuting the music of the game.
   */
  public void muteSound() {
    if (musicIsPlaying) {
      mediaPlayer.pause();
      musicIsPlaying = false;
      shouldEffectPlay = false;
      muteButton.setGraphic(unmuteButtonImage);
      Logger.getInstance().write("Sound muted", "Mute sound button pressed");
    } else {
      mediaPlayer.play();
      muteButton.setGraphic(muteButtonImage);
      musicIsPlaying = true;
      shouldEffectPlay = true;
      Logger.getInstance().write("Sound unmuted", "Mute sound button pressed");
    }

  }

}
