package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

/**
 * Class for eaten collisions.
 * @author SEMGroup27
 *
 */
public class OnCollisionEatenComponentLeaf implements CollisionComponent {

  /**
   * Constructor.
   * @param next CollisionComponent
   */
  public OnCollisionEatenComponentLeaf(CollisionComponent next) {
  }
  
  /**
   * Method for handling collisions.
   * @param other Displayable object
   */
  public void handleCollision(Displayable other) {
    other.onCollisionEaten();
  }
}
