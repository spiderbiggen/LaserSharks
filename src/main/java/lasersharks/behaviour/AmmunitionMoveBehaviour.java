package lasersharks.behaviour;

import lasersharks.behaviour.interfaces.MoveBehaviour;

/**
 * Represents behaviour of a displayable that can't move.
 * @author SEMGroup27
 *
 */
public class AmmunitionMoveBehaviour implements MoveBehaviour {

  /**
   * The constructor of the behaviour object.
   */
  public AmmunitionMoveBehaviour() {
  }

  /**
   * does nothing, as this behavour specifies a displayable that cant move.
   * @param fish the fish it should eat.
   */
  @Override
  public boolean move(double frametime) {
    return true;
  }

}
