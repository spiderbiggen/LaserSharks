package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionDestroyLaserComponentLeaf implements CollisionComponent {

  public OnCollisionDestroyLaserComponentLeaf(CollisionComponent next) {
  }
  
  public void handleCollision(Displayable other) {
    other.onCollisionDestroyLaser();
  }
  
}
