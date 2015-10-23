package lasersharks.behaviour.collision;

import lasersharks.interfaces.Displayable;

public interface CollisionComponent {
  void handleCollision(Displayable other);
}