package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionDestroyLaserComponentLeaf implements CollisionComponent {

  public OnCollisionDestroyLaserComponentLeaf(final CollisionComponent next) {
    //Empty
  }

  public void handleCollision(final Displayable other) {
    other.onCollisionDestroyLaser();
  }
  
}
