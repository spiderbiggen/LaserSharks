package lasersharks;

import javafx.scene.shape.Rectangle;

/**
 * Interface for swimmer class.
 * 
 * @author Youri
 *
 */
@SuppressWarnings("restriction")
public interface Swimmer {

  /**
   * Returns the Position of this fish.
   * 
   * @return the current position
   */
  Position getPosition();

  /**
   * Sets the position of the fish.
   * 
   * @param position
   *          the position to set to.
   */
  void setPosition(Position position);

  /**
   * Returns the Size of this fish.
   * 
   * @return the current size
   */
  float getSize();

  /**
   * @return the speed.
   */
  double getSpeed();

  /**
   * @param speed
   *          the speed to set.
   */
  void setSpeed(int speed);

  /**
   * @return the direction.
   */
  Direction getDirection();

  /**
   * @param direction
   *          the direction to set.
   */
  void setDirection(Direction direction);

  /**
   * The current fish will move, this will return false if it moves out of the view.
   * 
   * @param frametime
   *          the time between frames in seconds
   * 
   * @return true if fish is in view
   */
  boolean move(double frametime);

  /**
   * We calculate the distance between the fishes. The sum of the size of both fishes is our hitbox.
   * Hitbox is now a circle, with size the radius in pixels.
   * 
   * @param fish
   *          we want to check if the fishbot collides with this fish,
   * @return true if the fishes collide and false if not.
   */
  boolean collision(Fish fish);

  /**
   * this function checks if the fish is on the screen or not.
   * 
   * @return true if the fish is on the screen, and false if not.
   */
  boolean isOnScreen();

  /**
   * Method for killing fish.
   */
  void kill();

  /**
   * Check if fish is alive.
   * 
   * @return aliveness of the fish.
   */
  boolean isAlive();

  /**
   * Will return the string resource for this fish.
   * 
   * @return The resource's name
   */
  String getImageResource();

  /**
   * return the aspect ratio between the width and the height. height/width
   * 
   * @return the aspect ratio
   */
  double getWidthScale();

  /**
   * Draw a rectangle shaped hitbox around the fishbot.
   * 
   * @return a rectangle hitbox.
   */
  Rectangle makeHitbox();
}
