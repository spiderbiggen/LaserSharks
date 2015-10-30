package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;
/**
 * Class for propagation on collision, this part propagates the eaten event.
 * @author SEMGroup27
 *
 */
public class OnCollisionEatenComponent implements CollisionComponent {

  private final CollisionComponent next;

  /**
   * Constructor.
   * @param next component to be called after this one.
   */
  public OnCollisionEatenComponent(final CollisionComponent next) {
    this.next = next;
  }

  /**
   * Handle the collision.
   * @param other The element on with wich the component is interacting.
   */
  public void handleCollision(final Displayable other) {
    other.onCollisionEaten();
    next.handleCollision(other);
  }
}
