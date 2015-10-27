package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionPlayerLosesComponent implements CollisionComponent {
  private final Displayable me;
  private final CollisionComponent next;

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