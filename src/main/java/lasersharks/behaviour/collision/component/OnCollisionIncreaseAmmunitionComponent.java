package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionIncreaseAmmunitionComponent implements CollisionComponent {
  private Displayable me;
  private CollisionComponent next;

  public OnCollisionIncreaseAmmunitionComponent(Displayable me, CollisionComponent next) {
    this.me = me;
    this.next = next;
  }

  @Override
  public void handleCollision(Displayable other) {
    me.increaseAmmunition(other.onCollisionAmmunitionIncrement());
    next.handleCollision(other);
  }

}