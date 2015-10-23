package lasersharks.behaviour.collision;

import lasersharks.behaviour.collision.component.DefaultCompomnentLeaf;
import lasersharks.behaviour.collision.component.OnCollisionDestroyLaserCompomnentLeaf;
import lasersharks.behaviour.collision.component.OnCollisionSizeDecrementComponent;
import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

/**
 * The class for the collisionBehavior of the FishBots.
 * 
 * @author SEMgroup27
 *
 */
public class FishCollisionBehaviour extends AbstractCollisionBehaviour {
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
        new OnCollisionDestroyLaserCompomnentLeaf(
        new DefaultCompomnentLeaf()));
  }

  @Override
  public void colideWith(Displayable other) {
    this.handler.handleCollision(other);
  }

}
