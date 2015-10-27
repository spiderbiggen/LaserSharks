package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionEatenComponentLeaf implements CollisionComponent {

  public OnCollisionEatenComponentLeaf(final CollisionComponent next) {
    //Empty
  }

  public void handleCollision(final Displayable other) {
    other.onCollisionEaten();
  }
}
