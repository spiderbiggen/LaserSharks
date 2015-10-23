package lasersharks.behaviour.collision;

import lasersharks.interfaces.Displayable;

public class OnCollisionDestroyLaserCompomnent {
  public void handleCollision(Displayable other) {
    other.onCollisionDestroyLaser();
  }
}
