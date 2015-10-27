package lasersharks.behaviour;

/**
 * Defines the interface to decrease an objects size.
 */
public interface SizeDecrementBehaviour {

  /**
   * Notify ~ has been hit by the laser.
   *
   * @param size size by which object needs to decrement.
   */
  void onCollisionSizeDecrement(int size);
}
