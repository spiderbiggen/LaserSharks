package lasersharks.behaviour;

/**
 * Interface which let a Displayable object increase the player high score on collision.
 * @author SEMGroup27
 */
public interface HighScoreIncrementBehaviour {

  /**
   * Call method which returns the increment value.
   * @return highscoreIncrement.
   */
  int onCollisionHighScoreIncrement();
}
