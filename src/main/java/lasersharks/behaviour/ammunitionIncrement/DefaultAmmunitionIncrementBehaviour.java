package lasersharks.behaviour.ammunitionIncrement;

import lasersharks.behaviour.AmmunitionIncrementBehaviour;

/**
 * Default increment behaviour for Ammunition.
 * @author SEMGroup27
 *
 */
public class DefaultAmmunitionIncrementBehaviour implements AmmunitionIncrementBehaviour {
  @Override
  public int onCollisionAmmunitionIncrement() {
    return 0;
  }
}
