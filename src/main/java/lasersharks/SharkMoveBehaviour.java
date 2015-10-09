package lasersharks;

/**
 * represents the default move behaviour of a shark the player controls.
 * @author sytze
 *
 */
public class SharkMoveBehaviour implements MoveBehaviour {
  private static final float HALF_RATE = 0.5F;
  Swimmer swimmer;
  
  /**
   * the constructor.
   * @param swimmer
   */
  public SharkMoveBehaviour(Swimmer swimmer) {
    this.swimmer = swimmer;
  }
  
  /**
   * moves the shark in a direction.
   * @param frametime the refresh rate of the screen.
   * @return true if the shark was able to move.
   */
  public boolean move(double frametime) {
    swimmer.getPosition().updatePosition(swimmer.getDirection(), (swimmer.getSpeed() / frametime), swimmer.getSize());
    // this will make sure the fish stay within both

    swimmer.getPosition().clipPosition((swimmer.getSize() * swimmer.getWidthScale() * HALF_RATE),
        (swimmer.getSize() * HALF_RATE));
    return true;
  }
}
