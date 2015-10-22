package lasersharks.behaviour.highscoreincrement;

import lasersharks.behaviour.HighScoreIncrementBehaviour;
import lasersharks.interfaces.Displayable;

/**
 * Highscore Increment behaviour for SeaObjects.
 * @author SEMGroup27
 *
 */
public class FishHighScoreIncrementBehaviour implements HighScoreIncrementBehaviour {
  private Displayable element;
  
  /**
   * Constructor.
   * @param element object for wich to act.
   */
  public FishHighScoreIncrementBehaviour(Displayable element) {
    this.element = element;
  }
  
  @Override
  public int onCollisionHighScoreIncrement(int timePenalty) {
    return (int) Math.max(element.getSize() + element.getSpeed() - timePenalty, 0);
  }
}
