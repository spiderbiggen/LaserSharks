package lasersharks.behaviour.collision;

import lasersharks.behaviour.collision.interfaces.AmmunitionIncrementBehaviour;

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
