package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

/**
 * Class for when the player loses when colliding.
 *
 * @author SEMGroup27
 */
public class OnCollisionPlayerLosesComponent implements CollisionComponent {
  private final Displayable me;
  private final CollisionComponent next;

  /**
   * Constructor.
   *
   * @param me   Displayable object
   * @param next CollisionComponent
   */
  public OnCollisionPlayerLosesComponent(final Displayable me, final CollisionComponent next) {
    this.me = me;
    this.next = next;
  }

  @Override
  public void handleCollision(final Displayable other) {
    other.onCollisionPlayerLoses(me.getSize());
    next.handleCollision(other);
  }

}