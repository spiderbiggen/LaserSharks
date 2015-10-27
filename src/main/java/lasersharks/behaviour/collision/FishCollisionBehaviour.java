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
  private final CollisionComponent handler;

  /**
   * Constructor.
   * 
   * @param me
   *          this.
   */
  public FishCollisionBehaviour(final Displayable me) {
    super();
    this.handler = 
        new OnCollisionSizeDecrementComponent(me, 
        new OnCollisionDestroyLaserComponentLeaf(
        new DefaultComponentLeaf()));
  }

  @Override public void collideWith(final Displayable other) {
    this.handler.handleCollision(other);
  }

}
