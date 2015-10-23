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
   * Method that handles the possible collides for SeaObject with one another.
   * 
   * @param object
   *          The SeaObject that is collided with.
   */
  void colideWith(Displayable object);

}