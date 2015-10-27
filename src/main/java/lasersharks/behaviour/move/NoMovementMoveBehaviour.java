package lasersharks.behaviour.move;

import lasersharks.behaviour.MoveBehaviour;

/**
 * Represents behaviour of a displayable that can't move.
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
   * does nothing, as this behaviour specifies a displayable that cant move.
   *
   * @param frameTime
   *          scalar based on the time elapsed since the last move
   */
  @Override public boolean move(double frameTime) {
    return true;
  }

}
