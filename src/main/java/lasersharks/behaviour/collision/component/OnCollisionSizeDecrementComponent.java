package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

/**
 * Class for size decrement for collisions.
 * @author SEMGroup27
 *
 */
public class OnCollisionSizeDecrementComponent implements CollisionComponent {
  private Displayable me;
  private CollisionComponent next;

  /**
   * Constructor.
   * 
   * @param me Displayable object
   * @param next CollisionComponent
   */
  public OnCollisionSizeDecrementComponent(Displayable me, CollisionComponent next) {
    this.me = me;
    this.next = next;
  }

  @Override
  public void handleCollision(Displayable other) {
    me.onCollisionSizeDecrement(other.getOnCollisionSizeDecrement());
    next.handleCollision(other);
  }

}