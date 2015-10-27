package lasersharks.behaviour;

/**
 * the interface of move behaviour objects.
 * 
 * @author SEMGroup27
 *
 */
public interface MoveBehaviour {

  /**
   * Moves the fish.
   * 
   * @param frametime
   *          the refresh rate of the screen.
   * @return true if the fish was able to move.
   */
  boolean move(double frametime);
}
