package lasersharks.behaviour.lasercollision;

import lasersharks.behaviour.interfaces.LaserCollisionBehaviour;

/**
 * Eaten behaviour for Fishes.
 * @author SEMGroup27
 *
 */
public class FishLaserCollisionBehaviour implements LaserCollisionBehaviour {

  @Override
  public boolean onCollisionDestroyLaser() {
    return false;
  }

}
