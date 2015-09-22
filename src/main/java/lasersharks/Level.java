package lasersharks;

import java.util.List;

import lasersharks.gui.LevelGUI;

/**
 * This level represents the connection between the fishController, game and screen controller.
 * 
 * @author Sytze, Youri
 */
public class Level {
  private static final float START_SIZE = 80.0f;
  private static final double START_SPEED = 670;
  private static final Direction START_DIRECTION = Direction.None;
  private LaserShark shark;
  private FishController fishCon;
  private ScreenController screenCon;
  private KeyboardController keyboardCon;

  /**
   * this is the constructor of the level class.
   * 
   * @param game
   *          controller from witch to take commands and where to find env data.
   * @param gui
   *          reference to the GUI Object.
   */
  public Level(LevelGUI gui) {
    this.fishCon = new FishController();
    this.shark = new LaserShark(Position.middlePosition(), START_SIZE, START_SPEED,
        START_DIRECTION);
    this.fishCon.setShark(this.shark);
    this.screenCon = new ScreenController(this, gui);
    this.keyboardCon = new KeyboardController(this.screenCon, this);
  }

  /**
   * Set shark direction.
   * 
   * @param dir
   *          direction in witch to move.
   */
  public void setSharkDirection(Direction dir) {
    this.shark.setDirection(dir);
  }

  /**
   * Method for getting information for next frame.
   * 
   * @param frametime
   *          time between frames in seconds
   * @return info for next frame
   */
  public List<Fish> getNextFrameInfo(double frametime) {
    return this.fishCon.getNextCycleInformation(frametime);
  }

  /**
   * @return the fishCon
   */
  public FishController getFishCon() {
    return fishCon;
  }

  /**
   * @param fishCon
   *          the fishCon to set
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
   * @param shark
   *          the shark to set
   */
  public void setShark(LaserShark shark) {
    this.shark = shark;
  }
  
  /**
   * Set the shark to his beginning state.
   */
  public void setBeginShark() {
    this.shark = new LaserShark(Position.middlePosition(), START_SIZE, START_SPEED,
        START_DIRECTION);
    this.fishCon.setShark(this.shark);
  }

  /**
   * @return the screenCon
   */
  public ScreenController getScreenCon() {
    return screenCon;
  }

  /**
   * @param screenCon
   *          the screenCon to set
   */
  public void setScreenCon(ScreenController screenCon) {
    this.screenCon = screenCon;
  }

  /**
   * @return the keyboardCon
   */
  public KeyboardController getKeyboardCon() {
    return keyboardCon;
  }

  /**
   * @param keyboardCon
   *          the keyboardCon to set
   */
  public void setKeyboardCon(KeyboardController keyboardCon) {
    this.keyboardCon = keyboardCon;
  }

  /**
   * Launch game.
   */
  public void launch() {
    this.screenCon.start();
  }
  
  /**
   * Restart game.
   */
  public void restartGame() {
    screenCon.restart();
    fishCon.clearFish();
  }

}
