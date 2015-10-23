package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionDestroyLaserCompomnentLeaf implements CollisionComponent {
  private CollisionComponent next;

  public OnCollisionDestroyLaserCompomnentLeaf(CollisionComponent next) {
    this.next = next;
  }
  
  public void handleCollision(Displayable other) {
    other.onCollisionDestroyLaser();
  }
  
}
