package lasersharks.behaviour;

/**
 * Interface wich let displaybe object increase the playerhighscore on collision.
 * @author Youri
 *
 */
public interface HighScoreIncrementBehaviour {
  /**
   * Call method wich returns the increment value.
   * @param timePenalty pennalty from timelimit.
   * @return timeIncrement.
   */
  int onCollisionHighScoreIncrement(int timePenalty);
}
