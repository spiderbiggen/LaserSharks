package lasersharks.controllers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import lasersharks.Direction;
import lasersharks.interfaces.DirectionCallback;

/**
 * Controller for handling the inputs to move the shark.
 * 
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class DirectionInputController implements EventHandler<KeyEvent> {

  private DirectionCallback callback;
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
  public DirectionInputController(DirectionCallback fishCon) {
    this.callback = fishCon;
  }

  /**
   * Will handle the actuation of key presses.
   * 
   * @param event
   *          the event in which a key is actuated
   * @return true if and only if the key is properly handled
   */
  private boolean keyPressed(KeyEvent event) {
    return keySwitch(event, true);
  }

  /**
   * Will handle the release of key presses.
   * 
   * @param event
   *          the event in which a key is released
   * @return true if and only if the key is properly handled
   */
  private boolean keyReleased(KeyEvent event) {
    return keySwitch(event, false);
  }

  /**
   * Will handle a key event.
   * 
   * @param event
   *          the event in which a key is released
   * @param pressed
   *          boolean for key pressed and key released
   * @return handled
   */
  private boolean keySwitch(KeyEvent event, boolean pressed) {
    boolean handled = false;
    switch (event.getCode()) {
      case UP:
      case W:
        pressedUp = pressed;
        handled = true;
        break;
      case DOWN:
      case S:
        pressedDown = pressed;
        handled = true;
        break;
      case LEFT:
      case A:
        pressedLeft = pressed;
        handled = true;
        break;
      case RIGHT:
      case D:
        pressedRight = pressed;
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
        dir = Direction.SouthWest;
      } else if (pressedRight && !pressedLeft) {
        dir = Direction.SouthEast;
      } else {
        dir = Direction.South;
      }
    } else if (pressedUp && !pressedDown) {
      if (pressedLeft && !pressedRight) {
        dir = Direction.NorthWest;
      } else if (pressedRight && !pressedLeft) {
        dir = Direction.NorthEast;
      } else {
        dir = Direction.North;
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
