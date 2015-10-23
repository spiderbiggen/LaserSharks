package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionDestroyLaserComponentLeaf implements CollisionComponent {
  private CollisionComponent next;

  public OnCollisionDestroyLaserComponentLeaf(CollisionComponent next) {
    this.next = next;
  }
  
  public void handleCollision(Displayable other) {
    other.onCollisionDestroyLaser();
  }
  
}
