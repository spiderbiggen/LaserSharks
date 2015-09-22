package lasersharks;

import java.io.IOException;
import java.util.List;

import javafx.scene.Scene;
import lasersharksgui.GamePane;
import lasersharksgui.LevelGUI;
import lasersharksgui.LosingPane;
import lasersharksgui.MainGui;
import lasersharksgui.StandardPane;
import lasersharksgui.WinPane;

/**
 * This is the class that will manage the screen.
 * 
 * @author Youri
 *
 */
@SuppressWarnings("restriction")
public class ScreenController {
  private GamePane currentPane;
  private FishController fishCon;
  private static final int GAME_WINNING_SIZE = 320;

  /**
   * Constructor.
   * 
   * @param fishCon
   *          the fishController from wich to receive data.
   * @param gui
   *          pointer to the active gui.
   */
  public ScreenController(GamePane pane) {
    super();
    this.fishCon = new FishController();
    this.currentPane = pane;
    this.currentPane.setScreenController(this);
  }

  /**
   * Get information for next frame and checks if the shark is bigger than the winning size.
   * 
   * @param frametime
   *          time between frames in seconds
   * 
   * @return FishInfo
   * @throws IOException 
   */
  public List<Fish> getNextFrameInfo(double frametime) throws IOException {
    if (!this.fishCon.getShark().isAlive()) {
      MainGui.browseTo(LosingPane.class);
    } else if (this.fishCon.getShark().getSize() > GAME_WINNING_SIZE) {
      MainGui.browseTo(WinPane.class);
    }
    return this.fishCon.getNextCycleInformation(frametime);
  }

  /**
   * get the shark from the level.
   * 
   * @return the shark
   */
  public LaserShark getShark() {
    return this.fishCon.getShark();
  }

  /**
   * Propagation function for starting the game.
   */
  public void start() {
    this.currentPane.startGame();
  }
}
