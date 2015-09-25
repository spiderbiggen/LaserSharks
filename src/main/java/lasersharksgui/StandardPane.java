package lasersharksgui;

import java.io.File;

import com.sun.media.jfxmedia.MediaException;

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

/**
 * The standardPane is the standard for creating new panes.
 * It is an empty pane containing the background and music playing.
 * @author sytze
 *
 */
public abstract class StandardPane extends Pane {

  //text variables
  protected static final int TEXT_SCALE_SIZE_BIG = 17;
  protected static final int TEXT_SCALE_SIZE_MED = 10;
  protected static final int TEXT_SCALE_SIZE_SMALL = 4;
  
  //audio variables
  protected static MediaPlayer mediaPlayer;
  protected static MediaPlayer soundPlayer;
  private static boolean musicIsPlaying = false;
  
  //sprite and image variables
  protected ImageView sharkImage;
  
  protected static final int SCREEN_POSITION_THREE = 3;
  protected static final int SCREEN_POSITION_FIVE = 5;
  protected static final int SCREEN_POSITION_TEN = 10;
  
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
        new Image(Options.getInstance().getBackGroundImage(), 
            Options.getGlobalWidth(), Options.getGlobalHeight(), true, false), 
            BackgroundRepeat.REPEAT,
        BackgroundRepeat.NO_REPEAT, 
        BackgroundPosition.DEFAULT, 
        BackgroundSize.DEFAULT);
    setBackground(new Background(myBI));
  }
  
  /**
   * This function plays a music track on repeat.
   * @param path the path of the musicfile that should be played.
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
   * @param path the path of the sound file that should be played.
   */
  public static void playSoundEffect(String path) {
    try {
    soundPlayer = new MediaPlayer(new Media(new File(path).toURI().toString()));
    soundPlayer.play();
    } catch (Exception e) {
      Logger.getInstance().write("AudioPlay failed", e.getMessage());
    }
  }
  
  /**
   * Displays the score in the upper right corner of the screen.
   * @param score a string containing the score of the player.
   */
  public void showScore(String score) {
    addText("Score: " + score, TEXT_SCALE_SIZE_SMALL, Position.upperCornerPosition());
  }
  
  /**
   * This function adds a textbox directly on the screen.
   * @param message The message that should be shown.
   * @param textSize The size of the message.
   * @param position the position of the message on the screen.
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
   * This function adds a textbox directly on the screen. 
   * The X position here is automatically set to the middle.
   * @param message The message that should be shown.
   * @param textSize The size of the message.
   * @param deltaY how far from the middle of the screen the message should be shown.
   */
  public void addMidText(String message, int textSize, double deltaY) {
    addText(message, textSize, new Position(Position.middlePosition().getPosX(), 
        Position.middlePosition().getPosY() - deltaY));
  }
}
