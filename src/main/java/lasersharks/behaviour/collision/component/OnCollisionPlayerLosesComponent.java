package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

/**
 * Class for when the player loses when colliding.
 * @author SEMGroup27
 *
 */
public class OnCollisionPlayerLosesComponent implements CollisionComponent {
  private Displayable me;
  private CollisionComponent next;

  /**
   * Constructor.
   * @param me Displayable object
   * @param next CollisionComponent
   */
  public OnCollisionPlayerLosesComponent(Displayable me, CollisionComponent next) {
    this.me = me;
    this.next = next;
  }

  @Override
  public void handleCollision(Displayable other) {
    other.onCollisionPlayerLoses(me.getSize());
    next.handleCollision(other);
  }

}