package lasersharks.behaviour.collision.component;

import lasersharks.Highscores;
import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

public class OnCollisionHighScoreIncrementComponent implements CollisionComponent {
  private CollisionComponent next;

  public OnCollisionHighScoreIncrementComponent(CollisionComponent next) {
    this.next = next;
  }

  @Override
  public void handleCollision(Displayable other) {
    Highscores.getInstance().increaseScore(
        other.getOnCollisionHighScoreIncrement(Highscores.getInstance().getTimePenalty()));
    next.handleCollision(other);
  }

}