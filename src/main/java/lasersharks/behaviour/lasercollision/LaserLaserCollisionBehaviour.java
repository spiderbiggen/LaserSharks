package lasersharks.behaviour.lasercollision;

import lasersharks.behaviour.LaserCollisionBehaviour;
import lasersharks.interfaces.Displayable;

/**
 * Eaten behavior for Fishes.
 * 
 * @author SEMGroup27
 *
 */
public class LaserLaserCollisionBehaviour implements LaserCollisionBehaviour {
  private final Displayable element;

  /**
   * Constructor.
   * 
   * @param element
   *          this.
   */
  public LaserLaserCollisionBehaviour(final Displayable element) {
    super();
    this.element = element;
  }

  @Override
  public void onCollisionDestroyLaser() {
    element.kill();
  }

}
