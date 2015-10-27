package lasersharks.behaviour.collision.component;

import lasersharks.HighScores;
import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionHighScoreIncrementComponent implements CollisionComponent {
  private final CollisionComponent next;

  public OnCollisionHighScoreIncrementComponent(final CollisionComponent next) {
    this.next = next;
  }

  @Override public void handleCollision(final Displayable other) {
    HighScores.getInstance().increaseScore(
        other.getOnCollisionHighScoreIncrement(HighScores.getInstance().getTimePenalty()));
    next.handleCollision(other);
  }

}