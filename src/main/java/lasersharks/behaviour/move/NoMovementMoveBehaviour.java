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
   * does nothing, as this behavour specifies a displayable that cant move.
   *
   * @param frametime
   *          time in seceonds since lat update
   */
  @Override public boolean move(final double frametime) {
    return true;
  }

}
