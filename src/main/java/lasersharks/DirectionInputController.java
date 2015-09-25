package lasersharks;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

/**
 * Controller for handling the inputs to move the shark.
 * 
 * @author Stefan
 *
 */
@SuppressWarnings("restriction")
public class DirectionInputController implements EventHandler<KeyEvent> {

  private DirectionCallback callback;
  private Scene scene;

  private boolean pressedUp;
  private boolean pressedDown;
  private boolean pressedLeft;
  private boolean pressedRight;

  /**
   * Constructor.
   * 
   * @param fishCon
   *          callback
   */
  public DirectionInputController(ScreenController screenCon, DirectionCallback fishCon) {
    this.scene = screenCon.getGlobalScene();
    this.callback = fishCon;
  
    scene.addEventHandler(KeyEvent.ANY, this);
  }
  /**
   * Will handle the actuation of keypresses.
   * 
   * @param event
   *          the event in which a key is actuated
   * @return true if and only if the key is properly handled
   */
  private boolean keyPressed(KeyEvent event) {
    boolean handled = false;
    switch (event.getCode()) {
      case UP:
      case W:
        pressedUp = true;
        handled = true;
        break;
      case DOWN:
      case S:
        pressedDown = true;
        handled = true;
        break;
      case LEFT:
      case A:
        pressedLeft = true;
        handled = true;
        break;
      case RIGHT:
      case D:
        pressedRight = true;
        handled = true;
        break;
      default:
        break;
    }
    return handled;
  }

  /**
   * Will handle the release of keypresses.
   * 
   * @param event
   *          the event in which a key is released
   * @return true if and only if the key is properly handled
   */
  private boolean keyReleased(KeyEvent event) {
    boolean handled = false;
    switch (event.getCode()) {
      case UP:
      case W:
        pressedUp = false;
        handled = true;
        break;
      case DOWN:
      case S:
        pressedDown = false;
        handled = true;
        break;
      case LEFT:
      case A:
        pressedLeft = false;
        handled = true;
        break;
      case RIGHT:
      case D:
        pressedRight = false;
        handled = true;
        break;
      default:
        break;
    }
    return handled;
  }

  /**
   * Will parse the actions corresponding to the current key presses.
   */
  private void handleInput() {
    Direction dir = Direction.None;

    if (pressedDown && !pressedUp) {
      if (pressedLeft && !pressedRight) {
        dir = Direction.NorthWest;
      } else if (pressedRight && !pressedLeft) {
        dir = Direction.NorthEast;
      } else {
        dir = Direction.North;
      }
    } else if (pressedUp && !pressedDown) {
      if (pressedLeft && !pressedRight) {
        dir = Direction.SouthWest;
      } else if (pressedRight && !pressedLeft) {
        dir = Direction.SouthEast;
      } else {
        dir = Direction.South;
      }
    } else if (pressedLeft && !pressedRight) {
      dir = Direction.West;
    } else if (pressedRight && !pressedLeft) {
      dir = Direction.East;
    }

    this.callback.putDirection(dir);
  }

  @Override
  public void handle(KeyEvent event) {
    boolean handled = false;
    if (event.getEventType() == KeyEvent.KEY_PRESSED) {
      handled = keyPressed(event);
    } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
      handled = keyReleased(event);
    }
    if (handled) {
      handleInput();
    }
  }
}
