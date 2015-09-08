package lasersharks;

import javafx.scene.Scene;
import lasersharks.gui.LevelGUI;

/**
 * This is the class that will manage the screen.
 * 
 * @author Youri
 *
 */
@SuppressWarnings("restriction")
public class ScreenController extends Thread {
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
    this.scene = gui.getScene();
  }

  /**
   * Executable part of the Screencontroler, forwards fishdata to the gui.
   */
  public void run() {
    while (this.running) {
      try {
        this.gui.showFishList(this.level.getNextFrameInfo());
        sleep(FRAME_DELAY);
      } catch (InterruptedException e) { }
    }
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
}
