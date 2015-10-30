package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

/**
 * Class for propagation weather the laser ought to be destroyed.
 * @author SEMGroup27
 *
 */
public class OnCollisionDestroyLaserComponent implements CollisionComponent {

  private final CollisionComponent next;
  
  /**
   * Constructor.
   * @param next component to be called after this one.
   */
  public OnCollisionDestroyLaserComponent(final CollisionComponent next) {
    this.next = next;
  }
  
  /**
   * Handle the collision.
   * @param other The element on with wich the component is interacting.
   */
  public void handleCollision(final Displayable other) {
    other.onCollisionDestroyLaser();
    next.handleCollision(other);
  }

}
