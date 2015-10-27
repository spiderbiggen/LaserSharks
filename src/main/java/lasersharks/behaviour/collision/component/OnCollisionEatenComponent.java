package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionEatenComponent implements CollisionComponent {

  private final CollisionComponent next;

  public OnCollisionEatenComponent(final CollisionComponent next) {
    this.next = next;
  }

  public void handleCollision(final Displayable other) {
    other.onCollisionEaten();
    next.handleCollision(other);
  }
}
