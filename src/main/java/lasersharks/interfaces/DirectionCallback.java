package lasersharks.interfaces;

import lasersharks.Direction;

/**
 * Interface for handling direction callbacks.
 *
 * @author SEMGroup27
 */
public interface DirectionCallback {

  /**
   * Method by which the direction is propagated.
   *
   * @param dir current direction.
   */
  void putDirection(Direction dir);
}
