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
import lasersharks.Fish;
import lasersharks.Highscores;
import lasersharks.LaserShark;
import lasersharks.Position;
import lasersharks.ScreenController;

public class GamePane extends StandardPane{

  private static final float HALF_SCALE = 0.5f;
  AnimationTimer animation;
  private final double timeToMilis = 1_000_000;
  private ScreenController screenController;
  private static long time = 0;
  private static int score;
  public GamePane() {
    
  }
  
  
  /**
   * Function for start of drawing fish on screen.
   */
  public void startGame() {
    animation = new AnimationTimer() {

      @Override
      public void handle(long now) {
        double frametime = (now - time) / timeToMilis;
        final double milis = 1000;
        try {
          showFishList(screenController.getNextFrameInfo(milis / frametime));
        } catch (IOException e) {
          e.printStackTrace();
        }
        showShark(screenController.getShark());
        showScore();
        time = now;
      }

    };
    animation.start();
  }

  /**
   * Displays the score in the upper right corner of the screen.
   */
  public void showScore() {
    addText("Score: " + score, TEXT_SCALE_SIZE_MED , Position.upperCornerPosition());  
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
  public void showFishList(List<Fish> list) {
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
    image.setFitWidth(size * fish.getWidthScale());

    image.setX(position.getPosX());
    image.setY(position.getPosY());

    return image;
  }
  
  /**
   * Get's the current score from this game.
   * 
   * @return the score
   */
  public static int getScore() {
    return score;
  }

  /**
   * Set the score to newScore.
   * 
   * @param newScore
   *          the newScore
   */
  public static void setScore(int newScore) {
    score = newScore;
  }

  /**
   * Increase the current score the player has according to the size of the fish eaten.
   * 
   * @param fish
   *          the fish that is used to calculate the additional score
   */
  public static void increaseScore(Fish fish) {
    if (fish.isAlive()) {
      score = (int) (score + fish.getSize() * HALF_SCALE + Highscores.getFishBonus());
    }
  }
}
