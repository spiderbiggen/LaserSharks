package lasersharks.controllers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * The shootcontroller is responsible for handling the keyEvents that have to do with shooting.
 *
 * @author SEMGroup27
 */
@SuppressWarnings("restriction")
public class ShootController implements EventHandler<KeyEvent> {

  private final FishController fishController;

  /**
   * The constructor of the class.
   *
   * @param fishController the fishController that this eventHandler is linked to.
   */
  public ShootController(final FishController fishController) {
    super();
    this.fishController = fishController;
  }

  @Override
  public void handle(KeyEvent event) {
    if (event.getCode() == KeyCode.SPACE && event.getEventType() == KeyEvent.KEY_PRESSED) {
      shoot();
    }
  }

  /**
   * orders the fishController to create a laser at the shark.
   */
  private void shoot() {
    fishController.shootLaser();
  }

}
