package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionEatenComponentLeaf implements CollisionComponent {
  private CollisionComponent next;

  public OnCollisionEatenComponentLeaf(CollisionComponent next) {
    this.next = next;
  }
  
  
  public void handleCollision(Displayable other) {
    other.onCollisionEaten();
  }
}
