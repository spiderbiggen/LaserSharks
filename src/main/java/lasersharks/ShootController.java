package lasersharks;

import java.awt.Event;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ShootController implements EventHandler<KeyEvent>{

  private FishController fishController;
  private boolean pressedSpace; 
  
  
  public ShootController(FishController fishController) {
    super();
    this.fishController = fishController;
    pressedSpace = false;   
  }
  
  
  
  @Override
  public void handle(KeyEvent event) {    
    if (event.getCode() == KeyCode.SPACE) {
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
   * shoots a laser.
   */
  public void shoot() {
    fishController.shootLaser();
  }

}
