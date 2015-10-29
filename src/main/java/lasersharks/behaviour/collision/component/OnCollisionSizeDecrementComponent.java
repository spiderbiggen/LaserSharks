package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

/**
 * Class for size decrement for collisions.
 *
 * @author SEMGroup27
 */
public class OnCollisionSizeDecrementComponent implements CollisionComponent {
  private final Displayable me;
  private final CollisionComponent next;

  /**
   * Constructor.
   *
   * @param me   Displayable object
   * @param next CollisionComponent
   */
  public OnCollisionSizeDecrementComponent(final Displayable me, final CollisionComponent next) {
    this.me = me;
    this.next = next;
  }

  @Override
  public void handleCollision(final Displayable other) {
    me.onCollisionSizeDecrement(other.getOnCollisionSizeDecrement());
    next.handleCollision(other);
  }

}