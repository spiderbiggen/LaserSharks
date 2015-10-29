package lasersharks.behaviour.ammunitionincrement;

import lasersharks.behaviour.AmmunitionIncrementBehaviour;

/**
 * Default increment behaviour for Ammunition.
 *
 * @author SEMGroup27
 */
public class DefaultAmmunitionIncrementBehaviour implements AmmunitionIncrementBehaviour {

  /**
   * @return the amount by which to increment the ammunition.
   */
  @Override
  public int onCollisionAmmunitionIncrement() {
    return 0;
  }
}
