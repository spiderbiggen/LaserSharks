package lasersharks.behaviour.move;

import lasersharks.behaviour.MoveBehaviour;
import lasersharks.interfaces.Displayable;

/**
 * Represents the default move behaviour of a shark the player controls.
 * @author SEMGroup27
 *
 */
public class LaserSharkMoveBehaviour implements MoveBehaviour {
  private static final float HALF_RATE = 0.5F;

  private Displayable diplayable;
  
  /**
   * the constructor.
   * @param diplayable the shark
   */
  public LaserSharkMoveBehaviour(Displayable diplayable) {
    this.diplayable = diplayable;
  }
  
  /**
   * Moves the shark in a direction.
   * @param frametime the refresh rate of the screen.
   * @return true if the shark was able to move.
   */
  public boolean move(double frametime) {
    diplayable.getPosition().updatePosition(
        diplayable.getDirection(), 
        (diplayable.getSpeed() / frametime), 
        diplayable.getSize()
    );
    // this will make sure the fish stay within the borders.

    diplayable.getPosition().clipPosition(
        (diplayable.getSize() * diplayable.getWidthScale() * HALF_RATE),
        (diplayable.getSize() * HALF_RATE)
    );
    return true;
  }
}
