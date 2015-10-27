package lasersharks.behaviour.move;

import lasersharks.behaviour.MoveBehaviour;
import lasersharks.interfaces.Displayable;

/**
 * Represents the moving behaviour of a FishBot.
 * 
 * @author SEMGroup27
 *
 */
public class DefaultMoveBehaviour implements MoveBehaviour {

  private Displayable displayable;

  /**
   * The constructor.
   * 
   * @param diplayable
   *          the fish this behaviour applies to.
   */

  public DefaultMoveBehaviour(Displayable diplayable) {
    this.displayable = diplayable;
  }

  /**
   * Moves the FishBot in the right direction.
   *
   * @param frameTime
   *          the frame time of the screen.
   * @return true of the fish was able to move.
   */
  @Override 
  public boolean move(double frameTime) {
    return displayable.getPosition().updatePosition(displayable.getDirection(),
        displayable.getSpeed() / frameTime, displayable.getSize());
  }

}
