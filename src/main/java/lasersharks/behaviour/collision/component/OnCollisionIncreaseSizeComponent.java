package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

/**
 * Class for increasing size when collidiong.
 *
 * @author SEMGroup27
 */
public class OnCollisionIncreaseSizeComponent implements CollisionComponent {
  private final Displayable me;
  private final CollisionComponent next;

  /**
   * Constructor.
   *
   * @param me   Displayable object
   * @param next CollisionComponent
   */
  public OnCollisionIncreaseSizeComponent(final Displayable me, final CollisionComponent next) {
    this.me = me;
    this.next = next;
  }

  @Override
  public void handleCollision(final Displayable other) {
    me.increaseSize(other.onCollisionSizeIncrement());
    next.handleCollision(other);
  }

}