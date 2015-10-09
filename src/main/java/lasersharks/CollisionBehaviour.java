package lasersharks;

import javafx.scene.shape.Rectangle;

/**
 * This interface is implemented by object that have different behaviours of collision functions.
 * @author sytze
 *
 */
public interface CollisionBehaviour {

  /**
   * Returns the middle point.
   * @return the middle point of a swimmer.
   */
  public Position getMiddlePoint();
  
  /**
   * Makes a rectangle the shape of the fish.
   * @return returns a rectangle the shape of the fish.
   */
  public Rectangle makeHitbox();
  
  /**
   * Checks if a fish collides with an other fish.
   * @param swimmer the fish to check.
   * @return true if the fish collide.
   */
  public boolean collide(Swimmer swimmer);
}
