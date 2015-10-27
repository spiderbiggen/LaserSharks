package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionIncreaseSizeComponent implements CollisionComponent {
  private final Displayable me;
  private final CollisionComponent next;

  public OnCollisionIncreaseSizeComponent(final Displayable me, final CollisionComponent next) {
    this.me = me;
    this.next = next;
  }

  @Override public void handleCollision(final Displayable other) {
    me.increaseSize(other.onCollisionSizeIncrement());
    next.handleCollision(other);
  }

}