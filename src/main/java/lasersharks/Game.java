package lasersharks;

import lasersharks.gui.LevelGUI;

/**
 * Class for starting the game.
 * @author Youri
 *
 */
public class Game {
  
  private Level level;
  
  /**
   * Launch game.
   * @param gui the pointer to the active gui.
   */
  public void launch(LevelGUI gui) {
    this.level = new Level(this, gui);
    level.launch();
  }
  
  /**
   * Sets the level of the game.
   * @param level the level of the game.
   */
  public void setLevel(Level level) {
    this.level = level;
  }
  
  /**
   * Gets the level of the game.
   * @return the level.
   */
  public Level getLevel() {
    return level;
  }
  

}
