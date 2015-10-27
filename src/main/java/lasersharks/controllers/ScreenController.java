package lasersharks.controllers;

import javafx.scene.Scene;
import lasersharks.interfaces.Displayable;
import lasersharks.seaobjects.LaserShark;
import lasersharksgui.MainGui;
import lasersharksgui.panes.GamePane;
import lasersharksgui.panes.LosingPane;
import lasersharksgui.panes.WinPane;

import java.util.List;

/**
 * This is the class that will manage the screen.
 * 
 * @author SEMGroup27
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
   * @param pane
   *          the GamePane this {@link ScreenController} is set to
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
   * @param frameTime
   *          time between frames in seconds
   * 
   * @return FishInfo
   */
  public List<Displayable> getNextFrameInfo(double frameTime) {
    if (MainGui.getInstance().getCurrentPane() instanceof GamePane) {
      GamePane gamePane = (GamePane) MainGui.getInstance().getCurrentPane();
      if (!this.fishCon.getShark().isAlive()) {
        MainGui.browseToGlobal(LosingPane.class);
        gamePane.removeAllEventHandlers();
      } else if (this.fishCon.getShark().getSize() > GAME_WINNING_SIZE) {
        MainGui.browseToGlobal(WinPane.class);
        gamePane.removeAllEventHandlers();
      }
    }

    return this.fishCon.getNextCycleInformation(frameTime);
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

  /**
   * @return the scene used for this {@link ScreenController}.
   */
  public Scene getGlobalScene() {
    return MainGui.getInstance().getCurrentScene();
  }

  /**
   *
   * @return the fishController used for this {@link ScreenController}.
   */
  public FishController getFishController() {
    return fishCon;
  }

}
