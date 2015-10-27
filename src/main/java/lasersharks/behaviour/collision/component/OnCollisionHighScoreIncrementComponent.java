package lasersharks.behaviour.collision.component;

import lasersharks.HighScores;
import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionHighScoreIncrementComponent implements CollisionComponent {
  private CollisionComponent next;

  public OnCollisionHighScoreIncrementComponent(CollisionComponent next) {
    this.next = next;
  }

  @Override
  public void handleCollision(Displayable other) {
    HighScores.getInstance().increaseScore(
        other.getOnCollisionHighScoreIncrement(HighScores.getInstance().getTimePenalty()));
    next.handleCollision(other);
  }

}