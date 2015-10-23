package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionSizeDecrementComponent implements CollisionComponent {
  private Displayable me;
  private CollisionComponent next;

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