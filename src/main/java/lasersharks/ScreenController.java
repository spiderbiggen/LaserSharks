package lasersharks;

import javafx.scene.Scene;
import lasersharks.gui.LevelGUI;

/**
 * This is the class that will manage the screen.
 * @author Youri
 *
 */
public class ScreenController extends Thread {
  private LevelGUI gui;
  private Level level;
  private Scene scene;
  
  /**
   * Constructor.
   * @param level
   */
  public ScreenController(Level level) {
    super();
    this.level = level;
    
    
    
    
    
  }
  
  
  public Scene getScene() {
    return this.scene;
  }
  
}
