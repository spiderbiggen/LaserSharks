package lasersharks.behaviour.ammunitionIncrement;

import lasersharks.behaviour.AmmunitionIncrementBehaviour;

/**
 * Ammunition increment behaviour for Ammunition.
 * @author SEMGroup27
 *
 */
public class AmmoAmmunitionIncrementBehaviour implements AmmunitionIncrementBehaviour {
  private static final int AMMUNITION_SIZE = 10;

  @Override
  public synchronized int onCollisionAmmunitionIncrement() {
    return AMMUNITION_SIZE;
  }
}
