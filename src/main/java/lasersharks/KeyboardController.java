package lasersharks;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * @author Stefan
 *
 */
@SuppressWarnings("restriction")
public class KeyboardController implements EventHandler<KeyEvent> {
  private DirectionCallback callback;
  private Scene scene;

  private boolean pressedUp;
  private boolean pressedDown;
  private boolean pressedLeft;
  private boolean pressedRight;

  /**
   * Constructor.
   * 
   * @param screenCon
   *          scene holder
   * @param fishCon
   *          callback
   */
  public KeyboardController(ScreenController screenCon, DirectionCallback fishCon) {
    this.scene = screenCon.getScene();
    this.callback = fishCon;

    scene.addEventHandler(KeyEvent.ANY, this);
  }

  private void keyPressed(KeyEvent event) {
    if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
      pressedUp = true;
    }
    if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
      pressedDown = true;
    }
    if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
      pressedLeft = true;
    }
    if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
      pressedRight = true;
    }
  }

  private void keyReleased(KeyEvent event) {
    if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
      pressedUp = false;
    }
    if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
      pressedDown = false;
    }
    if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
      pressedLeft = false;
    }
    if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
      pressedRight = false;
    }
  }

  /**
   * Will pass the keyboard input on to the Level to set the sharks direction.
   */
  private void handleInput() {
    Direction dir = Direction.None;
    if (pressedDown) {
      if (pressedLeft && !pressedRight) {
        dir = Direction.NorthWest;
      } else if (pressedRight) {
        dir = Direction.NorthEast;
      } else {
        dir = Direction.North;
      }
    } else if (pressedUp) {
      if (pressedLeft && !pressedRight) {
        dir = Direction.SouthWest;
      } else if (pressedRight) {
        dir = Direction.SouthEast;
      } else {
        dir = Direction.South;
      }
    } else if (pressedLeft && !pressedRight) {
      dir = Direction.West;
    } else if (pressedRight) {
      dir = Direction.East;
    }
    callback.putDirection(dir);
  }

  @Override
  public void handle(KeyEvent event) {
    if (event.getEventType() == KeyEvent.KEY_PRESSED) {
      keyPressed(event);
    } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
      keyReleased(event);
    }
    handleInput();
  }
}
