package lasersharks;

import java.util.List;

import javafx.scene.Scene;
import lasersharks.gui.LevelGUI;

/**
 * This is the class that will manage the screen.
 * 
 * @author Youri
 *
 */
@SuppressWarnings("restriction")
public class ScreenController {
  private LevelGUI gui;
  private Level level;
  private Scene scene;
  private static final int GAME_WINNING_SIZE = 320;

  /**
   * Constructor.
   * 
   * @param level
   *          the level from witch to receive data.
   * @param gui
   *          pointer to the active gui.
   */
  public ScreenController(Level level, LevelGUI gui) {
    super();
    this.level = level;
    this.gui = gui;
    this.gui.setScreenController(this);
    this.scene = gui.getScene();
  }

  /**
   * Get information for next frame and checks if the shark is bigger than the winning size.
   * 
   * @param frametime
   *          time between frames in seconds
   * 
   * @return FishInfo
   */
  public List<Fish> getNextFrameInfo(double frametime) {
    if (!this.level.getFishCon().getShark().isAlive()) {
      this.gui.setLoseSceneTrue();
      this.gui.chooseScene();
    } else if (this.level.getShark().getSize() > GAME_WINNING_SIZE) {
      this.gui.setWinSceneTrue();
      this.gui.chooseScene();
    }

    return this.level.getNextFrameInfo(frametime);
  }

  /**
   * get the shark from the level.
   * 
   * @return the shark
   */
  public LaserShark getShark() {
    return this.level.getShark();
  }

  /**
   * get the scene from the gui.
   * 
   * @return the scene from the gui
   */
  public Scene getScene() {
    return this.scene;
  }

  /**
   * Returns the static LevelGUI.
   * 
   * @return the gui
   */
  public LevelGUI getGui() {
    return gui;
  }

  /**
   * Propagation function for starting the game.
   */
  public void start() {
    this.gui.startGame();
  }
  
  /**
   * Restart the game.
   */
  public void restart() {
    //this.gui.setRestartGameTrue();    
    
    this.gui.stopAnimation();
    this.gui.restartGame();
    this.level.getFishCon().clearFish();
    this.level.getFishCon().getShark().setAlive();
    this.gui.setPlaySceneTrue();
   
    System.out.println("screenr");
    this.gui.chooseScene();
  }

}
