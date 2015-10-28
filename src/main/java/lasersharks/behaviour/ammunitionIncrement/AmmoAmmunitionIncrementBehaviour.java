package lasersharks.behaviour.ammunitionIncrement;

import lasersharks.behaviour.AmmunitionIncrementBehaviour;
import lasersharks.controllers.AudioController;

/**
 * Ammunition increment behaviour for Ammunition.
 * 
 * @author SEMGroup27
 *
 */
public class AmmoAmmunitionIncrementBehaviour implements AmmunitionIncrementBehaviour {
  /**
   * The amount by which to increase the ammo.
   */
  private static final int AMMUNITION_SIZE = 10;

  @Override
  public synchronized int onCollisionAmmunitionIncrement() {
    AudioController.getInstance().playPickupSoundEffect();
    return AMMUNITION_SIZE;
  }
}
