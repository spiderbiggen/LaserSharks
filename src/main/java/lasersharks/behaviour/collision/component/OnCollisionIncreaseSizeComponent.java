package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

/**
 * Class for increasing size when collidiong.
 * @author SEMGroup27
 *
 */
public class OnCollisionIncreaseSizeComponent implements CollisionComponent {
  private Displayable me;
  private CollisionComponent next;

  /**
   * Constructor.
   * @param me Displayable object
   * @param next CollisionComponent
   */
  public OnCollisionIncreaseSizeComponent(Displayable me, CollisionComponent next) {
    this.me = me;
    this.next = next;
  }

  @Override
  public void handleCollision(Displayable other) {
    me.increaseSize(other.onCollisionSizeIncrement());
    next.handleCollision(other);
  }

}