package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionEatenComponentLeaf implements CollisionComponent {

  public OnCollisionEatenComponentLeaf(CollisionComponent next) {
  }
  
  
  public void handleCollision(Displayable other) {
    other.onCollisionEaten();
  }
}
