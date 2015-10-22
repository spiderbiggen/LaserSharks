package lasersharks.behaviour.collision;

import lasersharks.behaviour.CollisionBehaviour;
import lasersharks.interfaces.Displayable;

public class FishCollisionBehaviour implements CollisionBehaviour{
  Displayable object;
  
  /**
   * Constructor
   * @param me this.
   */
  public FishCollisionBehaviour(Displayable me) {
    super();
    this.object = me;
  }

  @Override
  public void colideWith(Displayable other) {
    object.onCollisionSizeDecrement(other.getOnCollisionSizeDecrement());
    other.onCollisionDestroyLaser();
  }
  
}
