package lasersharks;

import lasersharks.gui.LevelGUI;

/**
 * Class for starting the game.
 * @author Youri
 *
 */
public class Game {
  
  /**
   * Launch game.
   */
  public void launch(LevelGUI gui) {
    Level level = new Level(this, gui);
    level.launch();
  }

}
