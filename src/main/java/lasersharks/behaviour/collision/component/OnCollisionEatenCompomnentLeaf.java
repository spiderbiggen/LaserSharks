package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionEatenCompomnentLeaf implements CollisionComponent {
  private CollisionComponent next;

  public OnCollisionEatenCompomnentLeaf(CollisionComponent next) {
    this.next = next;
  }
  
  
  public void handleCollision(Displayable other) {
    other.onCollisionEaten();
  }
}
