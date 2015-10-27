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
  private final Displayable displayable;
  
  /**
   * the constructor.
   * @param Displayable the displayable to which this behaviour applies
   */
  public LaserSharkMoveBehaviour(final Displayable Displayable) {
    this.displayable = Displayable;
  }
  
  /**
   * moves the shark in a direction.
   * @param frameTime the refresh rate of the screen.
   * @return true if the shark was able to move.
   */
  public boolean move(final double frameTime) {
    displayable.getPosition()
        .updatePosition(displayable.getDirection(), displayable.getSpeed() / frameTime,
            displayable.getSize());
    // this will make sure the fish stay within the borders.

    displayable.getPosition()
        .clipPosition(displayable.getSize() * displayable.getWidthScale() * HALF_RATE,
            displayable.getSize() * HALF_RATE);
    return true;
  }
}
