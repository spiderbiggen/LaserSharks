package lasersharks;

/**
 * the interface of move behaviour objects.
 * @author sytze
 *
 */
public interface MoveBehaviour {
  /**
   * moves the fish.
   * @param frametime the refresh rate of the screen.
   * @return true if the fish was able to move.
   */
  public boolean move(double frametime);
}
