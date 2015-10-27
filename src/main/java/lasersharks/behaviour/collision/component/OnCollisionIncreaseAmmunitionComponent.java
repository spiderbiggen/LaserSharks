package lasersharks.behaviour.collision.component;

import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionIncreaseAmmunitionComponent implements CollisionComponent {
  private final Displayable me;
  private final CollisionComponent next;

  public OnCollisionIncreaseAmmunitionComponent(final Displayable me,
      final CollisionComponent next) {
    this.me = me;
    this.next = next;
  }

  @Override
  public void handleCollision(final Displayable other) {
    me.increaseAmmunition(other.onCollisionAmmunitionIncrement());
    next.handleCollision(other);
  }

}