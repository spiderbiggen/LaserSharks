package lasersharks.interfaces;

/**
 * Interface for Collision Component.
 *
 * @author SEMGroup27
 */
public interface CollisionComponent {

  /**
   * Method vor handling collisions.
   *
   * @param other Displayable
   */
  void handleCollision(Displayable other);
}