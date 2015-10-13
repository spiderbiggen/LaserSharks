package lasersharks;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ShootController implements EventHandler<KeyEvent>{

  private FishController fishController;
  
  public ShootController(FishController fishController) {
    super();
    this.fishController = fishController;
  }
  
  
  @Override
  public void handle(KeyEvent event) {
    if (event.getCode() == KeyCode.SPACE) {
      shoot();
    }
  }
  
  /**
   * shoots a laser.
   */
  public void shoot() {
    fishController.shootLaser();
  }

}
