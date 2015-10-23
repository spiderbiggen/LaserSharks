package lasersharks.behaviour.collision;

import lasersharks.behaviour.CollisionBehaviour;
import lasersharks.behaviour.collision.component.DefaultComponentLeaf;
import lasersharks.behaviour.collision.component.OnCollisionDestroyLaserComponentLeaf;
import lasersharks.behaviour.collision.component.OnCollisionSizeDecrementComponent;
import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

/**
 * The class for the collisionBehavior of the FishBots.
 * 
 * @author SEMgroup27
 *
 */
public class FishCollisionBehaviour implements CollisionBehaviour {
  private CollisionComponent handler;

  /**
   * Constructor.
   * 
   * @param me
   *          this.
   */
  public FishCollisionBehaviour(Displayable me) {
    super();
    this.handler = 
        new OnCollisionSizeDecrementComponent(me, 
        new OnCollisionDestroyLaserComponentLeaf(
        new DefaultComponentLeaf()));
  }

  @Override
  public void colideWith(Displayable other) {
    this.handler.handleCollision(other);
  }

}
