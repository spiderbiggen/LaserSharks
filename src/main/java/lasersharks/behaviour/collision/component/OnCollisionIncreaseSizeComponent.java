package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionIncreaseSizeComponent implements CollisionComponent {
  private Displayable me;
  private CollisionComponent next;

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