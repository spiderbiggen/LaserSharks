package lasersharks;

/**
 * Interface for handeling direction callbacks.
 * @author Youri
 *
 */
public interface DirectionCallback {
  /**
   * Method by wich the direction is propagated.
   * @param dir current direction.
   */
  void putDirection(Direction dir);
}
