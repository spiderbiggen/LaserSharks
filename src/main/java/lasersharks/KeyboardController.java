package lasersharks;

import javafx.scene.Scene;

/**
 * 
 * @author Youri
 *
 */
public class KeyboardController {
  private Level callback;
  private Scene scene;
  
  /**
   * Constructor.
   * @param screenCon scene holder
   * @param level callback
   */
  public KeyboardController(ScreenController screenCon, Level level) {
    this.scene = screenCon.getScene();
    this.callback = level;
  }
}
