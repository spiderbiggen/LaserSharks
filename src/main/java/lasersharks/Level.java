package lasersharks;

import java.sql.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This level represents the connection between the fishController, game and screen controller.
 * @author Sytze
 */
public class Level {
  private LaserShark shark;
  private FishController fishCon;
  private ScreenController screenCon;
  private KeyboardController keyboardCon;
  
  private int score;
  
  /**
   * this is the constructor of the level class.
   */
  public Level() {
    fishCon = new FishController();
    shark = new LaserShark(Position.middlePosition(), Direction.East, 1.0f);
    fishCon.addFish(shark);
    
    screenCon = new ScreenController(this);
    keyboardCon = new KeyboardController(screenCon, this);
  }
  
  /**
   * Set shark direction.
   * @param dir
   */
  public void setSharkDirection(Direction dir) {
    this.shark.setDirection(dir);
  }
  
  /**
   * Method for getting information for next frame.
   * @return
   */
  public List<Fish> getNextFrameInfo() {
    return this.fishCon.getNextCycleInformation();
  }

  /**
   * @return the fishCon
   */
  public FishController getFishCon() {
    return fishCon;
  }

  /**
   * @param fishCon the fishCon to set
   */
  public void setFishCon(FishController fishCon) {
    this.fishCon = fishCon;
  }

  /**
   * @return the shark
   */
  public LaserShark getShark() {
    return shark;
  }

  /**
   * @param shark the shark to set
   */
  public void setShark(LaserShark shark) {
    this.shark = shark;
  }

  /**
   * @return the screenCon
   */
  public ScreenController getScreenCon() {
    return screenCon;
  }

  /**
   * @param screenCon the screenCon to set
   */
  public void setScreenCon(ScreenController screenCon) {
    this.screenCon = screenCon;
  }

  /**
   * @return the score
   */
  public int getScore() {
    return score;
  }

  /**
   * @param score the score to set
   */
  public void setScore(int score) {
    this.score = score;
  }
  
  @Override
  public String toString() {
    return "Level [shark=" + shark + ", fishCon=" + fishCon + ", screenCon=" + screenCon
        + ", score=" + score + "]";
  }
}
