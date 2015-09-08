package lasersharks;

import lasersharks.gui.LevelGUI;
import javafx.scene.Scene;

/**
 * This is the class that will manage the screen.
 * 
 * @author Youri
 *
 */
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
  public ScreenController(Level level) {
    super();
    this.level = level;
    this.running = false;
  }

  /**
   * Executable part of the Screencontroler, forwards fishdata to the gui.
   */
  public void run() {
    while (this.running) {
      try {
        this.gui.drawFishes(this.level.getNextFrameInfo());
        sleep(FRAME_DELAY);
      } catch (InterruptedException e) { }
    }
  }
  
  /**
   * Pauze the game.
   */
  public void pauze() {
    this.running = false;
  }
  
  /**
   * Start / resume the game.
   */
  public void start() {
    this.running = true;
  }

  /**
   * get the scene from the gui.
   * @return
   */
  public Scene getScene() {
    return this.scene;
  }
}
