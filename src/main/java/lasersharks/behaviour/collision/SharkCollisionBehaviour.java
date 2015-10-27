package lasersharks.behaviour.collision;

import lasersharks.behaviour.CollisionBehaviour;
import lasersharks.behaviour.collision.component.OnCollisionPlayerLosesComponent;
import lasersharks.behaviour.collision.component.OnCollisionIncreaseSizeComponent;
import lasersharks.behaviour.collision.component.OnCollisionIncreaseAmmunitionComponent;
import lasersharks.behaviour.collision.component.DefaultComponentLeaf;
import lasersharks.behaviour.collision.component.OnCollisionHighScoreIncrementComponent;
import lasersharks.behaviour.collision.component.OnCollisionEatenComponentLeaf;
import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

/**
 * The class for the collisionBehavior of the LaserShark.
 * 
 * @author SEMgroup27
 *
 */
public class SharkCollisionBehaviour implements CollisionBehaviour {
  private CollisionComponent handler;

  /**
   * Constructor.
   * 
   * @param me
   *          this.
   */
  public SharkCollisionBehaviour(Displayable me) {
    super();
    this.handler = 
        new OnCollisionPlayerLosesComponent(me, 
        new OnCollisionIncreaseSizeComponent(me, 
        new OnCollisionIncreaseAmmunitionComponent(me,
        new OnCollisionHighScoreIncrementComponent(
        new OnCollisionEatenComponentLeaf(
        new DefaultComponentLeaf())))));
  }

  @Override
  public void collideWith(Displayable other) {
    handler.handleCollision(other);
  }

}
