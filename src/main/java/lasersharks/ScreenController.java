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
  private static final int GAME_WINNING_SIZE = 1000;

  /**
   * Constructor.
   * 
   * @param level
   *          the level from witch to recieve data.
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
   * Get information for next frame and checks if the lasershark size is bigger than the winning size.
   * 
   * @return FishInfo
   */
  public List<Fish> getNextFrameInfo() {

    if (this.level.getFishCon().getShark().getSize() > GAME_WINNING_SIZE) {
      this.gui.setWinSceneTrue();
      this.gui.chooseScene();
    }
    return this.level.getNextFrameInfo();
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
}
