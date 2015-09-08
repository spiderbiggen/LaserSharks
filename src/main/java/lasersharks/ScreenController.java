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
  private static final int FRAME_DELAY = 20;
  private LevelGUI gui;
  private Level level;
  private Scene scene;
  private boolean running;

  /**
   * Constructor.
   * 
   * @param level
   *          the level from witch to recieve data.
   */
  public ScreenController(Level level, LevelGUI gui) {
    super();
    this.level = level;
    this.running = true;
    this.gui = gui;
    this.gui.setScreenController(this);
    this.scene = gui.getScene();
  }
  
  /**
   * Get infor for next frame.
   * @return FishInfo
   */
  public List<Fish> getNextFrameInfo() {
    return this.level.getNextFrameInfo();
  }

  /**
   * get the scene from the gui.
   * @return the scene from the gui
   */
  public Scene getScene() {
    return this.scene;
  }
  
  /**
   * Returns the static LevelGUI.
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
}
