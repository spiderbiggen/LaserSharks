package lasersharks.behaviour.collision.component;

import lasersharks.HighScores;
import lasersharks.interfaces.CollisionComponent;
import lasersharks.interfaces.Displayable;

/**
 * Class for highscore increment when colliding.
 *
 * @author SEMGroup27
 */
public class OnCollisionHighScoreIncrementComponent implements CollisionComponent {
  private final CollisionComponent next;

  /**
   * Constructor.
   *
   * @param next CollisionComponent
   */
  public OnCollisionHighScoreIncrementComponent(final CollisionComponent next) {
    this.next = next;
  }

  @Override
  public void handleCollision(final Displayable other) {
    HighScores.getInstance().increaseScore(
        other.getOnCollisionHighScoreIncrement());
    next.handleCollision(other);
  }

}