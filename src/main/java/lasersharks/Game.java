package lasersharks;

/**
 * Class for starting the game.
 * @author Youri
 *
 */
public class Game {
  /**
   * @param args parameters to influence the startup of this game
   */
  public static void main(String[] args) {
    Game g = new Game();
    g.launch();
  }

  /**
   * Launch game.
   */
  private void launch() {
    Level level = new Level(this);
    level.launch();
  }

}
