package lasersharks.behaviour.collision;

import lasersharks.behaviour.collision.interfaces.LaserCollisionBehaviour;

/**
 * Eaten behaviour for Fishes.
 * @author SEMGroup27
 *
 */
public class DefaultLaserCollisionBehaviour implements LaserCollisionBehaviour {

  @Override
  public boolean onCollisionDestroyLaser() {
    return true;
  }

}
