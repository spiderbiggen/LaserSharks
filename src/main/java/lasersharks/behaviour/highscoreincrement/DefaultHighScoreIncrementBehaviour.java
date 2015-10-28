package lasersharks.behaviour.highscoreincrement;

import lasersharks.behaviour.HighScoreIncrementBehaviour;

/**
 * High score Increment behaviour for SeaObjects.
 * @author SEMGroup27
 *
 */
public class DefaultHighScoreIncrementBehaviour implements HighScoreIncrementBehaviour {
  
  @Override
  public int onCollisionHighScoreIncrement(int timePenalty) {
    return 0;
  }
  
}
