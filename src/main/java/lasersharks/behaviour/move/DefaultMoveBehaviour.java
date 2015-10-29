package lasersharks.behaviour.move;

import lasersharks.behaviour.MoveBehaviour;
import lasersharks.interfaces.Displayable;

/**
 * Represents the moving behaviour of a Fish.
 * 
 * @author SEMGroup27
 *
 */
public class DefaultMoveBehaviour implements MoveBehaviour {

  private final Displayable displayable;

  /**
   * the constructor.
   *
   * @param displayable
   *          the fish this behaviour applies to.
   */
  public DefaultMoveBehaviour(final Displayable displayable) {
    this.displayable = displayable;
  }

  /**
   * moves the Fish in the right direction.
   *
   * @param frameTime
   *          the frame time of the screen.
   * @return true of the fish was able to move.
   */
  @Override
  public boolean move(final double frameTime) {
    return displayable.getPosition().updatePosition(displayable.getDirection(),
        displayable.getSpeed() / frameTime, displayable.getSize());
  }

}
