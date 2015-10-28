package lasersharks.behaviour;

/**
 * Behaviour to increase the ammo.
 */
public interface AmmunitionIncrementBehaviour {
  /**
   * @return the amount to increase the ammo by.
   */
  int onCollisionAmmunitionIncrement();
}
