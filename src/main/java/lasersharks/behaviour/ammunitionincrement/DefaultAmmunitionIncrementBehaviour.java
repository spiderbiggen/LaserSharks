package lasersharks.behaviour.ammunitionincrement;

import lasersharks.behaviour.AmmunitionIncrementBehaviour;

/**
 * Default increment behaviour for Ammunition.
 *
 * @author SEMGroup27
 */
public class DefaultAmmunitionIncrementBehaviour implements AmmunitionIncrementBehaviour {

  @Override
  public int onCollisionAmmunitionIncrement() {
    return 0;
  }
}
