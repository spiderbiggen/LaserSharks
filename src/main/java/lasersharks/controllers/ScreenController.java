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

  private final GamePane currentPane;
  private final FishController fishCon;
  private static final int GAME_WINNING_SIZE = 400;

  /**
   * Constructor.
   * 
   * @param pane
   *          the GamePane this screencontroller is set to
   */
  public ScreenController(final GamePane pane) {
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
   */
  public List<Displayable> getNextFrameInfo(double frametime) {
      if (!this.fishCon.getShark().isAlive()) {
        MainGui.browseToGlobal(LosingPane.class);
      } else if (this.fishCon.getShark().getSize() > GAME_WINNING_SIZE) {
        MainGui.browseToGlobal(WinPane.class);
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

  /**
   * @return the scene used for this screencontroller.
   */
  public Scene getGlobalScene() {
    return MainGui.getInstance().getCurrentScene();
  }

  /**
   * 
   * @return the fishcontroller used for this screencontroller.
   */
  public FishController getFishController() {
    return fishCon;
  }

}
