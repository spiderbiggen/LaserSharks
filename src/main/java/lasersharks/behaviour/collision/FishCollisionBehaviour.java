package lasersharks.behaviour.collision;

import lasersharks.interfaces.Displayable;

/**
 * The class for the collisionBehavior of the FishBots.
 * 
 * @author SEMgroup27
 *
 */
public class FishCollisionBehaviour extends AbstractCollisionBehaviour {
  private Displayable object;

  /**
   * Constructor.
   * 
   * @param me
   *          this.
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
