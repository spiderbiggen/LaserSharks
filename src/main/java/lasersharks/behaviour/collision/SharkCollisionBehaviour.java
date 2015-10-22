package lasersharks.behaviour.collision;

import lasersharks.behaviour.interfaces.CollisionBehaviour;
import lasersharks.interfaces.Displayable;

public class SharkCollisionBehaviour implements CollisionBehaviour{
  Displayable object;
  
  /**
   * Constructor.
   * @param me this.
   */
  public SharkCollisionBehaviour(Displayable me) {
    super();
    this.object = me;
  }

  @Override
  public synchronized void colideWith(Displayable other) {
    other.onCollisionPlayerLoses(object.getSize());
    object.increaseSize(other.onCollisionSizeIncrement());
    object.increaseAmmunition(other.onCollisionAmmunitionIncrement());
    other.onCollisionEaten();
  }
  
}
