package lasersharks.interfaces;

import lasersharks.Direction;

/**
 * Interface for handeling direction callbacks.
 * 
 * @author SEMGroup27
 *
 */
public interface DirectionCallback {
  /**
   * Method by wich the direction is propagated.
   * 
   * @param dir
   *          current direction.
   */
  void putDirection(Direction dir);
}
