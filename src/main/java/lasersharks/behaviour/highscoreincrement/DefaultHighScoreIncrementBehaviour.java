package lasersharks.behaviour.highscoreincrement;

import lasersharks.behaviour.HighScoreIncrementBehaviour;

/**
 * High score Increment behaviour for SeaObjects.
 *
 * @author SEMGroup27
 */
public class DefaultHighScoreIncrementBehaviour implements HighScoreIncrementBehaviour {

  @Override
  public int onCollisionHighScoreIncrement(final int timePenalty) {
    return 0;
  }

}
