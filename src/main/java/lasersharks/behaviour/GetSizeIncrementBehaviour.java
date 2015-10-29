package lasersharks.behaviour;

/**
 * Defines interface to increment size.
 *
 * @author SEMGroup27
 */
public interface GetSizeIncrementBehaviour {

  /**
   * get size increment gained by colliding with object.
   *
   * @return size increment.
   */
  float onCollisionSizeIncrement();
}
