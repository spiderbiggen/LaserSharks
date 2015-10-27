package lasersharks.behaviour;

import lasersharks.interfaces.Displayable;

/**
 * Interface for the collisionBehaviour classes.
 * 
 * @author SEMgroup27
 *
 */
public interface CollisionBehaviour {
  /**
   * Method that handles the possible collides for AbstractSeaObject with one another.
   * 
   * @param object
   *          The AbstractSeaObject that is collided with.
   */
  void collideWith(Displayable object);

}