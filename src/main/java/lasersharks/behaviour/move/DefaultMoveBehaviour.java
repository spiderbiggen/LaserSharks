package lasersharks.behaviour.move;

import lasersharks.behaviour.MoveBehaviour;
import lasersharks.interfaces.Displayable;

/**
 * represents the moving behaviour of a FishBot.
 * 
 * @author SEMGroup27
 *
 */
public class DefaultMoveBehaviour implements MoveBehaviour {

  private Displayable displayable;

  /**
   * the constructor.
   * 
   * @param displayable
   *          the fish this behaviour applies to.
   */
  public DefaultMoveBehaviour(Displayable displayable) {
    this.displayable = displayable;
  }

  /**
   * moves the FishBot in the right direction.
   * 
   * @param frametime
   *          the frametime of the screen.
   * @return true of the fish was able to move.
   */
  @Override
  public boolean move(double frametime) {
    return displayable.getPosition().updatePosition(displayable.getDirection(),
        displayable.getSpeed() / frametime, displayable.getSize());
  }

}
