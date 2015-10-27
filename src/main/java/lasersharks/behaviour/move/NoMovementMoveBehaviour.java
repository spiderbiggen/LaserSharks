package lasersharks.behaviour.move;

import lasersharks.behaviour.MoveBehaviour;

/**
 * Represents behaviour of a Displayable that can't move.
 * 
 * @author SEMGroup27
 *
 */
public class NoMovementMoveBehaviour implements MoveBehaviour {

  /**
   * The constructor of the behaviour object.
   */
  public NoMovementMoveBehaviour() {
  }

  /**
   * Does nothing, as this behaviour specifies a Displayable that cannot move.
   *
   * @param frameTime
   *          scalar based on the time elapsed since the last move
   */
  @Override public boolean move(double frameTime) {
    return true;
  }

}
