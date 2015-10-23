package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionPlayerLosesComponent implements CollisionComponent {
  private Displayable me;
  private CollisionComponent next;

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