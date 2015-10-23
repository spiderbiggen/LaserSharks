package lasersharks.behaviour.collision;

import lasersharks.Highscores;
import lasersharks.behaviour.CollisionBehaviour;
import lasersharks.behaviour.collision.component.DefaultCompomnentLeaf;
import lasersharks.behaviour.collision.component.OnCollisionEatenCompomnentLeaf;
import lasersharks.behaviour.collision.component.OnCollisionHighScoreIncrementComponent;
import lasersharks.behaviour.collision.component.OnCollisionIncreaseAmmunitionComponent;
import lasersharks.behaviour.collision.component.OnCollisionIncreaseSizeComponent;
import lasersharks.behaviour.collision.component.OnCollisionPlayerLosesComponent;
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
        new OnCollisionEatenCompomnentLeaf(
        new DefaultCompomnentLeaf())))));
  }

  @Override
  public synchronized void colideWith(Displayable other) {
    handler.handleCollision(other);
  }

}
