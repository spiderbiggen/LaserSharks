package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionDestroyLaserComponent implements CollisionComponent {

  private final CollisionComponent next;

  public OnCollisionDestroyLaserComponent(final CollisionComponent next) {
    this.next = next;
  }

  public void handleCollision(final Displayable other) {
    other.onCollisionDestroyLaser();
    other.onCollisionDestroyLaser();
    next.handleCollision(other);
  }

}
