package lasersharks;

import java.io.IOException;
import java.util.List;

import javafx.scene.Scene;
import lasersharksgui.LevelGUI;

/**
 * This is the class that will manage the screen.
 * 
 * @author Youri
 *
 */
@SuppressWarnings("restriction")
public class ScreenController {
  private LevelGUI gui;
  private Scene scene;
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
  public ScreenController(FishController fishCon, LevelGUI gui) {
    super();
    this.fishCon = fishCon;
    this.gui = gui;
    this.gui.setScreenController(this);
    this.scene = gui.getScene();
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
      this.gui.setLoseSceneTrue();
      this.gui.chooseScene();
    } else if (this.fishCon.getShark().getSize() > GAME_WINNING_SIZE) {
      this.gui.setWinSceneTrue();
      this.gui.chooseScene();
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
  
  /**
   * Restart the game.
   * @throws IOException 
   */
  public void restart() throws IOException {
    
    this.gui.stopAnimation();
    this.gui.restartGame();    
    this.fishCon.getShark().setAlive();
    this.gui.setPlaySceneTrue();
    this.gui.chooseScene();
    this.fishCon.clearFish();
    this.fishCon.getShark().setSize(fishCon.getStartSize());
    this.gui.setScore(0);
  }

}
