package lasersharks.behaviour.lasercollision;

import lasersharks.behaviour.interfaces.LaserCollisionBehaviour;
import lasersharks.interfaces.Displayable;
import lasersharks.seaobjects.LaserBullet;

/**
 * Eaten behaviour for Fishes.
 * @author SEMGroup27
 *
 */
public class LaserLaserCollisionBehaviour implements LaserCollisionBehaviour {
  Displayable element;
  
  public LaserLaserCollisionBehaviour(Displayable element) {
    super();
    this.element = element;
  }

  @Override
  public void onCollisionDestroyLaser() {
    element.kill();
  }

}
