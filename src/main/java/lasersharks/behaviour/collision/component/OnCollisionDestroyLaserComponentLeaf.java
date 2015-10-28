package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

/**
 * Class for destroying a laser component.
 * @author SEMGroup27
 *
 */
public class OnCollisionDestroyLaserComponentLeaf implements CollisionComponent {

  /**
   * Constructor.
   * @param next CollisionComponent 
   */
  public OnCollisionDestroyLaserComponentLeaf(CollisionComponent next) {
  }
  
  /**
   * Method for handling collisions.
   * @param other Displayable object
   */
  public void handleCollision(Displayable other) {
    other.onCollisionDestroyLaser();
  }
  
}
