package lasersharks.behaviour.move;

import lasersharks.behaviour.MoveBehaviour;
import lasersharks.interfaces.Displayable;

/**
 * represents the default move behaviour of a shark the player controls.
 * @author SEMGroup27
 *
 */
public class LaserSharkMoveBehaviour implements MoveBehaviour {
  private static final float HALF_RATE = 0.5F;
  private Displayable displayable;
  
  /**
   * the constructor.
   * @param displayable the fish this behaviour applies to.
   */
  public LaserSharkMoveBehaviour(Displayable displayable) {
    this.displayable = displayable;
  }
  
  /**
   * moves the shark in a direction.
   * @param frametime the refresh rate of the screen.
   * @return true if the shark was able to move.
   */
  public boolean move(double frametime) {
    displayable.getPosition().updatePosition(displayable.getDirection(), displayable.getSpeed() / frametime, displayable.getSize());
    // this will make sure the fish stay within the borders.

    displayable.getPosition().clipPosition((displayable.getSize() * displayable.getWidthScale() * HALF_RATE),
        (displayable.getSize() * HALF_RATE));
    return true;
  }
}
