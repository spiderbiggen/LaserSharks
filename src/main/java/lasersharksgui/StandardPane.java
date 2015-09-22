package lasersharksgui;

import java.io.File;
import java.io.FileNotFoundException;

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
import lasersharks.Direction;
import lasersharks.LaserShark;
import lasersharks.Options;
import lasersharks.Position;

public abstract class StandardPane extends Pane {

  //text variables
  protected static final int TEXT_SCALE_SIZE_BIG = 17;
  protected static final int TEXT_SCALE_SIZE_MED = 10;
  protected static final int TEXT_SCALE_SIZE_SMALL = 4;
  
  //audio variables
  protected static Media media;
  protected static MediaPlayer mediaPlayer;
  private static boolean musicIsPlaying = false;
  
  //sprite and image variables
  protected ImageView sharkImage;
  
  
  public StandardPane() {
    super();
    addBackGround();
    if(!musicIsPlaying){
      playMusic(Options.getInstance().getMusicFileName());
    }
  }
  
  /**
   * This function adds a background to the panel
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
   * This function plays a music track on repeat
   * @param path
   */
  public static void playMusic(String path) {
    media = new Media(new File(path).toURI().toString());
    mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setAutoPlay(true);
    mediaPlayer.play();
    musicIsPlaying = true;
  }
  
  /**
   * Displays the score in the upper right corner of the screen.
   */
  public void showScore(String score) {
    addText("Score: " + score,TEXT_SCALE_SIZE_SMALL,Position.upperCornerPosition());
  }
  
  public void addText(String message, int textSize, Position position){
    Text gameText = new Text(message);
    gameText.setScaleX(textSize);
    gameText.setScaleY(textSize);
    gameText.setX(position.getPosX());
    gameText.setY(position.getPosY());
    getChildren().add(gameText);
  }
  
  public void addMidText(String message, int textSize, double deltaY) {
    addText(message, textSize, new Position(Position.middlePosition().getPosX(), 
        Position.middlePosition().getPosY() - deltaY ));
  }
}
