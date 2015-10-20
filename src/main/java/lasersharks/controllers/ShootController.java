package lasersharks.controllers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * The shootcontroller is responsible for handling the keyEvents that has to do with shooting.
 * 
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class ShootController implements EventHandler<KeyEvent> {

  private FishController fishController;
  private boolean pressedSpace;

  /**
   * The constructor of the class.
   * 
   * @param fishController
   *          the fishController that this eventHandler is linked to.
   */
  public ShootController(FishController fishController) {
    super();
    this.fishController = fishController;
    pressedSpace = false;
  }

  @Override
  public void handle(KeyEvent event) {
    if (event.getCode() == KeyCode.L) {
      if (event.getEventType() == KeyEvent.KEY_PRESSED && !pressedSpace) {
        shoot();
        pressedSpace = true;
      }
      if (event.getEventType() == KeyEvent.KEY_RELEASED && pressedSpace) {
        pressedSpace = false;
      }
    }
  }

  /**
   * orders the fishController to create a laser at the shark.
   */
  public void shoot() {
    fishController.shootLaser();
  }

}
