package lasersharks.behaviour.collision;

import lasersharks.behaviour.CollisionBehaviour;
import lasersharks.behaviour.collision.component.DefaultComponent;
import lasersharks.behaviour.collision.component.OnCollisionEatenComponent;
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
  private final CollisionComponent handler;

  /**
   * Constructor.
   * 
   * @param me
   *          this.
   */
  public SharkCollisionBehaviour(final Displayable me) {
    super();
    this.handler =
        new OnCollisionPlayerLosesComponent(me,
            new OnCollisionIncreaseSizeComponent(me,
                new OnCollisionIncreaseAmmunitionComponent(me,
                    new OnCollisionHighScoreIncrementComponent(
                        new OnCollisionEatenComponent(
                            new DefaultComponent())))));
  }

  @Override
  public void collideWith(final Displayable other) {
    handler.handleCollision(other);
  }

}
